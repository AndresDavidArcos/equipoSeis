package com.example.picobotellaequipo6.view.fragment

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.FragmentHomeBinding
import kotlin.random.Random

class home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var spinningMediaPlayer: MediaPlayer
    private var currentRotation = 0f
    private var isAnimating = false
    private var soundIsOn = false;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blinkingBtnEffect()
        rotateBottleListener()
        playMusic()
        toolBarListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    private fun toolBarListeners() {
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.sound -> {

                    if(soundIsOn){
                        item.setIcon(R.drawable.sound_off)
                        mediaPlayer.setVolume(0.0f, 0.0f)
                        soundIsOn = false;
                    }else{
                        item.setIcon(R.drawable.sound_on)
                        mediaPlayer.setVolume(1.0f, 1.0f)
                        soundIsOn = true;

                    }
                }
                R.id.add -> {
                    Log.i("nav", "entro")
                    findNavController().navigate(R.id.action_home2_to_addChallenge)
                }
            }

            true
        }
    }


    private fun playSpinningSound() {
        spinningMediaPlayer = MediaPlayer.create(requireView().context, R.raw.spinningbottle)
        spinningMediaPlayer.start()    }

    private fun playMusic() {
        mediaPlayer = MediaPlayer.create(requireView().context, R.raw.mainviewmusic)

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
            /*
                        showRandomChallenge()
            */
        }
        countdownAnimator.start()
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