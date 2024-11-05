package org.example;

import java.util.Scanner;

public class App {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to tic-tac-toe!\n");
        System.out.println("1. Player vs Player.");
        System.out.println("2. Player vs Computer.");
        int mode;
        do {
            System.out.print("Which mode you want? ");
            mode = input.nextInt();
            if (mode == 0) {
                return;
            } else if (mode == 1) {
                Game game = new Game();
                Player x = new Player(game, 1), o = new Player(game, 0);
                do {
                    game.printBoard();
                    System.out.println((game.xTurn() ? 'X' : 'O') + "'s turn.");
                    (game.xTurn() ? x : o).play();
                    Game.State state = game.state();
                    if (state == Game.State.TIE)  {
                        game.printBoard();
                        System.out.println("Tie!");
                        return;
                    } else if (state == Game.State.WIN) {
                        game.printBoard();
                        System.out.println((game.xTurn() ? 'O' : 'X') + " wins!");
                        return;
                    }
                } while (true);
            } else if (mode == 2) {
                System.out.println("Not implemented yet :)");
            } else {
                System.out.println("Invalid mode!");
            }
        } while (true);
    }
}
