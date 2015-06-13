package pl.edu.agh.kis.soa;

import java.util.List;

/**
 * Created by filson on 13.06.15.
 */
public interface StudentsDAOInterface {
    void addStudent(EncjaStudent student);
    boolean deleteStudent(int id);
    EncjaStudent editStudent(EncjaStudent student);
    boolean editStudent(int numerAlbumu, String imie, String nazwisko);
    EncjaStudent getStudent(int id);
    List<EncjaStudent> getStudentsList();
}
