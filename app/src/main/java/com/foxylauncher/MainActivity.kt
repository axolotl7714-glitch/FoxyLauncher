
package com.foxylauncher

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.LinearGradient
import android.graphics.Shader

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setBackgroundColor(Color.rgb(24, 25, 31))
            setPadding(24, 24, 24, 24)
        }

        val title = TextView(this).apply {
            text = "FOXY LAUNCHER"
            textSize = 27f
            setTextColor(Color.rgb(255, 160, 55))
            gravity = Gravity.CENTER
            letterSpacing = 0.12f
        }

        root.addView(title)

        val subtitle = TextView(this).apply {
            text = "Dein Minecraft-Abenteuer beginnt hier"
            textSize = 14f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        root.addView(subtitle)

        root.addView(
            CaveView(this),
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
            )
        )

        val playButton = Button(this).apply {
            text = "SPIELEN"
            textSize = 18f
            setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "Minecraft-Installation kommt als Nächstes!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        root.addView(
            playButton,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                60
            )
        )

        val settingsButton = Button(this).apply {
            text = "EINSTELLUNGEN"
            setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "Einstellungen folgen später.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        root.addView(settingsButton)

        setContentView(root)
    }
}

class CaveView(context: android.content.Context) : View(context) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()

        paint.shader = LinearGradient(
            0f, 0f, 0f, h,
            Color.rgb(45, 37, 39),
            Color.rgb(15, 18, 24),
            Shader.TileMode.CLAMP
        )
        canvas.drawRect(0f, 0f, w, h, paint)
        paint.shader = null

        // Cave entrance
        paint.color = Color.rgb(76, 65, 61)
        canvas.drawOval(
            w * 0.12f, h * 0.12f,
            w * 0.88f, h * 0.92f,
            paint
        )

        paint.color = Color.rgb(18, 20, 25)
        canvas.drawOval(
            w * 0.23f, h * 0.25f,
            w * 0.77f, h * 0.91f,
            paint
        )

        // Cave floor
        paint.color = Color.rgb(75, 67, 55)
        canvas.drawOval(
            w * 0.12f, h * 0.78f,
            w * 0.88f, h * 0.96f,
            paint
        )

        // Fox body
        paint.color = Color.rgb(232, 112, 30)
        canvas.drawOval(
            w * 0.36f, h * 0.53f,
            w * 0.67f, h * 0.84f,
            paint
        )

        // Fox tail
        canvas.drawOval(
            w * 0.56f, h * 0.69f,
            w * 0.81f, h * 0.86f,
            paint
        )

        paint.color = Color.WHITE
        canvas.drawCircle(w * 0.78f, h * 0.78f, w * 0.045f, paint)

        // Fox head
        paint.color = Color.rgb(245, 135, 42)
        canvas.drawCircle(w * 0.49f, h * 0.48f, w * 0.13f, paint)

        // Ears
        val ears = Path().apply {
            moveTo(w * 0.39f, h * 0.42f)
            lineTo(w * 0.38f, h * 0.27f)
            lineTo(w * 0.47f, h * 0.38f)
            close()
            moveTo(w * 0.52f, h * 0.38f)
            lineTo(w * 0.62f, h * 0.27f)
            lineTo(w * 0.60f, h * 0.43f)
            close()
        }
        canvas.drawPath(ears, paint)

        // Face
        paint.color = Color.WHITE
        canvas.drawCircle(w * 0.45f, h * 0.49f, w * 0.018f, paint)
        canvas.drawCircle(w * 0.54f, h * 0.49f, w * 0.018f, paint)

        paint.color = Color.rgb(40, 30, 25)
        canvas.drawCircle(w * 0.45f, h * 0.49f, w * 0.008f, paint)
        canvas.drawCircle(w * 0.54f, h * 0.49f, w * 0.008f, paint)

        // Nose
        canvas.drawCircle(w * 0.495f, h * 0.54f, w * 0.014f, paint)

        // Cave light
        paint.color = Color.rgb(255, 190, 90)
        canvas.drawCircle(w * 0.20f, h * 0.38f, w * 0.025f, paint)
    }
}
