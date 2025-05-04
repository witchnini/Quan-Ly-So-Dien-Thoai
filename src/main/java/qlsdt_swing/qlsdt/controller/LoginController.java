package qlsdt_swing.qlsdt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import qlsdt_swing.qlsdt.func.UserFunc;
import qlsdt_swing.qlsdt.entity.User;
import qlsdt_swing.qlsdt.view.LoginView;
import qlsdt_swing.qlsdt.view.PhoneNumberView;

public class LoginController {
    private UserFunc userDao;
    private LoginView loginView;
    private PhoneNumberView phonenumberView;
    
    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserFunc();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }
    
    /**
     * Lớp LoginListener chứa cài đặt cho sự kiện click button "Login"
     * 
     * @author viettuts.vn
     */
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                PhoneNumberController phoneNumberController = new PhoneNumberController(new PhoneNumberView());
                loginView.setVisible(false);
                phoneNumberController.showPhoneNumberView();
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
    }
}