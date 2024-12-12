package com.example.matchinggame.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matchinggame.data.Card
import com.example.matchinggame.data.GameManager

class GameViewModel : ViewModel() {
    //variables for the functions below, do not function without it
    var currentDifficulty: Int = 6
        private set

    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> get() = _cards

    private val _timer = MutableLiveData<Int>()
    val timer: LiveData<Int> get() = _timer

    private val _highScores = MutableLiveData<MutableMap<Int, Int>>(mutableMapOf())
    val highScores: LiveData<MutableMap<Int, Int>> get() = _highScores

    private var countDownTimer: CountDownTimer? = null
    private var currentTime: Int = 0
    private var currentMatches: Int = 0
    private var currentSetMatches = 0 // Track matches in the current set


    // initialize the game here with difficulty and set the matches to zero
    fun startGame(difficulty: Int) {
        currentDifficulty = difficulty
        _cards.value = GameManager.generateCards(difficulty)
        currentMatches = 0
        currentSetMatches = 0
        startTimer(35) //game time
    }
    //starts game timer
    private fun startTimer(startTime: Int) {
        currentTime = startTime
        _timer.value = currentTime
        countDownTimer?.cancel()

        countDownTimer = object : CountDownTimer(currentTime * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentTime = (millisUntilFinished / 1000).toInt()
                _timer.value = currentTime
            }

            override fun onFinish() {
                currentTime = 0
                _timer.value = currentTime
            }
        }.start()
    }


    //function to restart timer on new game
    private fun restartTimer() {
        countDownTimer?.cancel()
        if (currentTime > 0) {
            startTimer(currentTime)
        }
    }
    //generates new set of cards with chosen difficulty
    fun generateNewCards() {
        _cards.value = GameManager.generateCards(currentDifficulty)
        currentSetMatches = 0
    }
    //updates view statistics with max matches
    fun updateHighScore(difficulty: Int, matches: Int) {
        val currentScores = _highScores.value ?: mutableMapOf()
        val currentHighScore = currentScores[difficulty] ?: 0

        if (matches > currentHighScore) {
            currentScores[difficulty] = matches
            _highScores.value = currentScores // Update the high scores
        }
    }
    //adds time to user if they get a correct match
    fun addTime(seconds: Int) {
        currentTime += seconds
        _timer.value = currentTime
        restartTimer()
    }
    // subtract time if they mess up a match
    fun subtractTime(seconds: Int) {
        currentTime -= seconds
        _timer.value = currentTime
        restartTimer()
    }


    //set highscores to zero as default but allowing changes
    fun getHighScoresWithDefaults(): Map<Int, Int> {
        val highScoresMap = _highScores.value ?: mutableMapOf()
        return listOf(6, 10, 12).associateWith { highScoresMap[it] ?: 0 }
    }

    //function to reset the current set's match count
    fun resetCurrentSetMatches() {
        currentSetMatches = 0
    }
}






