package com.app.checkcreditscore.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.checkcreditscore.domain.ScoreStatus
import com.app.checkcreditscore.domain.model.EntityMain
import com.app.checkcreditscore.domain.model.response.EntityMainResponse
import com.app.checkcreditscore.domain.usecase.UseCase
import com.app.checkcreditscore.domain.usecase.UseCaseFactory
import com.app.checkcreditscore.util.DataState
import com.app.checkcreditscore.view.model.UIModel
import com.app.checkcreditscore.view.model.transform.transform
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ViewModelTest {
    @get:Rule
    val taskRule: TestRule = InstantTaskExecutorRule()

    @Test
    fun unchangedDataSet() {
        val caseFactory = CaseFactory {
            createEntityResponse()
        }
        val viewModel = ViewModelMain(caseFactory)
        val expectedState = viewModel.dataState.value

        viewModel.setStateEvent(ViewModelMain.StateEvent.None)
        Assert.assertEquals(expectedState, viewModel.dataState.value)
    }

    @Test
    fun loadDataSet() {
        val caseFactory = CaseFactory {
            createEntityResponse()
        }
        val viewModel = ViewModelMain(caseFactory)
        val expectedState = DataState.Loading

        val sets = mutableListOf<DataState<UIModel>>()
        viewModel.dataState.observeForever { dataState ->
            sets.add(dataState)
        }
        viewModel.setStateEvent(ViewModelMain.StateEvent.GetOverview)

        val actualDataState = sets[0]
        Assert.assertEquals(expectedState, actualDataState)
    }

    @Test
    fun errorResponseDataSet() {
        val getResponse = createEntityResponse(isOkay = false)
        val caseFactory = CaseFactory {
            getResponse
        }
        val viewModel = ViewModelMain(caseFactory)

        val sets = mutableListOf<DataState<UIModel>>()
        viewModel.dataState.observeForever { dataState ->
            sets.add(dataState)
        }
        viewModel.setStateEvent(ViewModelMain.StateEvent.GetOverview)

        val actualDataState = sets[1]
        Assert.assertTrue(actualDataState is DataState.Error)
    }

    @Test
    fun errorExceptionDataSet() {
        val caseFactory = CaseFactory {
            error(String())
        }
        val viewModel = ViewModelMain(caseFactory)

        val sets = mutableListOf<DataState<UIModel>>()
        viewModel.dataState.observeForever { dataState ->
            sets.add(dataState)
        }
        viewModel.setStateEvent(ViewModelMain.StateEvent.GetOverview)

        val actualDataState = sets[1]
        Assert.assertTrue(actualDataState is DataState.Error)
    }

    @Test
    fun successResponseDataSet() {
        val expectedResponse = createEntityResponse()
        val caseFactory = CaseFactory {
            expectedResponse
        }
        val viewModel = ViewModelMain(caseFactory)
        val expectedState = DataState.Success(expectedResponse.entityMain.transform())

        val sets = mutableListOf<DataState<UIModel>>()
        viewModel.dataState.observeForever { dataState ->
            sets.add(dataState)
        }
        viewModel.setStateEvent(ViewModelMain.StateEvent.GetOverview)

        val actualDataState = sets[1]
        Assert.assertEquals(expectedState, actualDataState)
    }

    private fun createEntityResponse(
        isOkay: Boolean = true,
        message: String = String()
    ): EntityMainResponse {
        return EntityMainResponse(
            EntityMain(
                score = 0,
                minScore = 0,
                maxScore = 0,
                scoreType = ScoreStatus.NONE
            )
        ).apply {
            this.isOkay = isOkay
            this.message = message
        }
    }
}

class CaseFactory<out T>(
    private val useCaseExecuteBlock: suspend () -> T
) : UseCaseFactory<T> {

    override fun create(): UseCase<T> = object : UseCase<T> {

        override suspend fun execute(): T = useCaseExecuteBlock()
    }
}