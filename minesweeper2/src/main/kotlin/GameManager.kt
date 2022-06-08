package minesweeper

class GameManager {

    private var minesweeper: Minesweeper? = null

    fun createGame():Minesweeper {
        print("How many mines do you want on the field?")
        //val mines = readln().toInt()
        val mines = 10
        this.minesweeper = Minesweeper(mines)
        return this.minesweeper!!
    }

    fun playGame() {
        println("Set/delete mine marks (x and y coordinates): ")
        //val (xCoord, yCoord) = readln().split(" ").map { it.toInt() }
        val (xCoord, yCoord) = "3 2".split(" ").map { it.toInt() }
        minesweeper!!.updateField(yCoord, xCoord)
    }

    fun tellIsUserWin(): Boolean {
        val isUserWin = minesweeper!!.isUserWin()
        if (isUserWin) {
            println("Congratulations! You found all the mines!")
        }
        return isUserWin
    }
}