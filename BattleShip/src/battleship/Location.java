package battleship;

public class Location {
    private final int row;
    private final int col;
    private Ship ship;
    private boolean marked;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Ship getShip() {
        return ship;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void mark(){
        marked = true;
    }

    public boolean isEmpty() {
        return ship == null;
    }

    public String toStringWithShips(){
        if (!marked && ship == null) {
            return ".  ";
        }

        if (!marked && ship != null) {
            return ship.getLetter() + "  ";
        }

        if (marked && ship == null) {
            return "o  ";
        }

        return "x" + ship.getLetter() + " ";
    }
    public String toString(){
            if (!marked) {
                return ".  ";
            }

            if (marked && ship != null) {
                if(ship.isSinking()){
                    return "x" + ship.getLetter() + " ";
                }
                return "x  ";
            }

            if (marked && ship == null) {
                return "o  ";
            }
        return " ";
    }
}
