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

    @Getter
    @Setter
    private List<TimeSchedule> schedulesDeparture;

    @Getter
    @Setter
    private List<TimeSchedule> schedulesArrival;

    int i = 0;

    private List<String> stations;

    public List<String> getStations() {
        stations = Loader.getStations();
        return stations;
    }

    @Getter
    private String selectedItem = "Saint Petersburg";

    private String lastChangesInfo = " changes ";

    public String getLastChangesInfo() {
        return lastChangesInfo;
    }

    public void update() {
        if (dataManager.getStatusChanges()) {
            lastChangesInfo = dataManager.getLastInfoChanges();
            schedulesArrival = dataManager.updateScheduleArrival(selectedItem);
            schedulesDeparture = dataManager.updateScheduleDeparture(selectedItem);
            log.info("update @schedule ... ");
            dataManager.resetStatusChanges();
        }
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
        schedulesDeparture = dataManager.updateScheduleDeparture(selectedItem);
        schedulesArrival = dataManager.updateScheduleArrival(selectedItem);
    }

//    @Schedule(second = "*/60", minute = "*", hour = "*", persistent = false)
//    public void updateState() {
//        setLastChangesInfo(" ... ");
//        if (dataManager.getStatusChanges()) {
//            lastChangesInfo = dataManager.getLastInfoChanges();
//            schedulesDeparture = dataManager.updateScheduleDeparture(selectedItem);
//            schedulesArrival = dataManager.updateScheduleArrival(selectedItem);
//            log.info("update @schedule ... ");
//            dataManager.resetStatusChanges();
//        }
//    }

    @PostConstruct
    private void init() throws IOException, TimeoutException {
        listener.start();
        schedulesDeparture = dataManager.createScheduleDeparture(selectedItem);
        schedulesArrival = dataManager.createScheduleArrival(selectedItem);
    }

    @PreDestroy
    private void destroy() throws IOException, TimeoutException {
        listener.stop();
    }
}
