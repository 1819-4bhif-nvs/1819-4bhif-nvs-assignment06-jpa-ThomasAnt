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

import java.time.LocalDateTime;

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
    }

    @Test
    public void familyGraveTest (){
        target = client.target("http://localhost:8085/Graveyard/rs/familygrave");
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
    @Test
    public void GraveyardkeeperTest (){
        target = client.target("http://localhost:8085/Graveyard/rs/graveyardkeeper");
        LocalDateTime ldt = LocalDateTime.now();
        JsonObject jsonObject = Json.createObjectBuilder().add("firstname","Hans").add("lastname","Gummi").add("salary",1220).build();
        Response re = target.request(MediaType.APPLICATION_JSON).post(Entity.json(jsonObject));
        assertThat(re.getStatus(),is(200));
        JsonObject responseObject = re.readEntity(JsonObject.class);

        assertThat(responseObject.getString("firstname"),is("Hans"));
        assertThat(responseObject.getString("lastname"),is("Gummi"));
        assertThat(responseObject.getInt("salary"),is(1220));

        int id = responseObject.getInt("id");

        target = client.target("http://localhost:8085/Graveyard/rs/graveyardkeeper/" + id);
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));
        responseObject = response.readEntity(JsonObject.class);
        assertThat(responseObject.getString("firstname"),is("Hans"));
        assertThat(responseObject.getInt("lastname"),is("Gummi"));
        assertThat(responseObject.getInt("salary"),is(1220));

        target = client.target("http://localhost:8085/Graveyard/rs/graveyardkeeper");
        jsonObject = Json.createObjectBuilder().add("firstname","Hans").add("lastname","Gummi").add("salary",1520).add("worksSince",ldt.toString()).build();
        response = target.request(MediaType.APPLICATION_JSON).put(Entity.json(jsonObject));
        responseObject = response.readEntity(JsonObject.class);
        assertThat(responseObject.getString("salary"),is(1520));

        target = client.target("http://localhost:8085/Graveyard/rs/graveyardkeeper/" + id);
        this.target.request().delete();
    }
    @Test
    public void OneManGraveTest (){
        target = client.target("http://localhost:8085/Graveyard/rs/onemangrave");
        LocalDateTime ldt = LocalDateTime.now();
        JsonObject jsonObject = Json.createObjectBuilder().add("firstname","Margit").add("lastname","Marks").add("age",77).add("price",1000).build();
        Response re = target.request(MediaType.APPLICATION_JSON).post(Entity.json(jsonObject));
        assertThat(re.getStatus(),is(200));
        JsonObject responseObject = re.readEntity(JsonObject.class);

        assertThat(responseObject.getString("firstname"),is("Margit"));
        assertThat(responseObject.getString("lastname"),is("Marks"));
        assertThat(responseObject.getInt("age"),is(77));
        assertThat(responseObject.getInt("price"),is(1000));

        int id = responseObject.getInt("id");

        target = client.target("http://localhost:8085/Graveyard/rs/onemangrave/" + id);
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));
        responseObject = response.readEntity(JsonObject.class);
        assertThat(responseObject.getString("firstname"),is("Margit"));
        assertThat(responseObject.getString("lastname"),is("Marks"));
        assertThat(responseObject.getInt("age"),is(77));
        assertThat(responseObject.getInt("price"),is(1000));
        target = client.target("http://localhost:8085/Graveyard/rs/onemangrave");
        jsonObject = Json.createObjectBuilder().add("firstname","Margit").add("lastname","Marks").add("age",88).add("price",1000).build();
        response = target.request(MediaType.APPLICATION_JSON).put(Entity.json(jsonObject));
        responseObject = response.readEntity(JsonObject.class);
        assertThat(responseObject.getString("age"),is(88));

        target = client.target("http://localhost:8085/Graveyard/rs/onemangrave/" + id);
        this.target.request().delete();
    }
}
