How to Run:

To run the app, first unzip the file. Next, open android studio and select
the option to open an existin project. Navigate to the folder you just unzipped
and select it as the directory. After the app finishes building, connect an
Android device, click 'Run' at the top of Android Studio, then select the devce.
Android Studio will install and run the app on the device.


How to Play:

The app begins in the start screen with 2 options. The player(s) can select
single player or multiplayer mode. Clicking on single player sends you to the
single player game where the snake moves using the tilt control. Control the
snake and set a high score by eating oranges. When the snake dies, the game
automatically ends and the player is sent to the end screen. There will be text
displaying the player's score. Two buttons appear. The player can either restart or
return to start screen. In multiplayer, 2 players control the snake usng the touchscreen.
The red buttons correspond to the red snake and the blue buttons correspond to
the blue snake. Win by getting a higher score than your opponent or outliving
them. When the game ends, the end screen pops up, allowing the player to choose
to restart or return to start again. The winner will be displayed with their score.
If the game is a draw, that will be displayed instead.

Test Cases for Single-player:
If snake eats its own tail it dies.
If snake runs into wall or game boundary it dies.
If snake eats orange it grows.


Test Cases for Multiplayer:
If snakes die at the same time, they die and it's a draw.

If a snake dies (by colliding with a wall or its tail the other snake's tail) 
before time runs out, it loses.

If a snake eats orange it grows. If no snakes have died before time runs out,
snake who ate more oranges wins. 
