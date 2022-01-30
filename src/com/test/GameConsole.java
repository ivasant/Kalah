package com.test;

import java.util.List;
import java.util.Scanner;
import com.test.strategy.Strategy;
import com.test.strategy.StrategyFactory;

public class GameConsole extends GameBase {

  protected final Scanner scanner = new Scanner(System.in);
  protected final Strategy strategy = StrategyFactory.getStrategy(StrategyFactory.StrategyType.MAX_CAPTION);

  public GameConsole(int storeCountPerCell, boolean playWithComputer) {
    super(new ConsoleBoard(storeCountPerCell), playWithComputer);
  }

  @Override
  protected int inputMove(Player player) {
    if (player.isComputer) {
      int move = strategy.getMove(board, player);
      System.out.println("Computer move is " + board.getPitName(move));
      return move;
    }
    List<String> nameList = board.getPlayerPitNameList(player.num);
    String pitName;
    do {
      System.out.println("Input a letter of a house, from which you want to make a move. Allowed values are: "
          + String.join(", ", nameList) + ". Input \"q\" to quit.");
      pitName = scanner.nextLine();
    } while (!pitName.equals("q") && !nameList.contains(pitName));
    if (pitName.equals("q"))
      System.exit(0);
    return board.getPitIndexByName(pitName);
  }

  public void printWinnerInfo(Player winner) {
    System.out.println("Game is over. The winner is " + winner.name);
  }

  public String inputPlayerName() {
    System.out.println("Input player name: ");
    return scanner.nextLine();
  }

}
