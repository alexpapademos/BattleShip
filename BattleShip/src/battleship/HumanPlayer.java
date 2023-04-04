package battleship;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private static final String aircraftType = "aircraftCarrier";
    private static final String destroyerType = "destroyer";
    private static final String submarineType = "submarine";
    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name) {
        super(name);
    }

    public void placeShips(Field otherField) {
        
        System.out.println("Enter A for automatic placement of ships or M for manual");
        String answer = scanner.next();
        
        if (answer.equalsIgnoreCase("a")){
            otherField.placeShipRandomly(new AircraftCarrier(otherField), 0, true);
            otherField.placeShipRandomly(new AircraftCarrier(otherField), 0, true);
            otherField.placeShipRandomly(new Destroyer(otherField), 0, true);
            otherField.placeShipRandomly(new Destroyer(otherField), 0, true);
            otherField.placeShipRandomly(new Destroyer(otherField), 0, true);
            otherField.placeShipRandomly(new Submarine(otherField), 0, true);
            otherField.placeShipRandomly(new Submarine(otherField), 0, true);

            System.out.println("Human player " + getName() + " has automatically positioned his ships..");
            System.out.println(otherField.toStringWithShips());
            return;
        }
        
        placeAircrafts(otherField);
        placeDestroyers(otherField);
        placeSubmarines(otherField);
        
        System.out.println("Human player " + getName() + " put his ships");
        System.out.println(otherField.toStringWithShips());
    }

    private void placeAircrafts(Field otherField) {
        boolean success;

        for (int i = 1; i <= 2; i++) {
            do {
                System.out.println(otherField.toStringWithShips());
                System.out.println("Δώσε θέση για το " + i + " " + aircraftType);
                String locationStr = scanner.next();

                System.out.println("Δώσε προσανατολισμό πλοίου");
                String directionStr = scanner.next();

                success = placeShip(locationStr, directionStr, otherField, aircraftType);
            } while (!success);
        }
    }

    private void placeDestroyers(Field otherField) {
        boolean success;

        for (int i = 1; i <= 3; i++) {
            do {
                System.out.println(otherField.toStringWithShips());
                System.out.println("Δώσε θέση για το " + i + " " + destroyerType);
                String locationStr = scanner.next();

                System.out.println("Δώσε προσανατολισμό πλοίου");
                String directionStr = scanner.next();

                success = placeShip(locationStr, directionStr, otherField, destroyerType);
            } while (!success);
        }
    }

    private void placeSubmarines(Field otherField) {
        boolean success;

        for (int i = 1; i <= 2; i++) {
            do {
                System.out.println(otherField.toStringWithShips());
                System.out.println("Δώσε θέση για το " + i + " " + submarineType);
                String locationStr = scanner.next();

                //System.out.println("Δώσε προσανατολισμό πλοίου");
                //String directionStr = scanner.next();
                String directionStr = "v";

                success = placeShip(locationStr, directionStr, otherField, submarineType);
            } while (!success);
        }
    }

    private boolean placeShip(String locationStr, String directionStr,
                              Field otherField, String shipType) {
        Location location;
        ShipDirection direction = ShipDirection.fromString(directionStr);

        try{
            location = otherField.getLocation(locationStr);
        }
        catch (InvalidLocationException e){
            System.out.println(e.getMessage());
            return false;
        }
        
        Ship ship;
        if (shipType.equals(aircraftType)) {
            ship = new AircraftCarrier(otherField);
        } else if (shipType.equals(destroyerType)) {
            ship = new Destroyer(otherField);
        } else {
            ship = new Submarine(otherField);
        }

        ship.setDir(direction);
        ship.setStart(location);

        return otherField.placeShip(ship, false);
    }
    
    public Location selectMove() throws MoveIsCommandException {
        Location moveLoc = null;
        boolean retry;
        
        do{
            retry = false;
        
            System.out.println(getField());

            System.out.println("Enter your hit location:");
            String moveString = scanner.next();

            try{
                Command command = Command.fromString(moveString);
                // άρα έχω πάρει command αν δεν έχω exception
                // και πρέπει να δω τι γίνεται με το command...
                command.execute();

                throw new MoveIsCommandException(command);
            }
            catch (InputMismatchException e) {
                try{
                    moveLoc = getField().getLocation(moveString);
                } catch (InvalidLocationException e2) {
                    retry = true;
                    System.out.println("Wrong input please try again...");
                }
            }
        } while (retry);
        
        
        return moveLoc;
    }
    
    public void play(){
        System.out.println("Now plays " + getName() + "...");       
        Location l;
        
        do{
            try{
                l = selectMove();
            }
            catch (MoveIsCommandException e){
                System.out.println("You gave command!!");
                if (e.getCommand() == Command.EXIT){
                    
                }
                else if (e.getCommand() == Command.HELP){
                    
                }
                
                l = null;
            }
        }while (l == null || l.isMarked());
        
        getField().processValidMove(l);

        System.out.println("\n");
    }
}
