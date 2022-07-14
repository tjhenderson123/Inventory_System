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

import model.*;


public class ModifyPartForm implements Initializable {
    Stage stage;
    Parent scene;
    @FXML
    private Label labelToggleInHouOrOutSou;
    @FXML
    private ToggleGroup toggleSource;
    @FXML
    private TextField iDText;
    @FXML
    private TextField nameText;
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
    private RadioButton inHouse;
    @FXML
    private RadioButton outSourced;
    @FXML
    private Button save;
    private static Part passPart;


    /**
     *
     * @param url uniform resource locator
     * @param resourceBundle resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iDText.setText(String.valueOf(passPart.getId()));
        nameText.setText(passPart.getName());
        invText.setText(String.valueOf(passPart.getStock()));
        priceCostText.setText(String.valueOf(passPart.getPrice()));
        maxText.setText(String.valueOf(passPart.getMax()));
        minText.setText(String.valueOf(passPart.getMin()));
        if(passPart instanceof InHouse) {
            machineOrCompanyText.setText(String.valueOf(((InHouse) passPart).getMachineID()));
            labelToggleInHouOrOutSou.setText("Machine ID");
            toggleSource.selectToggle(inHouse);
        }
        else {
            machineOrCompanyText.setText(String.valueOf(((Outsourced) passPart).getCompanyName()));
            labelToggleInHouOrOutSou.setText("Company Name");
            toggleSource.selectToggle(outSourced);
        }
    }

    /**
     *
     * @param p
     */
    public static void receivePart(Part p){
        passPart = p;
    }


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
            int id = Integer.parseInt(iDText.getText());
            String name = nameText.getText();
            int stock = Integer.parseInt(invText.getText());
            int max = Integer.parseInt(maxText.getText());
            int min = Integer.parseInt(minText.getText());
            double price = Double.parseDouble(priceCostText.getText());
            if (min <= max && stock >= min && stock <= max) {

                if (inHouse.isSelected()) {
                    int machineID = Integer.parseInt(machineOrCompanyText.getText());
                    Inventory.updatePart(Integer.parseInt(iDText.getText()), new InHouse(id, name, price, stock, min, max, machineID));
                }

                if (outSourced.isSelected()) {
                    String companyName = machineOrCompanyText.getText();
                    Inventory.updatePart(Integer.parseInt(iDText.getText()), new Outsourced(id, name, price, stock, min, max, companyName));
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
