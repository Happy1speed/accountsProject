import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    public static void loginLoop(Scanner scnr) {
        boolean correctUserInfo = false;
        boolean correctPasswordInfo = false;
        String username = "";
        String password = "";

        while (!correctPasswordInfo && !correctPasswordInfo) {


            if (!correctUserInfo) {
                System.out.print("Enter username or exit: ");
                try {
                    username = scnr.nextLine();

                    if (username.equalsIgnoreCase("exit")) {
                        break;
                    }

                    boolean couldFindUserName = false;

                    for (BaseAccount account : GlobalData.accountList) {
                        if (account.getUsername().equals(username)) {
                            couldFindUserName = true;
                            break;
                        }
                    }

                    if (couldFindUserName) {

                        while (!correctPasswordInfo) {
                            System.out.print("Enter password or exit: ");
                            try {
                                String findPassword = scnr.nextLine();

                                if (findPassword.equalsIgnoreCase("exit")) {
                                    break;
                                }

                                boolean couldFindPassword = false;

                                for (BaseAccount account : GlobalData.accountList) {
                                    if (account.getUsername().equals(username)) {
                                        if (account.getPassword().equals(findPassword)) {
                                            couldFindPassword = true;
                                            break;
                                        }
                                    }
                                }

                                if (couldFindPassword) {
                                    correctUserInfo = true;

                                    System.out.println("Successfully logged in!");

                                    for (BaseAccount account : GlobalData.accountList) {
                                        if (account.getUsername().equals(username)) {
                                            //Instance of checks the class inheritance to test if the BaseAccount is a student account.
                                            //Student account is what is verified as a student account.
                                            if (account instanceof StudentAccount studentAccount) {
                                                LoggedInAsStudentLoop.loop(scnr, studentAccount);
                                            }
                                            else if (account instanceof TeacherAccount teacherAccount) {
                                                LoggedInAsTeacherLoop.loop(scnr, teacherAccount);
                                            }
                                            break;
                                        }
                                    }

                                    //This is the exit point where now the username and password have been confirmed to be in the system.


                                } else {
                                    System.out.println("Could not find password");
                                }

                                correctPasswordInfo = true;
                            } catch (InputMismatchException inputMismatchException) {
                                System.out.println("Invalid Password");
                            }
                        }
                    }

                    else {
                        System.out.println("Could not find username");
                    }

                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("Invalid Username");
                }
            }

        }


    }
}
