package org.example;

import java.util.Scanner;

public class App {
    public static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\033[1mWhich mode you want?\033[0m");
        System.out.println("1. Player vs Player.");
        System.out.print("2. Player vs Computer.\n\n~> ");
        int mode = input.nextInt();

        if (mode == 1) {
            Game game = new Game();
            Player x = new Player(game, 1), o = new Player(game, 0);
            do {
                System.out.print("\033[H\033[2J");
                game.printBoard();
                System.out.println((game.xTurn() ? 'X' : 'O') + "'s turn.");
                (game.xTurn() ? x : o).play();
                Game.State state = game.state();
                if (state == Game.State.TIE) {
                    System.out.print("\033[H\033[2J");
                    game.printBoard();
                    System.out.println("Tie!");
                    return;
                } else if (state == Game.State.WIN) {
                    System.out.print("\033[H\033[2J");
                    game.printBoard();
                    System.out.println((game.xTurn() ? 'O' : 'X') + " wins!");
                    return;
                }
            } while (true);
        } else if (mode == 2) {
            System.out.println("Not implemented yet :)");
        } else if (mode != 0) {
            System.out.println("Invalid mode!");
        }
    }
}
