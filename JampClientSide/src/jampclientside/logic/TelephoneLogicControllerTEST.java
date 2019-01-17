/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.TelephoneBean;
import jampclientside.exceptions.CreateException;
import jampclientside.exceptions.DeleteException;
import jampclientside.exceptions.UpdateException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author odoo
 */
public class TelephoneLogicControllerTEST implements TelephoneLogic {
      private ArrayList<TelephoneBean> telephones;
      public TelephoneLogicControllerTEST(){
        telephones=new ArrayList();
        //Create 25 TelephoneBean fake data objects.
        for(int i=0;i<25;i++)
            telephones.add(new TelephoneBean("Telefono"+i,"Telefono de Prueba"+i, i, false));
    
    }

    @Override
    public void deleteTelephone(TelephoneBean phone) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateTelephone(TelephoneBean phone) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createTelephone(TelephoneBean phone) throws CreateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TelephoneBean> findAllTelephone() {
        return telephones;
    }
    
    @Override
    public List<TelephoneBean> findAllTelephoneByTxoko() {
        return telephones;
    }

    @Override
    public List<TelephoneBean> findTelephoneById(Integer idTelephone) {
          return telephones;
    }

    @Override
    public List<TelephoneBean> findTelephoneByName(String name) {
          return telephones;
    }
    
}
