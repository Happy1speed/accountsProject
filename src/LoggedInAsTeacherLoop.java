import java.util.Scanner;

public class LoggedInAsTeacherLoop {
    public static void loop(Scanner scnr, TeacherAccount user) {
        boolean exit = false;

        while (!exit) {

            System.out.println("Teacher Interface Home");
            System.out.println("Options: view_students | create_assignment | edit_grade | add_student | remove_student | exit");

            String getChoice = scnr.nextLine();

            if (getChoice.equalsIgnoreCase("view_students")) {
                for (StudentAccount studentAccount : user.getCoursesTaught().getFirst().getStudentRoster()) {
                    System.out.println(studentAccount.getUsername() + " : " + studentAccount.getStudentGrade());
                }
            }
            else if (getChoice.equalsIgnoreCase("edit_grade")) {
                //todo Add this
                System.out.println("DEBUG RESPONSE: ADD THIS");
            }
            else if (getChoice.equalsIgnoreCase("add_student")) {

                System.out.println("Enter Student Name:");

                String studentNameInput = scnr.nextLine();

                boolean successfullyAddedStudent = false;

                for (StudentAccount stud : GlobalData.studentList) {
                    if (studentNameInput.equalsIgnoreCase(stud.getUsername())) {
                        user.getCoursesTaught().getFirst().addStudentToRoster(stud);
                        successfullyAddedStudent = true;
                    }
                }
                if (successfullyAddedStudent) {
                    System.out.println("Added " + studentNameInput + " to course");
                }
                else {
                    System.out.println("Could not find student");
                }

            }
            else if (getChoice.equalsIgnoreCase("remove_student")) {

                System.out.println("Enter Student Name: ");

                String studentNameInput = scnr.nextLine();

                boolean successfullyRemovedStudent = false;

                for (StudentAccount stud : user.getCoursesTaught().getFirst().getStudentRoster()) {
                    if (studentNameInput.equalsIgnoreCase(stud.getUsername())) {
                        user.getCoursesTaught().getFirst().removeStudentFromRoster(stud.getUsername());
                        successfullyRemovedStudent = true;
                    }
                }
                if (successfullyRemovedStudent) {
                    System.out.println("Removed " + studentNameInput + " from course");
                }
                else {
                    System.out.println("Could not find student in course");
                }
            }

            else if (getChoice.equalsIgnoreCase("create_assignment")) {
                AssignmentUI.writeAssignment(scnr);
            }

            else if (getChoice.equalsIgnoreCase("exit")) {
                exit = true;
                break;
            }
            else {
                System.out.println("Invalid option; please make sure you spelled the command correctly!");
            }
        }

    }
}
