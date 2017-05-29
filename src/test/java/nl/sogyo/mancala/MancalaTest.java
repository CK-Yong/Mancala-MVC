package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MancalaTest {


    private Hole field1, field2, field3, field4, field5, field6, field8, field9, field10, field11, field12, field13, field15;
    private Kalaha field7, field14;

    @Before
    public void initializeTestVariables() {
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
        field15 = (Hole) field14.getNeighbour();
    }

    @Test
    public void testInitiateWith4Stones() {
        Assert.assertEquals(4, field1.getStones());
    }

    @Test
    public void testCreateNeighbourHas4Stones() {
        Assert.assertEquals(4, field2.getStones());
    }

    @Test
    public void testPassing4StonesFromFirstHole() {
        field1.play();
        Assert.assertEquals(0, field1.getStones()); //Hole 1
        Assert.assertEquals(5, field5.getStones());
        Assert.assertEquals(4, field6.getStones());
    }

    @Test
    public void testIfField7And14AreKalaha() {
        Assert.assertEquals("Kalaha", field7.getClass().getSimpleName());
        Assert.assertEquals("Kalaha", field14.getClass().getSimpleName());
    }

    @Test
    public void testHole1IsLooped() {
        Assert.assertEquals(field1, field15);
    }

    @Test
    public void testIfPlayersAreInstantiated() {
        Player owner = new Player();
        Assert.assertEquals(true, owner.isActive());
        Assert.assertEquals(false, owner.getOpponent().isActive());
    }

    @Test
    public void testIfFields1and7HaveSameOwner() {
        Assert.assertEquals(field1.getOwner(), field7.getOwner());
    }

    @Test
    public void testIfFields7and8HaveDifferentOwners() {
        Assert.assertNotEquals(field7.getOwner(), field8.getOwner());
    }

    @Test
    public void testIfFields8and14HaveSameOwners() {
        Assert.assertEquals(field8.getOwner(), field14.getOwner());
    }

    @Test
    public void testIfPlayersCanSwitchTurn() {
        Player owner = new Player();
        owner.switchTurn();
        Assert.assertEquals(false, owner.isActive());
        Assert.assertEquals(true, owner.getOpponent().isActive());
    }

    @Test
    public void testIfPlayMethodEndsAturnAndSwitchPlayers() {
        field1.play();
        Assert.assertEquals(false, field1.getOwner().isActive());
        Assert.assertEquals(true, field13.getOwner().isActive());
        field8.play();
        Assert.assertEquals(true, field1.getOwner().isActive());
        Assert.assertEquals(false, field13.getOwner().isActive());
    }

    @Test
    public void testIfLandingInKalahaResultsInAnotherTurn() {
        field1.setStones(6);
        field1.play();
        Assert.assertEquals(1, field7.getStones());
        Assert.assertEquals(true, field7.getOwner().isActive());
    }

    @Test
    public void testIfAskingField1ForOpposingHoleReturnsTheRightHole() {
        Field opposingHole = field1.findMyOpposingHole();
        Assert.assertEquals(field13, opposingHole);
    }

    @Test
    public void testIfAskingField9ForOpposingHoleReturnsTheRightHole() {
        Field opposingHole = field9.findMyOpposingHole();
        Assert.assertEquals(field5, opposingHole);
    }

    @Test
    public void testIfPlayMethodResultsInCapture() {
        field1.setStones(1);
        field2.setStones(0);
        field1.play();
        Assert.assertEquals(0, field2.getStones());
        Assert.assertEquals(0, field12.getStones());
        Assert.assertEquals(5, field7.getStones());
        Assert.assertEquals(false, field1.getOwner().isActive());
        Assert.assertEquals(true, field12.getOwner().isActive());
    }

    @Test
    public void testIfAskingField1ForKalahaReturnsField7() {
        Field potentialKalaha = field1.findMyKalaha();
        Assert.assertEquals(potentialKalaha, field7);
    }

    @Test
    public void testIfAskingField8ForKalahaReturnsField14() {
        Field potentialKalaha = field8.findMyKalaha();
        Assert.assertEquals(potentialKalaha, field14);
    }

    @Test
    public void testIfAskingKalahaForKalahaReturnsItself() {
        Field potentialKalaha = field7.findMyKalaha();
        Assert.assertEquals(potentialKalaha, field7);
    }

    @Test
    public void testIfOpposingKalahaDoesntReceiveStonesDuringTurnAndPlayersAreStillSwitched() {
        field1.setStones(13);
        field1.play();
        Assert.assertEquals(0, field1.getStones());
        Assert.assertEquals(0, field14.getStones());
        Assert.assertEquals(0, field13.getStones());
        Assert.assertEquals(7, field7.getStones()); //1 from passing, 5 from capture, 1 from landing on field1.
        Assert.assertEquals(false, field1.getOwner().isActive());
    }

    @Test
    public void testIfLandingInOpponentsHoleDoesntResultInCapture() {
        field1.setStones(7);
        field8.setStones(0);
        field1.play();
        Assert.assertEquals(5, field6.getStones());
        Assert.assertEquals(1, field8.getStones());
        Assert.assertEquals(1, field7.getStones());
        Assert.assertEquals(0, field14.getStones());
        Assert.assertEquals(false, field1.getOwner().isActive());
    }

    @Test
    public void testIfSelectingEmptyHolesCannotBeDone() {
        field1.setStones(0);
        field1.play();
        Assert.assertEquals(0, field1.getStones());
        Assert.assertEquals(true, field1.getOwner().isActive());
    }

    @Test
    public void testIfStonesInActiveFieldsAreCorrectlyCounted() {
        field1.setStones(1);
        field2.setStones(1);
        field3.setStones(1);
        field4.setStones(1);
        field5.setStones(1);
        field6.setStones(1);
        field7.setStones(5);
        field14.setStones(5);

        boolean validMovesLeft = field1.stillLegalMovesLeft(field1);
        Assert.assertEquals(true, validMovesLeft);
    }

    @Test
    public void testIfFindInactiveKalahaReturnsField14() {
        Kalaha potentialKalaha = (Kalaha) field1.findInactiveKalaha();
        Assert.assertEquals(potentialKalaha, field14);
    }

    @Test
    public void testIfHolesAreSweptIntoField7() {
        Field initialField = field1;
        Kalaha receivingKalaha = field7;
        field1.sweepRemainingStones(initialField, receivingKalaha);
        Assert.assertEquals(48, field7.getStones());
    }

    @Test
    public void testIfLastTurnCaptureLeadsToSweepingToField14Kalaha() {
        field1.setStones(0);
        field2.setStones(1);
        field3.setStones(0);
        field4.setStones(0);
        field5.setStones(0);
        field6.setStones(0);
        field2.play();
        field8.play();
        Assert.assertEquals(5, field7.getStones());
        Assert.assertEquals(20, field14.getStones());
    }

    @Test
    public void testIfAllHolesAreSweptToTheOppositeKalahaWhenLandingInOwnKalaha() {
        field1.setStones(0);
        field2.setStones(0);
        field3.setStones(0);
        field4.setStones(0);
        field5.setStones(0);
        field6.setStones(1);
        field6.play();
        Assert.assertEquals(1, field7.getStones());
        Assert.assertEquals(24, field14.getStones());
    }

    @Test
    public void testIfAllHolesAreSweptToTheOtherKalahaWhenLandingOnField14Kalaha() {
        field8.setStones(0);
        field9.setStones(0);
        field10.setStones(0);
        field11.setStones(0);
        field12.setStones(0);
        field13.setStones(1);
        field1.play();
        field13.play();
        Assert.assertEquals(24, field7.getStones());
        Assert.assertEquals(1, field14.getStones());
    }
}

