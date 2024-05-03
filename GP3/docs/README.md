Project Title
text based UI Mancala: 
Simple overview of use/purpose.

Description

So bascially I have created a text UI based Mancala game using OOP concepts
to implement it. I have classes like Pit, Player, Store, Board, MancalaGame.
The pit class is used to manage and represent the individual pits on the game board. Players can add stones to pits, get the current stone count in a pit, and remove all stones from a pit. The Player class is used to represent and manage the two players in the game. It stores their names, their individual stores where they collect stones etc. THe Store class represent a player's store in the Mancala game. It keeps track of the number of stones in the store and the owner (player) of the store.
The board class is responsible for representing the game board, managing the distribution of stones, handling capture conditions, and providing methods to query the state of the board during a game of Mancala. Lastly, the MancalaGame class serves as the core controller for a Mancala game. It manages the game board, player actions, game state, and win conditions.

Getting Started

Dependencies

Describe any prerequisites, libraries, OS version, etc., needed before installing and running your program.

The dependencies are: JDK and Gradle

Executing program

How to build and run the program
Step-by-step bullets

Step 1: gradle build
Step 2: gradle echo
Step 3:  -jar build/libs/Mancala.jar  (type this into your command line)
Step 4: output should be displayed on screen if build was successful 
Step 5: youll be prompted to enter in 2 player names 

use code blocks for commands



include the expected output
Welcome to Mancala!

Enter name for Player 1: maham
Enter name for Player 2: daniel
        4 4 4 4 4 4 
(0)                     (0)
        4 4 4 4 4 4 

maham's turn.
Select a pit (1-12): 


Limitations
What isn't done? What things cause errors?
Everything is working.

Author Information
Your name and contact information including your email address
Name: Maham Tariq
ID: 1211053
Email: mtariq09@uoguelph.ca

Development History
Keep a log of what things you accomplish when.  You can use git's tagging feature to tag the versions or you can reference commits.

0.2

Various bug fixes and optimizations
See commit change or See release history



0.1

Initial Release




Acknowledgments
Inspiration, code snippets, etc.

https://cookhealthalliance.com/computers/software-languages/java/mancala-w-java/ 

used this as reference when i was creating my mancala game

awesome-readme
[simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)