package com.example.matchinggame.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatsViewModel : ViewModel() {

    private val _statistics = MutableLiveData<String>()
    val statistics: LiveData<String> get() = _statistics

    fun loadStatistics() {
        // Example statistic data
        _statistics.value = "Most Matched Cards: Card 1"
    }
}
