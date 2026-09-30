package com.example.tennisscorer.logic

import org.junit.jupiter.api.Assertions.*
import org.junit.Test

class TiebreakGameTest {

    @Test
    fun add1point(){
        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer("Bob")
        val tiebreakGame= TiebreakGame(player1,player2)

        tiebreakGame.addPoint(player1)

        assertEquals(1,tiebreakGame.player1Score)
    }

    fun check_player_not_in_game(){
        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer("Bob")
        val player3= TennisPlayer("Eve")
        val tiebreakGame= TiebreakGame(player1,player2)

        assertThrows(IllegalArgumentException::class.java){tiebreakGame.addPoint(player3)}
    }

    @Test
    fun check_tiebreak_won_7_5(){
        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer("Bob")

        val tiebreakGame= TiebreakGame(player1,player2)

        for (i in 1..5){

            tiebreakGame.addPoint(player1)
            tiebreakGame.addPoint(player2)
        }

        for (i in 1..2){

            tiebreakGame.addPoint(player1)
        }

        assertEquals(7,tiebreakGame.player1Score)
        assertEquals(5,tiebreakGame.player2Score)
        assertEquals(player1,tiebreakGame.gameWinner)

    }

    @Test
    fun check_tiebreak_won_9_7(){
        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer("Bob")

        val tiebreakGame= TiebreakGame(player1,player2)

        for (i in 1..7){

            tiebreakGame.addPoint(player1)
            tiebreakGame.addPoint(player2)
        }

        for (i in 1..2){

            tiebreakGame.addPoint(player1)
        }

        assertEquals(9,tiebreakGame.player1Score)
        assertEquals(7,tiebreakGame.player2Score)
        assertEquals(player1,tiebreakGame.gameWinner)

    }

    @Test
    fun check_tiebreak_not_decided_7_6(){
        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer("Bob")

        val tiebreakGame= TiebreakGame(player1,player2)

        for (i in 1..6){

            tiebreakGame.addPoint(player1)
            tiebreakGame.addPoint(player2)
        }

            tiebreakGame.addPoint(player1)

        assertEquals(7,tiebreakGame.player1Score)
        assertEquals(6,tiebreakGame.player2Score)
        assertEquals(null,tiebreakGame.gameWinner)

    }

    @Test
    fun check_tiebreak_not_decided_9_9(){
        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer("Bob")

        val tiebreakGame= TiebreakGame(player1,player2)

        for (i in 1..9){

            tiebreakGame.addPoint(player1)
            tiebreakGame.addPoint(player2)
        }


        assertEquals(9,tiebreakGame.player1Score)
        assertEquals(9,tiebreakGame.player2Score)
        assertEquals(null,tiebreakGame.gameWinner)

    }

    @Test
    fun check_cannot_update_if_tiebreak_over(){

        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer("Bob")

        val tiebreakGame= TiebreakGame(player1,player2)

        for (i in 1..5){

            tiebreakGame.addPoint(player1)
            tiebreakGame.addPoint(player2)
        }

        for (i in 1..2){

            tiebreakGame.addPoint(player1)
        }


        assertEquals(false,tiebreakGame.addPoint(player1))

    }



}