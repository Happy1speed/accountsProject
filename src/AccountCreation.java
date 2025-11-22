public class AccountCreation {
    
    public static void createAccount(String userName, String email, String password, boolean isTeacher) {
        if(isTeacher == true) {
            TeacherAccount teacher = new TeacherAccount(userName, email, password);
            teacher.save();
            GlobalData.teacherList.add(teacher);
            GlobalData.accountList.add(teacher);
        }
        else {
            StudentAccount student = new StudentAccount(userName, email, password, 100);
            student.save();
            GlobalData.studentList.add(student);
            GlobalData.accountList.add(student);
        }
        
    }
}
