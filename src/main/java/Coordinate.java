import java.util.ArrayList;

public class Coordinate {

    private int col;
    private int row;

    public Coordinate(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public ArrayList<Coordinate> getBounds() {
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();

        int col = this.getCol();
        int row = this.getRow();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                coordinates.add(new Coordinate(col + j, row + i));
            }
        }

        return coordinates;
    }

    public static boolean doesOverlap(Coordinate[] firstGroup, Coordinate[] secondGroup) {
        for (Coordinate firstListCoordinate : firstGroup) {
            for (Coordinate secondListCoordinate : secondGroup) {
                if (firstListCoordinate.equals(secondListCoordinate)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        Coordinate coordinate = (Coordinate) obj;
        return (this.col == coordinate.getCol() && this.row == coordinate.getRow());
    }
}
