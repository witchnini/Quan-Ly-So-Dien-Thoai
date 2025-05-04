package qlsdt_swing.qlsdt.func;

import qlsdt_swing.qlsdt.entity.User;

public class UserFunc {
    public boolean checkUser(User user) {
        if (user != null) {
            if ("nini".equals(user.getUserName()) 
                    && "nini".equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}