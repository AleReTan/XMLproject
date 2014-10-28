import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class XmlWriter {

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public void saveStudents(String filePath ,List<Student> students) throws Exception {
        //создаем XMLOutputFactory
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        //создаем XMLEventWriter
        XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(filePath));
        //создаем an EventFactory
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        //перевод строки и табуляция
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");

        StartDocument startDocument = eventFactory.createStartDocument();
        eventWriter.add(startDocument);
        //переводим строчку
        eventWriter.add(end);
        // создаем открывающий тег
        StartElement studentStartElement = eventFactory.createStartElement("", "", "students");
        eventWriter.add(studentStartElement);
        eventWriter.add(end);

        for (Student student : students) {
            eventWriter.add(tab);
            StartElement studentStartElement1 = eventFactory.createStartElement("", "", "student");
            eventWriter.add(studentStartElement1);
            eventWriter.add(end);
            eventWriter.add(tab);
            createNode(eventWriter, "firstName", student.getFirstName());
            eventWriter.add(tab);
            createNode(eventWriter, "lastName", student.getLastName());
            eventWriter.add(tab);
            createNode(eventWriter, "birthday", dateFormat.format(student.getBirthday()));
            eventWriter.add(tab);
            createNode(eventWriter, "course", String.valueOf(student.getCourse()));
            eventWriter.add(tab);
            createNode(eventWriter, "group", String.valueOf(student.getGroup()));
            eventWriter.add(tab);
            eventWriter.add(eventFactory.createEndElement("", "", "student"));
            eventWriter.add(end);
        }

        eventWriter.add(eventFactory.createEndElement("", "", "students"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();
    }

    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // создаем начальный тэг
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // заполнем
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // создаем закрывающий тэг
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);

    }
}

