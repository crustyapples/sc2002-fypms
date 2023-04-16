package src.CustomExceptions;

import src.ConsoleColors;

/**
 * The type Invalid input exception.
 */
public class InvalidInputException {
    /**
     * The type Invalid project id exception.
     */
    public static class InvalidProjectIDException extends Exception {
        /**
         * Instantiates a new Invalid project id exception.
         */
        public InvalidProjectIDException() {
            super(ConsoleColors.RED_BRIGHT + "Invalid ProjectID! Please try again!\n" + ConsoleColors.RESET);
        }
    }

    /**
     * The type Invalid request id exception.
     */
    public static class InvalidRequestIDException extends Exception {
        /**
         * Instantiates a new Invalid request id exception.
         */
        public InvalidRequestIDException() {
            super(ConsoleColors.RED_BRIGHT + "Invalid RequestID! Please try again!\n" + ConsoleColors.RESET);
        }
    }


    /**
     * The type Invalid supervisor id exception.
     */
    public static class InvalidSupervisorIDException extends Exception {
        /**
         * Instantiates a new Invalid supervisor id exception.
         */
        public InvalidSupervisorIDException() {
            super(ConsoleColors.RED_BRIGHT + "Invalid SupervisorID! Please try again!\n" + ConsoleColors.RESET);
        }
    }

    /**
     * The type Invalid user id password exception.
     */
    public static class InvalidUserIDPasswordException extends Exception {
        /**
         * Instantiates a new Invalid user id password exception.
         */
        public InvalidUserIDPasswordException() {
            super(ConsoleColors.RED_BRIGHT + "Invalid user ID or password. Please try again!\n" + ConsoleColors.RESET);
        }
    }


}
