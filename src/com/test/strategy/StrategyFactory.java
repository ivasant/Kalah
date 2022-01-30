package com.test.strategy;

public class StrategyFactory {

  public enum StrategyType {
    MAX_CAPTION, MAX_MOVE
  }

  public static Strategy getStrategy(StrategyType strategyType) {
    switch (strategyType) {
      case MAX_MOVE: return new StrategyMaxMoveCount();
      case MAX_CAPTION: return new StrategyMaxCaption();
      default: throw new IllegalStateException("Unknown strategy");
    }
  }

}
