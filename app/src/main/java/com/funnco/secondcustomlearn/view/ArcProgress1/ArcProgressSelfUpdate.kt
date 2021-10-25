package com.funnco.secondcustomlearn.view.ArcProgress1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.funnco.secondcustomlearn.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ArcProgressSelfUpdate @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr){

    private companion object{
        private const val FILL = 0
    }

    private var color = 0
    private var style = FILL
    private var paint = Paint()

    private var sectorsCount = 4
    private var updateTime = 10L
    private var currentSector = 0

    init {
        if(attrs!=null){
            val styledAttrs = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ArcProgress,
                defStyleAttr,
                0
            )

            color = styledAttrs.getColor(R.styleable.ArcProgress_customColor, Color.RED)
            style = styledAttrs.getInt(R.styleable.ArcProgress_customStyle, FILL)
            styledAttrs.recycle()
        }

        paint.color = color
        if(style == FILL){
            paint.style = Paint.Style.FILL
        } else {
            paint.style = Paint.Style.STROKE
        }
        paint.strokeWidth=5f
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if(updateTime == 0L || sectorsCount == 0){
            return
        }

        var toAngle = 360f/sectorsCount*currentSector
        if(toAngle > 360){
            toAngle = 0f
        }

        canvas?.drawArc(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            -90f,
            toAngle,
            true,
            paint
        )
    }

    fun setUpdateTime(time: Long){
        updateTime = time
    }

    fun setSectorsCount(sectors: Int){
        sectorsCount = sectors
    }

    fun runUpdateLoop(){
        GlobalScope.launch {
            while (true) {
                currentSector++
                invalidate()
                if(currentSector > sectorsCount){
                    currentSector = 1
                }
                delay(updateTime)
            }
        }
    }
}