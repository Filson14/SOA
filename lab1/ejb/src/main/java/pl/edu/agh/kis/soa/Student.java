package pl.edu.agh.kis.soa;

/**
 * Created with IntelliJ IDEA.
 * User: filip.pasternak
 * Date: 3/17/15
 * Time: 9:36 AM
 * To change this template use File | Settings | File Templates.
 */
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

public class Student {
    private String firstName;
    private String lastName;
    private String pesel;
    private ArrayList<String> subjects = new ArrayList<String>();
    private String ignore;

    public Student() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setSubjects(ArrayList<String> subjects) {
        this.subjects = subjects;
    }

    @XmlElement(name="imie")
    public String getFirstName() {
        return firstName;
    }

    @XmlElement(name="nazwisko")
    public String getLastName() {
        return lastName;
    }

    @XmlElement(name="pesel")
    public String getPesel() {
        return pesel;
    }

    @XmlElementWrapper(name="przedmioty")
    @XmlElement(name="przedmiot")
    public ArrayList<String> getSubjects() {
        return subjects;
    }

    public void addSubject(String subject){
        subjects.add(subject);
    }

    @XmlTransient
    public String getIgnore(){
        return ignore;
    }

    public void setIgnore(String ignore){
        this.ignore = ignore;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        result.append(this.firstName + " " + this.lastName + NEW_LINE);
        result.append(this.pesel + NEW_LINE);
        result.append("Przedmioty: " + NEW_LINE);
        for(int i=0; i<subjects.size(); i++)
            result.append("  > " + subjects.get(i) + NEW_LINE);
        result.append(NEW_LINE);

        return result.toString();
    }
}

