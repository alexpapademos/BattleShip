package battleship;

public enum ShipDirection {
    HORIZONTAL, VERTICAL;

    public static ShipDirection fromString(String dirString){
        if (dirString.equals("v")) {
            return VERTICAL;
        } else if (dirString.equals("h")) {
            return HORIZONTAL;
        } else {
            return null;
        }
    }
}
