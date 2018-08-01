package elina.railwayApp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import elina.railwayApp.model.Schedule;
import elina.railwayApp.utils.Utils;
import lombok.extern.log4j.Log4j;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Log4j
public class Loader {


    public static List<Schedule> getSchedules() {

        Client client = new Client();
        ObjectMapper objectMapper = new ObjectMapper();
        WebResource webResource = client.resource(Utils.URL_SCHEDULES);
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String response = clientResponse.getEntity(String.class);
        List<Schedule> schedules = null;
        try {
            schedules = objectMapper.readValue(response, new TypeReference<List<Schedule>>() {
            });
        } catch (IOException e) {
            log.error("ERROR, CAN'T LOAD SCHEDULES " + e.getMessage());
        }
        return schedules;
    }

    public static Schedule getById(Long id) {
        Client client = new Client();
        ObjectMapper objectMapper = new ObjectMapper();
        WebResource webResource = client.resource(Utils.URL_SCHEDULE_BY_ID + id);
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String response = clientResponse.getEntity(String.class);
        Schedule schedule = null;
        try {
            schedule = objectMapper.readValue(response, new TypeReference<Schedule>() {
            });
        } catch (IOException e) {
            log.error("ERROR, CAN'T LOAD SCHEDULE BY ID " + e.getMessage());
        }
        return schedule;
    }

    public static List<String> getStations() {
        Client client = new Client();
        ObjectMapper objectMapper = new ObjectMapper();
        WebResource webResource = client.resource(Utils.URL_STATIONS);
        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        String response = clientResponse.getEntity(String.class);
        List<String> stations = null;
        try {
            stations = objectMapper.readValue(response, new TypeReference<List<String>>() {
            });
        } catch (IOException e) {
            log.error("ERROR, CAN'T LOAD STATIONS " + e.getMessage());
        }
        return stations;
    }
}
