package com.example.tennisscorer.logic

class TiebreakGame(val player1: TennisPlayer,val player2: TennisPlayer) {

    var player1Score=0
    var player2Score=0

    var tiebreakWinner: TennisPlayer?=null

    fun addPoint(player: TennisPlayer): Boolean{

        if (tiebreakWinner!=null) return false
        when (player) {
            player1 -> player1Score += 1
            player2 -> player2Score += 1
            else -> throw (IllegalArgumentException("This Player is not a part of this Game!"))
        }

        if (isTiebreak()) tiebreakWinner=player

        return  true
    }

    fun isTiebreak(): Boolean{
        return ((player1Score>=7 && player1Score>=player2Score+2)||(player1Score>=7 && player1Score>=player2Score+2))
    }

}