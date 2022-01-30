package com.test;

import java.util.Arrays;
import java.util.List;

public class ConsoleBoard extends BoardBase {

  protected final String[] pitNameArr =
      {"A", "B", "C", "D", "E", "F", "Store1", "a", "b", "c", "d", "e", "f", "Store2"};

  @Override
  public void print() {
    for (int i = STORE2_POS; i > STORE1_POS; i--) {
      System.out.printf("%7s", pitNameArr[i]);
    }
    System.out.println();
    for (int i = STORE2_POS; i > STORE1_POS; i--) {
      System.out.printf("%7d", board[i]);
    }
    System.out.println("\r\n");
    System.out.printf("%7s", "");
    for (int i = 0; i <= STORE1_POS; i++) {
      System.out.printf("%7d", board[i]);
    }
    System.out.println();
    System.out.printf("%7s", "");
    for (int i = 0; i < STORE1_POS; i++) {
      System.out.printf("%7s", pitNameArr[i]);
    }
    System.out.printf("%12s\r\n%n", pitNameArr[STORE1_POS]);
  }

  public String getPitName(int pos) {
    return pitNameArr[pos];
  }

  public List<String> getPlayerPitNameList(int playerNum) {
    return Arrays.asList((playerNum == 1) ?
        Arrays.copyOfRange(pitNameArr, 0, STORE1_POS) :
        Arrays.copyOfRange(pitNameArr, STORE1_POS + 1, STORE2_POS));
  }

  public int getPitIndexByName(String pitName) {
    return Arrays.asList(pitNameArr).indexOf(pitName);
  }

  public ConsoleBoard(int seedCountPerHouse) {
    super(seedCountPerHouse);
  }

}
