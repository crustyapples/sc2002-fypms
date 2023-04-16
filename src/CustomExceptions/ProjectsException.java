package src.CustomExceptions;

import src.ConsoleColors;

/**
 * The ProjectsException class contains custom exceptions related to projects.
 */
public class ProjectsException {
    /**
     * This exception is thrown when a project is not registered.
     */
    public static class ProjectNotRegisteredException extends Exception {
        /**
         * Constructs a new ProjectNotRegisteredException with the default error message.
         */
        public ProjectNotRegisteredException() {
            super(ConsoleColors.RED_BRIGHT + "You have not registered a project!\n" + ConsoleColors.RESET);
        }
    }

    /**
     * This exception is thrown when the user does not have access to the project list.
     */
    public static class NoAccessToProjectListException extends Exception {
        /**
         * Constructs a new NoAccessToProjectListException with the default error message.
         */
        public NoAccessToProjectListException() {
            super(ConsoleColors.RED_BRIGHT + "You are currently allocated to a FYP and do not have access to available project list!" + ConsoleColors.RESET);
        }
    }

    /**
     * This exception is thrown when the user is not allowed to make a project selection.
     */
    public static class ProjectSelectionNotAllowedException extends Exception {
        /**
         * Constructs a new ProjectSelectionNotAllowedException with the default error message.
         */
        public ProjectSelectionNotAllowedException() {
            super(ConsoleColors.RED_BRIGHT + "You are not allowed to make a selection again as you deregistered your FYP." + ConsoleColors.RESET);
        }
    }
}
