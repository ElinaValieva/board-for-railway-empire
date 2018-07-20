package elina.railwayApp;

import elina.railwayApp.model.Schedule;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
@Getter
@Setter
public class ScheduleBeanTest {

    private List<Schedule> schedules;

    @PostConstruct
    private void init() throws IOException {
        Loader loader = new Loader();
        schedules = loader.getSchedules();
    }
}
