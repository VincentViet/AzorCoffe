package com.azor.DashboardUIBeta;

import com.azor.models.Bill;
import com.azor.models.BillInvoice;
import com.azor.utils.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Presenter implements IPresenter {

    private IView dashboardView;

    Presenter(IView dashboardView){
        this.dashboardView = dashboardView;
    }

    @Override
    public ObservableList<BillInvoice> getBillInvoice() {
        ObservableList<BillInvoice> billInvoices= FXCollections.observableArrayList();
        try {
            ResultSet bill_idResult = Database.getInstance()
                    .query(String.format("Select bill_id from bill where tablefood_id = 1"));
            bill_idResult.first();
            String bill_id = bill_idResult.getString(1);
            ResultSet food_idResult = Database.getInstance()    // trả về các id của thức ăn có trong hóa đơn
                    .query(String.format("Select food_id, count from billdetail where bill_id = '%s'",bill_id));
            while (food_idResult.next()){
                BillInvoice temp = new BillInvoice();
                temp.setQuantity(food_idResult.getInt(2));
                ResultSet food_nameResult = Database.getInstance()  //trả về tên thức ăn và số lượng theo từng id của thức ăn lấy ở trên
                        .query(String.format("Select food_name, price from food where food_id = '%d'", food_idResult.getInt(1)));
                food_nameResult.first();
                temp.setDescription(food_nameResult.getString(1));
                temp.setPrice(food_nameResult.getInt(2));
                temp.setAmount();
                billInvoices.add(temp);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return billInvoices;
    }
}
