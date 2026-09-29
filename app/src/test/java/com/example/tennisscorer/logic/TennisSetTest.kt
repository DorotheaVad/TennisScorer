package com.example.tennisscorer.logic

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class TennisSetTest {

    @Test
    fun test_constructor() {
        val player1 = TennisPlayer("Bob")
        val player2 = TennisPlayer("Alice")
        val ts = TennisSet(player1, player2)
        assertEquals(1, (ts.games).size)
        assertNotNull(ts.currentGame)
    }

    @Test
    fun start_new_game_test() {
        val player1 = TennisPlayer("Bob")
        val player2 = TennisPlayer("Alice")
        val ts = TennisSet(player1, player2)
        val pointerGame = ts.currentGame
        ts.startNewGame()
        assertEquals(2, (ts.games).size)
        assertNotNull(ts.currentGame)
        assertNotEquals(pointerGame, ts.currentGame)

    }

    @Test
    fun add_1_set_point() {
        val player1 = TennisPlayer("Bob")
        val player2 = TennisPlayer("Alice")
        val ts = TennisSet(player1, player2)
        ts.addGamePointToSet(player1)
        assertEquals(0, ts.setScore[player2])
        assertEquals(1, ts.setScore[player1])
    }

    @Test
    fun set_not_decided_3_2() {
        val player1 = TennisPlayer("Bob")
        val player2 = TennisPlayer("Alice")
        val ts = TennisSet(player1, player2)

        for(i in 1..3){
        ts.addGamePointToSet(player1)
        }

        for (i in 1..2){
        ts.addGamePointToSet(player2)}

        assertEquals(2, ts.setScore[player2])
        assertEquals(3, ts.setScore[player1])
        assertEquals(false, ts.checkSetWin())
    }

    @Test
    fun set_decided_6_2() {
        val player1 = TennisPlayer("Bob")
        val player2 = TennisPlayer("Alice")
        val ts = TennisSet(player1, player2)

        for(i in 1..3){
            ts.addGamePointToSet(player1)
        }

        for (i in 1..2){
            ts.addGamePointToSet(player2)}

        for(i in 1..3){
            ts.addGamePointToSet(player1)
        }

        assertEquals(2, ts.setScore[player2])
        assertEquals(6, ts.setScore[player1])
        assertEquals(true, ts.checkSetWin())

    }

    @Test
    fun set_not_decided_5_6() {
        val player1 = TennisPlayer("Bob")
        val player2 = TennisPlayer("Alice")
        val ts = TennisSet(player1, player2)

        for(i in 1..6){
            ts.addGamePointToSet(player1)
        }


        for(i in 1..5){
            ts.addGamePointToSet(player2)
        }


        assertEquals(5, ts.setScore[player2])
        assertEquals(6, ts.setScore[player1])
        assertEquals(false, ts.checkSetWin())

    }

    @Test
    fun test_tiebreak() {
        val player1 = TennisPlayer("Bob")
        val player2 = TennisPlayer("Alice")
        val ts = TennisSet(player1, player2)

        for(i in 1..6){
            ts.addGamePointToSet(player1)
        }

        for(i in 1..6){
            ts.addGamePointToSet(player2)
        }


        assertEquals(6, ts.setScore[player2])
        assertEquals(6, ts.setScore[player1])
        assertEquals(false, ts.checkSetWin())
        assertEquals(true, ts.checkTiebreak())
    }

    @Test
    fun check_that_game_is_won_and_start_new_game(){
        val player1 = TennisPlayer("Bob")
        val player2 = TennisPlayer("Alice")
        val ts = TennisSet(player1, player2)

        for (i in 1..4){
        ts.currentGame.addPoint(player1)}


        assertEquals(true,ts.updateSetScore())
        assertEquals(1, ts.setScore[player1])
        assertEquals(2,ts.games.size)


    }

    @Test
    fun check_that_game_is_not_won_and_continue(){
        val player1 = TennisPlayer("Bob")
        val player2 = TennisPlayer("Alice")
        val ts = TennisSet(player1, player2)

        for (i in 1..3){
        ts.currentGame.addPoint(player1)}


        assertEquals(false,ts.updateSetScore())
        assertEquals(0, ts.setScore[player1])
        assertEquals(1,ts.games.size)


    }

    @Test
    fun check_that_set_is_won(){
        val player1 = TennisPlayer("Bob")
        val player2 = TennisPlayer("Alice")
        val ts = TennisSet(player1, player2)

        for (i in 1..6){
        ts.currentGame.addPoint(player2)
        ts.currentGame.addPoint(player2)
        ts.currentGame.addPoint(player2)
        ts.currentGame.addPoint(player2)

        ts.updateSetScore()
        }


        assertEquals(6, ts.setScore[player2])
        assertEquals(6,ts.games.size)
        assertEquals(player2,ts.setWinner)


    }


    @Test
    fun cannot_update_set_score_after_win(){

        val player1 = TennisPlayer("Bob")
        val player2 = TennisPlayer("Alice")
        val ts = TennisSet(player1, player2)

        for (i in 1..6){
            ts.currentGame.addPoint(player2)
            ts.currentGame.addPoint(player2)
            ts.currentGame.addPoint(player2)
            ts.currentGame.addPoint(player2)

            ts.updateSetScore()
        }

        val uS=ts.updateSetScore()

        assertEquals(false,uS)
        assertEquals(player2,ts.setWinner)



    }


}