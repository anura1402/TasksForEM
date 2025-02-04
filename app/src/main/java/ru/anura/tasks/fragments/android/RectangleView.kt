package ru.anura.tasks.fragments.android

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import ru.anura.tasks.R
import kotlin.random.Random

class RectangleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var progress = 0
    private var color: Int = getRandomColor()
    private val paint = Paint().apply { isAntiAlias = true }

    init {
        setBackgroundResource(R.drawable.border)
        setOnClickListener {
            progress += 10
            if (progress > 100) progress = 0
            color = getRandomColor()
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val widthProgress = width * (progress / 100f)
        paint.color = color
        canvas.drawRect(0f, 0f, widthProgress, height.toFloat(), paint)
    }

    private fun getRandomColor(): Int {
        return Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    }
}