package battleship;

import java.util.Random;

public class Field {
    private final int rows;
    private final int cols;
    private Player player;
    private final Location[][] locs;

    public Field(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        locs = new Location[rows][cols];

        for (int i = 0 ; i < rows ; i++) {
            for (int j = 0 ; j < cols ; j++) {
                locs[i][j] = new Location(i, j);
            }
        }
    }
    
    public void setPlayer(Player p){
        player = p;
    }

    public Location getLocation(int r, int c){
        return locs[r][c];
    }
    
    public String getLocationAsString(Location location){
        int r = location.getRow();
        int c = location.getCol();
        String s = Character.toString((char)('A' + r));
        return s + (c + 1) + "";
    }

    public Location getLocation(String locString) throws InvalidLocationException {
        locString = locString.toUpperCase();

        char chr_row = locString.charAt(0);
        int row = chr_row - 'A';

        int col = Integer.parseInt(locString.substring(1)) - 1;

        //System.out.println(row + "  " + col);

        if (col < 0 || col >= cols){
            throw new InvalidLocationException("Λάθος γραμμή!");
        }

        if (row < 0 || row >= rows){
            throw new InvalidLocationException("Λάθος στήλη!");
        }

        return locs[row][col];
    }

    public void placeShipRandomly(Ship s, int maxTries, boolean checkMarked){
        Random random = new Random();
        boolean isValidPosition;
        int length = s.getLength();
        Location loc;
        Location start;
        int row, col, tries = 0;
        ShipDirection dir;
        
        
        do{
            tries++;
            
            isValidPosition = true;
            
            row = random.nextInt(rows);
            col = random.nextInt(cols);
            
            start = locs[row][col];
            
            if (random.nextBoolean()){
                dir = ShipDirection.HORIZONTAL;
            }
            else {
                dir = ShipDirection.VERTICAL;
            }
            
            if (dir == ShipDirection.HORIZONTAL && col + length - 1 >= cols) {
                if (maxTries > 0 && tries == maxTries) return;
                isValidPosition = false;
                continue;
            }

            if (dir == ShipDirection.VERTICAL  && row + length - 1 >= rows) {
                if (maxTries > 0 && tries == maxTries) return;
                isValidPosition = false;
                continue;
            }
            
            
            for (int i = 0 ; i < length ; i++) {
                if (dir == ShipDirection.HORIZONTAL) loc = locs[row][col+i];
                else loc = locs[row+i][col];

                if (!loc.isEmpty()) {
                    if (maxTries > 0 && tries == maxTries) return;
                    isValidPosition = false;
                    break;
                }

                if (loc.isMarked() && checkMarked) {
                    if (maxTries > 0 && tries == maxTries) return;
                    isValidPosition = false;
                    break;
                }
            }
            
            
        } while (!isValidPosition);


        for (int i = 0 ; i < length ; i++) {
            if (dir == ShipDirection.HORIZONTAL) {
                loc = locs[row][col+i];
            } else {
                loc = locs[row+i][col];
            }

            loc.setShip(s);
        }
        
        s.setDir(dir);
        s.setStart(start);
    }
    
    public boolean placeShip(Ship s, boolean checkMarked) {
        int length = s.getLength();
        Location start = s.getStart();
        int row = start.getRow();
        int col = start.getCol();
        ShipDirection dir = s.getDir();

        if (dir == ShipDirection.HORIZONTAL && col + length - 1 >= cols) {
            System.out.println("1");
            return false;
        }

        if (dir == ShipDirection.VERTICAL  && row + length - 1 >= rows) {
            System.out.println("2");
            return false;
        }

        Location loc;

        for (int i = 0 ; i < length ; i++) {
            if (dir == ShipDirection.HORIZONTAL) loc = locs[row][col+i];
            else loc = locs[row+i][col];

            if (!loc.isEmpty()) {
                System.out.println("3");
                return false;
            }

            if (loc.isMarked() && checkMarked) {
                System.out.println("4");
                return false;
            }
        }
        for (int i = 0 ; i < length ; i++) {
            if (dir == ShipDirection.HORIZONTAL) {
                loc = locs[row][col+i];
            } else {
                loc = locs[row+i][col];
            }

            loc.setShip(s);
        }
        return true;
    }

    public String toStringWithShips() {
        String str = "\t ";

        for (int i = 1 ; i <= cols ; i++) {
            if (i < 9) {
                str += i + "  ";
            } else {
                str += i + " ";
            }
        }

        str+= "\n\t";

        for (int i = 1 ; i <= cols ; i++) {
            str += "---";
        }


        str+= "\n";
        for (int i = 0 ; i < rows ; i++) {
            str += (char)('A' + (char)i) + " |  ";

            for (int j = 0 ; j < cols ; j++) {
                str += locs[i][j].toStringWithShips();
            }
            str+= "\n";
        }
        return str;
    }
    public String toString() {
        String str = "\t ";

        for (int i = 1 ; i <= cols ; i++) {
            if (i < 9) {
                str += i + "  ";
            } else {
                str += i + " ";
            }
        }

        str+= "\n\t";

        for (int i = 1 ; i <= cols ; i++) {
            str += "---";
        }

        str+= "\n";
        for (int i = 0 ; i < rows ; i++) {
            str += (char)('A' + (char)i) + " |  ";

            for (int j = 0 ; j < cols ; j++) {
                str += locs[i][j];
            }
            str+= "\n";
        }
        return str;
    }
    
    
    public void processValidMove(Location moveLoc){
        moveLoc.mark();
        Ship ship = moveLoc.getShip();
        
        
        if(ship == null){
            System.out.println("no ship");
        }
        else {
            ship.hit();
            if(ship.isSinking()){
                System.out.println(ship.getSinkMessage());
                player.addToScore(ship.getPoints());
            }
            else{
                System.out.println(ship.getHitMessage());
            }
        }
    }
}
