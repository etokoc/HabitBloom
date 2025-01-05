package com.ekdev.habitapp.presentation.ui.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatButton
import com.ekdev.habitapp.databinding.CustomStepBarBinding

class StepProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {

    private val binding: CustomStepBarBinding?
    private var stepClickListener: OnStepClickListener? = null

    init {
        val inflater = LayoutInflater.from(context)
        binding = CustomStepBarBinding.inflate(inflater, this, true)
        stepListeners()
    }

    /*
    * Set listener for step buttons
     */
    fun setOnStepClickListener(listener: OnStepClickListener) {
        this.stepClickListener = listener
    }

    private fun stepListeners() {
        binding?.btn21Days?.setOnClickListener {
            updateCheckedState(binding.btn21Days)
            updateProgress(1)
            stepClickListener?.onStepClicked(StepEnum.STEP_21_DAYS)
        }
        binding?.btn1Month?.setOnClickListener {
            updateCheckedState(binding.btn1Month)
            updateProgress(2)
            stepClickListener?.onStepClicked(StepEnum.STEP_1_MONTH)
        }
        binding?.btn3Month?.setOnClickListener {
            updateCheckedState(binding.btn3Month)
            updateProgress(3)
            stepClickListener?.onStepClicked(StepEnum.STEP_3_MONTH)
        }
    }

    private fun updateCheckedState(selectedButton: AppCompatButton) {
        binding?.btn21Days?.isSelected = false
        binding?.btn1Month?.isSelected = false
        binding?.btn3Month?.isSelected = false
        selectedButton.isSelected = true
    }


    private fun updateProgress(step: Int) {
        val progress = (step / 3f) * 100
        binding?.stepProgressBar?.progress = progress.toInt()
    }

    interface OnStepClickListener {
        fun onStepClicked(step: StepEnum)
    }

    /*
    * Enum for step buttons
     */
    enum class StepEnum {
        STEP_21_DAYS,
        STEP_1_MONTH,
        STEP_3_MONTH
    }

}