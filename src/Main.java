import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        //Bunch of dummy values

        String studentName = "john_doe";
        String dummyEmail = "email";
        String dummyPassword = "password";
        double dummyGrade = 97.0;
        HashMap<String, Double> dummyHashMap = new HashMap<String, Double>();
        dummyHashMap.put("assign1", 67.0);
        dummyHashMap.put("assign2", 1.433);
        dummyHashMap.put("assign3", 3.0);
        dummyHashMap.put("assign4", 23333333333.2322231);
        dummyHashMap.put("assign5", 0.0);

//        Assignment assignment = new Assignment(80, "assign1", "2/20/2025");
//
//        assignment.save();
//
//        StudentAccount studentAccount = new StudentAccount("user", "email", "password", 30);
//
//        studentAccount.setKeyValueGrade("assign1", 20);
//        studentAccount.setKeyValueGrade("assign2", 50);
//
//        studentAccount.save();

        DataLoader.load();

    }
}
