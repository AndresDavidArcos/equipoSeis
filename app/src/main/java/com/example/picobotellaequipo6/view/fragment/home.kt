package com.example.picobotellaequipo6.view.fragment

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.animation.doOnEnd
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.picobotellaequipo6.R
import com.example.picobotellaequipo6.databinding.FragmentHomeBinding
import com.example.picobotellaequipo6.databinding.ShowChallengeBinding
import com.example.picobotellaequipo6.utils.Constants.APP_URL
import com.example.picobotellaequipo6.viewmodel.ChallengesViewModel
import kotlin.random.Random

class home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var spinningMediaPlayer: MediaPlayer
    private var currentRotation = 0f
    private var isAnimating = false
    private var soundIsOn = false
    private val challengesViewModel: ChallengesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        blinkingBtnEffect()
        rotateBottleListener()
        playMusic()
        toolBarListeners()
        challengesViewModel.getPokemons()
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
                        soundIsOn = false
                    }else{
                        item.setIcon(R.drawable.sound_on)
                        mediaPlayer.setVolume(1.0f, 1.0f)
                        soundIsOn = true

                    }
                }
                R.id.game -> {
                    findNavController().navigate(R.id.action_home2_to_instructions)
                }

                R.id.favorite -> {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(APP_URL)
                    startActivity(i)
                }

                R.id.share -> {
                    val text = "App pico botella.\nSolo los valientes lo juegan !!\n$APP_URL"
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    intent.putExtra(Intent.EXTRA_TEXT, text)
                    startActivity(Intent.createChooser(intent, "Compartir enlace"))
                }
                R.id.add -> {
                    findNavController().navigate(R.id.action_home2_to_challenges2)
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
                    override fun onAnimationStart(animation: Animator) {
                        mediaPlayer.setVolume(0.0f, 0.0f)
                        binding.botonGirarBotella.visibility = View.GONE;
                    }

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
            binding.botonGirarBotella.visibility = View.VISIBLE;
            showRandomChallenge()
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


    private fun showRandomChallenge() {
        val inflater = LayoutInflater.from(requireContext())
        val binding = ShowChallengeBinding.inflate(inflater)

        challengesViewModel.getListInvetory()
        challengesViewModel.listInventory.observe(viewLifecycleOwner) { lista ->
            if (lista.isEmpty()) {
                binding.texto.text = "No hay retos disponibles"
            }else{
                binding.texto.text = lista.shuffled()[0].name
            }
        }

        challengesViewModel.listPokemons.observe(viewLifecycleOwner){ lista ->
            val pokemon = lista.shuffled()[0]
            val url = pokemon.img.replace("http://", "https://")
            Glide.with(binding.root.context).load(url).into(binding.image)
        }

        val alertDialog = AlertDialog.Builder(requireContext()).create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)

        binding.btnAceptar.setOnClickListener {
            alertDialog.dismiss()
            if(soundIsOn){
                mediaPlayer.setVolume(1.0f, 1.0f)
            }else{
                mediaPlayer.setVolume(0.0f, 0.0f)
            }
        }
        alertDialog.show()
    }

}