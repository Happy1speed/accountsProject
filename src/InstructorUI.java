import java.util.ArrayList;
import java.util.Scanner;

public class InstructorUI {

    public TeacherAccount currentlyLoggedIn;

    public void addStudent() {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter new student username: ");
        String username = scnr.nextLine();

        System.out.print("Enter student email: ");
        String email = scnr.nextLine();

        System.out.print("Enter password: ");
        String password = scnr.nextLine();

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
1
    public void exportGrade() {
        System.out.println("\nExporting all grades...");
        for (StudentAccount s : GlobalData.studentList) {
            GlobalData.saveableList.add(s);
        }
        System.out.println("Grades successfully exported!");
    }
}
