package com.app.checkcreditscore.view.fragment

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.checkcreditscore.util.*
import com.app.checkcreditscore.view.model.UIModel
import com.app.checkcreditscore.view.viewmodel.ViewModelMain
import com.app.view.checkcreditscore.databinding.BindViewBinding
import dagger.hilt.android.AndroidEntryPoint

private const val SHOW_SCORE_DELAY_MS = 3000L

@AndroidEntryPoint
class FragmentView : Fragment() {
    private val viewModel: ViewModelMain by viewModels()

    private lateinit var resourceResolver: Resolver
    private lateinit var textSpanFactory: TextSpan

    private var displayCircle: DisplayCircle? = null

    private var _binding: BindViewBinding? = null
    private val binding get() = _binding!!

    // create view using inflater
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return BindViewBinding.inflate(inflater, container, false).also { binding ->
            _binding = binding
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setStateEvent(ViewModelMain.StateEvent.GetOverview)
        initView()
        observeViewModel()
        getDataIfNeed()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        resourceResolver = ResolverImpl(context)
        textSpanFactory = TextSpanImpl(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        displayCircle?.cancel()
        displayCircle = null
    }

    // initialize
    private fun initView() {
        binding.errorRetry.onErrorRetryClick = {
            viewModel.setStateEvent(ViewModelMain.StateEvent.GetOverview)
        }
    }

    // as per response if it is success and error it will set visibility
    private fun observeViewModel() {
        viewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    setViewsVisibility(scoreGroupVisibility = View.VISIBLE)
                    renderScore(dataState.data)
                }
                is DataState.Error -> {
                    setViewsVisibility(errorRetryVisibility = View.VISIBLE)
                }
                DataState.Loading -> {
                    setViewsVisibility(progressBarVisibility = View.VISIBLE)
                }
            }.exhaustive
        })
    }

    // set view visibility as per data
    private fun setViewsVisibility(
        scoreGroupVisibility: Int = View.GONE,
        progressBarVisibility: Int = View.GONE,
        errorRetryVisibility: Int = View.GONE
    ) {
        binding.scoreGroup.visibility = scoreGroupVisibility
        binding.progressBar.visibility = progressBarVisibility
        binding.errorRetry.visibility = errorRetryVisibility
    }

    // get data from endpoint if it is require
    private fun getDataIfNeed() {
        if (viewModel.dataState.value == null) {
            viewModel.setStateEvent(ViewModelMain.StateEvent.GetOverview)
        }
    }

    // it show circle bby using countdown timer class
    private inner class DisplayCircle(
        private val model: UIModel,
        private val duration: Long
    ) : CountDownTimer(duration, 10) {

        override fun onTick(millisUntilFinished: Long) {
            val elapsedTime = (duration - millisUntilFinished).toFloat()
            val progress = (elapsedTime / duration).coerceAtMost(model.progressCirclePercentage)
            val alpha = elapsedTime / duration

            binding.scoreCircleProgress.setProgress(progress)
            binding.scoreSummary.alpha = alpha
        }

        override fun onFinish() {
            binding.scoreCircleProgress.setProgress(model.progressCirclePercentage)
            binding.scoreSummary.alpha = 1f
        }
    }

    // display score text using this function
    private fun renderScore(model: UIModel) {
        binding.scoreSummary.text = model.getScoreSummary(
            textSpanFactory = textSpanFactory,
            resourceResolver = resourceResolver
        )
        binding.scoreSummary.alpha = 0f

        resourceResolver.getColor(model.progressCircleColorRes)?.let { color ->
            binding.scoreCircleProgress.setProgressColor(color)
        }

        displayCircle = DisplayCircle(model, SHOW_SCORE_DELAY_MS).also {
            it.start()
        }
    }

    companion object {
        fun newInstance() = FragmentView()
    }
}