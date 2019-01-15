package jampclientside;


import jampclientside.logic.EventLogic;
import jampclientside.logic.ILogicFactory;
import jampclientside.logic.ProductLogic;
import jampclientside.ui.controller.PC05EventsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import jampclientside.logic.UserLogic;
import jampclientside.ui.controller.PC07ProductsController;
import jampclientside.ui.controller.PC08PhoneNumbersController;

/**
 * Class that is going to launch the first window of the application, the window of
 * Login
 * 
 * @author Paula
 */
public class UiApplicationProduct extends Application {

    /**
     * Method that throws the first stage and receives the stage.
     * @param stage
     * @throws Exception throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        //vamos a cargar un objeto de la logica , para eso llamamos a la factoria 
        //UserLogic iLogicUser = UserLogic.getILogic();
        //vamos a cargar un objeto de la logica , para eso llamamos a la factoria 
        ProductLogic iLogicProduct = ILogicFactory.getProductLogic();
        //Instanciamos el cargador
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/view/PC07Products.fxml"));
        //Cargamos el documento en el root
        Parent root = (Parent) loader.load();
        //Obtengo el controlador, por que luego quiero pasarle el stage, entonces primero lo tengo que tener
        PC07ProductsController controler = (PC07ProductsController) loader.getController();
        //le paso el objeto de logica al controlador
        controler.setILogicProduct(iLogicProduct);
        //al metodo set stage le paso el stage, al controlador le paso el escenario
        controler.setStage(stage);
        //al metodo initstage del controlador le paso el root
        controler.initStage(root);
    }

    /**
     * Method that serves to launch the start method of this same class
     *
     * @param args data type that you send to the main method of the class
     */
    public static void main(String[] args) {
        //Lanza el metodo start
        launch();

    }

}
