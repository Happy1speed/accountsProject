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
                System.out.println("Enter -1 to go back.");
                System.out.print("Enter username: ");
                try {
                    username = scnr.nextLine();

                    if (username.equals("-1")) {
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
                            System.out.println("Enter -1 to go back.");
                            System.out.print("Enter password: ");
                            try {
                                String findPassword = scnr.nextLine();

                                if (findPassword.equals("-1")) {
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
