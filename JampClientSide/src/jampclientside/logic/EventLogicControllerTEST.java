/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;


import jampclientside.entity.EventBean;
import jampclientside.exceptions.CreateException;
import jampclientside.exceptions.DeleteException;
import jampclientside.exceptions.IdNotOkException;
import jampclientside.exceptions.NameNotOkException;
import jampclientside.exceptions.ReadException;
import jampclientside.exceptions.UpdateException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

/**
 *
 * @author WIN10
 */
public class EventLogicControllerTEST implements EventLogic {
        private ArrayList<EventBean> events;
    private static final Logger LOGGER = Logger.getLogger("package.class");  
      public EventLogicControllerTEST(){
        LOGGER.info("Building fake events data for testing UI.");
        events=new ArrayList();
        //Create 25 UserBean fake data objects.
        for(float i=0;i<25;i++)
            events.add(new EventBean("cumple"+i,"cumple de paula"+i,"zorionak"+i,+i));
    }
        

    @Override
    public void deleteEvent(Integer idEvent) throws DeleteException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createEvent(EventBean event) throws CreateException {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<EventBean> findAllEvents(Integer idTxoko) throws ReadException {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return null;
    }

    @Override
    public EventBean findEventById(Integer idEvent, Integer idTxoko) throws ReadException, IdNotOkException {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return null;
    }

    @Override
    public EventBean findEventByName(String name, Integer idTxoko) throws ReadException, NameNotOkException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return null;
    }

    @Override
    public void attendEvent(Integer idEvent, Integer idUser) throws UpdateException {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
    }

    @Override
    public Collection getAllEvents(){
        LOGGER.info("Return the events.");
     return events;
     
     }
}
