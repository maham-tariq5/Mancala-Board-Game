

#1: 
i want you to create a mancala game in java, i will you give all the methods of one the classes called MancalaGame.java, here are the required methods for the class, Mancala 

setPlayers(onePlayer: Player, twoPlayer: Player) : : void

getPlayers() : : Array List

getCurrentPlayer() : : Player

setCurrentPlayer(player: Player) : : void

setBoard(theBoard: Board) : : void

getBoard() : : Board

getNumStones(pitNum: int) : : int

move(startPit: int) : : int

getStoreCount(player: Player) : : int


getWinner() : : Player

isGameOver() boolean 

startNewGame() : : void

toString() : : String





#2 the second class will board.java, here are the methods for it please implement them Board

setUpPits() : : void

getPits() : : ArrayList

getStores() : : ArrayList

setUpStores() : : void

initializeBoard() : : void

resetBoard() : : void

registerPlayers(one: Player, two: Player) : : void

moveStones(startPit: int,player: Player) : : int

distributeStones(startingPoint: int) : : int

captureStones(stoppingPoint: int) : : int

getNumStones(pitNum: int) : : int

isSideEmpty(pitNum: int) boolean



#3 here is the 3rd class, Pit.java Pit

getStoneCount() : : int

addStone() : : void

removeStones() : : int


#4 Player

Player()

Player(name: String)

getName() : : String

setName(name: String) : : void

setStore(store: Store) : : void

getStoneCount() : : int heres the player class




#5

Store

setOwner(player: Player) : : void

getOwner() : : Player

addStones(amount: int) : : void

getTotalStones() : : int

emptyStore() : : int

#6 implement all the board game


#7 give me a main method for all these classes