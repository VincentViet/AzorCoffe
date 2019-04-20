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
    public void invoice(JFXListView<String> Invoice ) {
        try {
            ResultSet result= Database.getInstance().query(String.format("Select * from billdetail"));
            result.first();
            BillDetail billDetail= new BillDetail(result.getInt(1), result.getInt(2), result.getInt(3), result.getInt(5));
            Invoice.getItems().add(billDetail.toString());
            //Invoice=new JFXListView<String>(billDetail.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
