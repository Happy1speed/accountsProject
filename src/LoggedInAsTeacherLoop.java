import java.util.Map;
import java.util.Scanner;

public class LoggedInAsTeacherLoop {
    public static void loop(Scanner scnr, TeacherAccount user) {
        boolean exit = false;

        InstructorUI UI = new InstructorUI(user);

        while (!exit) {

            boolean teacherInterfaceExit = false;

            System.out.println("Landing Page");
            System.out.println("Options: create_course | choose_course | exit");

            String getFirstChoice = scnr.nextLine();

            if (getFirstChoice.equalsIgnoreCase("create_course")) {
                CourseUI.createCourse(scnr, user);
            }
            else if (getFirstChoice.equalsIgnoreCase("choose_course")) {
                System.out.println("Input course name: ");
                String courseName = scnr.nextLine();


                if (GlobalData.courseList.isEmpty()) {
                    break;
                }

                for (Course course : GlobalData.courseList) {
                    if (course.getCourseName().equalsIgnoreCase(courseName)) {

                        //Course found

                        while (!teacherInterfaceExit) {

                            System.out.println("Teacher Interface Home");
                            System.out.println("Options: view_students | create_assignment | export_grade  | edit_grade | add_student | remove_student | exit");

                            String getChoice = scnr.nextLine();

                            if (getChoice.equalsIgnoreCase("view_students")) {

                                for (StudentAccount studentAccount : course.getStudentRoster()) {
                                    System.out.println(studentAccount.getUsername() + " : " + studentAccount.getStudentGrade());
                                }
                            }
                            else if (getChoice.equalsIgnoreCase("edit_grade")) {

                                if (!user.getCoursesTaught().isEmpty()) {

                                    boolean gradeUnedited = true;

                                    while (gradeUnedited) {
                                        System.out.println();
                                        System.out.println("Enter the name of the student you will be editing the grade of, or exit.");


                                        String getStudent = scnr.nextLine();

                                        if (getStudent.equalsIgnoreCase("exit")) {
                                            gradeUnedited = false;
                                            break;
                                        }

                                        StudentAccount subjectStudent = new StudentAccount("dummy", "dummy", "dummy", 0);

                                        boolean foundStudentInRoster = false;

                                        for (StudentAccount studentAccount : course.getStudentRoster()) {

                                            if (studentAccount.getUsername().equalsIgnoreCase(getStudent)) {
                                                foundStudentInRoster = true;
                                                subjectStudent = studentAccount;
                                                break;
                                            }
                                        }

                                        if (foundStudentInRoster) {
                                            System.out.println("Student Found!");
                                            System.out.println("Input the assignment name that you want to grade them on: ");

                                            String assignmentName = scnr.nextLine();

                                            for (Map.Entry<String, Double> entry : subjectStudent.getStudentAssignments().entrySet()) {
                                                System.out.println(entry.getKey());
                                            }

                                            if (subjectStudent.getStudentAssignments().get(assignmentName) != null) {
                                                System.out.println("Input new grade: ");

                                                double newGrade = scnr.nextDouble();

                                                subjectStudent.setKeyValueGrade(assignmentName, newGrade);

                                                System.out.println("Grade Set");
                                            }
                                            else {
                                                System.out.println("Couldn't find that assignment");
                                            }
                                        } else {
                                            System.out.println("Could not find student \"" + getStudent + "\" in course list.");
                                            System.out.println("If you are unsure of available names, return to Teacher Interface Home and try view_students.");
                                        }
                                    }
                                } else {
                                    System.out.println("You don't have any courses!");
                                }

                            } else if (getChoice.equalsIgnoreCase("add_student")) {

                                if (!user.getCoursesTaught().isEmpty()) {

                                    System.out.println("Enter Student Name:");

                                    String studentNameInput = scnr.nextLine();

                                    boolean successfullyAddedStudent = false;

                                    for (StudentAccount stud : GlobalData.studentList) {
                                        if (studentNameInput.equalsIgnoreCase(stud.getUsername())) {
                                            course.addStudentToRoster(stud);
                                            course.save();
                                            successfullyAddedStudent = true;
                                        }
                                    }
                                    if (successfullyAddedStudent) {
                                        System.out.println("Added " + studentNameInput + " to course");
                                    } else {
                                        System.out.println("Could not find student");
                                    }
                                } else {
                                    System.out.println("You don't have any courses!");
                                }

                            } else if (getChoice.equalsIgnoreCase("remove_student")) {

                                if (!user.getCoursesTaught().isEmpty()) {

                                    System.out.println("Enter Student Name: ");

                                    String studentNameInput = scnr.nextLine();

                                    boolean successfullyRemovedStudent = false;

                                    for (StudentAccount stud : user.getCoursesTaught().getFirst().getStudentRoster()) {
                                        if (studentNameInput.equalsIgnoreCase(stud.getUsername())) {
                                            user.getCoursesTaught().getFirst().removeStudentFromRoster(stud.getUsername());
                                            user.save();
                                            successfullyRemovedStudent = true;
                                        }
                                    }
                                    if (successfullyRemovedStudent) {
                                        System.out.println("Removed " + studentNameInput + " from course");
                                    } else {
                                        System.out.println("Could not find student in course");
                                    }
                                } else {
                                    System.out.println("You don't have any courses!");
                                }
                            } else if (getChoice.equalsIgnoreCase("create_assignment")) {
                                AssignmentUI.writeAssignment(scnr, course.getCourseName());

                            }
                            else if (getChoice.equalsIgnoreCase("export_grade")) {
                                if (!user.getCoursesTaught().isEmpty()) {

                                    System.out.println("Enter Student Name: ");

                                    String studentNameInput = scnr.nextLine();

                                    boolean successfullyRemovedStudent = false;

                                    for (StudentAccount stud : user.getCoursesTaught().getFirst().getStudentRoster()) {
                                        if (studentNameInput.equalsIgnoreCase(stud.getUsername())) {
                                            ExportGrade.export(stud);
                                            successfullyRemovedStudent = true;
                                            break;
                                        }
                                    }
                                    if (successfullyRemovedStudent) {
                                        System.out.println("Exported grade");
                                    } else {
                                        System.out.println("Could not find student in course");
                                    }
                                } else {
                                    System.out.println("You don't have any courses!");
                                }
                            }
                            else if (getChoice.equalsIgnoreCase("exit")) {
                                exit = true;
                                break;

                            }
                            else {
                                System.out.println("Invalid option; please make sure you spelled the command correctly!");
                            }
                        }
                        break;
                    }
                }

            }
            else if (getFirstChoice.equalsIgnoreCase("exit")) {
                exit = true;
                break;
            }
            else {
                System.out.println("Invalid option; please make sure you spelled the command correctly!");
            }


        }

    }
}
