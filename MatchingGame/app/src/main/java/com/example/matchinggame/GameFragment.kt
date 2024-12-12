package com.example.matchinggame

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.matchinggame.data.Card
import com.example.matchinggame.data.GameManager
import com.example.matchinggame.databinding.FragmentGameBinding
import com.example.matchinggame.viewmodel.GameBoardAdapter
import com.example.matchinggame.viewmodel.GameViewModel

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val viewModel: GameViewModel by activityViewModels()
    private var firstCard: Card? = null
    private var secondCard: Card? = null
    private var totalMatches = 0 // Keep track of the overall total matches
    private var currentSetMatches = 0 // Track matches in the current set

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater, container, false)

        val difficulty = arguments?.getInt("difficulty") ?: 6
        setupGameBoard()
        setupTimer()
        viewModel.startGame(difficulty) // Initialize game through ViewModel
        revealAllCardsTemporarily() // Show cards at the start
        return binding.root
    }
    //sets up game based on user choices in view model
    private fun setupGameBoard() {
        val adapter = GameBoardAdapter { card ->
            handleCardSelection(card)
        }
        binding.gameBoardRecyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.gameBoardRecyclerView.adapter = adapter

        viewModel.cards.observe(viewLifecycleOwner) { cards ->
            adapter.submitList(cards)
            binding.gameBoardRecyclerView.adapter?.notifyDataSetChanged() // Update RecyclerView
        }
    }
    //sets up timer for user when game starts
    private fun setupTimer() {
        viewModel.timer.observe(viewLifecycleOwner) { time ->
            binding.timerTextView.text = "Time Left: $time s"

            if (time <= 0) {
                showGameOver()
            }
        }
    }

    private fun handleCardSelection(card: Card) {
        if (card.isFlipped || firstCard == card || secondCard != null) return

        card.isFlipped = true
        binding.gameBoardRecyclerView.adapter?.notifyDataSetChanged()

        if (firstCard == null) {
            firstCard = card
        } else {
            secondCard = card
            checkForMatch()
        }
    }
    // handles logic for card selection, if two are matching stay upright else flip over
    private fun checkForMatch() {
        // Check if the two cards match
        if (firstCard?.imageResId == secondCard?.imageResId) {
            totalMatches++  // Increment total matches
            currentSetMatches++  // Increment matches for the current set
            viewModel.addTime(5)

            // Check if all cards in the set are matched
            if (currentSetMatches == viewModel.cards.value?.size?.div(2)) {
                // If all cards in the set are matched, update the high score
                viewModel.updateHighScore(viewModel.currentDifficulty, totalMatches)
                startNewSet()  // Generate a new set of cards and display them once all matches are found
            }
            // Reset the selected cards
            firstCard = null
            secondCard = null
        } else {
            // If no match, flip the cards back after a brief delay
            Handler(Looper.getMainLooper()).postDelayed({
                firstCard?.isFlipped = false
                secondCard?.isFlipped = false
                binding.gameBoardRecyclerView.adapter?.notifyDataSetChanged()
                // Reset the selected cards
                firstCard = null
                secondCard = null
                // Subtract time on mismatch
                viewModel.subtractTime(10)
            }, 1000)  // Delay to flip back the unmatched cards
        }
    }
    //create a new set after all current cards are matched
    private fun startNewSet() {
        // Generate new cards for the new set
        viewModel.generateNewCards()

        // Reset current set matches count before showing the new set
        currentSetMatches = 0

        // Reveal all cards briefly before letting the user interact
        revealAllCardsTemporarily()
    }

    private fun revealAllCardsTemporarily() {
        // Temporarily flip all cards to show the images
        viewModel.cards.value?.forEach { it.isFlipped = true }
        binding.gameBoardRecyclerView.adapter?.notifyDataSetChanged()

        // After 3 seconds, flip the cards back over
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.cards.value?.forEach { it.isFlipped = false }
            binding.gameBoardRecyclerView.adapter?.notifyDataSetChanged()
        }, 3000)  // Delay for 3 seconds to let the player see the cards
    }



    private fun showGameOver() {
        // Update high score before showing game over screen
        viewModel.updateHighScore(viewModel.currentDifficulty, totalMatches)

        val message = "Game Over! Total Matches: $totalMatches"
        binding.timerTextView.text = message
        binding.gameBoardRecyclerView.visibility = View.GONE
        binding.backToMainMenuButton.visibility = View.VISIBLE

        binding.backToMainMenuButton.setOnClickListener {
            findNavController().navigate(R.id.action_gameFragment_to_mainMenuFragment)
        }
    }
}



