package src.CustomExceptions;

import src.ConsoleColors;

public class ProjectsException {
    public static class ProjectNotRegisteredException extends Exception {
        public ProjectNotRegisteredException() {
            super(ConsoleColors.RED_BRIGHT + "You have not registered a project!\n" + ConsoleColors.RESET);
        }
    }

    public static class NoAccessToProjectListException extends Exception {
        public NoAccessToProjectListException() {
            super(ConsoleColors.RED_BRIGHT + "You are currently allocated to a FYP and do not have access to available project list!" + ConsoleColors.RESET);
        }
    }

    public static class ProjectSelectionNotAllowedException extends Exception {
        public ProjectSelectionNotAllowedException() {
            super(ConsoleColors.RED_BRIGHT + "You are not allowed to make a selection again as you deregistered your FYP." + ConsoleColors.RESET);
        }
    }
}
