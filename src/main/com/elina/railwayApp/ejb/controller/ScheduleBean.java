package elina.railwayApp.ejb.controller;

import elina.railwayApp.ejb.service.TimeScheduleBean;
import elina.railwayApp.model.TimeSchedule;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ViewScoped
@ManagedBean
public class ScheduleBean {

    @EJB
    private TimeScheduleBean timeScheduleBean;

    public List<TimeSchedule> getScheduleDeparture() {
        return timeScheduleBean.getSchedulesDeparture();
    }

    public List<TimeSchedule> getScheduleArrival() {
        return timeScheduleBean.getSchedulesArrival();
    }

    public void update() {
        timeScheduleBean.update();
    }

    public String getLastChangedInfo() {
        return timeScheduleBean.getLastChangesInfo();
    }

    public List<String> getStations() {
        return timeScheduleBean.getStations();
    }

    public String getSelectedItem() {
        return timeScheduleBean.getSelectedItem();
    }

    public void setSelectedItem(String selectedItem) {
        timeScheduleBean.setSelectedItem(selectedItem);
    }
}
