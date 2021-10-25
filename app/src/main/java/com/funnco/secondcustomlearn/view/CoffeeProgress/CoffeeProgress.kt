package com.funnco.secondcustomlearn.view.CoffeeProgress

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.funnco.secondcustomlearn.R

class CoffeeProgress @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var paint = Paint()

    private var currentStep = 0
    private var maxSteps = 1000

    private var destinationStep = 500

    private var animator = ValueAnimator.ofInt(currentStep,destinationStep).apply {
        duration = 1000L
        addUpdateListener { animator ->
            val animatedValue = animator.animatedValue as Int
            setCurrentStep(animatedValue, true)
        }
    }

    private var coffeePicture = BitmapFactory.decodeResource(resources, R.drawable.cup)

    init {
        if(attrs != null){
            var styledAttrs = context.obtainStyledAttributes(
                attrs,
                R.styleable.CoffeeProgress,
                defStyleAttr,
                0
            )

            paint.color = styledAttrs.getColor(R.styleable.CoffeeProgress_progressColor, Color.RED)
            maxSteps = styledAttrs.getInt(R.styleable.CoffeeProgress_stepsCount, 1000)

            styledAttrs.recycle()
        }

        paint.style = Paint.Style.FILL

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var tempBitmap = BitmapFactory.decodeResource(resources, R.drawable.cup)
        coffeePicture = Bitmap.createScaledBitmap(tempBitmap, width,height,false)
        canvas?.drawBitmap(coffeePicture, 0f, 0f, paint)

        if (currentStep == 0){
            return
        }

        var offset = 150
        var currentTop = height- (height.toFloat()/maxSteps*currentStep).toInt() + offset

        canvas?.drawRect(
            Rect(
                0, currentTop, width, height
            ),
            paint
        )

        canvas?.drawOval(
            RectF(
                0f,height- (height.toFloat()/maxSteps*currentStep) - 50 +offset,width.toFloat(),currentTop + 300f + offset),
            paint
        )

        canvas?.drawBitmap(coffeePicture, 0f, 0f, paint)
    }

    fun setCurrentStep(current : Int){
        destinationStep = current*100
        animator.setIntValues(currentStep, destinationStep)
        animator.start()
    }

    private fun setCurrentStep(current: Int, animated: Boolean){
        currentStep = current
        invalidate()
    }

    fun setMaxSteps (value: Int){
        maxSteps = value*100
    }
}