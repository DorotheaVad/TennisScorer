package com.example.tennisscorer.logic

class TiebreakGame(player1: TennisPlayer, player2: TennisPlayer):TennisGame(player1,player2) {


    override fun addPoint(player: TennisPlayer): Boolean{

        if (gameWinner!=null) return false
        when (player) {
            player1 -> player1Score += 1
            player2 -> player2Score += 1
            else -> throw (IllegalArgumentException("This Player is not a part of this Game!"))
        }

        if (isGame(player1Score,player2Score)) gameWinner=player

        return  true
    }

    override fun isGame(score1:Int,score2:Int): Boolean{
        return ((score1>=7 && score1>=score2+2)||(score2>=7 && score2>=score1+2))
    }

}