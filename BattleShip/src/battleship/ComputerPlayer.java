package battleship;

import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String name) {
        super(name);
    }
    
    public void placeShips(Field otherField){
        otherField.placeShipRandomly(new AircraftCarrier(otherField), 0, true);
        otherField.placeShipRandomly(new AircraftCarrier(otherField), 0, true);
        otherField.placeShipRandomly(new Destroyer(otherField), 0, true);
        otherField.placeShipRandomly(new Destroyer(otherField), 0, true);
        otherField.placeShipRandomly(new Destroyer(otherField), 0, true);
        otherField.placeShipRandomly(new Submarine(otherField), 0, true);
        otherField.placeShipRandomly(new Submarine(otherField), 0, true);
        
        System.out.println("Computer player " + getName() + " has positioned his ships..");
        System.out.println(otherField.toStringWithShips());
    }
    public Location selectMove()
    throws MoveIsCommandException
    {
        Random random = new Random();
        int row = random.nextInt(13);
        int col = random.nextInt(10);
        return getField().getLocation(row,col);
    }
    
    public void play(){
        System.out.println("Now plays " + getName() + "...");       
        Location moveLoc = null;
        
        do{
            try{
                moveLoc = selectMove();
            }
            catch (MoveIsCommandException e){
                
            }
        }while (moveLoc.isMarked());
        
        System.out.println("The position is " + getField().getLocationAsString(moveLoc));
        
        getField().processValidMove(moveLoc);
        
        
        System.out.println(getField());
        
        System.out.println("\n");
    }
    
}
