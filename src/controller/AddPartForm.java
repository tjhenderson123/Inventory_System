package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import model.InHouse;
import model.Inventory;
import model.Outsourced;


public class AddPartForm implements Initializable  {

    Stage stage;
    Parent scene;

    @FXML
    private Label labelToggleInHouOrOutSou;
    @FXML
    private TextField invText;
    @FXML
    private TextField priceCostText;
    @FXML
    private TextField maxText;
    @FXML
    private TextField machineOrCompanyText;
    @FXML
    private TextField minText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField iDText;
    @FXML
    private ToggleGroup toggleSource;
    @FXML
    private RadioButton inHouse;
    @FXML
    private RadioButton outSourced;
    @FXML
    private Button cancel;
    @FXML
    private Button save;

    /**
     *
     * @param url uniform resource locator
     * @param resourceBundle resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    /**
     * RUNTIME ERROR: "illegal start of expression" - Resolved issue by adding a curly bracket to end of onCancel method.
     */
    /**
     *
     * @param actionEvent activated on cancel button
     * @throws IOException indicates that method can throw an input/output exception (“How to Use the Throws....”, 2021)
     */
    public void onCancel(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     *
     * @param actionEvent activated on save button
     * @throws IOException indicates that method can throw an input/output exception (“How to Use the Throws....”, 2021)
     */
    public void onSave(ActionEvent actionEvent) throws IOException {

        try {
            int id = Inventory.getPartGenID();
            String name = nameText.getText();
            int stock = Integer.parseInt(invText.getText());
            int max = Integer.parseInt(maxText.getText());
            int min = Integer.parseInt(minText.getText());
            double price = Double.parseDouble(priceCostText.getText());

            if (min <= max && stock >= min && stock <= max) {
                if (inHouse.isSelected()) {
                    int machineID = Integer.parseInt(machineOrCompanyText.getText());
                    Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineID));
                }
                if (outSourced.isSelected()) {
                    String companyName = machineOrCompanyText.getText();
                    Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
                }
                stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Max amount must be greater then or equal to the minimum amount. " +
                        "Inventory must be between max and minimum amounts");
                alert.showAndWait();
            }
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Incorrect data type was entered. Please try again.");
            alert.showAndWait();
        }


    }


    /**
     *
     * @param actionEvent not used
     */
    public void onID(ActionEvent actionEvent) {
    }

    /**
     *
     * @param actionEvent not used
     */
    public void onName(ActionEvent actionEvent) {
    }

    /**
     *
     * @param actionEvent not used
     */
    public void onInv(ActionEvent actionEvent) {
    }

    /**
     *
     * @param actionEvent not used
     */
    public void onPriceCost(ActionEvent actionEvent) {
    }

    /**
     *
     * @param actionEvent not used
     */
    public void onMax(ActionEvent actionEvent) {
    }

    /**
     *
     * @param actionEvent not used
     */
    public void onMachineID(ActionEvent actionEvent) {
    }

    /**
     *
     * @param actionEvent not used
     */
    public void onMin(ActionEvent actionEvent) {
    }

    /**
     *
     * @param actionEvent activated on radio button clicked then sets text
     */
    public void onInHouse(ActionEvent actionEvent) {
        labelToggleInHouOrOutSou.setText("Machine ID");
    }

    /**
     *
     * @param actionEvent activated on radio button clicked then sets text
     */
    public void onOutsourced(ActionEvent actionEvent) {
        labelToggleInHouOrOutSou.setText("Company Name");
    }



}
