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

public class FamilyGraveIT {
    private Client client;
    private WebTarget target;

    @Before
    public void init()
    {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8085/Graveyard/rs/familygrave");
    }

    @Test
    public void TEST (){
        JsonObject jsonObject = Json.createObjectBuilder().add("familyName","Kaliz").add("numberBurried",4).add("price",220).build();
        Response re = target.request(MediaType.APPLICATION_JSON).post(Entity.json(jsonObject));
        assertThat(re.getStatus(),is(200));
        JsonObject responseObject = re.readEntity(JsonObject.class);

        assertThat(responseObject.getString("familyName"),is("Kaliz"));
        assertThat(responseObject.getInt("numberBurried"),is(4));
        assertThat(responseObject.getInt("price"),is(220));

        int id = responseObject.getInt("id");

        target = client.target("http://localhost:8085/Graveyard/rs/familygrave/" + id);
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));
        responseObject = response.readEntity(JsonObject.class);
        assertThat(responseObject.getString("familyName"),is("Kaliz"));
        assertThat(responseObject.getInt("numberBurried"),is(4));
        assertThat(responseObject.getInt("price"),is(220));

        target = client.target("http://localhost:8085/Graveyard/rs/familygrave");
        jsonObject = Json.createObjectBuilder().add("familyName","Kalitz").add("numberBurried",4).add("price",220).build();
        response = target.request(MediaType.APPLICATION_JSON).put(Entity.json(jsonObject));
        responseObject = response.readEntity(JsonObject.class);
        assertThat(responseObject.getString("familyName"),is("Kalitz"));

        target = client.target("http://localhost:8085/Graveyard/rs/familygrave/" + id);
        this.target.request().delete();


    }
}
