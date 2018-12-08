package at.htl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GraveyardSt {
    private Client client;
    private WebTarget target;

    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8085/Graveyard/rs");
    }
    @Test
    public void T01_GetGraveyard(){
        Response response = target.path("/graveyard").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));
    }

    @Test
    public void T02_GetGraveyardkeeper(){
        Response response = target.path("/graveyardkeeper").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));
    }

    @Test
    public void T03_GetFamilyGraves(){
        Response response = target.path("/familygrave").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));
        JsonArray ja = response.readEntity(JsonArray.class);
        assertThat(ja.size(),is(2));
    }

    @Test
    public void T04_OneManGraves(){
        Response response = target.path("/onemangrave").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));
        JsonArray ja = response.readEntity(JsonArray.class);
        assertThat(ja.size(),is(1));
    }

    @Test
    public void T05_InsertAndDeleteFamilyGrave(){
        JsonObject jsonObject = Json.createObjectBuilder().add("Location","Linz").add("price",100L).add("Familyname","Hauer").add("Numberburried",3L).build();
        Response r = target.path("/familygrave").request().post(Entity.json(jsonObject));
        assertThat(r.getStatus(),is(204));
        Response response = target.path("/familygrave").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));
        JsonArray ja = response.readEntity(JsonArray.class);
        assertThat(ja.size(),is(3));
        System.out.println(r.readEntity(long.class));
        Response response1 = target.path("/familygrave/3").request().delete();
        assertThat(response1.getStatus(),is(204));
        response = target.path("/familygrave").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));
        ja = response.readEntity(JsonArray.class);
        assertThat(ja.size(),is(2));
    }

    @Test
    public void T06_InsertAndDeleteOneManGrave(){
        JsonObject jsonObject = Json.createObjectBuilder().add("Location","Linz").add("price",100L).add("Age","22").add("FirstName","Alex").add("LastName","Maurer").build();
        Response r = target.path("/onemangrave").request().post(Entity.json(jsonObject));
        assertThat(r.getStatus(),is(204));
        Response response = target.path("/onemangrave").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));
        JsonArray ja = response.readEntity(JsonArray.class);
        assertThat(ja.size(),is(2));
        System.out.println(r.readEntity(long.class));
        Response response1 = target.path("/onemangrave/2").request().delete();
        assertThat(response1.getStatus(),is(204));
        response = target.path("/onemangrave").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(),is(200));
        ja = response.readEntity(JsonArray.class);
        assertThat(ja.size(),is(1));
    }
}
