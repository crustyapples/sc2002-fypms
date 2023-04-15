package src.CustomExceptions;

import src.ConsoleColors;

public class InvalidInputException {
    public static class InvalidProjectIDException extends Exception {
        public InvalidProjectIDException() {
            super(ConsoleColors.RED_BRIGHT + "Invalid ProjectID!\n" + ConsoleColors.RESET);
        }
    }

    public static class InvalidRequestIDException extends Exception {
        public InvalidRequestIDException() {
            super(ConsoleColors.RED_BRIGHT + "Invalid RequestID!\n" + ConsoleColors.RESET);
        }
    }


    public static class InvalidSupervisorIDException extends Exception {
        public InvalidSupervisorIDException() {
            super(ConsoleColors.RED_BRIGHT + "Invalid SupervisorID!\n" + ConsoleColors.RESET);
        }
    }


}
