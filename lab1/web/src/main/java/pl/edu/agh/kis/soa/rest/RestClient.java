package pl.edu.agh.kis.soa.rest;


import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.omg.CORBA.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by filson on 25.05.15.
 */
public class RestClient {
    public static final String REST_URL = "http://localhost:8080/lab1-web/test/";
    public static final String REST_URL_JPA = "http://localhost:8080/lab1-web/RestJPA/";

    public static void handleResponse(ClientResponse response){
        if(response.getStatus() != 200){
            throw new RuntimeException("Błąd HTTP: Kod odpowiedzi " + response.getStatus());
        }else{
            System.out.println(response.getEntity(String.class));
        }
    }

    public static void main(String [] args) {
        Client client = Client.create();
        WebResource webResource;
        ClientResponse response;
/*
        webResource = client
                .resource(REST_URL_JPA + "addNew/250056/filip/pasternak");
        webResource.post();

        webResource = client
                .resource(REST_URL_JPA + "addNew/123456/jan/nowak");
        webResource.post();

        webResource = client
                .resource(REST_URL_JPA + "addNew/654321/stanislaw/kowalski");
        webResource.post();
*/
        webResource = client.resource(REST_URL_JPA + "getAll");
        response = webResource.get(ClientResponse.class);
        handleResponse(response);

        webResource = client
                .resource(REST_URL_JPA + "edit/654321?imie=staszek");
        webResource.put();

        webResource = client
                .resource(REST_URL_JPA + "delete/123456");
        webResource.delete();

        webResource = client.resource(REST_URL_JPA + "getAll");
        response = webResource.get(ClientResponse.class);
        handleResponse(response);

        webResource = client.resource(REST_URL_JPA + "getStudent/250056");
        response = webResource.get(ClientResponse.class);
        handleResponse(response);


        /* KOD KLIENTA Z ZALICZENIA TEMATU REST
        // GET z QueryParam
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add("name", "filip");
        params.add("surname", "pasternak");

        webResource = client
                .resource(REST_URL + "hello")
                .queryParams(params);

        response = webResource.accept("application/json")
                .get(ClientResponse.class);
        System.out.println("GET i QueryParams");
        handleResponse(response);

        // GET z PathParam
        webResource = client.resource(REST_URL + "anotherHello/filip/pasternak");
        response = webResource.get(ClientResponse.class);
        System.out.println("GET i PathParams:");
        handleResponse(response);

        ///////////////   MODYFIKACJA ELEMENTÓW W KONTENERZE   ////////////////////
        // Test metody GET - printAllPeople
        // Wypisanie obecnych
        webResource = client.resource(REST_URL + "getAllPeople");
        response = webResource.get(ClientResponse.class);
        handleResponse(response);

        // Dodanie nowego
        webResource = client.resource(REST_URL + "addNewPerson/jan/kowalski");
        webResource.post();
        System.out.println("Dodano Jana Kowalskiego");

        // Wypisanie obecnych
        webResource = client.resource(REST_URL + "getAllPeople");
        response = webResource.get(ClientResponse.class);
        handleResponse(response);

        // Edycja osoby z indeksem 2
        webResource = client.resource(REST_URL + "modifyPerson/2/nowak");
        webResource.put();
        System.out.println("Zmieniono nazwisko osoby z indeksem 2.");

        // Wypisanie obecnych
        webResource = client.resource(REST_URL + "getAllPeople");
        response = webResource.get(ClientResponse.class);
        handleResponse(response);

        // Usuwanie osoby z indeksem 3
        webResource = client.resource(REST_URL + "deletePerson/3");
        webResource.delete();
        System.out.println("Usunieto osobe z indeksem 3.");

        // Wypisanie obecnych
        webResource = client.resource(REST_URL + "getAllPeople");
        response = webResource.get(ClientResponse.class);
        handleResponse(response);

        ////////////////////////////////////////////////////////////////


        ///////////////////// AUTENTYKACJA ///////////////////////////////////////

        System.out.println("Logowanie");
        // logowanie - w przypadku błędnych danych logowania wyrzuca wyjątek z błędem http 403.
        // login - dowolny, haslo - password
        webResource = client.resource(REST_URL + "authorize?login=filip&password=password");
        //response = webResource.get(ClientResponse.class);
        //handleResponse(response);
        webResource.post();

        //Odpalenie metody wymagającej autentykacji
        webResource = client.resource(REST_URL + "helloWithPassword");
        response = webResource
                .accept("application/json")
                .get(ClientResponse.class);
        System.out.println(response.getEntity(String.class));
        //handleResponse(response);

        ////////////////////// POBRANIE PLIKÓW PRZEZ GET   ///////////////////////

        // POBRANIE PLIKU PDF
        webResource = client.resource(REST_URL + "downloadPDF");
        response = webResource.get(ClientResponse.class);
        if(response.getStatus() != 200) handleResponse(response);

        File responsePdf = response.getEntity(File.class);
        File newFile = new File(System.getProperty("user.home")+ "/Pobrane/pdf_from_rest.pdf");
        responsePdf.renameTo(newFile);

        try {
            FileWriter fw = new FileWriter(responsePdf);
            fw.close();
            System.out.println("Plik pdf zapisany w katalogu Pobrane, w katalogu domowym.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // POBRANIE PLIKU PNG
        webResource = client.resource(REST_URL + "downloadPNG");
        response = webResource.get(ClientResponse.class);
        if(response.getStatus() != 200) handleResponse(response);

        File responsePng = response.getEntity(File.class);
        newFile = new File(System.getProperty("user.home")+ "/Pobrane/png_from_rest.pdf");
        responsePng.renameTo(newFile);

        try {
            FileWriter fw2 = new FileWriter(responsePng);
            fw2.close();
            System.out.println("Plik png zapisany w katalogu Pobrane, w katalogu domowym.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////////////////////////////////////////////
        */
    }
}
