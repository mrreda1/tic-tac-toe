package org.tictactoe;

import java.util.Scanner;

public class App {
    public static final Scanner input = new Scanner(System.in);
    // public static final Reader input = new Reader();

    public static void main(String[] args) {
        System.out.println("\033[1mWhich mode you want?\033[0m");
        System.out.println("1. Player vs Player.");
        System.out.print("2. Player vs Computer.\n\n~> ");
        int mode = input.nextInt();

        if (mode == 1) {
            PlayerVsPlayer();
        } else if (mode == 2) {
            PlayerVsComputer();
        } else if (mode != 0) {
            System.out.println("Invalid mode!");
        }
    }

    private static void PlayerVsPlayer() {
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

    }

    private static void PlayerVsComputer() {
        Game game = new Game();
        int role = 1;
        Player player, computer;
        System.out.print("\033[H\033[2J");
        System.out.println("1. Play as X.");
        System.out.print("2. Play as O.\n\n~> ");
        do {
            role = input.nextInt() - 1;
            if (role < 0 || role > 1) {
                System.out.print("Invalid option!\n~> ");
            } else {
                computer = new Computer(game, role);
                player = new Player(game, (role + 1) % 2);
                break;
            }
        } while (true);
        do {
            System.out.print("\033[H\033[2J");
            if ((game.xTurn() ? 1 : 0) == player.getSign()) {
                game.printBoard();
                player.play();
            } else {
                computer.play();
            }
            Game.State state = game.state();
            if (state == Game.State.TIE) {
                System.out.print("\033[H\033[2J");
                game.printBoard();
                System.out.println("Tie!");
                return;
            } else if (state == Game.State.WIN) {
                System.out.print("\033[H\033[2J");
                game.printBoard();
                if ((game.xTurn() ? 1 : 0) == player.getSign()) {
                    System.out.println("As expected, computer wins.");
                } else {
                    System.out.println("You are a legend!");
                }
                return;
            }
        } while (true);

    }
}
