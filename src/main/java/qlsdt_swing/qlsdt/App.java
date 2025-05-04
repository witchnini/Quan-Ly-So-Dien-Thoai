package qlsdt_swing.qlsdt;

import java.awt.EventQueue;

import qlsdt_swing.qlsdt.controller.LoginController;
import qlsdt_swing.qlsdt.view.LoginView;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView view = new LoginView();
                LoginController controller = new LoginController(view);
                // hiển thị màn hình login
                controller.showLoginView();
            }
        });
    }
}