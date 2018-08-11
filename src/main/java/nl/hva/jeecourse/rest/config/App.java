package nl.hva.jeecourse.rest.config;


import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * The application config
 *
 * @author marciofk
 */
@ApplicationPath("api")
public class App extends ResourceConfig {

    public App() {
        System.out.println("Ops");
    }



}
