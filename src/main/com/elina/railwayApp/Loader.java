package elina.railwayApp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import elina.railwayApp.model.Schedule;
import elina.railwayApp.utils.Utils;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

public class Loader {


    public static List<Schedule> getSchedules() throws IOException {

        Client client = new Client();
        ObjectMapper objectMapper = new ObjectMapper();
        WebResource webResource = client.resource(Utils.URL_SCHEDULES);
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String response = clientResponse.getEntity(String.class);
        List<Schedule> schedules = objectMapper.readValue(response, new TypeReference<List<Schedule>>() {
        });
        return schedules;
    }

    public static List<String> getStations() throws IOException {
        Client client = new Client();
        ObjectMapper objectMapper = new ObjectMapper();
        WebResource webResource = client.resource(Utils.URL_STATIONS);
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String response = clientResponse.getEntity(String.class);
        List<String> stations = objectMapper.readValue(response, new TypeReference<List<String>>() {
        });
        return stations;
    }
}
