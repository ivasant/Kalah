package com.test.strategy;

import com.test.Board;
import com.test.Player;

public abstract class StrategyBase {

  public int getOptimalCapturePos(Board board, Player player) {
    int captureSeedCount = -1;
    int optimalPos = -1;
    for (int i = board.getFirstHousePos(player.num); i <  board.getStorePos(player.num); i++) {
      if (board.getSeedCount(i) == 0)
        continue;
      int lastPos = board.getLastPosOfMove(i, player.num);
      if (board.isNotStore(lastPos) && board.isOwnHouse(lastPos, player.num) && board.getSeedCount(lastPos) == 0) {
        int oppositeHousePos = board.getOppositeHouse(lastPos);
        if (board.getSeedCount(oppositeHousePos) > captureSeedCount) {
          captureSeedCount = board.getSeedCount(oppositeHousePos);
          optimalPos = i;
        }
      }
    }
    return optimalPos;
  }
}
