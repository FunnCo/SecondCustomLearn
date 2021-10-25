package com.funnco.secondcustomlearn.view.InteractiveView2

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import com.google.android.material.card.MaterialCardView

class MovableCard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : MaterialCardView(context, attrs) {

    private lateinit var listOfItems : List<String>

    private fun setListOfItems(items : List<String>){
        listOfItems = items
    }

    private var currentIndex = 0
    private fun nextItem(){
        currentIndex++
        invalidate()
    }

    private val listener = object:  GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            return true;
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }

    val detector = GestureDetector(context, listener)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


    }

}