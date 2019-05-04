package com.azor.manager;

import com.azor.AzorCoffee;
import com.azor.models.BillDetail;
import com.azor.utils.Database;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

class Presenter implements IPresenter {
    IView view;
    Presenter(IView view){
        this.view = view;
    }

    @Override
    public void minimize() {
        AzorCoffee.primaryStage.setIconified(true);
    }

    @Override
    public void maximize() {
        AzorCoffee.primaryStage.setFullScreenExitHint("Press ESC to exit fullscreen mode.");
        AzorCoffee.primaryStage.setFullScreen(true);
    }

    @Override
    public void close() {
        Platform.exit();
    }

    @Override
    // Xuất hóa đơn
    public void Invoice(JFXListView<String> Invoice) {
        try {
            ResultSet bill_id_result= Database.getInstance()
                    .query(String.format("SELECT bill_id " +
                            "FROM bill " +
                            "WHERE tablefood_id = 1 and status = 0 " ));
            bill_id_result.first();
            String bill_id= bill_id_result.getString(1);
            System.out.println(bill_id);
            ResultSet bill_detail_result =Database.getInstance()
                    .query(String.format("select * " +
                            "from billdetail " +
                            "where bill_id = '%s' ", bill_id));
            while (bill_detail_result.next()){
                BillDetail temp= new BillDetail(bill_detail_result.getInt(1),bill_detail_result.getInt(2), bill_detail_result.getInt(3),bill_detail_result.getInt(4));
                Invoice.getItems().add(temp.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
