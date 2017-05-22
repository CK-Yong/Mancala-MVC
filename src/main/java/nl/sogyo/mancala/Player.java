package nl.sogyo.mancala;

/**
 * Created by ckyoung on 26-Apr-17.
 */
public class Player {
    private boolean Active;
    private Player opponent;
    private String name;

    public Player() {
        Active = true;
        name = "Player 1";
        instantiateSecondPlayer();
    }

    private Player(boolean myTurn, Player myOpponent) {
        Active = myTurn;
        name = "Player 2";
        opponent = myOpponent;
    }

    private void instantiateSecondPlayer() {
        boolean opponentStartingState = !Active;
        Player myself = this;
        opponent = new Player(opponentStartingState, myself);
    }

    void switchTurn() {
        Active = !Active;
        opponent.Active = !opponent.Active;
    }

    public boolean isActive(){
        return Active;
    }

    public String getName(){
        return name;
    }

    public Player getOpponent(){
        return opponent;
    }
}
