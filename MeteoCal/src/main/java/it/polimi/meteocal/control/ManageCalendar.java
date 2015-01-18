/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.meteocal.control;

import it.polimi.meteocal.entity.User;
import it.polimi.meteocal.entity.Event;
import java.security.Principal;
import java.util.ArrayList;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author Simone
 */

@ManagedBean
@RequestScoped
public class ManageCalendar {
    @PersistenceContext
    EntityManager em;
    
    @Inject
    Principal principal;
    
    @Inject
    RegisterValidation rv;
    
    @Resource
    UserTransaction utx;
    
    private User user;
    
    public void setUser(User user){
        this.user=user;
    }
    
    public User getUser(){
    
        if(this.user==null){
            this.user=new User();
        }
        return user;
    }
    public boolean scheduleEvent(Date date){
        try {
            Iterator<Event> cal;            
           
            user = rv.getLoggedUser();            
            cal = user.getEventCollection().iterator();           
            
            while(cal.hasNext())
            {
                Event e= cal.next();
                if( e.getStartTime().getYear()   <=  date.getYear() &&
                    e.getStartTime().getMonth()  <=  date.getMonth() &&
                    e.getStartTime().getDate()   <=  date.getDate() &&
                    e.getEndTime().getYear()   >=  date.getYear() &&
                    e.getEndTime().getMonth()  >=  date.getMonth() &&
                    e.getEndTime().getDate()   >=  date.getDate())
                            return true;                    
            }
            return false;                                    
        } catch (Exception e) {
            
            e.printStackTrace();
            
            
        }
        return false;
    }
    public List<Event> dayEvent(Date date){
        try {
            Iterator<Event> event; 
            List<Event> list = new ArrayList();
           
            user = rv.getLoggedUser();            
            event = user.getEventCollection().iterator();           
            
            while(event.hasNext())
            {
                Event e= event.next();
                if( e.getStartTime().getYear()   <=  date.getYear() &&
                    e.getStartTime().getMonth()  <=  date.getMonth() &&
                    e.getStartTime().getDate()   <=  date.getDate() &&
                    e.getEndTime().getYear()   >=  date.getYear() &&
                    e.getEndTime().getMonth()  >=  date.getMonth() &&
                    e.getEndTime().getDate()   >=  date.getDate())
                            list.add(e);
            }
            return list;                                    
        } catch (Exception e) {
            
            e.printStackTrace();
            
            
        }
        return null;
    }
}
