package pl.edu.agh.kis.soa; /**
 * Created with IntelliJ IDEA.
 * User: filip.pasternak
 * Date: 3/17/15
 * Time: 9:10 AM
 * To change this template use File | Settings | File Templates.
 */

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.annotation.security.RolesAllowed;
import javax.jws.soap.SOAPBinding;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.ws.api.annotation.AuthMethod;
import org.jboss.ws.api.annotation.TransportGuarantee;
import org.jboss.ws.api.annotation.WebContext;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.logging.Logger;

@Stateless
@WebService(name = "HelloWorld", portName = "MyOwnPort", targetNamespace = "http://soa.kis.agh.edu.pl/ws")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
@SecurityDomain("soapLabWsSD")
@WebContext(transportGuarantee = TransportGuarantee.NONE, authMethod = AuthMethod.BASIC)
@RolesAllowed("AdminRole")
public class HelloWorld {
    private static final Logger logger = Logger.getLogger("HelloWorld");
    private ArrayList<Student> studentsList = new ArrayList<Student>();

    public HelloWorld(){
        String[] subs = {"Angielski", "Polski", "Chemia"};
        addStudent("Jan", "Kowalski", "1245", subs);
        addStudent("Marian", "Kowalski", "1123", subs);
        addStudent("Adam", "Nowak", "121235", subs);
        addStudent("Jan", "Lisowski", "1135", subs);
    }

    public void addStudent(String name, String surname, String pesel, String[] subjects){
        Student s = new Student();
        s.setFirstName(name);
        s.setLastName(surname);
        s.setPesel(pesel);
        for(int i=0; i<subjects.length; i++)
            s.addSubject(subjects[i]);
        studentsList.add(s);
    }

    @WebMethod
    public String hello(@WebParam(name="userName") String name){
        logger.info("hello invoked, name=" + name);
        return "Hello " + name;
    }

    @WebMethod
    public Student getStudent(@WebParam(name="pesel") String pesel){
        logger.info("getStudent invoked, name=" + pesel);
        Student s = new Student();
        s.setFirstName("Jan");
        s.setLastName("Kowalski");
        s.setPesel(pesel);
        s.addSubject("SOA");
        s.addSubject("Bazy Danych");
        s.setIgnore("Nie ma mnie");

        return s;
    }
    @WebMethod
    @XmlElementWrapper(name="Lista")
    @XmlElement(name="Student")
    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(ArrayList<Student> studentsList) {
        this.studentsList = studentsList;
    }
}
