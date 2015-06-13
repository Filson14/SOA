package pl.edu.agh.kis.soa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by filson on 13.06.15.
 */

@Entity(name="Student")
public class EncjaStudent implements Serializable{

    /*@Id
    @SequenceGenerator(name="seq_student", sequenceName="seq_student_id", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_student")
    private Long id;
*/
    @Id
    @Column(nullable=false, unique=true)
    private int numerAlbumu;

    @Column(length=20, nullable=false, unique=false)
    private String imie;

    @Column(length=40, nullable=false, unique=false)
    private String nazwisko;


    public EncjaStudent(){

    }

    //public Student(Long id, String imie, String nazwisko, String numerAlbumu){
    public EncjaStudent(String imie, String nazwisko, Integer numerAlbumu){
        super();
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerAlbumu = numerAlbumu;
    }
    /*
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    */
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public int getNumerAlbumu() {
        return numerAlbumu;
    }

    public void setNumerAlbumu(int numerAlbumu) {
        this.numerAlbumu = numerAlbumu;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("Student ");
        s.append(this.numerAlbumu);
        s.append("\n");
        s.append(this.imie);
        s.append(" ");
        s.append(this.nazwisko);
        s.append("\n\n");

        return s.toString();
    }
}
