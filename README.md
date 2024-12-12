# Matching Game Final Project - 12/12

 ## Overview

 This app is a matching game that has multiple difficulties as well as keeps the highest score of the user at a certain difficulty.

 ## Features

 Contains multiple difficulties including Easy(6 Cards), Medium(10 Cards), and Hard(12 Cards).

 Contains a statistics view where users can see their highest scores on those difficulties being unique from one difficulty to another.

 Has a christmas theme for december.

 Has a timer system that will add time to the user if they get a correct match and will penalize the user for any incorrect matches.

 UI and navigation is very simple, starts with a Start screen that leads into the main menu where the majority of options for the app occurs.

 Card art that also matches the theme for christmas.

 ## Screenshots of the app here:
![startscreen](https://github.com/user-attachments/assets/b0ce433e-1edc-426d-924f-d9ca6a2ea09d)
![MainMenu](https://github.com/user-attachments/assets/0b4d42ba-2418-49a2-8efc-8912fe8ab75d)
![viewstatistics](https://github.com/user-attachments/assets/2b2ce937-81b1-46e6-b8d4-d0d770a50ccb)
![selecteddiff](https://github.com/user-attachments/assets/ffff7d97-7c64-4726-a8a8-d20d711a10d0)
![gamestart](https://github.com/user-attachments/assets/e4c3d93b-b8b4-4ff5-b362-2a850982a2ee)
![midgame](https://github.com/user-attachments/assets/410deffd-abb2-4f0f-b707-4c86521dfdd0)
![gameover](https://github.com/user-attachments/assets/96fe812e-1eba-4a56-8c1d-9dd448d4013f)
![updatedstats](https://github.com/user-attachments/assets/528dcaf3-37f3-423e-80d6-0ccce3741cbc)

# Functions within the code:

I used fragment within XML to make my views for the screens on the app, this includes a game over screen, main menu screen, game screen, stats screen, start screen, and card display.

I used one viewmodel mainly throughout, this is the GameViewModel which is used to manage most of the game data including: cards, timer, card matches, and difficulty scores.

I created GameLogic within Data called GameManager which helped generate the cards for the selected difficulty.

I used a recycler view, which required a lot of google searches but works for listening to a users input based on their card choices checking for a match.

I used live data, this is so the UI will change automatically based on incoming data including: cards being set, timer updates, and the score updates.

## Video for app is too large, needed to upload to google drive.
 
