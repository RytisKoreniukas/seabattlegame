import java.util.*;



public class ShipsRepository {

    private static Scanner scanner = new Scanner(System.in);

    private ArrayList<Ship> ships = new ArrayList <Ship>();

    public ArrayList<Ship> getRandomShipSet() {
        this.generateRandomShipsSet();
        return ships;
    }

    public void generateRandomShipsSet() {
        Coordinate startCoord = generateFirstCoord();
        boolean horizontal = generateOrientation();
        int size = getShipSize();
        Ship ship = new Ship(size, horizontal, startCoord);
        if (this.ships.size() < 10) {
            if (ship.availableToAdd(this.ships)) {
                this.ships.add(ship);
                this.generateRandomShipsSet();
            } else {
                this.generateRandomShipsSet();
            }
        }
    }

    private int getShipSize() {
        if (this.ships.isEmpty()) {
            return 4;
        } else if (this.ships.size() < 3) {
            return 3;
        } else if (this.ships.size() < 6) {
            return 2;
        } else {
            return 1;
        }
    }

    private Coordinate generateFirstCoord() {
        Random ran = new Random();
        int row = ran.nextInt(10);
        int col = ran.nextInt(10);
        return new Coordinate(col, row);
    }

    private boolean generateOrientation() {
        Random ran = new Random();
        return ran.nextInt(2) == 1;
    }

    public static void printShipsPositions(List <Ship> ships) {
        String [][] output = new String[10][10];
        for (int col = 0; col < 10; col ++) {
            for(int row = 0; row < 10; row ++){
                output[col][row] = " . ";
            }
        }


        for (Ship ship: ships) {
            for (Coordinate coordinate : ship.getCoordinates()) {
                output[coordinate.getRow()][coordinate.getCol()] = " o ";
            }
        }

        System.out.println(" A  B  C  D  E  F  G  H  I  J");
        int i = 1;
        for (String[] row : output) {

            for (String field : row) {
                System.out.print(field);
            }
            System.out.println(" " + i);
            i++;
        }
        System.out.println();

    }

    public ArrayList<Ship> getUserShips(int shipsGenerationMode) {
        switch (shipsGenerationMode) {
            case 1:
                this.generateManualShipsSet();
                break;
            default:
                this.getRandomShipSet();
                printShipsPositions(this.ships);
                break;
        }
        return this.ships;
    }

    private void generateManualShipsSet() {
        if (this.ships.size() < 10) {
            int size = getShipSize();
            System.out.println("Lets generate your " + size + " - seated ship!");
            System.out.println();
            Coordinate startCoord = getFirstCoord();
            boolean horizontal = getOrientation();
            Ship ship = new Ship(size, horizontal, startCoord);

            if (ship.availableToAdd(this.ships)) {
                this.ships.add(ship);
                printShipsPositions(ships);
                this.generateManualShipsSet();
            } else {
                System.out.println("Oh no! The ship cannot be added! Lets try again!");
                this.generateManualShipsSet();
            }
        } else {
            System.out.println("All your ships have been added! Lets play!");
        }
    }

    private Coordinate getFirstCoord() {
        return new Coordinate(getCol(), getRow());
    }

    private int getCol() {
        int col;
        System.out.println("Say COLUMN LETTER of your ship's first coordinate: ");
        try {
            col = App.convertLetterColToArrayCol(scanner.nextLine());
        } catch (InputMismatchException e) {
            System.out.println("Please use letters from A to J for insert");
            col = getCol();
        }

        return col;
    }

    private int getRow() {
        int row;
        System.out.println("Say ROW NUMBER of your ship's first coordinate: ");
        try {
            row = App.convertUserRowToArrayRow(Integer.parseInt(scanner.nextLine()));
        } catch (InputMismatchException e) {
            System.out.println("Please use numbers from 1 to 10 for insert");
            row = getRow();
        } catch (NumberFormatException e) {
            System.out.println("Please use numbers from 1 to 10 for insert");
            row = getRow();
        }

        return row;
    }

    private boolean getOrientation() {
        return readOrientation() == 1 ? true : false;
    }

    private int readOrientation() {
        int horizontal;
        System.out.println("Say ship orientation:" +
                "\n 1 - horizontal" +
                "\n 2 - vertical");

       try {
           horizontal = Integer.parseInt(scanner.nextLine());
           if (horizontal < 1 || horizontal > 2) {
               System.out.println("Whats wrong, captain? Here, have your default horizontal orientation!");
               horizontal = 1;
           }
       } catch (InputMismatchException e) {
           System.out.println("Please use numbers from 1 to 2 for setting ship orientation");
           horizontal = readOrientation();
       } catch (NumberFormatException e) {
           System.out.println("Please use numbers from 1 to 2 for setting ship orientation");
           horizontal = readOrientation();
       }

       return horizontal;
    }
}
