package com.test.strategy;

import com.test.Board;
import com.test.Player;

public abstract class StrategyBase {

  public int getOptimalCapturePos(Board board, Player player, int storePos, int optimalPos, int captureSeedCount) {
    for (int i = board.getFirstHousePos(player.num); i < storePos; i++) {
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
