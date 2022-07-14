package model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static int partGenID = 1;
    private static int prodGenID = 1001;

    /**
     *
     * @return the generated partid
     */
    public static int getPartGenID(){
        return  partGenID++;
    }

    /**
     *
     * @return the generated productid
     */
    public static int getProdGenID(){
        return prodGenID++;
    }

    /**
     *
     * @param newPart adds a part object to newpart
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     *
     * @param newProduct adds a product object to newproduct
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     *
     * @param partID uses partid to search for a part
     * @return the part
     */
    public static Part lookupPart(int partID)  {
        for (Part part : allParts) {
            if(partID == part.getId()){
                return part;
            }
        }
        return null;

    }

    /**
     *
     * @param productID uses productid to search for a product
     * @return the product
     */
    public static Product lookupProduct(int productID) {
        for (Product product : allProducts) {
            if(productID == product.getId()){
                return product;
            }
        }
        return null;

    }

    /**
     *
     * @param partName uses partname to search for a part
     * @return returns list of parts
     */
    public static ObservableList<Part> lookupPart (String partName){
        ObservableList<Part> list = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                list.add(part);
            }
        }
        return list;
    }

    /**
     *
     * @param productName uses productname to search for a products
     * @return list of products
     */
    public static ObservableList<Product> lookupProduct (String productName){
        ObservableList<Product> list = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                list.add(product);
            }
        }
        return list;
    }

    /**
     *
     * @param index uses index to update a part
     * @param selectedPart uses selectedpart to update a part
     */
    public static void updatePart ( int index, Part selectedPart){
        index = -1;
        for(Part part : Inventory.getAllParts()){
            index++;

            if(part.getId() == selectedPart.getId()) {
                Inventory.getAllParts().set(index, selectedPart);
            }
        }

    }

    /**
     *
     * @param index uses index to update a part
     * @param newProduct uses newproduct to update a part
     */
    public static void updateProduct ( int index, Product newProduct){
        index = -1;
        for(Product product : Inventory.getAllProducts()){
            index++;

            if(product.getId() == newProduct.getId()) {
                Inventory.getAllProducts().set(index, newProduct);
            }
        }
    }

    /**
     *
     * @param selectedPart deletes selected part
     */
    public static void deletePart (Part selectedPart){
        for(Part part : getAllParts()) {
            if(part.getId() == selectedPart.getId())
                Inventory.getAllProducts().remove(part);
        }
    }

    /**
     *
     * @param selectedProduct deletes selected product
     */
    public static void deleteProduct (Product selectedProduct){
        for(Product product : getAllProducts()) {
            if(product.getId() == selectedProduct.getId())
                Inventory.getAllProducts().remove(product);
        }
    }

    /**
     *
     * @return the list allparts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     *
     * @return the list allproducts
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}

