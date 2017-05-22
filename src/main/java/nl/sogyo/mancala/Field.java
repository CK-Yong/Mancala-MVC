package nl.sogyo.mancala;

/**
 * Created by ckyoung on 26-Apr-17.
 */
public abstract class Field {
    private int stones;
    private Field neighbour;
    private Player owner;

    protected void takeOneAndPassOn(int stonesLeft) {
        stones++;
        stonesLeft--;
        if (stonesLeft > 0) neighbour.takeOneAndPassOn(stonesLeft);
        else handleEndOfTurn();
    }

    protected void handleEndOfTurn() {
        Field initialField = this;
        if (!stillLegalMovesLeft(initialField)) endGame(initialField);
    }

    protected boolean stillLegalMovesLeft(Field initialField) {
        return (getOwner().isActive() && (stones > 0)) ||
                (!neighbour.equals(initialField) && neighbour.stillLegalMovesLeft(initialField));
    }

    private void endGame(Field initialField) {
        Kalaha receivingKalaha = (Kalaha) findInactiveKalaha();
        sweepRemainingStones(initialField, receivingKalaha);
        Player winner = determineWinner();
        printWinner(winner);
    }

    protected Field findInactiveKalaha() {
        return neighbour.findInactiveKalaha();
    }

    protected void sweepRemainingStones(Field initialField, Kalaha receivingKalaha) {
        receivingKalaha.takeMyStones(stones);
        stones = 0;
        if (!neighbour.equals(initialField)) neighbour.sweepRemainingStones(initialField, receivingKalaha);
    }

    Player determineWinner() {
        Player winner = null;
        Kalaha firstKalaha = (Kalaha) findMyKalaha();
        Kalaha secondKalaha = (Kalaha) firstKalaha.getNeighbour().findMyKalaha();
        if (firstKalaha.getStones() > secondKalaha.getStones()) winner = firstKalaha.getOwner();
        else if (firstKalaha.getStones() < secondKalaha.getStones()) winner = secondKalaha.getOwner();
        return winner;
    }

    private void printWinner(Player winner) {
        System.out.println(winner == null ? "Game has been tied. No winner found." : winner.getName() + " has won the game.");

    }

    abstract protected Field findMyKalaha();

    abstract protected Field findMyOpposingHole();

    public int getStones() {
        return stones;
    }

    public void setStones(int number) {
        if (number >= 0) stones = number;
    }

    public Field getNeighbour() {
        return neighbour;
    }

    public void setNeighbour(Field setNeighbour) {
        if (neighbour == null) neighbour = setNeighbour;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player setOwner) {
        if (owner == null) owner = setOwner;
    }

}
