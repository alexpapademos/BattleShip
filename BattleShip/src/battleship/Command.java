package battleship;

import java.util.InputMismatchException;

public enum Command {
    HELP {
        public void execute() {
            String info =
                "Enter 'help' to get more info about the commands and the game\n" +
                "Enter 'save' to save the game to a file\n" +
                "Enter 'load' to load the game from a file\n" +
                "Enter 'exit' to exit the game.";
            System.out.println("Help: ");
            System.out.println(info);
        }
    }, LOAD {
        public void execute() {
            System.out.println("Loading game.");
        }
    }, SAVE {
        public void execute() {
            System.out.println("Saving game.");
        }
    }, EXIT {
        public void execute() {
            System.out.println("Exiting game.");
            System.exit(0);
        }
    };

    public abstract void execute();

    public static Command fromString(String commandString) {

        switch (commandString) {
            case "help":
                return HELP;
                
            case "load":
                return LOAD;

            case "save":
                return SAVE;

            case "exit":
                return EXIT;

            default:
                throw new InputMismatchException();
        }
    }
}
