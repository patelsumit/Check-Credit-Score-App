package com.app.checkcreditscore.domain.usecase

import kotlinx.coroutines.runBlocking
import org.junit.Test

class BaseTest {

    @Test
    fun doTask() = runBlocking {
        var isWork = false

        object : Base<Unit>() {
            override suspend fun doWork() {
                isWork = true
            }
        }.execute()

        if (!isWork) {
            error("Method doWork() was not called")
        }
    }
}