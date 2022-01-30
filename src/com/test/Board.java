package com.test;

import java.util.List;

public interface Board {
  void print();
  void setInitialState();
  String getPitName(int pos);
  boolean isNotStore(int pos);
  List<String> getPlayerPitNameList(int playerNum);
  int getPitIndexByName(String pitName);
  int getSeedCount(int pos);
  void setSeedCount(int pos, int value);
  int getOppositeStorePos(int playerNum);
  int getNextPit(int pos);
  void incSeedCount(int pos, int delta);
  int getStorePos(int playerNum);
  int getOppositeHouse(int pos);
  int getAllSeedCount();
  int getSeedCountInAllHouses(int playerNum);
  int getFirstHousePos(int playerNum);
  int getLastPosOfMove(int pos, int playerNum);
  int getFirstNotEmptyHouse(int playerNum);
  boolean isOwnHouse(int pos, int playerNum);
  int getSize();

}
