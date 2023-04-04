package battleship;

public class AircraftCarrier extends Ship {

    public AircraftCarrier(Field field) {
        super(5, 5, field, 'A');
    }
    
    
    public String getSinkMessage(){
        return "An aircraft carrier is sinking!";
    }
     /* public String getHitMessage(){
        return "A aircraftcarrier is hitted!";
    }*/
}
