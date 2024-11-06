package org.tictactoe;

public class Player {
    private int sign;
    private Game game;

    Player(Game game, int sign) {
        this.sign = sign;
        this.game = game;
    }

    public int play() {
        do {
            System.out.print("Input move (1-9): ");
            int pos = App.input.nextInt();
            if (!game.mark(pos - 1)) {
                System.out.println("Invalid position!");
            } else {
                return pos;
            }
        } while (true);
    }

    public int getSign() {
        return sign;
    }

    public Game getGame() {
        return game;
    }
}
