/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.business.meteocal.boundary;

import java.util.Date;
import java.text.SimpleDateFormat;

import it.polimi.meteocal.business.control.ManageCalendar;
import it.polimi.meteocal.business.entity.User;
import it.polimi.meteocal.business.entity.Event;
import java.util.Locale;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Simone
 */
@Named
@RequestScoped
public class Calendar {
    
    @EJB
    private ManageCalendar mc;
    
    private User user;
    private static Date date;
    private boolean set =false;
    
    public Date getDate() {
        if(date == null){
            date=new Date();
            date.setDate(1);
        }
        return date;
    }   
    public void today(){
        date=new Date();
    }
    public String getDayNum(){
        return new SimpleDateFormat("d",Locale.US).format(this.getDate());
    } 
    public String getDayStr(){
        return new SimpleDateFormat("E",Locale.US).format(this.getDate());
    }
    public String getMonthStr(){
        return new SimpleDateFormat("MMMM",Locale.US).format(this.getDate());
    }
    public String getMonthNum(){
        return new SimpleDateFormat("M",Locale.US).format(this.getDate());
    }
    public String getYear(){
        return new SimpleDateFormat("y",Locale.US).format(this.getDate());
    }
    public String getHour(){
        return new SimpleDateFormat("H",Locale.US).format(this.getDate());
    }
    public String getMinute(){
        return new SimpleDateFormat("m",Locale.US).format(this.getDate());
    }
    public void next(){
            date.setDate(1);
            date.setMonth(this.getDate().getMonth()+1);
            set = false;
    }
    public void prev(){
        date.setDate(1);
        date.setMonth(this.getDate().getMonth()-1);
        set = false;
    }
    public String configure(int pos){
         String result="";
         if(!set)
                if(date.getDay()==pos%7){
                     result=this.getDayNum();
                     Date tmp = (Date)date.clone();
                     tmp.setDate(tmp.getDate()+1);
                     if(date.getMonth()==tmp.getMonth())
                         date.setDate(date.getDate()+1); 
                     else
                          set=true;
                }                
         return result;
     }
    
    public void setUser(User user){
        this.user=user;
    }
    
    public User getUser(){
    
        if(this.user==null){
            this.user=new User();
        }
        return user;
    }
    
}
