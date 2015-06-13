package pl.edu.agh.kis.soa.rest.model;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: filip.pasternak
 * Date: 5/5/15
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class Person {
    private String name;
    private String surname;
    private Integer age;
    private ArrayList<String> schools;

    public Person() {
        this.name = "N/A";
        this.surname = "N/A";
        this.age = new Integer(0);
        this.schools = new ArrayList<String>();
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.age = new Integer(0);
        this.schools = new ArrayList<String>();
        setDefaultSchools();
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = new Integer(age);
        this.schools = new ArrayList<String>();
        setDefaultSchools();
    }

    public Person(String name, String surname, int age, ArrayList<String> schools) {
        this.name = name;
        this.surname = surname;
        this.age = new Integer(age);
        this.schools = schools;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ArrayList<String> getSchools() {
        return schools;
    }

    public void setSchools(ArrayList<String> schools) {
        this.schools = schools;
    }

    public void setDefaultSchools(){
        schools.add("Podstawowka");
        schools.add("Gimnazjum");
        schools.add("Technikum");
        schools.add("Uniwersytet");
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("\n");
        s.append(this.name + " " + this.surname + ", lat: " + this.age + "\n");
        s.append("Szkoly:\n");
        for(int i=0; i<this.schools.size(); i++)
            s.append("   " + this.schools.get(i) + "\n");
        s.append("---------------------------------------\n");

        return s.toString();
    }

}
