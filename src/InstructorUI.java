import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InstructorUI {

    public TeacherAccount currentlyLoggedIn;

    public InstructorUI(TeacherAccount teacherAccount) {
        this.currentlyLoggedIn = teacherAccount;
    }

    public void addStudent(Scanner scnr) {

        boolean breakout = false;

        while (!breakout) {

            System.out.println("Choose students to add. ");
            System.out.println("Input -1 to exit");

            //Note: This assumes only one course is active (The hardcoded one)
            for (StudentAccount studentAccount : currentlyLoggedIn.getCoursesTaught().getFirst().getStudentRoster()) {
                System.out.println("| " + studentAccount.getUsername() + " |");
            }

            String requestedUsername = scnr.nextLine();

            if (requestedUsername.equals("-1")) {
                breakout = true;
                break;
            }

            for (StudentAccount studentAccount : currentlyLoggedIn.getCoursesTaught().getFirst().getStudentRoster()) {
                if (requestedUsername.equals(studentAccount.getUsername())) {
                    currentlyLoggedIn.getCoursesTaught().getFirst().addStudentToRoster(studentAccount);
                    System.out.println("Successfully added " + requestedUsername + " to course");
                }
                else {
                    System.out.println("Could not find username " + requestedUsername + " in list");
                }
            }
        }
    }

    public void createAssignment(Scanner scnr) {
        AssignmentUI assignmentUI = new AssignmentUI();
        assignmentUI.writeAssignment(scnr);
    }

    public void recordGrades(Scanner scnr) {

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
