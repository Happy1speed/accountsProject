import java.util.ArrayList;


//Still need to extend BaseAccount
public class TeacherAccount {
    //I highkey doubt this is right but trying to make a teacher account that can own multiple courses
private ArrayList<Courses> ownedCourses;


    // Default constructor, I want to do the superclass thing but need clarificiation
    public TeacherAccount() {
        ownedCourses = new ArrayList<>();
    }


    // Method to add a course
    public void addCourse(Courses course) {
        ownedCourses.add(course);
    }


    // Display courses
    public ArrayList<Courses> getCoursesTaught() {
        return ownedCourses;
    }
}
