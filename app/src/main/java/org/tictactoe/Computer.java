package org.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Computer extends Player {
    private int choice;

    Computer(Game game, int sign) {
        super(game, sign);
    }

    @Override
    public int play() {
        minimax(getGame(), 0);
        getGame().mark(choice);
        return choice;
    }

    private int minimax(Game game, int depth) {
        if (game.state() != Game.State.STILL) {
            return score(game, depth);
        }
        depth++;
        ArrayList<int[]> scores = new ArrayList<int[]>();
        for (int i = 0; i < 9; i++) {
            Game cpy = new Game(game);
            if (cpy.mark(i)) {
                scores.addLast(new int[] { minimax(cpy, depth), i });
            }
        }
        Collections.sort(scores, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });
        if ((game.xTurn() ? 1 : 0) == getSign()) {
            choice = scores.getLast()[1];
            return scores.getLast()[0];
        } else {
            choice = scores.getFirst()[1];
            return scores.getFirst()[0];
        }
    }

    private int score(Game game, int depth) {
        if (game.state() != Game.State.WIN) {
            return 0;
        } else if ((game.xTurn() ? 1 : 0) == getSign()) {
            return depth - 10;
        } else {
            return 10 - depth;
        }
    }
}
