import java.util.Arrays;

public class Table {

    private int [][] tableMatrix;
    private final int [] ROW_INDICATORS = {1,2,3,4,5,6,7,8,9,10};

    public Table() {
        setInitialTable();
    }

    public void setInitialTable() {
        int[][] newTab = new  int[10][10];
        for (int [] row : newTab) {
            Arrays.fill(row, 0);
        }
        this.tableMatrix = newTab;
    }

    public void markTable(int valueOfChar, Coordinate coordinate) {
        this.tableMatrix[coordinate.getCol()][coordinate.getRow()]= valueOfChar;
    }

    public void printTable(){
        System.out.println(" A  B  C  D  E  F  G  H  I  J ");
        for(int row = 0; row < 10; row++){

            for (int col= 0; col < 10; col++ ){
                if (this.tableMatrix[col][row] == 0){
                    System.out.print(" ~ ");
                }else if(this.tableMatrix[col][row] == 1){
                    System.out.print(" M ");
                }else if(this.tableMatrix[col][row] == 2){
                    System.out.print(" H ");
                } else if(this.tableMatrix[col][row] == 3){
                    System.out.print(" X ");
                } else if(this.tableMatrix[col][row] == 4){
                    System.out.print(" O ");
                }
            }
            System.out.println(" " + ROW_INDICATORS[row]);
        }
        System.out.println();

    }



}
