package src.Controller;

import src.Entity.Student;

import java.io.IOException;
import java.util.List;

/**
 * The interface Student data handler.
 */
public interface IStudentDataHandler {

    /**
     * Loads students from the database.
     *
     * @return a list of loaded students
     * @throws IOException if there's an error reading from the database
     */
    List<Student> loadStudentsFromDatabase() throws IOException;

    /**
     * Saves students to the database.
     *
     * @param students a list of students to be saved
     * @throws IOException if there's an error writing to the database
     */
    void saveStudentsToDatabase(List<Student> students) throws IOException;
}

