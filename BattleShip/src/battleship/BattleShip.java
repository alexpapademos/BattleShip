package battleship;

public class BattleShip {

    public static void main(String[] args) {
        Game game = new Game();

        game.init();
        game.placeShips();
        game.play();
        game.showResult();
    }
}
