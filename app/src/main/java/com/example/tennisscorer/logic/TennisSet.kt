package com.example.tennisscorer.logic

import androidx.compose.runtime.currentRecomposeScope

class TennisSet(val player1: TennisPlayer,val player2: TennisPlayer) {
    val games=mutableListOf<TennisGame>()
    var currentGame: TennisGame?= TennisGame(player1,player2)
    var setWinner:TennisPlayer?=null

    var tiebreakGame:TiebreakGame?=null
    var setScore=mutableMapOf<TennisPlayer?,Int>(player1 to 0, player2 to 0)


    init {
        games.add(currentGame!!)
    }

     fun startNewGame(){
        currentGame=TennisGame(player1,player2)
        games.add(currentGame!!)

    }

    private fun startTieBreakGame(){
        tiebreakGame= TiebreakGame(player1,player2)
        currentGame=tiebreakGame

    }

    fun updateSetScore(): Boolean {
        val player = currentGame!!.gameWinner
        if (setWinner == null && player != null) {

            if (tiebreakGame == null) {
                addGamePointToSet(player)
                if (checkSetWin()) {
                    setWinner = player
                } else if (checkTiebreak()) {
                    startTieBreakGame()
                } else {
                    startNewGame()
                }
            } else{
                setWinner = player
            }

            return true
        }

        return false
    }

    fun addGamePointToSet(player: TennisPlayer?){

        val playerScore=setScore[player]?:throw IllegalArgumentException("This player is not inside the game !")

        setScore[player]=playerScore+1

    }

    fun checkSetWin(): Boolean{
        val score1=setScore[player1]?:throw IllegalArgumentException("This player is not inside the game !")
        val score2=setScore[player2]?:throw IllegalArgumentException("This player is not inside the game !")
        return ((score1>=6 && score2<=score1-2)||(score2>=6 && score1<=score2-2))

    }

    fun checkTiebreak(): Boolean{
        val score1=setScore[player1]?:throw IllegalArgumentException("This player is not inside the game !")
        val score2=setScore[player2]?:throw IllegalArgumentException("This player is not inside the game !")

        return (score1==score2 && score1==6)
    }


}