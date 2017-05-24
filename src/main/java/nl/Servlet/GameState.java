package nl.Servlet;
import nl.sogyo.mancala.*;

/**
 * Created by ckyoung on 23-May-17.
 */
public class GameState {
    private Hole field1, field2, field3, field4, field5, field6, field8, field9, field10, field11, field12, field13;
    private Kalaha field7, field14;
    private Field[] gameBoard;

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

    public int getStonesOfHole(int requestedField){
        return gameBoard[requestedField-1].getStones();
    }

    public GameState playHole(int input){
        Hole holeToPlay = (Hole) gameBoard[input-1];
        holeToPlay.play();
        return this;
    }

    public Player getActivePlayer(){
        if(field1.getOwner().isActive()){
            return field1.getOwner();
        }
        return field8.getOwner();
    }

    public boolean isGameOver(){
        return field1.isGameOver();
    }

    public Player getWinner(){
        return field1.getWinner();
    }
}
