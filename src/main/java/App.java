import java.util.*;


/**
 * Simple ship battle game created by Rytis Koreniukas and Kamilė Medzevičiūtė
 * Vilnius Coding School JAVA Bootcamp 2017.10
 */


public class App {

    private static Scanner scanner = new Scanner(System.in);
    private static Random  rnd = new Random();
    private static UserClass comp = new UserClass("Computer");


    public static void main(String[] args) {

        int menuChoise = -1;
        boolean quit = false;

        printMainMenu();

        while (!quit) {

            boolean proceed = true;
            do {
                try {
                    menuChoise = scanner.nextInt();
                    proceed = false;
                } catch (InputMismatchException e) {
                    scanner.next();
                    System.out.println("Please use numbers for insert");
                }
            } while (proceed);

            switch (menuChoise) {
                case 1:
                    launchDestructionGame();
                    break;
                case 2:
                    launchPlayerVsComputerGame();
                    break;
                case 0:
                    quit = true;
                    System.out.println("Bye !");
                    break;
                default :
                    quit = true;
                    break;

            }
        }
    }

    private static void printMainMenu() {
        System.out.println(
                "Welcome to seabattle game, please choose gameplay mode:" +
                        "\n 1 - Player bombards computer" +
                        "\n 2 - Player against computer" +
                        "\n 0 - Quit game");

    }

    private static void printPlayerVsComputerMenu() {
        System.out.println("Please choose ships generation mode:" +
                "\n 1 – Generate ships manually" +
                "\n 2 - Generate ships automatically");
    }

    public static void launchDestructionGame() {
        scanner.nextLine();
        System.out.println("Welcome to fast enemy destruction game!");
        System.out.println("Please type your Username");


        String userName = scanner.nextLine();
        UserClass userClass1 = new UserClass(userName);


        Table table = new Table();
        ShipsRepository computersShips = new ShipsRepository();

        List<Ship> compShipList = computersShips.getRandomShipSet();

        int destructionMenuChoise = -1;



        System.out.println("Type: " +
                "\n 1 - to begin game" +
                "\n 0 - to quit");

        boolean proceeder = true;
        do {
            try {
                destructionMenuChoise = scanner.nextInt();
                proceeder = false;
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("It ain't number you try to write hear, Captain");
            }
        } while (proceeder);

        switch (destructionMenuChoise) {
            case 1:
                playDestructionGame(compShipList, userClass1, table);
                break;

            case 0:
                System.out.println("GAME OVER");
                break;
            default:
                break;


        }

    }

    public static void launchPlayerVsComputerGame() {
        scanner.nextLine();
        System.out.println("Welcome to human vs machine game!");
        System.out.println("Please type your Username");


        String userName = scanner.nextLine();
        UserClass userClass2 = new UserClass(userName);

        Table compTable = new Table();
        ShipsRepository computersShips = new ShipsRepository();

        List<Ship> compShipSet = computersShips.getRandomShipSet();

        ShipsRepository userShips = new ShipsRepository();
        ArrayList<Ship> userShipsSet = new ArrayList<Ship>();
        Table userTable = new Table();


        printPlayerVsComputerMenu();
        int shipsGenerationMode = -1;

        boolean proceedlignig = true;
        do {
            try {
                shipsGenerationMode= scanner.nextInt();
                proceedlignig = false;
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Numbers numbers numbers must be here");
            }
        } while (proceedlignig);


        userShipsSet = userShips.getUserShips(shipsGenerationMode);
        boolean proceedFurther = true;


        while (proceedFurther) {
            System.out.println("Type: " +
                    "\n 1 - to begin game" +
                    "\n 0 - to quit");

            int playerVsComputerMenuChoise = -1;

            boolean proceedering = true;
            do {
                try {
                    playerVsComputerMenuChoise = scanner.nextInt();
                    proceedering = false;
                } catch (InputMismatchException e) {
                    scanner.next();
                    System.out.println("Not number, Captain");
                }
            } while (proceedering);


            switch (playerVsComputerMenuChoise) {
                case 1:
                    playPlayerVsComputerGame(compShipSet, userShipsSet, userClass2, userTable, compTable);
                    proceedFurther = false;
                    break;

                case 0:
                    System.out.println("GAME OVER");
                    proceedFurther = false;
                    break;

                default:
                    System.out.println("Bad input, choose from 0 or 1");
                    break;
            }

        }
    }

    public static boolean playDestructionGame(List<Ship> compShips, UserClass user, Table table) {
        int killedShips = 0;
        int shotsMade = 0;
        System.out.println(" Hey Ho, Captain " + user.getName() + " lets begin the bloodiest battle of seven seas !!!!");
        System.out.println();


        while (killedShips < 10) {
            shotsMade++;
            scanner.nextLine();
            String col;
            System.out.println("Say column letter:");
            col = scanner.nextLine();

            if (col.equals("ShowEnemyShips")) {
                ShipsRepository.printShipsPositions(compShips);
                col = "A";
            }


            int rowe = 0;
            System.out.println("Say row number:");


            boolean proceedWithRowInts = true;
            do {
                try {
                    rowe = scanner.nextInt();
                    proceedWithRowInts = false;
                } catch (InputMismatchException e) {
                    scanner.next();
                    System.out.println("ARgHHH, input number, you bobbling dolphin !!!");
                }
            } while (proceedWithRowInts);


            if (rowe == 666) {
                killedShips = 10;
                ShipsRepository.printShipsPositions(compShips);
                System.out.println("Hey Cheater, you called total destruction wave!");
                System.out.println();
                System.out.println("Hail Total Sealord " + user.getName() + " AllMighty");
                System.out.println();
                System.out.println("GAME OVER");
                System.exit(0);
            }

            Coordinate gues = convertUserInputToCoordinate(col, rowe);

            killedShips += shoot(compShips, gues, user, table);
            table.printTable();
            System.out.println("Killed: " + killedShips);
            System.out.println("Shots made: " + shotsMade);
            System.out.println("+++++++++++++++++++++++++++++++");
            System.out.println();

        }
        if (killedShips == 10) {
            System.out.println("Enemy flotile destroyed hail overkill sealord" + user.getName() + "!!!");
            System.out.println("GAME OVER");
            return true;

        }
        return false;
    }

    public static boolean playPlayerVsComputerGame (List<Ship> compShips, List<Ship> userShips, UserClass user,Table userTable, Table compTable){
        int killedUserShips = 0;
        int killedComputerShips = 0;
        int shotsMade = 0;
        System.out.println("Battle starts, Captain " + user.getName() + ", computer shoots first !!!!");
        System.out.println();


        while (killedUserShips < 10&& killedComputerShips < 10) {
            killedUserShips += shoot(userShips,randomCoordinateGenerator(),comp,userTable);
            userTable.printTable();

            shotsMade++;
            scanner.nextLine();
            String col;
            System.out.println("Say column letter:");
            col = scanner.nextLine();

            if (col.equals("ShowEnemyShips")) {
                ShipsRepository.printShipsPositions(compShips);
                col = "A";
            }


            int rowe = 0;
            System.out.println("Say row number:");


            boolean proceedWithRowInts = true;
            do {
                try {
                    rowe = scanner.nextInt();
                    proceedWithRowInts = false;
                } catch (InputMismatchException e) {
                    scanner.next();
                    System.out.println("ARgHHH, input number, you bobbling dolphin !!!");
                }
            } while (proceedWithRowInts);


            if (rowe == 666) {
                killedComputerShips = 10;
                ShipsRepository.printShipsPositions(compShips);
                System.out.println("Hey Cheater, you called total destruction wave!");
                System.out.println();
                System.out.println("Hail Total Sealord " + user.getName() + " AllMighty");
                System.out.println();
                System.out.println("GAME OVER");
                System.exit(0);
            }

            Coordinate gues = convertUserInputToCoordinate(col, rowe);

            killedComputerShips += shoot(compShips, gues, user, compTable);
            compTable.printTable();
            System.out.println("Your cannons have fired " + shotsMade + "times");
            System.out.println("You destroyed ships:" + killedComputerShips);
            System.out.println("++++++++++++++++++++++++");
            System.out.println();

        }
        if (killedComputerShips == 10) {
            System.out.println("Enemy flotile destroyed hail overkill sealord" + user.getName() + "!!!");
            System.out.println("GAME OVER");
            return true;

        }
        if(killedUserShips == 10){
            System.out.println("You've lost, my dear ...");
        }
        return false;}


    public static int shoot(List<Ship> oponentShips, Coordinate userGues, UserClass user, Table table) {
        int rez = -1;

        for (Ship ship : oponentShips) {
            for (Coordinate coord : ship.getCoordinates()) {
                if (coordsMatch(coord, userGues)) {
                    if (user.hasTryedEarlyer(userGues)) {
                        System.out.println("You have tried this coordinate before");
                        return 0;
                    } else {
                        table.markTable(2, userGues);
                        ship.setShotParts(ship.getShotParts() + 1);
                        user.saveTakenShots(userGues);
                        System.out.println("Hit ");
                        if (ship.getSize() == ship.getShotParts()) {
                            for (Coordinate coordinate : ship.getCoordinates()) {
                                table.markTable(3, coordinate);
                            }
                            System.out.println("Bravo, " + user.getName() + " you have destroyed this ship !!!");
                            System.out.println();
                            return 1;

                        }
                        return 0;
                    }
                } else {
                    rez = 0;
                }
            }

        }
        if (rez == 0) {
            table.markTable(1, userGues);
            user.saveTakenShots(userGues);
            System.out.println("Missed !");
            System.out.println();
            return 0;
        }
        return 0;
    }

    private static boolean coordsMatch(Coordinate userGues, Coordinate ship) {
        return (userGues.getRow() == ship.getRow()) && (userGues.getCol() == ship.getCol());
    }

    static Coordinate convertUserInputToCoordinate(String colum, int ro) {
        int col = convertLetterColToArrayCol(colum);
        int row = convertUserRowToArrayRow(ro);
        return new Coordinate(col, row);
    }

    public static int convertLetterColToArrayCol(String val) {

        val = val.toUpperCase();

        int toReturn;

        if (val.equals("A")) {
            toReturn = 0;

        } else if (val.equals("B")) {
            toReturn = 1;

        } else if (val.equals("C")) {
            toReturn = 2;

        } else if (val.equals("D")) {
            toReturn = 3;

        } else if (val.equals("E")) {
            toReturn = 4;

        } else if (val.equals("F")) {
            toReturn = 5;

        } else if (val.equals("G")) {
            toReturn = 6;

        } else if (val.equals("H")) {
            toReturn = 7;

        } else if (val.equals("I")) {
            toReturn = 8;

        } else if (val.equals("J")) {
            toReturn = 9;

        } else {
            System.out.println("Whats wrong, captain? Here, have your default column A !!!");
            System.out.println();
            toReturn = 0;

        }

        return toReturn;
    }

    public static int convertUserRowToArrayRow(int val) {
        int toReturn;
        switch (val) {
            case 1:
                toReturn = 0;
                break;
            case 2:
                toReturn = 1;
                break;
            case 3:
                toReturn = 2;
                break;
            case 4:
                toReturn = 3;
                break;
            case 5:
                toReturn = 4;
                break;
            case 6:
                toReturn = 5;
                break;
            case 7:
                toReturn = 6;
                break;
            case 8:
                toReturn = 7;
                break;
            case 9:
                toReturn = 8;
                break;
            case 10:
                toReturn = 9;
                break;
            default:
                System.out.println("Hey captain, bad input, loosing your time, default row 1 !!!");
                System.out.println();
                toReturn = 0;
                break;
        }

        return toReturn;
    }

    public static Coordinate randomCoordinateGenerator (){
        int colum = rnd.nextInt(9);
        System.out.println("Computer has made his decision and: ");
        int row = rnd.nextInt(9);
        Coordinate randomCoordinate = new Coordinate(colum,row);
        return randomCoordinate;

    }

}




