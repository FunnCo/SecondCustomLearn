package com.funnco.secondcustomlearn.view.InteractiveView1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class MovableView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()

    init{
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
    }

    private val listener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            Toast.makeText(context, "double tapped", Toast.LENGTH_SHORT).show()
            return super.onDoubleTap(e)
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {

            if(velocityX > 0) {
                Toast.makeText(context, "Swiped right", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Swiped left", Toast.LENGTH_SHORT).show()
            }
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }
    private val detector: GestureDetector = GestureDetector(context, listener)

    private var xPos = 0f
    private var yPos = 0f
    private val width = 250f
    private val height = 250f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawOval(RectF(xPos-width/2,yPos-height/2,xPos+width/2,yPos+height/2),paint)
    }

    private fun correctAreaTouched(event: MotionEvent?): Boolean {
        if (event != null) {
            return (event.x >= xPos - width / 2 && event.x <=  xPos + width / 2) && (event.y >= yPos - height / 2 && event.y <=  yPos + height / 2)
        }
        return false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return super.onTouchEvent(event)
        if(event != null && correctAreaTouched(event)){
            xPos = event.x
            yPos = event.y
            invalidate()
        }

        return detector.onTouchEvent(event)
    }
}