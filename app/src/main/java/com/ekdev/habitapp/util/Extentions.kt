package com.ekdev.habitapp.util

import android.graphics.LinearGradient
import android.graphics.Shader
import android.widget.TextView

fun TextView.setGradientColor(colors: IntArray) {
    val width = paint.measureText(this.text.toString())
    val shader = LinearGradient(0f, 0f, width, 0f, colors, null, Shader.TileMode.CLAMP)
    paint.shader = shader
}