import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Course extends Saveable {
    //Note that the class is courses, we can change it back to Course but that basically made my computer explode so it's courses for now, in TeacherAccount it's courses too
    private String courseName;
    private String meetingTimes;
    private String roomNumber;
    private String instructorInfo;
    private int courseCode;
    private int credits;


    // Default constructor
    public Course() {
        courseName = "default";
        meetingTimes = "default";
        roomNumber = "default";
        courseCode = 0;
        credits = 0;
    }


    //Constructor with parameters, I may want to try to get teacher info from the teacher account instead of passing it here
    public Course(String _courseName, String _meetingTimes, String _instructorInfo, String _roomNumber, int _courseCode, int _credits) {
        courseName = _courseName;
        courseCode = _courseCode;
        credits = _credits;
        meetingTimes = _meetingTimes;
        roomNumber = _roomNumber;
        instructorInfo = _instructorInfo;
        studentRoster = new ArrayList<StudentAccount>(); //Not sure if this is right, I am a monkey and this is my code
    }


    //Getters and Setters in order of declaration
    public String getCourseName() {
        return courseName;
    }


    public void setCourseName(String _courseName) {
        courseName = _courseName;
    }


    public String getMeetingTimes() {
        return meetingTimes;
    }


    public void setMeetingTimes(String _meetingTimes) {
        meetingTimes = _meetingTimes;
    }


    public String getRoomNumber() {
        return roomNumber;
    }


    public void setRoomNumber(String _roomNumber) {
        roomNumber = _roomNumber;
    }


    public String getInstructorInfo() {
        return instructorInfo;
    }


    public void setInstructorInfo(String _instructorInfo) {
        instructorInfo = _instructorInfo;
    }


    public int getCourseCode() {
        return courseCode;
    }


    public void setCourseCode(int _courseCode) {
        courseCode = _courseCode;
    }


    public int getCredits() {
        return credits;
    }


    public void setCredits(int _credits) {
        credits = _credits;
    }


    //ArrayList for student roster, probably needs some work as I am not sure how really what an ArrayList is yet
    private ArrayList<StudentAccount> studentRoster = new ArrayList<StudentAccount>();


    public void addStudentToRoster(StudentAccount studentName) {
        studentRoster.add(studentName);
    }


    public void removeStudentFromRoster(String studentName) {
        studentRoster.remove(studentName);
    }


    public ArrayList<StudentAccount> getStudentRoster() {
        return studentRoster;
    }


    // Displays all info pretty neatly, aside from the roster
    public String displayInfo() {
         return ("Course Name: " + courseName + "\n" +
             "Meeting Times: " + meetingTimes + "\n" +
             "Instructor: " + instructorInfo + "\n" +
             "Room Number: " + roomNumber + "\n" +
             "Course Code: " + courseCode + "\n" +
             "Credits: " + credits);
    }

    //Please check this work
    @Override
    public void save() {
        //Absolute system file path
        String currentDir = System.getProperty("user.dir");


        //Absolute system file path + student folder
        String courseFolderDir = currentDir + "/course/";


        File crseFolder = new File(courseFolderDir);


        //Generate folder
        crseFolder.mkdirs();


        String currentFileDir = courseFolderDir + this.getCourseName() + ".txt";


        File crseFile = new File(currentFileDir);


        if (!crseFile.exists()) {
            try {
                crseFile.createNewFile();
            }
            catch (IOException e) {
                System.out.println("FAILED TO CREATE FILE");
            }
        }


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(currentFileDir))) {
            bufferedWriter.write(this.displayInfo());


            bufferedWriter.newLine();


            bufferedWriter.write(this.getStudentRoster());


            bufferedWriter.flush();
        }
        catch (IOException e) {
            System.out.println("WRITER EXCEPTION");
        }
    }

}


