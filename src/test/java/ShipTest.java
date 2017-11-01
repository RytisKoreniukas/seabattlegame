import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShipTest {

    @Test
    public void testShipCoordinatesAfterConstructingSmallShip() throws Exception {
        Ship ship = new Ship(1, true, new Coordinate(0,0));
        Coordinate[] expected = {new Coordinate(0,0)};
        Assertions.assertArrayEquals(expected, ship.getCoordinates());
    }

    @Test
    public void testShipCoordinatesAfterConstructingMediumShip() throws Exception {
        Ship ship = new Ship(2, true, new Coordinate(0,0));
        Coordinate[] expected = {new Coordinate(0,0), new Coordinate(1,0)};
        Assertions.assertArrayEquals(expected, ship.getCoordinates());
    }

    @Test
    public void testShipCoordinatesAfterConstructingLargeShip() throws Exception {
        Ship ship = new Ship(3, false, new Coordinate(1,1));
        Coordinate[] expected = {
                new Coordinate(1,1),
                new Coordinate(1,2),
                new Coordinate(1,3)
        };
        Assertions.assertArrayEquals(expected, ship.getCoordinates());
    }
}