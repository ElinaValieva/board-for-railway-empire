package elina.railwayApp;

import elina.railwayApp.model.Schedule;
import elina.railwayApp.model.TimeSchedule;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.util.List;

@ManagedBean
@RequestScoped
@Getter
@Setter
public class ScheduleBean {

    private List<TimeSchedule> schedulesDeparture;
    private List<TimeSchedule> schedulesArrival;

    @PostConstruct
    private void init() throws IOException {
        List<Schedule> schedules = Loader.getSchedules();
        schedulesDeparture = Converter.conventDeparture("Ufa", schedules);
        schedulesArrival = Converter.convertArrival("Ufa", schedules);
    }
}
