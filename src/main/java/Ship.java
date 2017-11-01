import java.util.ArrayList;

public class Ship {

    private Coordinate[] coordinates;
    private int shotParts;
    private int size;
    private boolean horizontal;


    public Ship (int size, boolean horizontal, Coordinate startCoord) {
        this.shotParts = 0;
        this.size = size;
        this.horizontal = horizontal;
        this.coordinates = new Coordinate[size];

        for (int i = 0; i < size; i++) {
            int col = startCoord.getCol();
            int row = startCoord.getRow();

            if (horizontal) {
                col += i;
            } else {
                row += i;
            }

            coordinates[i] = new Coordinate(col, row);
        }
    }

    public void setShotParts(int shotParts) {
        this.shotParts = shotParts;
    }

    public int getSize() {
        return this.size;
    }

    public int getShotParts() {
        return shotParts;
    }


    public Coordinate[] getCoordinates() {
        return coordinates;
    }

    public ArrayList<Coordinate> getRequiredArea() {
        ArrayList<Coordinate> requiredArea = new ArrayList<Coordinate>();

        for (Coordinate shipCoordinate : getCoordinates()) {
            for (Coordinate boundCoordinate : shipCoordinate.getBounds()){
                if (!requiredArea.contains(boundCoordinate)){
                    requiredArea.add(boundCoordinate);
                }
            }
        }

        return requiredArea;
    }

    public boolean availableToAdd(ArrayList<Ship> ships) {
        ArrayList<Coordinate> requiredArea = this.getRequiredArea();
        Coordinate[] coordinates1 = requiredArea.toArray(new Coordinate[requiredArea.size()]);
        if (this.containsIllegalCoordinates()) {
            return false;
        }

        for (Ship addedShip: ships) {
            Coordinate[] coordinates2 = addedShip.getCoordinates();
            if (Coordinate.doesOverlap(coordinates1, coordinates2)) {
                return false;
            }
        }
        return true;
    }

    public boolean containsIllegalCoordinates() {
        for (Coordinate coordinate : this.getCoordinates()) {
            if (coordinate.getRow() < 0 || coordinate.getRow() > 9 || coordinate.getCol() < 0 || coordinate.getCol() > 9) {
                return true;
            }
        }
        return false;
    }


}
