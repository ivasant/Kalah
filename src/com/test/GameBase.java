package com.test;

public abstract class GameBase {

  public Player player1 = null;
  public Player player2 = null;
  public final boolean playWithComputer;
  public final Board board;

  protected abstract String inputPlayerName();
  protected abstract int inputMove(Player player);
  protected abstract void printWinnerInfo(Player winner);

  public GameBase(Board board, boolean playWithComputer) {
    this.playWithComputer = playWithComputer;
    this.board = board;
  }

  public void start() {
    player1 = new Player(1, inputPlayerName(), false);
    player2 = (playWithComputer ?
        new Player(2, "Computer", true) :
        new Player(2, inputPlayerName(), false));
    board.setInitialState();
    board.print();
    Player player = player1;
    Player winner;
    int lastPos;
    do {

      do {
        int pos = inputMove(player);
        lastPos = makeMove(pos, player);
        captureSeed(lastPos, player);
        board.print();
      } while (canMakeNextMove(lastPos, player));

      winner = getWinner();
      player = getNextPlayer(player);

    } while (winner == null);

    printWinnerInfo(winner);
  }

  /**
   * Make a move starting from house at @pos.
   * @param pos  - index of house where the move starts
   * @return      - index of house/store where last seed goes
   */
  protected int makeMove(int pos, Player player) {
    if (!board.isOwnHouse(pos, player.num))
      throw new IllegalArgumentException("House " + board.getPitName(pos) + " is owned by another player");
    int seedCnt = board.getSeedCount(pos);
    board.setSeedCount(pos, 0);
    int oppositeStorePos = board.getOppositeStorePos(player.num);
    while (seedCnt != 0) {
      pos = board.getNextPit(pos);
      if (pos != oppositeStorePos) {
        board.incSeedCount(pos, 1);
        seedCnt--;
      }
    }
    return pos;
  }

  protected boolean canMakeNextMove(int lastPos, Player player) {
    return (lastPos == board.getStorePos(player.num));
  }

  protected void captureSeed(int pos, Player player) {
    if (board.isNotStore(pos) && board.getSeedCount(pos) == 1 && board.isOwnHouse(pos, player.num)) {
      int oppositeHouse = board.getOppositeHouse(pos);
      if (board.getSeedCount(oppositeHouse) > 0) {
        int storePos = board.getStorePos(player.num);
        board.incSeedCount(storePos, board.getSeedCount(oppositeHouse) + 1);
        board.setSeedCount(oppositeHouse, 0);
        board.setSeedCount(pos, 0);
      }
    }
  }


  protected Player getWinner() {
    if (board.getSeedCount(board.getStorePos(player1.num)) * 2 > board.getAllSeedCount())
      return player1;
    if (board.getSeedCount(board.getStorePos(player2.num)) * 2 > board.getAllSeedCount())
      return player2;
    if (board.getSeedCountInAllHouses(player1.num) == 0)
      return player2;
    if (board.getSeedCountInAllHouses(player2.num) == 0)
      return player1;
    return null;
  }

  protected Player getNextPlayer(Player player) {
    return (player == player1 ? player2 : player1);
 }

}
