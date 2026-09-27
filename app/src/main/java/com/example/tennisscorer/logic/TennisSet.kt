package com.example.tennisscorer.logic

import androidx.compose.runtime.currentRecomposeScope

class TennisSet(val player1: TennisPlayer,val player2: TennisPlayer) {
    val games=mutableListOf<TennisGame>()
    var currentGame= TennisGame(player1,player2)
    var setWinner:TennisPlayer?=null

    var tiebreakGame:TiebreakGame?=null
    var setScore=mutableMapOf<TennisPlayer?,Int>(player1 to 0, player2 to 0)


    init {
        games.add(currentGame)
    }

    private fun startNewGame(){
        currentGame=TennisGame(player1,player2)
        games.add(currentGame)

    }

    private fun startTieBreakGame(){
        //create the tiebreakGame , currentgame=tiebreakgame
    }

    fun endGame(): Boolean{ //change some things to introduce the tiebreak , should see how the addGamePoint and checkSetWin should work with tiebreaks or just have different behaviour (check whether the game is simple or tiebreak)
        val player=currentGame.gameWinner
        if (player!=null){

            addGamePoint(player)
            if (checkSetWin()){
                setWinner=player
            }else if (checkTiebreak()){
                startTieBreakGame()

            }
            else{
                startNewGame()
            }

            return true
        }
        return false
    }

    fun addGamePoint(player: TennisPlayer?){

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