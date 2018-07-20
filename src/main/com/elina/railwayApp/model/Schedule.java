package elina.railwayApp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Schedule implements Serializable {

    private Integer id;
    private String stationDepartureName;
    private String stationArrivalName;
    private String trainName;
    private String dateDeparture;
    private String dateArrival;
    private Integer price;

}
