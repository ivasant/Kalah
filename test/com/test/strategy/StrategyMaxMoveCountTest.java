package com.test.strategy;

import java.util.Arrays;
import com.test.Board;
import com.test.ConsoleBoard;
import com.test.Player;
import org.junit.jupiter.api.Test;

import static com.test.TestUtils.setBoardState;
import static org.junit.jupiter.api.Assertions.*;

class StrategyMaxMoveCountTest {

  Strategy strategy = new StrategyMaxMoveCount();
  Board board = new ConsoleBoard(4);
  Player player1 = new Player(1, "Player1", true);
  Player player2 = new Player(2, "Player2", true);

  @Test
  void getMove_player1_house() {
    setBoardState(board, new int[] {4,4,4,4,4,4,0,4,4,4,4,4,4,0});
    int pos = strategy.getMove(board, player1);
    assert(pos == 2);
  }

  @Test
  void getMove_player2_house() {
    setBoardState(board, new int[] {4,4,4,4,4,4,0,4,4,4,4,4,4,0});
    int pos = strategy.getMove(board, player2);
    assert(pos == 9);
  }

  @Test
  void getMove_player1_noHouse_noCapture() {
    setBoardState(board, new int[] {4,4,0,5,5,5,1,4,4,4,4,4,4,0});
    int pos = strategy.getMove(board, player1);
    assert(pos == 0);
  }

  @Test
  void getMove_player2_noHouse_noCapture() {
    setBoardState(board, new int[] {4,4,0,5,5,5,1,4,5,3,4,4,4,0});
    int pos = strategy.getMove(board, player2);
    assert(pos == 7);
  }

  @Test
  void getMove_player1_noHouse_capture() {
    setBoardState(board, new int[] {0,3,2,6,5,8, 1,4,4,4,4,4,4,0});
    int pos = strategy.getMove(board, player1);
    assert(pos == 5);
  }

  @Test
  void getMove_player2_noHouse_capture() {
    setBoardState(board, new int[] {4,4,4,4,4,4,1,0,3,2,6,5,8,0});
    int pos = strategy.getMove(board, player1);
    assert(pos == 12);
  }

  @Test
  void getMove_player1_noHouse_2captures() {
    setBoardState(board, new int[] {0,0,1,4,10,8,1,4,4,4,4,3,5,0});
    int pos = strategy.getMove(board, player1);
    assert(pos == 4);
  }

  @Test
  void getMove_player2_noHouse_2captures() {
    setBoardState(board, new int[] {0,5,3,4,4,4,4, 1,8,10,4,1,0,0});
    int pos = strategy.getMove(board, player1);
    assert(pos == 11);
  }

}