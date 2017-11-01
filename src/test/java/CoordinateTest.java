
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CoordinateTest {

    @Test
    public void compareEqualCoordinates() {
        Coordinate coordinate1 = new Coordinate(1,1);
        Coordinate coordinate2 = new Coordinate(1,1);

        Assertions.assertTrue(coordinate1.equals(coordinate2));
    }

    @Test
    public void compareDifferentlCoordinates() {
        Coordinate coordinate1 = new Coordinate(1,1);
        Coordinate coordinate2 = new Coordinate(1,2);

        Assertions.assertFalse(coordinate1.equals(coordinate2));
    }
}