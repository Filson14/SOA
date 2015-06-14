package pl.edu.agh.kis.soa.rest;

import pl.edu.agh.kis.soa.EncjaStudent;
import pl.edu.agh.kis.soa.StudentsDAO;
import pl.edu.agh.kis.soa.StudentsDAOInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by filson on 13.06.15.
 */

@Stateless
@Path("RestJPA")
public class StudentResource {
    @EJB
    private StudentsDAOInterface studentDao;

    public StudentResource(){

    }

    @GET
    @Path("check")
    public Response checkIfWOrks(){
        return Response.ok("Działa", MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("getStudent/{id}")
    public Response getStudent(@PathParam("id") int numerAlbumu){
        EncjaStudent s = studentDao.getStudent(numerAlbumu);
        if(s != null){
            return Response.ok(s, MediaType.APPLICATION_JSON).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("getAll")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllStudents(){
        List<EncjaStudent> list = studentDao.getStudentsList();
        StringBuilder resp = new StringBuilder("Lista studentow: \n");
        for(EncjaStudent s : list){
            resp.append(s.toString());
        }

        return Response.ok(resp.toString(), MediaType.TEXT_PLAIN).build();
    }

    @POST
    @Path("addNew/{numerAlbumu}/{imie}/{nazwisko}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewStudent(@PathParam("numerAlbumu") int numerAlbumu, @PathParam("imie") String imie, @PathParam("nazwisko") String nazwisko){
        EncjaStudent s = new EncjaStudent(imie, nazwisko, numerAlbumu);
        if(studentDao.addStudent(s))
            return Response.ok(s, MediaType.APPLICATION_JSON).build();
        else
            return Response.status(Response.Status.CONFLICT).build();
    }

    @PUT
    @Path("edit/{numerAlbumu}")
    public Response editStudent(@PathParam("numerAlbumu") int numerAlbumu, @QueryParam("imie") String imie, @QueryParam("nazwisko") String nazwisko){
        EncjaStudent s = studentDao.getStudent(numerAlbumu);
        if(s != null) {
            if (imie != null && imie.length() != 0) s.setImie(imie);
            if (nazwisko != null && nazwisko.length() != 0) s.setNazwisko(nazwisko);
            studentDao.editStudent(s);
            return Response.ok("Sukces", MediaType.TEXT_PLAIN).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    public Response deleteStudent(@PathParam("id") int numerAlbumu){
        if(studentDao.deleteStudent(numerAlbumu)){
            return Response.ok("Usunieto studenta " + numerAlbumu, MediaType.TEXT_PLAIN).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
