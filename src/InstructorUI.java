import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InstructorUI {

    public TeacherAccount currentlyLoggedIn;

    public void addStudent() {

        //Todo What is this
        // Currently this code is supposed to be a menu that:
        // Loops with   ,
        // Presents an interface with all students   ,
        // Adds selected student to the Course   ,
        // Repeat until option breaks out   .

        boolean correctUserInfo = false;
        boolean correctEmailInfo = false;
        boolean correctPasswordInfo = false;
        String username = "";
        String email = "";
        String password = "";
        while (!correctPasswordInfo || !correctEmailInfo || !correctUserInfo) {
            Scanner scnr = new Scanner(System.in);


            if (!correctUserInfo) {
                System.out.print("Enter new student username: ");
                try {
                    username = scnr.nextLine();

                    correctUserInfo = true;
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("Invalid Username");
                }
            }



            if (!correctEmailInfo) {
                System.out.print("Enter student email: ");
                try {
                    email = scnr.nextLine();

                    correctEmailInfo = true;
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("Invalid Email");
                }
            }



            if (!correctPasswordInfo) {
                System.out.print("Enter password: ");
                try {
                    password = scnr.nextLine();

                    correctPasswordInfo = true;
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("Invalid Password");
                }
            }

        }
        StudentAccount newStudent = new StudentAccount(username, email, password, 0.0);
        GlobalData.studentList.add(newStudent);
        GlobalData.saveableList.add(newStudent);

        System.out.println("Student " + username + " successfully added.");
    }

    public void createAssignment() {
        AssignmentUI assignmentUI = new AssignmentUI();
        assignmentUI.writeAssignment();
    }

    public void recordGrades() {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter assignment name to record grades for: ");
        String assignmentName = scnr.nextLine();

        for (StudentAccount s : GlobalData.studentList) {
            System.out.print("Enter grade for " + s + ": ");
            double grade = scnr.nextDouble();
            s.setKeyValueGrade(assignmentName, grade);
        }

        System.out.println("Grades recorded for assignment: " + assignmentName);
    }

    public void viewAllGrades(ArrayList<StudentAccount> studentList) {
        System.out.println("\n--- All Student Grades ---");
        for (StudentAccount s : studentList) {
            System.out.println(s + " | Grade: " + s.getStudentGrade());
        }
    }

    public void exportGrade() {
        System.out.println("\nExporting all grades...");
        for (StudentAccount s : GlobalData.studentList) {
            GlobalData.saveableList.add(s);
        }
        System.out.println("Grades successfully exported!");
    }
}
