package com.example.picobotellaequipo6.view

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var spinningMediaPlayer: MediaPlayer
    private var currentRotation = 0f
    private var isAnimating = false
    private var soundIsOn = false;

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        blinkingBtnEffect()
        rotateBottleListener()
        playMusic()
        toolBarListeners()
    }

    private fun toolBarListeners() {
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                    R.id.sound -> {

                    if(soundIsOn){
                        Log.i("sound", "entra")
                        item.setIcon(R.drawable.sound_off)
                        mediaPlayer.setVolume(0.0f, 0.0f)
                        soundIsOn = false;
                    }else{
                        Log.i("sound", "sale")

                        item.setIcon(R.drawable.sound_on)
                        mediaPlayer.setVolume(1.0f, 1.0f)
                        soundIsOn = true;

                    }
                }
            }

            true
        }
    }


    private fun playSpinningSound() {
        spinningMediaPlayer = MediaPlayer.create(this, R.raw.spinningbottle)
        spinningMediaPlayer.start()    }

    private fun playMusic() {
        mediaPlayer = MediaPlayer.create(this, R.raw.mainviewmusic)

        mediaPlayer.isLooping = true

        mediaPlayer.start()
        soundIsOn = true
    }

    fun getRandomDirection(): Int {
        return Random.nextInt(361)
    }

    private fun rotateBottleListener() {
        binding.botonGirarBotella.setOnClickListener {
            playSpinningSound()
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
                        startCountdown()
                    }

                    override fun onAnimationCancel(animation: Animator) {}

                    override fun onAnimationRepeat(animation: Animator) {}
                })
                .start()
        }
    }

    private fun startCountdown() {
        val countdownAnimator = ValueAnimator.ofInt(3, -1)
        countdownAnimator.duration = 4000
        countdownAnimator.interpolator = LinearInterpolator()
        countdownAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            binding.countdown.text = value.toString()
        }
        countdownAnimator.doOnEnd {
            binding.countdown.text = ""
            showRandomChallenge()
        }
        countdownAnimator.start()
    }

    private fun showRandomChallenge() {
        TODO("Not yet implemented")
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