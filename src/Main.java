package src;

import src.Boundary.*;
import src.Controller.*;
import src.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class is the main class of the application.
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {


        System.out.println(ConsoleColors.BLUE_BRIGHT + "███████╗██╗   ██╗██████╗ ███╗   ███╗███████╗" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BRIGHT + "██╔════╝╚██╗ ██╔╝██╔══██╗████╗ ████║██╔════╝" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BRIGHT + "█████╗   ╚████╔╝ ██████╔╝██╔████╔██║███████╗" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BRIGHT + "██╔══╝    ╚██╔╝  ██╔═══╝ ██║╚██╔╝██║╚════██║" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BRIGHT + "██║        ██║   ██║     ██║ ╚═╝ ██║███████║" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BRIGHT + "╚═╝        ╚═╝   ╚═╝     ╚═╝     ╚═╝╚══════╝" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BRIGHT + "Welcome to the FYP Management System!" + ConsoleColors.RESET);

        MainMenuUI menu = new MainMenuUI();
        menu.enterMenu();
    }
}
