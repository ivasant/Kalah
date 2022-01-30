package com.test;

public class TestUtils {

  public static void setBoardState(Board board, int[] seeds) {
    for (int i = 0; i < seeds.length; i++) {
      board.setSeedCount(i, seeds[i]);
    }
  }

  public static int[] getBoardState(Board board) {
    int[] res = new int[board.getSize()];
    for (int i = 0; i < board.getSize(); i++) {
      res[i] = board.getSeedCount(i);
    }
    return res;
  }

}
