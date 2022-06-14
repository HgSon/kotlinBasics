package minesweeper

class GameManager {

    private var minesweeper: Minesweeper? = null
    private var firstFree = false

    fun createGame(): Minesweeper {
        print("How many mines do you want on the field? ")
        //val mines = readln().toInt()
        val mines = 10
        this.minesweeper = Minesweeper(mines)
        return this.minesweeper!!
    }

    fun playGame() {
        print("Set/unset mine marks or claim a cell as free: ")
        //val (x, y, action) = readln().split(" ")
        val (x, y, action) = "3 2 free".split(" ")
        val xCoord = x.toInt()
        val yCoord = y.toInt()

        if (!firstFree && action == "free") {
            firstFree = true
            minesweeper!!.changeField(yCoord, xCoord)
        }

        minesweeper!!.updateField(yCoord, xCoord, action)
    }

    fun tellUsersWin(): Boolean {
        val isUserWin = minesweeper!!.isUserWin()
        if (isUserWin) {
            println("Congratulations! You found all the mines!")
        }
        return isUserWin
    }

    fun tellUsersLose(): Boolean {
        val isUserLose = minesweeper!!.isUserLose()
        if(isUserLose) {
            println("You stepped on a mine and failed!")
        }
        return isUserLose
    }
}