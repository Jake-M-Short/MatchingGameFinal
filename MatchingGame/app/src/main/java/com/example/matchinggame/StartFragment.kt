package com.example.matchinggame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.matchinggame.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        // Load the animation and apply it to the TextView
        val wavingAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.waving_text)
        binding.welcomeTextView.startAnimation(wavingAnimation)

        // Set the GIF for the background
        Glide.with(this)
            .asGif()
            .load(R.drawable.snow_gif)
            .into(binding.gifBackground)
        //set continue button to go to main menu
        binding.continueButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_mainMenuFragment)
        }

        return binding.root
    }
}
