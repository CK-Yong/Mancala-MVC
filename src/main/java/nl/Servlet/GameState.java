package nl.Servlet;

import nl.sogyo.mancala.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckyoung on 23-May-17.
 */
public class GameState implements Serializable {
    private Hole field1, field2, field3, field4, field5, field6, field8, field9, field10, field11, field12, field13;
    private Kalaha field7, field14;
    private Field[] gameBoard;
    private List<Integer> stonesInFieldList;
    private boolean gameOver;
    private String activePlayer;
    private String winner;

    public GameState() {
        gameBoard = initializeGameBoard();
    }

    private Field[] initializeGameBoard() {
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

    public int getStonesOfHole(int requestedField) {
        return gameBoard[requestedField - 1].getStones();
    }

    public GameState playHole(int input) {
        Hole holeToPlay = (Hole) gameBoard[input - 1];
        holeToPlay.play();
        return this;
    }

    public String getActivePlayer() {
        Player firstPlayer = field1.getOwner();
        activePlayer = firstPlayer.isActive() ? firstPlayer.getName() : firstPlayer.getOpponent().getName();
        return activePlayer;
    }

    public boolean isGameOver() {
        gameOver = field1.isGameOver();
        return gameOver;
    }

    public String getWinner() {
        winner = field1.getWinner().getName();
        if (winner == null) {
            return "Game is tied";
        }
        return winner + " wins!";
    }

    public List getStonesInFieldList() {
        stonesInFieldList = new ArrayList<Integer>();
        for (Field field : gameBoard) {
            stonesInFieldList.add(field.getStones());
        }
        return stonesInFieldList;
    }
}
