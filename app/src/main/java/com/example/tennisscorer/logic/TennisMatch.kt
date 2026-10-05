package com.example.tennisscorer.logic

class TennisMatch(val player1: TennisPlayer, val player2: TennisPlayer,val bestOf3:Boolean=true) {
    val sets =mutableListOf<TennisSet>()
    val matchScore=mutableMapOf<TennisPlayer?, Int>(player1 to 0,player2 to 0)
    var matchWinner: TennisPlayer?=null
    var currentSet:TennisSet = TennisSet(player1,player2)

    init {
        sets.add(currentSet)
    }

    fun startNewSet(){
        currentSet= TennisSet(player1,player2)
        sets.add(currentSet)
    }

    fun updateMatchScore():Boolean{
        val player = currentSet.setWinner
        if (matchWinner == null && player != null) {
            addMatchPoint(player)
            if (checkMatchWin()){
                matchWinner=player
            }else{
                startNewSet()
            }

            return true
        }

        return false
    }

    fun addMatchPoint( player: TennisPlayer){
        val playerScore=matchScore[player]?:throw IllegalArgumentException("This player is not inside the game !")
        matchScore[player]=playerScore+1
    }

    fun checkMatchWin(): Boolean{

        val player1Score=matchScore[player1]?:throw IllegalArgumentException("This player is not inside the game !")
        val player2Score=matchScore[player2]?:throw IllegalArgumentException("This player is not inside the game !")

        if (bestOf3) return (player1Score>player2Score && player1Score in 2..3) || (player2Score>player1Score && player2Score in 2..3)

        return (player1Score>player2Score && player1Score in 3..5) || (player2Score>player1Score && player2Score in 3..5)
    }



}