package com.example.matchinggame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.matchinggame.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {

    private lateinit var binding: FragmentGameOverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameOverBinding.inflate(inflater, container, false)

        val totalMatches = arguments?.getInt("matches") ?: 0
        binding.gameOverTextView.text = "Game Over! Total Matches: $totalMatches"

        binding.backToMainMenuButton.setOnClickListener {
            findNavController().navigate(R.id.action_gameOverFragment_to_mainMenuFragment)
        }

        return binding.root
    }
}
