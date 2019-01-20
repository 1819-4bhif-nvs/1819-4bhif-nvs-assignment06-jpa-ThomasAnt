package at.htl;

import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.fail;

public class GraveyardIT {
    private Client client;
    private WebTarget target;

    @Before
    public void init(){
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8085/Graveyard/rs/graveyard");
    }

    @Test
    public void crud (){

    }
}
