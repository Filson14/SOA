package pl.edu.agh.kis.soa.rest;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: filip.pasternak
 * Date: 5/5/15
 * Time: 9:36 AM
 * To change this template use File | Settings | File Templates.
 */

@ApplicationPath("/") // Pod jaką względną ścieżką będzie dostępna aplikacja
public class RestApplication extends Application{

    @Override
    public Set<Class<?>> getClasses(){
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(TestResource.class);
        return classes;
    }

    @Override
    public Set<Object> getSingletons(){
        Set<Object> singletons = new HashSet<Object>();
        singletons.add(new JacksonJsonProvider());
        return singletons;
    }

}
