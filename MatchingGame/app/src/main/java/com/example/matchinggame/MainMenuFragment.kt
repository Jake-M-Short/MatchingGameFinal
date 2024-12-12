package com.example.matchinggame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.matchinggame.databinding.FragmentMainMenuBinding

class MainMenuFragment : Fragment() {

    private lateinit var binding: FragmentMainMenuBinding
    private var selectedDifficulty: Int = 6 // Default to Easy

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainMenuBinding.inflate(inflater, container, false)

        binding.difficultyRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioButton = binding.root.findViewById<RadioButton>(checkedId)
            selectedDifficulty = when (radioButton.id) {
                R.id.easyRadioButton -> 6
                R.id.mediumRadioButton -> 10
                R.id.hardRadioButton -> 12
                else -> 6
            }
        }

        binding.startGameButton.setOnClickListener {
            val action = MainMenuFragmentDirections.actionMainMenuFragmentToGameFragment(selectedDifficulty)
            findNavController().navigate(action)
        }

        binding.statisticsButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_statsFragment)
        }


        return binding.root
    }
}
