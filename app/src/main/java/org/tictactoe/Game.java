package org.tictactoe;

import java.util.Arrays;

public class Game {
    private int[] board;
    private boolean turn;

    public static enum State {
        STILL, TIE, WIN
    };

    public static final char[] signs = { 'O', 'X' };

    Game() {
        turn = true;
        board = new int[9];
        Arrays.fill(board, -1);
    }

    Game(Game cpy) {
        turn = cpy.turn;
        board = Arrays.copyOf(cpy.board, 9);
    }

    public boolean mark(int pos) {
        if (pos < 0 || pos > 8 || board[pos] != -1) {
            return false;
        }
        board[pos] = turn ? 1 : 0;
        turn = !turn;
        return true;
    }

    public boolean occupied(int pos) {
        return (pos < 0 || pos > 8 || board[pos] != -1);
    }

    public void printBoard() {
        System.out.println(" --- --- --- ");
        for (int i = 0; i <= 6; i += 3) {
            for (int j = i; j == i || j % 3 != 0; j++) {
                System.out.print("| " + sign(board[j]) + ' ');
            }
            System.out.println("|\n --- --- --- ");
        }
        System.out.println();
    }

    public char sign(int s) {
        return (s < 0 || s > 1 ? ' ' : signs[s]);
    }

    public State state() {
        if ((board[0] != -1 && board[0] == board[4] && board[0] == board[8])
                || (board[2] != -1 && board[2] == board[4] && board[2] == board[6])) {
            return State.WIN;
        }
        for (int i = 0; i <= 6; i += 3) {
            if (board[i] != -1 && board[i] == board[i + 1] && board[i] == board[i + 2]) {
                return State.WIN;
            }
        }
        for (int i = 0; i <= 2; i++) {
            if (board[i] != -1 && board[i] == board[i + 3] && board[i] == board[i + 6]) {
                return State.WIN;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (board[i] == -1) {
                return State.STILL;
            }
        }
        return State.TIE;
    }

    public boolean xTurn() {
        return turn;
    }
}
