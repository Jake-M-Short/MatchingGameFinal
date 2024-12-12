package com.example.matchinggame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.matchinggame.databinding.FragmentStatsBinding
import com.example.matchinggame.viewmodel.GameViewModel

class StatsFragment : Fragment() {

    private lateinit var binding: FragmentStatsBinding
    private val viewModel: GameViewModel by activityViewModels() // Ensure shared ViewModel is used

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatsBinding.inflate(inflater, container, false)

        viewModel.highScores.observe(viewLifecycleOwner) { highScores ->
            val scoresWithDefaults = viewModel.getHighScoresWithDefaults()
            binding.easyScore.text = scoresWithDefaults[6]?.toString() ?: "0"
            binding.mediumScore.text = scoresWithDefaults[10]?.toString() ?: "0"
            binding.hardScore.text = scoresWithDefaults[12]?.toString() ?: "0"
        }

        binding.backToMainMenuButton.setOnClickListener {
            findNavController().navigate(R.id.action_statsFragment_to_mainMenuFragment)
        }

        return binding.root
    }
}

