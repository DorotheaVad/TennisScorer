package com.example.tennisscorer.logic

import org.junit.jupiter.api.Assertions.*
import org.junit.Test

class TennisMatchTest {

    @Test
    fun add_one_point_player1(){
        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer("Bob")

        val match= TennisMatch(player1,player2)

        match.addMatchPoint(player1)

        assertEquals(1,match.matchScore[player1])

    }


    @Test
    fun add_one_point_player2(){
        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer("Bob")

        val match= TennisMatch(player1,player2)

        match.addMatchPoint(player2)

        assertEquals(1,match.matchScore[player2])

    }


    @Test
    fun match_not_decided_1_1_bestOf3(){

        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer("Bob")

        val match= TennisMatch(player1,player2)

        match.addMatchPoint(player1)
        match.addMatchPoint(player2)

        assertEquals(false,match.checkMatchWin())

    }

    @Test
    fun match_not_decided_2_2_bestOf5(){

        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer("Bob")

        val match= TennisMatch(player1,player2,false)

        for (i in 1..2){
        match.addMatchPoint(player1)
        match.addMatchPoint(player2)}

        assertEquals(false,match.checkMatchWin())

    }

    @Test
    fun match_decided_2_0_bestOf3(){

        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer("Bob")

        val match= TennisMatch(player1,player2)

        match.addMatchPoint(player1)
        match.addMatchPoint(player1)


        assertEquals(true,match.checkMatchWin())

    }

    @Test
    fun match_not_decided_2_0_bestOf5(){

        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer("Bob")

        val match= TennisMatch(player1,player2,false)

        match.addMatchPoint(player1)
        match.addMatchPoint(player1)


        assertEquals(false,match.checkMatchWin())

    }


    @Test
    fun match_decided_3_2_bestOf5(){

        val player1= TennisPlayer("Alice")
        val player2= TennisPlayer("Bob")

        val match= TennisMatch(player1,player2,false)

        for (i in 1..2){
            match.addMatchPoint(player1)
            match.addMatchPoint(player2)}

        match.addMatchPoint(player1)

        assertEquals(true,match.checkMatchWin())

    }


}