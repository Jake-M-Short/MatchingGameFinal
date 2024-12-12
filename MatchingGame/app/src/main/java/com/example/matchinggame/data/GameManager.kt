package com.example.matchinggame.data

import com.example.matchinggame.R
//manages game logic such as reading the list of images and creating a set, also makes it so a chosen image is placed twice then not chosen again until next set
object GameManager {
    fun generateCards(difficulty: Int): List<Card> {
        val cardImages = listOf(
            R.drawable.cane,
            R.drawable.deer,
            R.drawable.mug,
            R.drawable.santa,
            R.drawable.stocking,
            R.drawable.tree,
        )

        require(difficulty / 2 <= cardImages.size) { "Not enough card images for the selected difficulty" }

        return cardImages.shuffled().take(difficulty / 2).flatMap { image ->
            listOf(
                Card(id = image, imageResId = image),
                Card(id = image, imageResId = image)
            )
            //shuffle card order so images that are matching are not directly next to one another
        }.shuffled()
    }
}

