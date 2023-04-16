package src.Controller;

import src.Entity.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Student data handler.
 */
public class StudentDataHandler implements IStudentDataHandler{
    private static final String STUDENT_FILE = "database/Students_List.txt";
    /**
     * Load students from database.
     * @return students the students
     * @throws IOException the io exception
     */
    public List<Student> loadStudentsFromDatabase() throws IOException {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");

                String name = data[0];
                String email = data[1];
                String deRegisteredStatus = data[2];
                boolean deRegistered = (deRegisteredStatus.equals("FALSE")) ? false : true;
                String userID = email.substring(0, email.indexOf('@'));

                String password = "password";

                Student student = new Student(userID, password, name, email);
                student.setDeRegistered(deRegistered);
                students.add(student);
            }
        }
        return students;
    }
    /**
     * Save students to database
     *
     * @param students the students
     * @throws IOException the io exception
     */
    public void saveStudentsToDatabase(List<Student> students) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE))) {

            for (Student student : students) {
                String deRegisteredStatus = (student.getDeRegistered()) ? "TRUE" : "FALSE";
                writer.write(student.getName() + "\t" + student.getEmail() + "\t" + deRegisteredStatus);
                writer.newLine();
            }
        }
    }
}
