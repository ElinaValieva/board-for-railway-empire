package elina.railwayApp;

import elina.railwayApp.model.TimeSchedule;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@ManagedBean
@ViewScoped
@Log4j
public class ScheduleBean {

    private DataManager dataManager = DataManager.getInstance();
    private Listener listener = new Listener();
    private Loader loader = Loader.getInstance();

    @Getter
    @Setter
    private List<TimeSchedule> schedulesDeparture;

    @Getter
    @Setter
    private List<TimeSchedule> schedulesArrival;

    @Getter
    private List<String> stations = loader.getStations();

    @Getter
    private String selectedItem = "Saint Petersburg";

    @Getter
    private String lastChangesInfo = "No changes ... ";

    public void update() {
        if (dataManager.getStatusChanges()) {
            lastChangesInfo = dataManager.getLastInfoChanges();
            log.info("update @schedule ... ");
            dataManager.resetStatusChanges();
        }
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
        schedulesDeparture = dataManager.loadScheduleDeparture(selectedItem);
        schedulesArrival = dataManager.loadScheduleArrival(selectedItem);
    }

    @PostConstruct
    private void init() throws IOException, TimeoutException {
        listener.start();
        schedulesDeparture = dataManager.loadScheduleDeparture(selectedItem);
        schedulesArrival = dataManager.loadScheduleArrival(selectedItem);
    }

    @PreDestroy
    private void destroy() throws IOException, TimeoutException {
        listener.stop();
    }
}
