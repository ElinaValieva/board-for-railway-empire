package elina.railwayApp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TimeSchedule {

    /**
     * Station_Main -> to station ....
     * from stations -> Station Main ....
     */
    private String time;
    private String station;
    private String train;
}
