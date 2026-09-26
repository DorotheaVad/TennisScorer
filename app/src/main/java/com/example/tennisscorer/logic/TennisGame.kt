package com.example.tennisscorer.logic

import androidx.navigationevent.NavigationEventInfo

class TennisGame(val player1: TennisPlayer,val player2 : TennisPlayer) {
    var player1Score = 0
    var player2Score = 0
    var deuceHappened: Boolean=false
    var deuce: Boolean=false

    var gameWinner: TennisPlayer?=null

    var advPlayer : TennisPlayer?=null



    fun addPoint(player: TennisPlayer) {

        when (player) {
            player1 -> player1Score += 1
            player2 -> player2Score += 1
            else -> println("Throw Exception")
        }

        deuce=isDeuce(player1Score, player2Score)
        if (!deuceHappened && deuce ){deuceHappened=true
        }else{
            if (isGame(player1Score,player2Score)){
                    gameWinner=player
                }else if(isAdv(player1Score,player2Score)){
                    advPlayer=player
                }else{
                    advPlayer=null
                }
    }
        return
    }



    fun isDeuce(score1:Int,score2:Int): Boolean {

        return (score1==score2 && score1>=3)
        }

    fun isAdv(score1:Int,score2:Int): Boolean{
        return (deuceHappened && ((score1==score2+1 || score2==score1+1)))
    }

    fun isGame(score1:Int, score2:Int): Boolean{
        return  (deuceHappened && ((score1==score2+2)||score2==score1+2)) || (!deuceHappened && (score1==4|| score2==4))
    }
}
