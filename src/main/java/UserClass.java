import java.util.ArrayList;
import java.util.List;

public class UserClass {

    private String name;
    private List <Coordinate> takenShots = new ArrayList<Coordinate>();


    public String getName() {
        return this.name;

    }

    public void saveTakenShots(Coordinate userCoord) {
        this.takenShots.add(userCoord);
    }

    public boolean hasTryedEarlyer(Coordinate coordinate){
        return (this.takenShots.contains(coordinate));
    }

    public UserClass(String name) {
        this.name = name;
    }



}
