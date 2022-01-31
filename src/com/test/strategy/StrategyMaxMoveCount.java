package com.test.strategy;

import com.test.Board;
import com.test.Player;

public class StrategyMaxMoveCount extends StrategyBase implements Strategy {

  @Override
  public int getMove(Board board, Player player) {
    int storePos = board.getStorePos(player.num);
    for (int i = board.getFirstHousePos(player.num); i < storePos; i++) {
      if (board.getSeedCount(i) == (storePos - i)) {
        return i;
      }
    }
    int optimalPos = getOptimalCapturePos(board, player);
    return (optimalPos != -1 ? optimalPos : board.getFirstNotEmptyHouse(player.num));
  }

}
