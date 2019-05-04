package com.azor.DashboardUIBeta;

import com.azor.models.Bill;
import com.azor.models.BillInvoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Scene implements IView, Initializable {

    @FXML
    private TableView<BillInvoice> billInfo;
    @FXML
    private TableColumn<BillInvoice, String> columnDescription;
    @FXML
    private TableColumn<BillInvoice, Integer> columnQuantity;
    @FXML
    private TableColumn<BillInvoice,Integer> columnPrice;
    @FXML
    private TableColumn<BillInvoice, Integer> columnAmount;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    private Presenter presenter= new Presenter(this);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up the columns in the table
        columnDescription.setCellValueFactory(new PropertyValueFactory<BillInvoice, String>("description"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<BillInvoice, Integer>("quantity"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<BillInvoice, Integer>("price"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<BillInvoice, Integer>("amount"));

        //load dummy data
        billInfo.setItems(presenter.getBillInvoice());
    }
}
