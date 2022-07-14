package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProductForm implements Initializable {
    Stage stage;
    Parent scene;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    @FXML
    private TableView<Part> botTableView;
    @FXML
    private TableView<Part> topTableView;
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
    private TextField minText;
    @FXML
    private TableColumn<Part,Integer> topPartID;
    @FXML
    private TableColumn<Part, String> topPartName;
    @FXML
    private TableColumn<Part, Integer> topInventoryLevel;
    @FXML
    private TableColumn<Part, Double> topPriceCost;
    @FXML
    private TextField searchForPart;
    @FXML
    private Button addProduct;
    @FXML
    private TableColumn<Part, Integer> botPartID;
    @FXML
    private TableColumn<Part, String> botPartName;
    @FXML
    private TableColumn<Part, Integer> botInventoryLevel;
    @FXML
    private TableColumn<Part, Double> botPriceCost;
    @FXML
    private Button removeAssociatedPart;
    @FXML
    private Button save;
    @FXML
    private Button cancel;

    /**
     *
     * @param url uniform resource locator
     * @param resourceBundle resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        topTableView.setItems(Inventory.getAllParts());
        topPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        topPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        topInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        topPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));

        botTableView.setItems(associatedParts);
        botPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        botPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        botInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        botPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     *
     * @param actionEvent activated on add button
     */
    public void onAddButton(ActionEvent actionEvent) {
        Part partToAdd = topTableView.getSelectionModel().getSelectedItem();
        associatedParts.add(partToAdd);
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
            int id = Inventory.getProdGenID();
            String name = nameText.getText();
            int stock = Integer.parseInt(invText.getText());
            int max = Integer.parseInt(maxText.getText());
            int min = Integer.parseInt(minText.getText());
            double price = Double.parseDouble(priceCostText.getText());
            // Inventory.addProduct(new Product(id, name, price, stock, min, max));

            if (min <= max && stock >= min && stock <= max) {

                int indexOfProd = Inventory.getAllProducts().indexOf(id);
                Product prod1 = new Product(id, name, price, stock, min, max);
                for (Part p : associatedParts) {
                    prod1.addAssociatedPart(p);
                }
                Inventory.addProduct(prod1);

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Max amount must be greater then or equal to the minimum amount. " +
                        "Inventory must be between max and minimum amounts");
                alert.showAndWait();
            }

        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Incorrect data type was entered. Please try again.");
            alert.showAndWait();
        }
    }
    /**
     *
     * @param actionEvent activated on Remove Associated Part button
     * @throws IOException indicates that method can throw an input/output exception (“How to Use the Throws....”, 2021)
     */
    public void onRemoveAssociatedPart(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Item");
        alert.setContentText("Are you sure you want to delete this item?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {

            botTableView.getItems().removeAll(botTableView.getSelectionModel().getSelectedItem());
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
    public void onMin(ActionEvent actionEvent) {
    }

    /**
     *
     * @param actionEvent activates on Search For Part text field being used
     */
    public void onSearchForPart(ActionEvent actionEvent) {
        String results = searchForPart.getText();

        ObservableList<Part> part = Inventory.lookupPart(results);

        if(part.size() == 0){
            try {
                int p = Integer.parseInt((results));
                Part par = Inventory.lookupPart(p);
                if (par != null)
                    part.add(par);

            }
            catch (NumberFormatException e) {
                //ignore
            }
        }
        topTableView.setItems(part);

    }

    /**
     *
     * @param tableViewSortEvent not used
     */
    public void onTopTableView(SortEvent<TableView> tableViewSortEvent) {
    }

    /**
     *
     * @param tableViewSortEvent not used
     */
    public void onBotTableView(SortEvent<TableView> tableViewSortEvent) {
    }
}
