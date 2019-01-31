/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;


import jampclientside.entity.EventBean;
import jampclientside.entity.TxokoBean;
import jampclientside.exceptions.BusinessLogicException;
import jampclientside.exceptions.CreateException;
import jampclientside.exceptions.DeleteException;
import jampclientside.exceptions.IdNotOkException;
import jampclientside.exceptions.NameNotOkException;
import jampclientside.exceptions.ReadException;
import jampclientside.exceptions.UpdateException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 *
 * @author WIN10
 */
public class EventLogicControllerTEST implements EventLogic {

    private ArrayList<EventBean> events;
    private static final Logger LOGGER = Logger.getLogger("package.class");
    private final LocalDateTime fechaActual =LocalDateTime.now();
    public EventLogicControllerTEST() {
        LOGGER.info("Building fake events data for testing UI.");
          LOGGER.info("Todays date "+ fechaActual);
        events = new ArrayList();
        //Create 25 UserBean fake data objects.
        //generar una 
      /*  for (float i = 0; i < 25; i++) {
            events.add(new EventBean("cumple" + i, "cumple de paula" + i,fechaActual, "zorionak" + i, +i));
        }
        */
    }

    @Override
    public void deleteEvent(EventBean event) throws BusinessLogicException{
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createEvent(EventBean event) throws BusinessLogicException {
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<EventBean> findAllEvents(String idTxoko) throws BusinessLogicException{
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    @Override
    public EventBean findEventByIdByTxoko(String idEvent, String idTxoko)throws IdNotOkException,BusinessLogicException {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    @Override
    public EventBean findEventByName(String name, String idTxoko)throws NameNotOkException,BusinessLogicException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }
    @Override
    public Collection<EventBean> getAllEvents() throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateEvent(EventBean event) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
