package com.example.picobotellaequipo6.view

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var currentRotation = 0f
    private var isAnimating = false
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        blinkingBtnEffect()
        rotationBottleEffect()
        rotateBottle()
    }

    fun getRandomDirection(): Int {
        return Random.nextInt(361)
    }

    private fun rotateBottle() {
        binding.botonGirarBotella.setOnClickListener {
            rotationBottleEffect()
        }
    }

    private fun rotationBottleEffect() {
        if (!isAnimating) {
            currentRotation = currentRotation + getRandomDirection()
            isAnimating = true
            binding.botella.animate()
                .rotation(currentRotation)
                .setDuration(3000)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {}

                    override fun onAnimationEnd(animation: Animator) {
                        isAnimating = false
                    }

                    override fun onAnimationCancel(animation: Animator) {}

                    override fun onAnimationRepeat(animation: Animator) {}
                })
                .start()
        }
    }

    private fun blinkingBtnEffect() {
        val orangeColor = Color.parseColor("#FFA500")
        val anim = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, orangeColor, Color.TRANSPARENT)

        anim.duration = 1000
        anim.repeatMode = ValueAnimator.REVERSE
        anim.repeatCount = Animation.INFINITE

        anim.addUpdateListener { animation ->
            val color = animation.animatedValue as Int
            binding.botonGirarBotella.backgroundTintList = ColorStateList.valueOf(color)
        }

        anim.start()
    }

}