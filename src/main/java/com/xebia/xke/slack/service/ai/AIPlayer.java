package com.xebia.xke.slack.service.ai;

import com.xebia.xke.slack.service.Game;
import com.xebia.xke.slack.entities.Board;
import com.xebia.xke.slack.enums.Level;
import com.xebia.xke.slack.enums.Theme;

import java.util.ArrayList;
import java.util.List;

public class AIPlayer extends AI {

  public AIPlayer(Level level, Board board) {
    super(level, board);
  }

  @Override
  public int[] getNextMove() {
    int[] result = minimax(level.getDepth(), maxEmoji);
    return new int[] {result[1], result[2]};
  }

  private int[] minimax(int depth, CharSequence player) {
    int currentScore;
    int bestRow = -1;
    int bestCol = -1;
    List<int[]> nextMoves = getPossibleMoves();
    int bestScore = (player == maxEmoji) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

    if (nextMoves.isEmpty() || depth == 0) {
      bestScore = evaluate();
    } else {
      for (int[] move : nextMoves) {
        board.getGrid()[move[0]][move[1]] = player;
        if (player == maxEmoji) {
          currentScore = minimax(depth - 1, minEmoji)[0];
          if (currentScore > bestScore) {
            bestScore = currentScore;
            bestRow = move[0];
            bestCol = move[1];
          }
        } else {
          currentScore = minimax(depth - 1, maxEmoji)[0];
          if (currentScore < bestScore) {
            bestScore = currentScore;
            bestRow = move[0];
            bestCol = move[1];
          }
        }
        board.getGrid()[move[0]][move[1]] = Theme.DEFAULT.getBlankEmoji();
      }
    }
    return new int[] {bestScore, bestRow, bestCol};
  }

  private List<int[]> getPossibleMoves() {
    List<int[]> nextMoves = new ArrayList<>();

    if (Game.hasWon(maxEmoji, board) || Game.hasWon(minEmoji, board)) {
      return nextMoves;
    }

    for (int row = 0; row < board.getWidth(); ++row) {
      for (int col = 0; col < board.getHeight(); ++col) {
        if (board.getGrid()[row][col] == Theme.DEFAULT.getBlankEmoji()) {
          nextMoves.add(new int[] {row, col});
        }
      }
    }
    return nextMoves;
  }

  private int evaluate() {
    int score = 0;
    score += evaluateLine(0, 0, 0, 1, 0, 2);
    score += evaluateLine(1, 0, 1, 1, 1, 2);
    score += evaluateLine(2, 0, 2, 1, 2, 2);
    score += evaluateLine(0, 0, 1, 0, 2, 0);
    score += evaluateLine(0, 1, 1, 1, 2, 1);
    score += evaluateLine(0, 2, 1, 2, 2, 2);
    score += evaluateLine(0, 0, 1, 1, 2, 2);
    score += evaluateLine(0, 2, 1, 1, 2, 0);
    return score;
  }

  private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
    int score = 0;

    if (board.getGrid()[row1][col1] == maxEmoji) {
      score = 1;
    } else if (board.getGrid()[row1][col1] == minEmoji) {
      score = -1;
    }

    if (board.getGrid()[row2][col2] == maxEmoji) {
      if (score == 1) {
        score = 10;
      } else if (score == -1) {
        return 0;
      } else {
        score = 1;
      }
    } else if (board.getGrid()[row2][col2] == minEmoji) {
      if (score == -1) {
        score = -10;
      } else if (score == 1) {
        return 0;
      } else {
        score = -1;
      }
    }

    if (board.getGrid()[row3][col3] == maxEmoji) {
      if (score > 0) {
        score *= 10;
      } else if (score < 0) {
        return 0;
      } else {
        score = 1;
      }
    } else if (board.getGrid()[row3][col3] == minEmoji) {
      if (score < 0) {
        score *= 10;
      } else if (score > 1) {
        return 0;
      } else {
        score = -1;
      }
    }
    return score;
  }
}
