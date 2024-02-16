package ui;

import java.util.Scanner;
import model.Player;
import model.Server;

// Description: Critical Ops Matchmaking system console based interface
public class CriticalOpsGame {
    private Server sv;
    private Scanner input;

    // EFFECTS: runs the matchmaking application
    public CriticalOpsGame() {
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGame() {
        boolean keepGoing = true;
        String command = null;

        sv = new Server();
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        while (keepGoing) {
            displayMenu();
            command = input.next();

            if (command.equals("8")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThank you for playing and have a nice day!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            createPlayer();
        } else if (command.equals("2")) {
            viewPlayers();
        } else if (command.equals("3")) {
            matchmake();
        } else if (command.equals("4")) {
            claimRewards();
        } else if (command.equals("5")) {
            viewHistory();
        } else if (command.equals("6")) {
            reportPlayer();
        } else if (command.equals("7")) {
            viewSkins();
        } else {
            System.out.println("Invalid selection. Please try again.");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to the Critical Ops matchmaking system!");
        System.out.println("Please select one of the options below:");
        System.out.println("\n1 -> create player");
        System.out.println("2 -> view all players");
        System.out.println("3 -> queue matchmaking");
        System.out.println("4 -> claim rewards");
        System.out.println("5 -> view match history");
        System.out.println("6 -> report player");
        System.out.println("7 -> view inventory");
        System.out.println("8 -> quit");
    }

    // MODIFIES: this
    // EFFECTS: creates a player with the given name and game mode
    private void createPlayer() {
        System.out.println("Please enter a non-empty player name:");
        String name = input.next();
        System.out.println("Please select one of the game modes below:");
        System.out.println("\n1 -> 1v1");
        System.out.println("2 -> Defuse");
        System.out.println("3 -> GunGame");
        String command = input.next();
        Player player;
        if (command.equals("1")) {
            player = new Player(name, "1v1");
            sv.addPlayer(player);
            System.out.println("Player created successfully!");
        } else if (command.equals("2")) {
            player = new Player(name, "Defuse");
            sv.addPlayer(player);
            System.out.println("Player created successfully!");
        } else if (command.equals("3")) {
            player = new Player(name, "GunGame");
            sv.addPlayer(player);
            System.out.println("Player created successfully!");
        } else {
            System.out.println("Invalid selection. Please try again.");
        }
    }

    // EFFECTS: displays all current players in the server
    private void viewPlayers() {
        System.out.println("name, game mode, rank, banned\n");
        for (Player player : sv.getPlayers()) {
            System.out.println(player.getName() + ", " + player.getGameMode() + ", " + player.getDisplayRank()
                    + ", " + player.isBanned());
        }
    }

    // MODIFIES: this
    // EFFECTS: finds a match for the given player if possible
    private void matchmake() {
        System.out.println("Please enter the name of the player you would like to start matchmaking for:");
        String name = input.next();
        if (sv.findPlayer(name) == null) {
            System.out.println("Player does not exist in the server. Please try again.");
        } else {
            System.out.println("Please choose the outcome of the matchmaking you would like the player to have:");
            System.out.println("\n1 -> win");
            System.out.println("2 -> lose");
            String command = input.next();
            if (command.equals("1")) {
                System.out.println(sv.matchmaking((sv.findPlayer(name)), true));
            } else if (command.equals("2")) {
                System.out.println(sv.matchmaking((sv.findPlayer(name)), false));
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: claims rewards for the given player if applicable
    private void claimRewards() {
        System.out.println("Please enter the name of the player you would like to claim rewards for:");
        String name = input.next();
        if (sv.findPlayer(name) != null) {
            System.out.println(sv.findPlayer(name).claimSkins());
        } else {
            System.out.println("Player does not exist in the server. Please try again.");
        }
    }

    // EFFECTS: displays the given player's match history
    private void viewHistory() {
        System.out.println("Please enter the name of the player you would like to view the history of:");
        String name = input.next();
        if (sv.findPlayer(name) != null) {
            System.out.println(sv.findPlayer(name).getHistory());
        } else {
            System.out.println("Player does not exist in the server. Please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: reports the player specified
    private void reportPlayer() {
        System.out.println("Please enter the name of the player you would like to report:");
        String name = input.next();
        if (sv.findPlayer(name) != null) {
            System.out.println(sv.findPlayer(name).handleReport());
        } else {
            System.out.println("Player does not exist in the server. Please try again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the given player's skins in their inventory
    private void viewSkins() {
        System.out.println("Please enter the name of the player you would like to view the inventory of:");
        String name = input.next();
        if (sv.findPlayer(name) != null) {
            System.out.println(sv.findPlayer(name).getSkins());
        } else {
            System.out.println("Player does not exist in the server. Please try again.");
        }
    }
}