package pl.edu.agh.kis.soa;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by filson on 13.06.15.
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Local(StudentsDAOInterface.class)
public class StudentsDAO implements StudentsDAOInterface {

    @PersistenceContext(unitName="primary")
    protected EntityManager em;

    public StudentsDAO(){
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("")
        EncjaStudent s1 = new EncjaStudent("filip", "pasternak", 250056);
        EncjaStudent s2 = new EncjaStudent("jan", "nowak", 676429);
        EncjaStudent s3 = new EncjaStudent("kamil", "kowalski", 96381);

        em.persist(s1);
        em.persist(s2);
        em.persist(s3);
    }


    public EntityManager getEM(){
        return em;
    }

    public void setEM(EntityManager em){
        this.em = em;
    }

    public void addStudent(EncjaStudent student){
        em.persist(student);
    }

    public boolean deleteStudent(int id){
        EncjaStudent s = em.find(EncjaStudent.class, id);
        if(s != null){
            em.remove(s);
            return true;
        }else{
            return false;
        }
    }

    public EncjaStudent editStudent(EncjaStudent student){
        return em.merge(student);
    }

    public boolean editStudent(int numerAlbumu, String imie, String nazwisko){
        EncjaStudent s = em.find(EncjaStudent.class, numerAlbumu);
        if(s != null){
            try {
                if (imie != null || imie.length() != 0) s.setImie(imie);
                if (nazwisko != null || nazwisko.length() != 0) s.setImie(nazwisko);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public EncjaStudent getStudent(int id){
        EncjaStudent s = em.find(EncjaStudent.class, id);
        return s;
    }

    public List<EncjaStudent> getStudentsList(){
        Query qry = em.createQuery("SELECT s FROM pl.edu.agh.kis.soa.rest.model.EncjaStudent s");
        return (List<EncjaStudent>)qry.getResultList();
    }
}
