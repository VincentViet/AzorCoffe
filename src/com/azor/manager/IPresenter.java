package com.azor.manager;

import com.jfoenix.controls.JFXListView;

interface IPresenter {
    void minimize();
    void maximize();
    void close();
    void Invoice(JFXListView<String> Invoice);
}
