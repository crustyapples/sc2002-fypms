package src.CustomExceptions;

import src.ConsoleColors;

/**
 * The InvalidInputException class contains custom exception classes for handling
 * various types of invalid inputs.
 */
public class InvalidInputException {
    /**
     * The InvalidProjectIDException class is a custom exception class that extends the
     * Exception class and is used to handle invalid project IDs.
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
     * The InvalidSupervisorIDException class is a custom exception class that extends the
     * Exception class and is used to handle invalid supervisor IDs.
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
     * The InvalidUserIDPasswordException class is a custom exception class that extends the
     * Exception class and is used to handle invalid user IDs or passwords.
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
