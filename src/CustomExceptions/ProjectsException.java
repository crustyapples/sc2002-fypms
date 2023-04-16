package src.CustomExceptions;

import src.ConsoleColors;

/**
 * The type Projects exception.
 */
public class ProjectsException {
    /**
     * The type Project not registered exception.
     */
    public static class ProjectNotRegisteredException extends Exception {
        /**
         * Instantiates a new Project not registered exception.
         */
        public ProjectNotRegisteredException() {
            super(ConsoleColors.RED_BRIGHT + "You have not registered a project!\n" + ConsoleColors.RESET);
        }
    }

    /**
     * The type No access to project list exception.
     */
    public static class NoAccessToProjectListException extends Exception {
        /**
         * Instantiates a new No access to project list exception.
         */
        public NoAccessToProjectListException() {
            super(ConsoleColors.RED_BRIGHT + "You are currently allocated to a FYP and do not have access to available project list!" + ConsoleColors.RESET);
        }
    }

    /**
     * The type Project selection not allowed exception.
     */
    public static class ProjectSelectionNotAllowedException extends Exception {
        /**
         * Instantiates a new Project selection not allowed exception.
         */
        public ProjectSelectionNotAllowedException() {
            super(ConsoleColors.RED_BRIGHT + "You are not allowed to make a selection again as you deregistered your FYP." + ConsoleColors.RESET);
        }
    }
}
