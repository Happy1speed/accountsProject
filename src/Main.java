import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

//public static Course course = new Course();

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        //It all starts with the load
        DataLoader.load();

        System.out.println("Teachers:");
        for (TeacherAccount teacherAccount : GlobalData.teacherList) {
            System.out.println(teacherAccount.getUsername());
        }
        System.out.println("Students:");

        for (StudentAccount sAccount : GlobalData.studentList) {
            System.out.println(sAccount.getUsername());
        }

        //Todo Make Course Creation interface as well as adapt rest of code to use multiple courses (?)

        boolean exit = false;

        System.out.println("Welcome");
        while (!exit) {
            System.out.println("Options:\nlogin | create_account | exit");

            String startInput = scnr.nextLine();

            if (startInput.equalsIgnoreCase("login")) {
                //Jump to login loop
                Login.loginLoop(scnr);
            }
            else if (startInput.equalsIgnoreCase("create_account")) {
                boolean exitAccountCreationLoop = false;
                System.out.println("Create an account");

                boolean isTeacher = false;
                String username = "";
                String email = "";
                String password = "";


                while (!exitAccountCreationLoop) {
                    System.out.println("Are you creating a teacher account?");
                    System.out.println("Options: yes | no | exit");
                    String getChoice = scnr.nextLine();

                    if (getChoice.equalsIgnoreCase("yes")) {
                        isTeacher = true;
                    }
                    else if (getChoice.equalsIgnoreCase("no")) {
                        isTeacher = false;
                    }
                    else if (getChoice.equalsIgnoreCase("exit")) {
                        exitAccountCreationLoop = true;
                        break;
                    }
                    else {
                        System.out.println("Invalid option; please make sure you spelled the command correctly!");
                    }



                    //Get username
                    System.out.println("Enter username or exit");

                    try {
                        String getUsernameChoice = scnr.nextLine();

                        if (getUsernameChoice.equalsIgnoreCase("exit")) {
                            exitAccountCreationLoop = true;
                            break;
                        }
                        else if (getUsernameChoice.equalsIgnoreCase("")) {
                            System.out.println("Unable to use blank username");
                            exitAccountCreationLoop = true;
                            break;
                        }

                        username = getUsernameChoice;

                    }
                    catch (InputMismatchException e) {
                        System.out.println("Invalid username");
                        exitAccountCreationLoop = true;
                        break;
                    }


                    //Email
                    System.out.println("Enter email or exit");

                    try {
                        String getEmailChoice = scnr.nextLine();

                        if (getEmailChoice.equalsIgnoreCase("exit")) {
                            exitAccountCreationLoop = true;
                            break;
                        }
                        else if (getEmailChoice.equalsIgnoreCase("")) {
                            System.out.println("Unable to use blank email");
                            exitAccountCreationLoop = true;
                            break;
                        }

                        email = getEmailChoice;

                    }
                    catch (InputMismatchException e) {
                        System.out.println("Invalid email");
                        exitAccountCreationLoop = true;
                        break;
                    }


                    //Password
                    System.out.println("Enter password or exit");

                    try {
                        String getPassChoice = scnr.nextLine();

                        if (getPassChoice.equalsIgnoreCase("exit")) {
                            exitAccountCreationLoop = true;
                            break;
                        }
                        else if (getPassChoice.equalsIgnoreCase("")) {
                            System.out.println("Unable to use blank password");
                            exitAccountCreationLoop = true;
                            break;
                        }

                        password = getPassChoice;

                    }
                    catch (InputMismatchException e) {
                        System.out.println("Invalid email");
                        exitAccountCreationLoop = true;
                        break;
                    }


                    AccountCreation.createAccount(username, email, password, isTeacher);
                    System.out.println("Account created!");



                }
            }
            else if (startInput.equalsIgnoreCase("exit")) {
                System.out.println("Shutting down, Goodbye");
                exit = true;
                break;
            }
            else {
                System.out.println("Invalid option; please make sure you spelled the command correctly!");
            }
        }

    }
}
