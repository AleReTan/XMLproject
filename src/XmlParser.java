import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    static private String FIRSTNAME = "firstName";
    static private String LASTNAME = "lastName";
    static private String BIRTHDAY = "birthday";
    static private String GROUP = "group";
    static private String COURSE = "course";
    static private String STUDENT = "student";

    public List<Student> parse(String inputFile) throws ParseException {
        List<Student> students = new ArrayList<Student>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try {
            // Создаем XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = new FileInputStream(inputFile);
            // читаем XML'ку
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            Student student = null;
            //пока есть еще события
            while (eventReader.hasNext()) {
                //берем событие за текущее
                XMLEvent event = eventReader.nextEvent();
                //проверяем является ли текущее событие первым, если является утанавливаем стартовый элемент
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();

                    if (startElement.getName().getLocalPart().equals (STUDENT)) {
                        student = new Student();
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(FIRSTNAME)) {
                            event = eventReader.nextEvent();
                            student.setFirstName(event.asCharacters().getData());
                            continue;
                        }
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(LASTNAME)) {
                        event = eventReader.nextEvent();
                        student.setLastName(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(BIRTHDAY)) {
                        event = eventReader.nextEvent();
                        student.setBirthday(dateFormat.parse(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(COURSE)) {
                        event = eventReader.nextEvent();
                        student.setCourse(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart().equals(GROUP)) {
                        event = eventReader.nextEvent();
                        student.setGroup(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }
                }
                //как нашли последний элемент, добавляем
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(STUDENT)) {
                        students.add(student);

                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return students;
    }
}
