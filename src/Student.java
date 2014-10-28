import java.text.SimpleDateFormat;
import java.util.Date;


public class Student {
    private String firstName;
    private String lastName;
    private Date birthday;
    private int course;
    private int group;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    Student(){
    }

    Student(String firstName, String lastName, Date birthday, int course, int group ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.course = course;
        this.group = group;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student " + firstName + " " + lastName + ", birthday "
                + dateFormat.format(birthday) + ", course = " + course + ", group = " + group;
    }
}
