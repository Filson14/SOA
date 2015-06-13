package pl.edu.agh.kis.soa.rest;

import pl.edu.agh.kis.soa.rest.model.Person;
import pl.edu.agh.kis.soa.rest.model.Test;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: filip.pasternak
 * Date: 5/5/15
 * Time: 9:40 AM
 * To change this template use File | Settings | File Templates.
 */

@Stateless
@Path("test")
public class TestResource {
     private static Logger logger = Logger.getLogger("TestResource");
    //private String PNG_FILE_PATH = "/Network/Servers/labserver.local/Volumes/Data/NetUsers/2013.2n.filip.pasternak/soa_maven/Rest/lab1/web/src/main/java/pl/edu/agh/kis/soa/rest/obraz.png";
    //private String PDF_FILE_PATH = "/Network/Servers/labserver.local/Volumes/Data/NetUsers/2013.2n.filip.pasternak/soa_maven/Rest/lab1/web/src/main/java/pl/edu/agh/kis/soa/rest//pdf.pdf";
    private String PNG_FILE_PATH = "/home/filson/obraz.png";
    private String PDF_FILE_PATH = "/home/filson/pdf.pdf";
    private ArrayList<Person> people = new ArrayList<Person>();

    /**
     * http://localhost:8080/lab1-web/test
     */

    public TestResource(){
        people.add(new Person("filip","pasternak", 24));
        people.add(new Person("allen","iverson", 40));
        people.add(new Person("derrick","rose", 26));
    }

    @POST // wrócić na POST
    @Path("authorize")
    public Response authorize(@QueryParam("login") String login, @QueryParam("password") String password, @Context HttpServletRequest request){
        logger.info(String.format("%s", "authorize invoked, login" + login));
        HttpSession httpSession = request.getSession();
        if(httpSession == null){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        // sprawdzanie hasla
        if( !login.isEmpty() && !login.equals("") && password.equals("password")){
            //zapis w sesji loginu usera
            httpSession.setAttribute("login", login); //zapisanie informacji o zalogowanym do sesji http
            return Response.ok("Sesja: " + httpSession + "\nLogin: " + login).build();
        }else{
            httpSession.setAttribute("login", null);
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("helloWithPassword")
    public Response helloWithPassword(@Context HttpServletRequest request){
        logger.info(String.format("Hello authorized invoked"));
        HttpSession session = request.getSession();
        if(session == null){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        String login = (String) session.getAttribute("login"); //odczytanie informacji o zalogowanym uzytkowniku z sesji http
        logger.info("Hello authorized, user logged: " + login);
        if(login != null){
            Person p = new Person(login, "unknown");
            return Response.ok(p, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
        //return Response.ok("Sesja: " + session + "\nLogin: " + login).build();
    }

    @GET
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(@QueryParam("name") String name, @QueryParam("surname") String surname){
        logger.info("hello invoked, name = " + name);

        Person test;
        test = (name == null || surname == null) ? new Person() : new Person(name, surname);
        return Response.ok(test, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("anotherHello/{name}/{surname}")
    @Produces(MediaType.TEXT_HTML)
    public Response helloHTML(@PathParam("name") String name, @PathParam("surname") String surname){
        StringBuilder response = new StringBuilder();
        response.append("<table border='2'><tr>");
        response.append("<td><p>Imie</p></td> <td><p>Nazwisko</p></td>");
        response.append("</tr><tr>");
        response.append("<td>" + name + "</td>");
        response.append("<td>" + surname + "</td>");
        response.append("</tr></table>");

        return Response.ok(response.toString(), MediaType.TEXT_HTML).build();
    }

    @GET
    @Path("downloadPDF")
    @Produces("application/pdf")
    public Response getPDF(){
        File file = new File(PDF_FILE_PATH);
        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "attachment; filename=RestDownloaded_pdf.pdf");

        return response.build();
    }

    @GET
    @Path("downloadPNG")
    @Produces("image/png")
    public Response getPNG(){
        File file = new File(PNG_FILE_PATH);
        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "attachment; filename=RestDownloaded_png.png");

        return response.build();
    }

    @GET
    @Path("getAllPeople")
    @Produces(MediaType.TEXT_PLAIN)
    public Response printAllPeople(){
        StringBuilder s = new StringBuilder();
        for(int i=0; i<people.size(); i++){
            s.append(people.get(i));
        }
        return Response.ok(s.toString(), MediaType.TEXT_PLAIN).build();
    }

    @POST
    @Path("addNewPerson/{name}/{surname}")
    public Response addNewPerson(@PathParam("name") String name, @PathParam("surname") String surname){
        Person p = new Person(name, surname);
        people.add(p);
        return Response.ok((Object)p).build();
    }

    @PUT
    @Path("modifyPerson/{index}/{newSurname}")
    public Response modifyPerson(@PathParam("index") int index, @PathParam("newSurname") String newSurname){
        StringBuilder s = new StringBuilder();
        Response.ResponseBuilder response;
        if(people.isEmpty()){
            s.append("Brak elementow do modyfikacji!");
            response = Response.notModified(s.toString());
        }else if(index >= 0 && index < people.size()){
            s.append("Zmieniono nazwisko z '" + people.get(index).getSurname() + "' na '" + newSurname + "'\n");
            people.get(index).setSurname(newSurname);
            response = Response.ok(s.toString(), MediaType.TEXT_PLAIN);
        }else{
            s.append("Niepoprawny indeks!");
            response = Response.notModified(s.toString());
        }
        return Response.ok(s.toString(), MediaType.TEXT_PLAIN).build();
    }

    @DELETE
    @Path("deletePerson/{index}")
    public Response deletePerson(@PathParam("index") int index){
        StringBuilder s = new StringBuilder();
        Response.ResponseBuilder response;
        if(people.isEmpty()){
            s.append("Brak elementow do usuniecia!");
            response = Response.notModified(s.toString());
        }else if(index >= 0 && index < people.size()){
            people.remove(index);
            s.append("Usunieto osobe z indeksem " + index);
            response = Response.ok(s.toString());
        }else{
            s.append("Niepoprawny indeks!");
            response = Response.notModified(s.toString());
        }
        return response.build();
    }
}
