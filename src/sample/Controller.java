package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Arrays;
import java.util.Collections;

public class Controller {

    /**
     * Utility methods
     */
    private boolean startDisplay = true;
    public void display(TextArea ta, String text) {
    /*  if (!continuousCheckBox.isSelected() && startDisplay) {
            ta.clear();
            startDisplay = false;
        } */
        ta.appendText(text);
    }

    public void displayln(TextArea ta, String text) {
        display(ta, text + "\n");
    }

    public void clearTextFields() {
        cashiereUPCTextField.setText("");
        productTextField.setText("");
        UPCTextField.setText("");
        quantityTextField.setText("");
        priceTextField.setText("");
        expDateTextField.setText("");
    }

    public FoodProduct collectTextFields() {
        String productName = productTextField.getText();
        String upc = UPCTextField.getText();
        int quantity = Integer.parseInt(quantityTextField.getText());
        double price = Double.parseDouble(priceTextField.getText());
        String expDate = expDateTextField.getText();
        return new FoodProduct(productName, quantity, price, expDate, upc);
    }

    /**
     * javafx methods
     */

    @FXML
    private TextArea reportTextArea;

    @FXML
    private TextField reportTextField;

    @FXML
    private Button stockerEnterButton;

    @FXML
    private TextArea checkoutTextArea;

    @FXML
    private TextField cashiereUPCTextField;

    @FXML
    private Button enterButton;

    @FXML
    private TextField productTextField;

    @FXML
    private TextField UPCTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField expDateTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    void enterButton(ActionEvent event) {

    }

    @FXML
    void handleAddButton(MouseEvent event) {
        FoodProduct fp = collectTextFields();
        Main.pdb.add(fp);
    }

    @FXML
    void handleDeleteButton(MouseEvent event) {
        FoodProduct fp = collectTextFields();
        Main.pdb.remove(fp.getName());

    }

    @FXML
    void handleEnterButton(MouseEvent event) {
        String upc = cashiereUPCTextField.getText();
        if (upc.equals("") || upc.length() != 10) {
            displayln(checkoutTextArea, "Must enter UPC number (10 decimal digits).");
        }
        else {
            FoodProduct fp = Main.pdb.scan(upc);
            if (fp == null) {
                displayln(checkoutTextArea, "Call manager, UPC " + upc + " not found.");
            }
            else {
                displayln(checkoutTextArea, fp.getName() + " " + fp.getPrice());
            }
        }

    }

    @FXML
    void handleStockerEnterButton(MouseEvent event) {
        String report = reportTextField.getText();
        FoodProductOrder fpo = FoodProductOrder.BY_EXPIRATION_DATE;
        if (report.equals("UPC")) {
            fpo = FoodProductOrder.BY_UPC;
        }
        FoodProduct[] fpa = Main.pdb.productArray(fpo);
        if (report.toLowerCase().equals("alpha")) {
            FoodProduct[] alpha = Arrays.copyOf(fpa, fpa.length);
            Arrays.sort(alpha);
            fpa = alpha;
        }
        reportTextArea.clear();
        for (FoodProduct fp : fpa) {
            displayln(reportTextArea, fp.toString());
        }
    }

    @FXML
    void handleUpdateButton(MouseEvent event) {
        FoodProduct fp = collectTextFields();
        Main.pdb.add(fp);
    }

}

