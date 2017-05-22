package nl.sogyo.mancala;

/**
 * Created by ckyoung on 26-Apr-17.
 */
public class Hole extends Field {
    public Hole() {
        setStones(4);
        setOwner(new Player());
        setNeighbour(createNeighbour());
    }

    public Hole(int counter, Field initialField, Player prevOwner) {
        setStones(4);
        setOwner(prevOwner);
        setNeighbour(createNeighbour(counter, initialField, prevOwner));
    }

    //Neighbour will be the 2nd Hole that is instantiated.
    private Field createNeighbour() {
        Field initialField = this;
        Player prevOwner = getOwner();
        return new Hole(2, initialField, prevOwner);
    }

    private Field createNeighbour(int counter, Field initialField, Player prevOwner) {
        if (counter == 6 || counter == 13) {
            return new Kalaha(counter + 1, initialField, prevOwner);
        }
        return new Hole(counter + 1, initialField, prevOwner);
    }

    public void play() {
        if (checkIfMoveAttemptIsValid()) {
            return;
        }
        int stonesToPass = getStones();
        setStones(0);
        this.getNeighbour().takeOneAndPassOn(stonesToPass);
    }

    private boolean checkIfMoveAttemptIsValid() {
        return checkAndHandlePlayingAnEmptyHole() || checkAndHandlePlayingOpponentsHole();
    }

    private boolean checkAndHandlePlayingAnEmptyHole() {
        if (getStones() != 0) return false;
        System.out.println("Empty holes cannot be selected for play.");
        return true;

    }

    private boolean checkAndHandlePlayingOpponentsHole() {
        if (getOwner().isActive()) return false;
        System.out.println("It's not your current turn.");
        return true;
    }

    @Override
    protected void handleEndOfTurn() {
        if (turnIsEligibleForCapture()) {
            captureOpposingHole();
        }
        getOwner().switchTurn();
        super.handleEndOfTurn();
    }

    private boolean turnIsEligibleForCapture() {
        return getStones() == 1 && getOwner().isActive();
    }


    private void captureOpposingHole() {
        Hole myOpposer = (Hole) findMyOpposingHole();
        Kalaha myKalaha = (Kalaha) findMyKalaha();
        if (myOpposer.getStones() != 0) {
            setStones(0);
            myOpposer.giveOurStonesTo(myKalaha);
        }
    }

    @Override
    protected Field findMyOpposingHole() {
        Field opposer = getNeighbour().findMyOpposingHole();
        opposer = opposer.getNeighbour();
        return opposer;
    }

    protected Field findMyKalaha() {
        return getNeighbour().findMyKalaha();
    }

    private void giveOurStonesTo(Kalaha myKalaha) {
        myKalaha.takeMyStones(getStones() + 1);
        setStones(0);
    }
}
