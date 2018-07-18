package elina.railwayApp;

import elina.railwayApp.model.Schedule;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
@Getter
@Setter
public class ScheduleBeanTest {

    private List<Schedule> schedules;

    @PostConstruct
    private void init() {
        schedules = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Schedule schedule = new Schedule();
            schedule.setTrainName("T12" + i);
            schedule.setStation("Station" + i);
            schedule.setTime("8:0" + i);
            schedules.add(schedule);
        }
    }
}
