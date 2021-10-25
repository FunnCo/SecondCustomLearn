package com.funnco.secondcustomlearn.view.CustomViewGroup

import android.content.Context
import android.icu.util.Measure
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import kotlin.math.max

class MessageContentGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        firstChild?.layout(
            paddingLeft,
            paddingTop,
            paddingLeft + (firstChild?.measuredWidth ?: 0),
            paddingTop + (firstChild?.measuredHeight ?: 0)
        )

        secondChild?.layout(
            p3 - p1 - paddingRight - (secondChild?.measuredWidth ?: 0),
            p4 - p2 - paddingBottom - (firstChild?.measuredHeight ?: 0),
            p3 - p1 - paddingRight,
            p4 - p2 - paddingBottom
        )
    }

    private val firstChild: View?
        get() = if(childCount > 0) getChildAt(0) else null
    private val secondChild: View?
        get() = if(childCount > 1) getChildAt(1) else null

    private fun checkChildCount(){
        if(childCount > 2) error("Can't contain more than 2 children")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        checkChildCount()

        firstChild?.let {measureChild(it, widthMeasureSpec)}
        secondChild?.let {measureChild(it, widthMeasureSpec)}

        val firstWidth = firstChild?.measuredWidth ?: 0
        val firstHeight = firstChild?.measuredHeight ?: 0
        val secondWidth  = secondChild?.measuredWidth ?: 0
        val secondHeight  = secondChild?.measuredHeight ?: 0

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(heightMeasureSpec) - paddingStart - paddingEnd

        val childrenOneSameLine = firstWidth + secondWidth < widthSize || widthMode == MeasureSpec.UNSPECIFIED

        val width = when(widthMode){
            MeasureSpec.UNSPECIFIED -> firstWidth + secondWidth
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> if(childrenOneSameLine){
                firstWidth + secondWidth
            } else {
                max(firstWidth, secondWidth)
            }
            else -> error("Sadness #2")
        }

        val height = if (childrenOneSameLine){
            max(firstHeight,secondHeight)
        } else{
            firstHeight+secondHeight
        }

        setMeasuredDimension(width + paddingStart + paddingEnd, height)
    }

    private fun measureChild(child: View, widthMeasureSpec: Int){
        val specSize = MeasureSpec.getSize(widthMeasureSpec) - paddingStart - paddingEnd

        val childWidthSpec = when (MeasureSpec.getMode(widthMeasureSpec)){
            MeasureSpec.UNSPECIFIED -> widthMeasureSpec
            MeasureSpec.AT_MOST -> widthMeasureSpec
            MeasureSpec.EXACTLY -> MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            else -> error("Sadness")
        }

        val childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)

        child.measure(childWidthSpec, childHeightSpec)
    }
}