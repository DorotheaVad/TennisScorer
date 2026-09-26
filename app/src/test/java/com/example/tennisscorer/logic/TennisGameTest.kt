package com.example.tennisscorer.logic

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class TennisGameTest {

    @Test
    fun player1_can_add_1_point() {
        val player1 = TennisPlayer("Alice")
        val player2 = TennisPlayer("Bob")
        val game = TennisGame(player1, player2)
        game.addPoint(player1)
        assertEquals(1, game.player1Score)
    }


    @Test
    fun player2_can_add_1_point() {
        val player1 = TennisPlayer("Alice")
        val player2 = TennisPlayer("Bob")
        val game = TennisGame(player1, player2)
        game.addPoint(player2)
        assertEquals(1, game.player2Score)
    }

    @Test
    fun non_player_cannot_add_point(){
        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer(name = "Bob")
        val nonplayer= TennisPlayer(name="Eve")
        val game= TennisGame(player1, player2 )
        assertThrows(IllegalArgumentException::class.java){
        game.addPoint(nonplayer)}

    }

    @Test
    fun test_is_deuce(){
        val player1 = TennisPlayer("Alice")
        val player2 = TennisPlayer("Bob")
        val game = TennisGame(player1, player2)

        game.addPoint(player1)//15
        game.addPoint(player1)//30
        game.addPoint(player1)//40

        game.addPoint(player2)
        game.addPoint(player2)
        game.addPoint(player2)//40

        assertEquals(true,game.isDeuce(game.player1Score,game.player2Score))
    }

    @Test
    fun can_detect_deuce(){
        val player1 = TennisPlayer("Alice")
        val player2 = TennisPlayer("Bob")
        val game = TennisGame(player1, player2)

        game.addPoint(player1)//15
        game.addPoint(player1)//30
        game.addPoint(player1)//40

        game.addPoint(player2)
        game.addPoint(player2)
        game.addPoint(player2)//40

        assertEquals(true,game.deuce)

    }

    @Test
    fun deuce_happens(){
        val player1 = TennisPlayer("Alice")
        val player2 = TennisPlayer("Bob")
        val game = TennisGame(player1, player2)

        game.addPoint(player1)//15
        game.addPoint(player1)//30
        game.addPoint(player1)//40

        game.addPoint(player2)
        game.addPoint(player2)
        game.addPoint(player2)//40

        assertEquals(true,game.deuceHappened)
    }

    @Test
    fun detects_advantage_player1(){
        val player1 = TennisPlayer("Alice")
        val player2 = TennisPlayer("Bob")
        val game = TennisGame(player1, player2)

        game.addPoint(player1)//15
        game.addPoint(player1)//30
        game.addPoint(player1)//40

        game.addPoint(player2)
        game.addPoint(player2)
        game.addPoint(player2)//40

        //Deuce

        game.addPoint(player1)

        assertEquals(player1,game.advPlayer)
    }

    @Test
    fun detects_advantage_player2(){
        val player1 = TennisPlayer("Alice")
        val player2 = TennisPlayer("Bob")
        val game = TennisGame(player1, player2)

        game.addPoint(player1)//15
        game.addPoint(player1)//30
        game.addPoint(player1)//40

        game.addPoint(player2)
        game.addPoint(player2)
        game.addPoint(player2)//40

        //Deuce

        game.addPoint(player2)//adv

        assertEquals(null,game.gameWinner)
        assertEquals(player2,game.advPlayer)
        assertEquals(false,game.deuce)

    }

    @Test
    fun detects_deuce_after_advantage(){
        val player1 = TennisPlayer("Alice")
        val player2 = TennisPlayer("Bob")
        val game = TennisGame(player1, player2)

        game.addPoint(player1)//15
        game.addPoint(player1)//30
        game.addPoint(player1)//40

        game.addPoint(player2)
        game.addPoint(player2)
        game.addPoint(player2)//40

        //Deuce

        game.addPoint(player2)//adv

        game.addPoint(player1)//deuce


        assertEquals(true,game.deuce)
        assertEquals(null,game.advPlayer)
    }

    @Test
    fun detects_game_after_deuce(){
        val player1 = TennisPlayer("Alice")
        val player2 = TennisPlayer("Bob")
        val game = TennisGame(player1, player2)

        game.addPoint(player1)//15
        game.addPoint(player1)//30
        game.addPoint(player1)//40

        game.addPoint(player2)
        game.addPoint(player2)
        game.addPoint(player2)//40

        //Deuce

        game.addPoint(player2)//adv

        game.addPoint(player2)//game


        assertEquals(player2,game.gameWinner)
        assertEquals(null,game.advPlayer)
        assertEquals(false,game.deuce)
        assertEquals(true,game.deuceHappened)
    }

    @Test
    fun detects_game_before_deuce(){
        val player1 = TennisPlayer("Alice")
        val player2 = TennisPlayer("Bob")
        val game = TennisGame(player1, player2)

        game.addPoint(player1)//15
        game.addPoint(player1)//30
        game.addPoint(player1)//40


        game.addPoint(player1)//game



        assertEquals(player1,game.gameWinner)
        assertEquals(null,game.advPlayer)
        assertEquals(false,game.deuce)
        assertEquals(false,game.deuceHappened)
    }

    @Test
    fun detects_40_15_normally(){
        val player1 = TennisPlayer("Alice")
        val player2 = TennisPlayer("Bob")
        val game = TennisGame(player1, player2)

        game.addPoint(player1)//15
        game.addPoint(player1)//30
        game.addPoint(player1)//40

        game.addPoint(player2)


        assertEquals(null,game.gameWinner)
        assertEquals(null,game.advPlayer)
        assertEquals(false,game.deuce)
        assertEquals(false,game.deuceHappened)
    }
}





