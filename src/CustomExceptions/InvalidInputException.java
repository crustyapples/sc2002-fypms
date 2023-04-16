package src.CustomExceptions;

import src.ConsoleColors;

public class InvalidInputException {
    public static class InvalidProjectIDException extends Exception {
        public InvalidProjectIDException() {
            super(ConsoleColors.RED_BRIGHT + "Invalid ProjectID! Please try again!\n" + ConsoleColors.RESET);
        }
    }

    public static class InvalidRequestIDException extends Exception {
        public InvalidRequestIDException() {
            super(ConsoleColors.RED_BRIGHT + "Invalid RequestID! Please try again!\n" + ConsoleColors.RESET);
        }
    }


    public static class InvalidSupervisorIDException extends Exception {
        public InvalidSupervisorIDException() {
            super(ConsoleColors.RED_BRIGHT + "Invalid SupervisorID! Please try again!\n" + ConsoleColors.RESET);
        }
    }

    public static class InvalidUserIDPasswordException extends Exception {
        public InvalidUserIDPasswordException() {
            super(ConsoleColors.RED_BRIGHT + "Invalid user ID or password. Please try again!\n" + ConsoleColors.RESET);
        }
    }


}
