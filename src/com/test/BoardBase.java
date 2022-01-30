package com.test;

import java.util.Arrays;
import java.util.List;

public abstract class BoardBase implements Board {

  public final int MAX_SEED_COUNT = 6; // Max seed count per house
  public final int MIN_SEED_COUNT = 3; // Min seed count per house
  public final int seedCount; // Initial seed count per house
  public final int allSeedCount; // All seeds count
  protected final int[] board = new int[14]; // Game board; from 0 to 6 - houses of Player1, from 7 to 13 - houses of Player2

  public final int STORE1_POS = 6; // Store position for Player1
  public final int STORE2_POS = 13; // Store position for Player2

  public abstract void print();
  public abstract String getPitName(int pos);
  public abstract List<String> getPlayerPitNameList(int playerNum);
  public abstract int getPitIndexByName(String pitName);

  public BoardBase(int seedCountPerHouse) {
    if (seedCountPerHouse > MAX_SEED_COUNT)
      throw new IllegalArgumentException("Max seed count per house is " + MAX_SEED_COUNT);
    if (seedCountPerHouse < MIN_SEED_COUNT)
      throw new IllegalArgumentException("Min seed count per house is " + MIN_SEED_COUNT);
    this.seedCount = seedCountPerHouse;
    allSeedCount = seedCount * 12;
  }

  public void setInitialState() {
    for (int i = 0; i < board.length; i++) {
      if (isNotStore(i))
        board[i] = seedCount;
    }
  }

  public boolean isNotStore(int pos) {
    return (pos != STORE1_POS && pos != STORE2_POS);
  }

  public int getOppositeHouse(int pos) {
    return STORE2_POS - 1 - pos;
  }

  public int getOppositeStorePos(int playerNum) {
    return (playerNum == 1 ? STORE2_POS : STORE1_POS);
  }

  public int getSeedCount(int pos) {
    return board[pos];
  }

  public void setSeedCount(int pos, int value) {
    board[pos] = value;
  }

  public int getNextPit(int pos) {
    return ((pos == board.length - 1) ? 0 : pos + 1);
  }

  public void incSeedCount(int pos, int delta) {
    board[pos] = board[pos] + delta;
  }

  public int getStorePos(int playerNum) {
    return (playerNum == 1 ? STORE1_POS : STORE2_POS);
  }

  public int getAllSeedCount() {
    return allSeedCount;
  }

  public int getSeedCountInAllHouses(int playerNum) {
    return (playerNum == 1 ?
        Arrays.stream(Arrays.copyOfRange(board, 0, STORE1_POS)).sum() :
        Arrays.stream(Arrays.copyOfRange(board, STORE1_POS + 1, STORE2_POS)).sum());
  }

  public int getFirstHousePos(int playerNum) {
    return (playerNum == 1 ? 0 : STORE1_POS + 1);
  }

  public int getLastPosOfMove(int pos, int playerNum) {
    int seedCount = board[pos];
    while (seedCount > 0) {
      pos = getNextPit(pos);
      if (playerNum == 1 && pos != STORE2_POS || playerNum != 1 && pos != STORE1_POS)
        seedCount--;
    }
    return pos;
  }

  public int getFirstNotEmptyHouse(int playerNum) {
    for (int i = getFirstHousePos(playerNum); i < getStorePos(playerNum); i++) {
      if (board[i] > 0)
        return i;
    }
    return -1;
  }

  public boolean isOwnHouse(int pos, int playerNum) {
    return (pos >= getFirstHousePos(playerNum) && pos < getStorePos(playerNum));
  }

  public int getSize() { return board.length; }

}
