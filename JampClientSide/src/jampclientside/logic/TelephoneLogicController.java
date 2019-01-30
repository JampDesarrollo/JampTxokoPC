/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;


import jampclientside.entity.TelephoneBean;
import jampclientside.exceptions.BusinessLogicException;
import static jampclientside.logic.TelephoneLogicController.collection;
import static jampclientside.logic.TelephoneLogicController.mongoDB;
import static jampclientside.logic.TelephoneLogicController.mongoclient;
import java.util.List;
import java.util.logging.Logger;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;


/**
 *
 * Class that implements the logic
 * interface.
 *
 * @author Julen
 */
public class TelephoneLogicController implements TelephoneLogic {

    /**
     * 
     */
    public static MongoClient mongoclient = MongoClients.create("mongodb://localhost:27017/jamp");
    
    /**
     * 
     */
    public static MongoDatabase mongoDB = mongoclient.getDatabase("jamp");
    
    /**
     * 
     */
    public static MongoCollection<Document> collection = mongoDB.getCollection("telephones");


    /**
     * Attribute to appear the information text.
     */
    private static final Logger LOGGER
            = Logger.getLogger("jamp.pc.logic.IlogicImplementationTelephone");

    /**
     * this method is for delete telephones.
     * @param phone
     * @throws jampclientside.exceptions.BusinessLogicException
     */
    @Override
    public void deleteTelephone(TelephoneBean phone) throws BusinessLogicException {
        collection.deleteOne(Filters.eq("name",phone.getName()));
    }

    /**
     * This method is for update telephones.
     * @param phone 
     * @throws jampclientside.exceptions.BusinessLogicException 
     */
    @Override
    public void updateTelephone(TelephoneBean phone) throws BusinessLogicException {
        Document setName =  new Document("$set", new Document("name",phone.getName()));
        collection.updateOne(new Document("idTelephone",phone.getId()), setName);
        
        Document setDescription =  new Document("$set", new Document("name",phone.getName()));
        collection.updateOne(new Document("idTelephone",phone.getId()), setDescription);
        
        Document setTelephone =  new Document("$set", new Document("name",phone.getName()));
        collection.updateOne(new Document("idTelephone",phone.getId()), setTelephone);
        
        Document setTown =  new Document("$set", new Document("name",phone.getName()));
        collection.updateOne(new Document("idTelephone",phone.getId()), setTown);
       
    }

    /**
     * This method is for create tellephones
     * @param phone 
     * @throws jampclientside.exceptions.BusinessLogicException 
     */
    @Override
    public void createTelephone(TelephoneBean phone) throws BusinessLogicException {
        Document document = new Document();
        
        document.put("name", phone.getName());
        document.put("description", phone.getDescription());
        document.put("telephone", phone.getTelephone());
        document.put("town", phone.getTown());
        
        collection.insertOne(document);
    }

    /**
     * This method is for find all telephones.
     * @return Collection of telephones 
     * @throws jampclientside.exceptions.BusinessLogicException 
     */
    @Override
    public List<TelephoneBean> findAllTelephone() throws BusinessLogicException {
        FindIterable<Document> fi;
        MongoCursor<Document> cursor;
        Document itera;
        fi = collection.find();
        cursor = fi.iterator();
        List<TelephoneBean> telephones = new ArrayList<>();
            try {
                if (!cursor.hasNext()) {
                    System.out.println("No se ha encontrado el telefono");
                }
                int i = 0;
                while (cursor.hasNext()) {
                    itera = cursor.next();
                    TelephoneBean telephone= new TelephoneBean();
                    telephone.setId(itera.getString("id"));
                    telephone.setName(itera.getString("name"));
                    telephone.setDescription(itera.getString("description"));
                    telephone.setTown(itera.getString("town"));
                    telephone.setTelephone(itera.getString("telephone"));

                    
                    telephones.add(telephone);
                    
                }
            } finally {
                cursor.close();
            }
        
        return telephones;
    }

    /**
     * This method is for find telephones by Id.
     * @param idTelephone
     * @return a telephone
     * @throws BusinessLogicException 
     */
    @Override
    public TelephoneBean findTelephoneById(Integer idTelephone) throws BusinessLogicException {
        FindIterable<Document> fi;
        MongoCursor<Document> cursor;
        fi = collection.find();
        cursor = fi.iterator();
        TelephoneBean telephone = new TelephoneBean();
            try {
                if (!cursor.hasNext()) {
                    System.out.println("No se ha encontrado el telefono");
                }
                int i = 0;
                while (cursor.hasNext()) {
                    //telephones.add(cursor.next());
                }
            } finally {
                cursor.close();
            }
        
        return telephone;
    }

    /**
     * This method is for find telephones by name.
     * @param name
     * @return Collecion of telephones
     * @throws BusinessLogicException 
     */
    @Override
    public List<TelephoneBean> findTelephoneByName(String name) throws BusinessLogicException {
        FindIterable<Document> fi;
        MongoCursor<Document> cursor;
        Document itera;
        fi = collection.find();
        cursor = fi.iterator();
        List<TelephoneBean> telephones = new ArrayList<>();
            try {
                if (!cursor.hasNext()) {
                    System.out.println("No se ha encontrado el telefono");
                }
                int i = 0;
                while (cursor.hasNext()) {
                    //telephones.add(cursor.next());
                }
            } finally {
                cursor.close();
            }
        
        return telephones;
    }
}