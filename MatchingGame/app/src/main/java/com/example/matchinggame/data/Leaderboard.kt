package com.example.matchinggame.data

data class LeaderboardEntry(val userName: String, val score: Int)

object Leaderboard {
    private val entries = mutableListOf<LeaderboardEntry>()

    fun addEntry(entry: LeaderboardEntry) {
        entries.add(entry)
        entries.sortByDescending { it.score }
    }

    fun getTopEntries(): List<LeaderboardEntry> = entries.take(10)
}
