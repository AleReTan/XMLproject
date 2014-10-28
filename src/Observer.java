import java.text.ParseException;
import java.util.*;

public class Observer {
    public void runXmlParser() throws ParseException {
        XmlParser read = new XmlParser();
        List<Student> readStudents = read.parse("src/studentsNew.xml");
        for (Student student : readStudents) {
            System.out.println(student);
        }
    }
    public void runXmlWriter() throws Exception {
        XmlWriter write =  new XmlWriter();
        //write.setFile("src/studentsNew.xml");
        List<Student> students = new ArrayList<Student>();
        Student student1 = new Student("Max","Lip",new GregorianCalendar(1994, Calendar.JULY,4).getTime(),4,60);
        Student student2 = new Student("Alex","Resh",new GregorianCalendar(1993, Calendar.JULY,27).getTime(),4,3);
        Student student3 = new Student("Andrew","Pan",new GregorianCalendar(1994, Calendar.FEBRUARY,14).getTime(),4,3);

        students.add(student1);
        students.add(student2);
        students.add(student3);
        write.saveStudents("src/studentsNew.xml",students);

    }
}
