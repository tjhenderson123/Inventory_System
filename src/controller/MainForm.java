package controller;

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

import static controller.ModifyPartForm.*;

public class MainForm implements Initializable {

    Stage stage;
    Parent scene;
    @FXML
    private TableView<Part> partTableView;
    @FXML
    private TableColumn<Part, Integer> partPartID;
    @FXML
    private TableColumn<Part, String> partPartName;
    @FXML
    private TableColumn<Part, Integer> partInventoryLevel;
    @FXML
    private TableColumn<Part, Double> partPriceCost;
    @FXML
    private Button partAdd;
    @FXML
    private Button partModify;
    @FXML
    private Button partDelete;
    @FXML
    private TextField searchByLeft;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, Integer> productPartID;
    @FXML
    private TableColumn<Product, String> productPartName;
    @FXML
    private TableColumn<Product, Integer> productInventoryLevel;
    @FXML
    private TableColumn<Product, Double> productPriceCost;
    @FXML
    private Button productModify;
    @FXML
    private Button productDelete;
    @FXML
    private Button productAdd;
    @FXML
    private TextField searchByRight;
    @FXML
    private Button exit;

    /**
     *
     * @param url universal resource locator
     * @param resourceBundle resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        partTableView.setItems(Inventory.getAllParts());
        partPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTableView.setItems(Inventory.getAllProducts());
        productPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));


    }

    /**
     *
     * @param actionEvent activated on add part button
     * @throws IOException indicates that method can throw an input/output exception (“How to Use the Throws....”, 2021)
     */
    public void onAddPart(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddPartForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene (root, 850, 500);
        stage.setTitle("Add Part Form");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param actionEvent activated on modify part button
     * @throws IOException indicates that method can throw an input/output exception (“How to Use the Throws....”, 2021)
     */
    public void onModifyPart(ActionEvent actionEvent) throws IOException {
        receivePart(partTableView.getSelectionModel().getSelectedItem());

        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyPartForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene (root, 850, 500);
        stage.setTitle("Modify Part Form");
        stage.setScene(scene);
        stage.show();

    }

    /**
     *
     * @param actionEvent activated on add product button
     * @throws IOException indicates that method can throw an input/output exception (“How to Use the Throws....”, 2021)
     */
    public void onAddProduct(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene (root, 1000, 600);
        stage.setTitle("Add Product Form");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param actionEvent activated on modify product button
     * @throws IOException indicates that method can throw an input/output exception (“How to Use the Throws....”, 2021)
     */
    public void onModifyProduct(ActionEvent actionEvent) throws IOException {
        ModifyProductForm.receiveProduct(productTableView.getSelectionModel().getSelectedItem());
        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyProductForm.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene (root, 1000, 600);
        stage.setTitle("Modify Product Form");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param actionEvent activated on delete part button
     * @throws IOException indicates that method can throw an input/output exception (“How to Use the Throws....”, 2021)
     */
    public void onDeletePart(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Item");
        alert.setContentText("Are you sure you want to delete this item?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
        partTableView.getItems().removeAll(partTableView.getSelectionModel().getSelectedItem());}
    }


    /**
     *
     * @param actionEvent activated on delete product button
     * @throws IOException indicates that method can throw an input/output exception (“How to Use the Throws....”, 2021)
     */
    public void onDeleteProduct(ActionEvent actionEvent) throws IOException {
        Product p1 = productTableView.getSelectionModel().getSelectedItem();
        int p2 =  p1.getAllAssociatedParts().size();
        if (p2 == 0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Item");
            alert.setContentText("Are you sure you want to delete this item?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                productTableView.getItems().removeAll(productTableView.getSelectionModel().getSelectedItem());}
        } else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Error");
            alert2.setContentText("Product cannot be deleted due to associated parts being present.");
            alert2.showAndWait();
        }
    }

    /**
     *
     * @param actionEvent activated on exit screen button
     * @throws IOException indicates that method can throw an input/output exception (“How to Use the Throws....”, 2021)
     */
    public void onExitScreen(ActionEvent actionEvent) throws IOException {
        System.exit(0);
    }

    /**
     *
     * @param actionEvent activated when left search text field is used (“How to Use the Throws....”, 2021)
     */
    public void onSearchByLeft(ActionEvent actionEvent) {
        String results = searchByLeft.getText();

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
        partTableView.setItems(part);

    }

    /**
     *
     * @param actionEvent activated when right search text field is used
     */
    public void onSearchByRight(ActionEvent actionEvent) {
        String results = searchByRight.getText();

        ObservableList<Product> product = Inventory.lookupProduct(results);

        if(product.size() == 0){
            try {
                int p = Integer.parseInt((results));
                Product prod = Inventory.lookupProduct(p);
                if (prod != null)
                    product.add(prod);

            }
            catch (NumberFormatException e) {
                //ignore
            }
        }
        productTableView.setItems(product);

    }

    /**
     *
     * @param tableViewSortEvent not used
     */
    public void onPartTableView(SortEvent<TableView> tableViewSortEvent) {
    }

    /**
     *
     * @param tableViewSortEvent not used
     */
    public void onProductTableView(SortEvent<TableView> tableViewSortEvent) {
    }
}
