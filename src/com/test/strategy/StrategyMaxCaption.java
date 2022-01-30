package com.test.strategy;

import com.test.Board;
import com.test.Player;

public class StrategyMaxCaption extends StrategyBase implements Strategy {

  @Override
  public int getMove(Board board, Player player) {
    int optimalPos = -1;
    int captureSeedCount = 0;
    int storePos = board.getStorePos(player.num);
    optimalPos = getOptimalCapturePos(board, player, storePos, optimalPos, captureSeedCount);
    if (optimalPos != -1)
      return optimalPos;
    for (int i = board.getFirstHousePos(player.num); i < storePos; i++) {
      if (board.getSeedCount(i) == (storePos - i)) {
        return i;
      }
    }
    return board.getFirstNotEmptyHouse(player.num);
  }

}
