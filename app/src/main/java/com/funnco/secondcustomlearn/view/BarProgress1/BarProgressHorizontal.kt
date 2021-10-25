package com.funnco.secondcustomlearn.view.BarProgress1

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.funnco.secondcustomlearn.R
import com.google.android.material.color.MaterialColors

class BarProgressHorizontal @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private companion object{
        const val FILL = 0
    }

    private var paint = Paint()
    private var secondColor = Color.TRANSPARENT
    private var firstColor = Color.BLACK
    private var style = FILL

    init {
        if(attrs != null){
            var styledAttrs = context.obtainStyledAttributes(
                attrs,
                R.styleable.BarProgressHorizontal,
                defStyleAttr,
                0
            )

            style = styledAttrs.getInt(R.styleable.BarProgressHorizontal_customRectStyle, FILL)
            firstColor = styledAttrs.getColor(R.styleable.BarProgressHorizontal_customRectColor, Color.BLACK)
            secondColor = styledAttrs.getColor(R.styleable.BarProgressHorizontal_customBackgroundColor, Color.TRANSPARENT)
            styledAttrs.recycle()
        }

        paint.color = firstColor
        paint.style = if(style == FILL) Paint.Style.FILL else Paint.Style.STROKE

        paint.strokeWidth = 10f
    }

    private var currentValue = 50
    private var maxValue = 100

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = secondColor
        canvas?.drawRect(Rect(0,0,width,height), paint)
        paint.color = firstColor



        canvas?.drawRect(Rect(0,0,width/maxValue*currentValue, height), paint)
    }

    fun setValue(value: Int){
        currentValue = value
        invalidate()
    }
}