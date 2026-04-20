package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Full immersive — hide status & nav bars
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        setContentView(R.layout.activity_splash_screen)

        val tvLogo    = findViewById<TextView>(R.id.tvLogo)
        val tvTagline = findViewById<TextView>(R.id.tvTagline)
        val divider   = findViewById<View>(R.id.divider)
        val dot1      = findViewById<View>(R.id.dot1)
        val dot2      = findViewById<View>(R.id.dot2)
        val dot3      = findViewById<View>(R.id.dot3)

        // --- Logo: fade + overshoot zoom ---
        tvLogo.alpha = 0f
        tvLogo.scaleX = 0.7f
        tvLogo.scaleY = 0.7f
        tvLogo.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(700)
            .setStartDelay(150)
            .setInterpolator(OvershootInterpolator(1.2f))
            .start()

        // --- Divider: scale in from center ---
        divider.scaleX = 0f
        divider.alpha = 0f
        divider.animate()
            .scaleX(1f)
            .alpha(1f)
            .setDuration(450)
            .setStartDelay(750)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()

        // --- Tagline: slide up + fade in ---
        tvTagline.alpha = 0f
        tvTagline.translationY = 28f
        tvTagline.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(550)
            .setStartDelay(900)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()

        // --- Loading dots: staggered pulse (visible after 1.3s) ---
        val dots = listOf(dot1, dot2, dot3)
        dots.forEachIndexed { i, dot ->
            dot.alpha = 0f
            dot.animate()
                .alpha(1f)
                .setDuration(300)
                .setStartDelay(1300 + i * 150L)
                .withEndAction { pulseDot(dot, i * 150L) }
                .start()
        }

        // --- Navigate to MainActivity after 3.2s ---
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 3200)
    }

    private fun pulseDot(dot: View, offset: Long) {
        dot.animate()
            .scaleX(1.5f).scaleY(1.5f).alpha(0.4f)
            .setDuration(500)
            .setStartDelay(offset)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .withEndAction {
                dot.animate()
                    .scaleX(1f).scaleY(1f).alpha(1f)
                    .setDuration(500)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .withEndAction { pulseDot(dot, 0) }
                    .start()
            }
            .start()
    }
}