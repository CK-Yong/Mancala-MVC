package nl.sogyo.mancala;

import java.util.Scanner;

/**
 * Created by ckyoung on 01-May-17.
 */
public class TotallyUnnecessaryGUI {

    private Hole field1, field2, field3, field4, field5, field6, field8, field9, field10, field11, field12, field13;
    private Kalaha field7, field14;
    private Field[] gameBoard;

    public void playGame() {
        System.out.println("Mancala game: ");
        Scanner playerInput = new Scanner(System.in);
        gameBoard = initializeGame();

        while (true) {
            printBoard();
            askPlayerToMakeATurn();
            try {
                int input = Integer.parseInt(playerInput.nextLine());
                playField(input);
            } catch (Exception e) {
                System.out.println("Error. Please input a valid number.");
            }
        }
    }

    private Field[] initializeGame() {
        field1 = new Hole();
        field2 = (Hole) field1.getNeighbour();
        field3 = (Hole) field2.getNeighbour();
        field4 = (Hole) field3.getNeighbour();
        field5 = (Hole) field4.getNeighbour();
        field6 = (Hole) field5.getNeighbour();
        field7 = (Kalaha) field6.getNeighbour();
        field8 = (Hole) field7.getNeighbour();
        field9 = (Hole) field8.getNeighbour();
        field10 = (Hole) field9.getNeighbour();
        field11 = (Hole) field10.getNeighbour();
        field12 = (Hole) field11.getNeighbour();
        field13 = (Hole) field12.getNeighbour();
        field14 = (Kalaha) field13.getNeighbour();
        Field[] gameBoard = {field1, field2, field3, field4, field5, field6, field7, field8, field9, field10, field11, field12, field13, field14};
        return gameBoard;
    }

    private void printBoard() {
        printSecondPlayerBoard();
        printFirstPlayerBoard();
    }

    private void printSecondPlayerBoard() {
        for (int i = gameBoard.length - 1; i > gameBoard.length / 2 - 1; i--) {
            if (i == 12) {
                System.out.print("- ");
            }
            System.out.print(gameBoard[i].getStones());
            System.out.print(" ");
        }
        System.out.print("     " + gameBoard[8].getOwner().getName());
        System.out.print("\n");
    }

    private void printFirstPlayerBoard() {
        System.out.print(String.valueOf(gameBoard[13].getStones()).replaceAll(".", " ") + "   ");
        for (int i = 0; i < gameBoard.length / 2; i++) {
            if (i == 6) {
                System.out.print("- ");
            }
            System.out.print(gameBoard[i].getStones());
            System.out.print(" ");
        }
        System.out.print(" " + gameBoard[0].getOwner().getName());
        System.out.print("\n");
    }

    private void askPlayerToMakeATurn() {
        if (field1.getOwner().isActive()) {
            System.out.print(field1.getOwner().getName());
        } else {
            System.out.print(field8.getOwner().getName());
        }
        System.out.print(", make your move by typing a number between 1 to 6, followed by pressing 'Enter/Return'.\n");
    }

    private void playField(int input) {
        if (input > 0 && input < 7) {
            makeValidMoveForPlayer(input);
        } else {
            System.out.println("Not a valid move. Numbers should be from 1 to 6.");
        }
    }

    private void makeValidMoveForPlayer(int input) {
        if (field1.getOwner().isActive()) {
            makeMoveForPlayer1(input);
        } else {
            makeMoveForPlayer2(input);
        }
    }

    private void makeMoveForPlayer1(int input) {
        Hole selectedHole;
        selectedHole = (Hole) gameBoard[input - 1];
        selectedHole.play();
    }

    private void makeMoveForPlayer2(int input) {
        Hole selectedHole;
        selectedHole = (Hole) gameBoard[input + 6];
        selectedHole.play();
    }
}
