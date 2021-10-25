package com.funnco.secondcustomlearn.view.ArcProgress2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.funnco.secondcustomlearn.R

class ArcProgress @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private companion object{
        const val FILL = 0
    }

    private var paint = Paint()
    private var style = FILL

    init {
        if(attrs != null){
            var styledAttrs = context.obtainStyledAttributes(
                attrs,
                R.styleable.ArcProgress,
                defStyleAttr,
                0
            )
            paint.color = styledAttrs.getColor(R.styleable.ArcProgress_customColor, Color.RED)
            style = styledAttrs.getInt(R.styleable.ArcProgress_customStyle, FILL)

            styledAttrs.recycle()
        }

        if(style == FILL){
            paint.style = Paint.Style.FILL
        } else {
            paint.style = Paint.Style.STROKE
        }
        paint.strokeWidth = 10f
    }

    private var currentValue = 0
    private var maxValue = 100

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var toAngle = 360f/maxValue*currentValue
        if(toAngle > 360){
            toAngle %= 360
        }

        canvas?.drawArc(
            5f,
            5f,
            width.toFloat()-10,
            height.toFloat()-10,
            -90f,
            toAngle,
            true,
            paint
        )
    }


    fun setValue(value: Int){
        currentValue = value
        invalidate()
    }

    fun setMaxValue(value: Int){
        maxValue = value
    }


}