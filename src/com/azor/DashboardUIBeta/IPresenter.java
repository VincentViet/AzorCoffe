package com.azor.DashboardUIBeta;

import com.azor.models.BillInvoice;
import javafx.collections.ObservableList;

public interface IPresenter {
    public ObservableList<BillInvoice> getBillInvoice();
}
