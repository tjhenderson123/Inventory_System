package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import model.*;

//Fix radio buttons to populate correct when opening modify part form.
//Need to be able to change parts as in house or out source and save properly.

/**
 * 
 *
 * “FUTURE ENHANCEMENT” When opening the "Add Part" or "Modify Part" forms, it would be better if
 *      * one of the radio buttons were automatically clicked when the screen is opened.
 *
 *      Works Cited:
 *      “How to Use the Throws Keyword in Java (and When to Use Throw).” Rollbar, 20 Sept. 2021, https://rollbar.com/blog/how-to-use-the-throws-keyword-in-java-and-when-to-use-throw/.
 */
public class Main extends Application {
    /**
     *
     * @param stage stage that's used for main screen
     * @throws Exception indicates an exception can be thrown
     */

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setTitle("Main Form");
        stage.setScene(new Scene(root, 850, 500 ));
        stage.show();
    }

    /**
     *
     * @param args main method argument
     */
    public static void main(String[] args){
        Product LHSprocket = new Product( Inventory.getProdGenID(), "LH Sprocket", 123.76, 7, 3, 10);
        Product RHSprocket = new Product( Inventory.getProdGenID(), "RH Sprocket", 123.76, 7, 3, 10);
        Product Diode387 = new Product( Inventory.getProdGenID(), "Diode387", 29.99, 17, 5, 25);
        Product Sensor36 = new Product( Inventory.getProdGenID(), "LH Sprocket", 45.99, 12, 5, 15);
        Product Sensor24 = new Product( Inventory.getProdGenID(), "LH Sprocket", 72.99, 5, 5, 15);


        Inventory.addProduct(LHSprocket);
        Inventory.addProduct(RHSprocket);
        Inventory.addProduct(Diode387);
        Inventory.addProduct(Sensor36);
        Inventory.addProduct(Sensor24);



        Part bolt27 = new InHouse(Inventory.getPartGenID(), "bolt27", .59, 159, 50, 200, 51);
        Part bol86 = new InHouse(Inventory.getPartGenID(), "bolt86", .68, 148, 50, 200, 52);
        Part diodeConnector4 = new InHouse(Inventory.getPartGenID(), "diodeConnector4", 3.51, 25, 10, 50, 53);
        Part diodeConnector7 = new InHouse(Inventory.getPartGenID(), "diodeConnector7", 4.34, 27, 10, 50, 54);
        Part cableSet9 = new Outsourced(Inventory.getPartGenID(), "cableBlue9", 17.85, 18, 10, 25, "Fast Cable Co.");

        Inventory.addPart(bolt27);
        Inventory.addPart(bol86);
        Inventory.addPart(diodeConnector4);
        Inventory.addPart(diodeConnector7);
        Inventory.addPart(cableSet9);

        launch(args);
    }
}
