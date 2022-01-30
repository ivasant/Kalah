package com.test.strategy;

import com.test.Board;
import com.test.Player;

public interface Strategy {
  int getMove(Board board, Player player);
}
