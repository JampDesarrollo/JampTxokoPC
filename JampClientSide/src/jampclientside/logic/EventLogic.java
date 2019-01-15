/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.EventBean;
import jampclientside.exceptions.CreateException;
import jampclientside.exceptions.IdNotOkException;
import jampclientside.exceptions.UpdateException;
import jampclientside.exceptions.NameNotOkException;
import jampclientside.exceptions.DeleteException;
import jampclientside.exceptions.ReadException;
import java.util.Collection;
import java.util.List;


/**
 * Es la interfaz de lógica. Si hay que hacer una llamada entre dos objetos de
 * diferentes clases, se necesita una interfaz.
 *
 * @author Paula
 */
public interface EventLogic {

    public void deleteEvent(Integer idEvent)throws DeleteException;
    public void createEvent(EventBean event)throws CreateException;
    public Collection<EventBean> findAllEvents(Integer idTxoko) throws ReadException;
    public EventBean findEventById(Integer idEvent, Integer idTxoko) throws ReadException, IdNotOkException;
    public EventBean findEventByName(String name, Integer idTxoko) throws ReadException, NameNotOkException;
    public void attendEvent(Integer idEvent, Integer idUser)throws UpdateException;
    public Collection getAllEvents();
}
