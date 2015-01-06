package it.polimi.business.meteocal.boundary;

import it.polimi.meteocal.business.control.ManageEvent;
import it.polimi.meteocal.business.entity.Event;
import it.polimi.meteocal.business.entity.Location;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class EventDetails {
    
    @EJB
    private ManageEvent me;

    private Event event;
    private Location loc;
    private String message;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy : HH:mm");
    private String startDateStr;
    private String endDateStr;
    
    
    public Date dateConverter(String input){
        
        Date date=null;
        try {
            date = formatter.parse(input);
        } catch (ParseException ex) {
            Logger.getLogger(EventDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return date;
    }
    public String getStartDateStr() {
        return this.startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }
    
    public String getEndDateStr() {
        return this.endDateStr;
    }

    public void setEndDateStr(String startDateStr) {
        this.endDateStr = startDateStr;
    }
    
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Location getLoc() {
        if (loc == null) {
            loc = new Location();
        }
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }
    
    public Event getEvent() {
        if (event == null) {
            event = new Event();
        }
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String create() {		
        
        
        event.setStartTime(dateConverter(startDateStr));
	event.setEndTime(dateConverter(endDateStr));

        me.createEvent(event,loc);
        
        message="Event Created!";
        
        return "/loggeduser/invitePeople.xhtml?faces-redirect=true&event="+event.getIdEvent();
    }
    
}
