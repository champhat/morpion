package morpion;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final TicTacToe game = new TicTacToe();
        Player player = Player.FIRST;
        final HashMap<Player, String> players = initPlayers();
        System.out.println(game);

        while (true) {
            System.out.println("Joueur : " + players.get(player) + " / Veuillez saisir un des chiffres [1-9] :");
            try {
                final int playerInput = scanInput();
                game.processInput(player, playerInput);
                System.out.println(game);
                if (game.checkWin()) {
                    System.out.println("Le joueur " + players.get(player) + " a gagné la partie ! :");
                    System.out.println(game);
                    break;
                }
                if (game.checkDraw()) {
                    System.out.println("Match nul. Personne n'a gagné !");
                    System.out.println(game);
                    break;
                }
                player = nextPlayer(player);
            } catch (TicTacToeInvalidInputException e) {
                System.out.println("Le nombre saisi doit être entre 1 et 9");
            } catch (Exception e) {
                System.out.println("Un nombre entier doit être saisi");
            }
        }
    }

    private static HashMap<Player, String> initPlayers() {
        HashMap<Player, String> players = new HashMap<Player, String>();
        final Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Nom du joueur 1 :");
            players.put(Player.FIRST, scanner.nextLine());
        } while (players.get(Player.FIRST).equals(StringConstant.BLANK));
        do {
            System.out.println("Nom du joueur 2 :");
            players.put(Player.SECOND, scanner.nextLine());
        } while (players.get(Player.SECOND).equals(StringConstant.BLANK));
        return players;
    }

    private static Integer scanInput() throws TicTacToeInvalidInputException {
        final Scanner scanner = new Scanner(System.in);
        final String input = scanner.nextLine();
        if (input.equals("exit") || input.equals("quit")) {
            System.out.println("Vous quittez la partie");
            System.exit(0);
        }
        final int inputInt = Integer.parseInt(input);
        if (inputInt < 1 || inputInt > 9) throw new TicTacToeInvalidInputException("Le chiffre doit être entre 1 et 9");
        return inputInt;
    }

    private static Player nextPlayer(Player player) {
        if (player.equals(Player.FIRST)) {
            return Player.SECOND;
        } else {
            return Player.FIRST;
        }
    }
}