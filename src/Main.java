import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Assignment assignment = new Assignment(80, "assign1", "2/20/2025");

        assignment.save();

        StudentAccount studentAccount = new StudentAccount("user", "email", "password", 30);

        studentAccount.setKeyValueGrade("assign1", 20);
        studentAccount.setKeyValueGrade("assign2", 50);

        studentAccount.save();

        DataLoader.load();

    }
}
