package nl.sogyo.mancala;

/**
 * Created by ckyoung on 26-Apr-17.
 */
public class Kalaha extends Field {
    public Kalaha(int counter, Field initialField, Player prevOwner) {
        setOwner(prevOwner);
        setNeighbour(createNeighbour(counter, initialField));
    }

    private Field createNeighbour(int counter, Field initialField) {
        Player prevOwner = getOwner().getOpponent();
        return counter == 14 ? initialField : new Hole(counter + 1, initialField, prevOwner);
    }

    @Override
    protected void takeOneAndPassOn(int stonesLeft) {
        if (getOwner().isActive()) super.takeOneAndPassOn(stonesLeft);
        else getNeighbour().takeOneAndPassOn(stonesLeft);
    }

    @Override
    protected Field findInactiveKalaha() {
        if (!getOwner().isActive()) return this;
        return getNeighbour().findInactiveKalaha();
    }

    @Override
    protected Field findMyKalaha() {
        return this;
    }

    @Override
    protected void sweepRemainingStones(Field initialField, Kalaha receivingKalaha) {
        if (!getNeighbour().equals(initialField)) getNeighbour().sweepRemainingStones(initialField, receivingKalaha);
    }

    @Override
    protected boolean stillLegalMovesLeft(Field initialField) {
        return !getNeighbour().equals(initialField) && getNeighbour().stillLegalMovesLeft(initialField);
    }

    void takeMyStones(int stonesReceived) {
        setStones(getStones()+stonesReceived);
    }

    @Override
    protected Field findMyOpposingHole() {
        return this;
    }
}
