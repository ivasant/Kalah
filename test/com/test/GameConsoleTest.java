package com.test;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.test.TestUtils.getBoardState;
import static com.test.TestUtils.setBoardState;

class GameConsoleTest {

  private GameConsole game = new GameConsole(4, false);
  {
    game.player1 = new Player(1, "Player1", false);
    game.player2 =  new Player(2, "Player2", false);
  }

  @Test
  void makeMove_player1_wrongMove() {
    setBoardState(game.board, new int[] {4,4,4,4,4,4,0,4,4,4,4,4,4,0});
    Assertions.assertThrows(IllegalArgumentException.class, () -> game.makeMove(7, game.player1));
  }

  @Test
  void makeMove_player2_wrongMove() {
    setBoardState(game.board, new int[] {4,4,4,4,4,4,0,4,4,4,4,4,4,0});
    Player player = new Player(2, "Player2", false);
    Assertions.assertThrows(IllegalArgumentException.class, () -> game.makeMove(1, game.player2));
  }

  @Test
  void makeMove_player1_noCross_noStore() {
    setBoardState(game.board, new int[] {4,4,4,4,4,4,0,4,4,4,4,4,4,0});
    game.makeMove(0, game.player1);
    assert(Arrays.equals(getBoardState(game.board), new int[] {0,5,5,5,5,4,0,4,4,4,4,4,4,0}));
  }

  @Test
  void makeMove_player2_noCross_noStore() {
    setBoardState(game.board, new int[] {4,4,4,4,4,4,0,4,4,4,4,4,4,0});
    game.makeMove(7, game.player2);
    assert(Arrays.equals(getBoardState(game.board), new int[] {4,4,4,4,4,4,0,0,5,5,5,5,4,0}));
  }

  @Test
  void makeMove_player1_noCross_ownStore() {
    setBoardState(game.board, new int[] {0,5,5,5,5,4,0,4,4,4,4,4,4,0});
    game.makeMove(1, game.player1);
    assert(Arrays.equals(getBoardState(game.board), new int[] {0,0,6,6,6,5,1,4,4,4,4,4,4,0}));
  }

  @Test
  void makeMove_player2_noCross_ownStore() {
    setBoardState(game.board, new int[] {0,5,5,5,5,4,0,4,4,4,4,4,4,0});
    game.makeMove(9, game.player2);
    assert(Arrays.equals(getBoardState(game.board), new int[] {0,5,5,5,5,4,0,4,4,0,5,5,5,1}));
  }

  @Test
  void makeMove_player1_cross_ownStore() {
    setBoardState(game.board, new int[] {0,0,6,6,6,1,2,5,5,4,4,4,4,0});
    game.makeMove(2, game.player1);
    assert(Arrays.equals(getBoardState(game.board), new int[] {0,0,0,7,7,2,3,6,6,4,4,4,4,0}));
  }

  @Test
  void makeMove_player2_cross_ownStore() {
    setBoardState(game.board, new int[] {0,0,6,6,6,1,2,5,3,4,4,4,6,0});
    game.makeMove(12, game.player2);
    assert(Arrays.equals(getBoardState(game.board), new int[] {1,1,7,7,7,1,2,5,3,4,4,4,0,1}));
  }

  @Test
  void makeMove_player1_cross_oppositeStore() {
    setBoardState(game.board, new int[] {1,1,4,3,2,12,1,5,5,4,2,4,4,0});
    game.makeMove(5, game.player1);
    assert(Arrays.equals(getBoardState(game.board), new int[] {2,2,5,4,3,0,2,6,6,5,3,5,5,0}));
  }

  @Test
  void makeMove_player2_cross_oppositeStore() {
    setBoardState(game.board, new int[] {1,5,4,3,2,7,1,1,0,7,2,4,11,0});
    game.makeMove(12, game.player2);
    assert(Arrays.equals(getBoardState(game.board), new int[] {2,6,5,4,3,8,1,2,1,8,3,4,0,1}));
  }

  @Test
  void captureSeed_player1_noCapture() {
    setBoardState(game.board, new int[] {1,5,4,3,2,7,1,1,0,7,2,4,11,0});
    game.captureSeed(8, game.player1);
    assert(Arrays.equals(getBoardState(game.board), new int[] {1,5,4,3,2,7,1,1,0,7,2,4,11,0}));
  }

  @Test
  void captureSeed_player2_noCapture() {
    setBoardState(game.board, new int[] {1,5,4,3,2,7,1,1,0,7,2,4,11,0});
    game.captureSeed(2, game.player2);
    assert(Arrays.equals(getBoardState(game.board), new int[] {1,5,4,3,2,7,1,1,0,7,2,4,11,0}));
  }

  @Test
  void captureSeed_player1_noCapture1() {
    setBoardState(game.board, new int[] {1,5,4,3,2,7,1,1,0,7,2,4,11,0});
    game.captureSeed(1, game.player1);
    assert(Arrays.equals(getBoardState(game.board), new int[] {1,5,4,3,2,7,1,1,0,7,2,4,11,0}));
  }

  @Test
  void captureSeed_player2_noCapture1() {
    setBoardState(game.board, new int[] {1,5,4,3,2,7,1,1,0,7,2,4,11,0});
    game.captureSeed(9, game.player2);
    assert(Arrays.equals(getBoardState(game.board), new int[] {1,5,4,3,2,7,1,1,0,7,2,4,11,0}));
  }

  @Test
  void captureSeed_player1_capture() {
    setBoardState(game.board, new int[] {5,1,4,3,2,7,1,1,5,3,2,4,10,0});
    game.captureSeed(1, game.player1);
    assert(Arrays.equals(getBoardState(game.board), new int[] {5,0,4,3,2,7,6,1,5,3,2,0,10,0}));
  }

  @Test
  void captureSeed_player2_capture() {
    setBoardState(game.board, new int[] {5,1,4,3,2,7,1,5,1,3,2,4,10,0});
    game.captureSeed(8, game.player2);
    assert(Arrays.equals(getBoardState(game.board), new int[] {5,1,4,3,0,7,1,5,0,3,2,4,10,3}));
  }

}