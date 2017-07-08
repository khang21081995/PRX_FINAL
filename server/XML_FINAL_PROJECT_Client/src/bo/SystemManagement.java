/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import db.DataAccessObject;
import encrypt.MD5Library;
import java.sql.ResultSet;
import javax.swing.JFrame;
import ui.FrmLogin;

/**
 *
 * @author phamquangkhang
 */
public class SystemManagement {

    public final int LOGIN_AS_MANAGER = 1;
    public final int LOGIN_AS_STAFF = 2;
    public final int LOGIN_FAILED = 0;
    DataAccessObject dao;

    public SystemManagement() throws Exception {
        dao = new DataAccessObject();
    }

    public int login(String username, String password) throws Exception {
        password = MD5Library.md5Encrypt(password);
        ResultSet rs;
        if ((rs = dao.getResulSet("Select [password],[isManager],[isBlock] from [Staff] where [username] = ?", username)).next()) {
            if (rs.getString("password").equals(password)) {
                if (rs.getString("isBlock").equalsIgnoreCase("1")) {
                    throw new Exception("Tài khoản " + username + " đã bị khóa");
                }

//                AppServices.startAppService();
                if (rs.getString("isManager").equalsIgnoreCase("1")) {
                    return LOGIN_AS_MANAGER;
                } else {
                    return LOGIN_AS_STAFF;
                }
            } else {
                throw new Exception("Sai tên tài khoản hoặc mật khẩu!!!");
            }
        } else {
            throw new Exception("Sai tên tài khoản hoặc mật khẩu!!!");
        }
    }

    public void logout(JFrame current) {
        util.Util.openNewFormAndDestroyCloseForm(current, new FrmLogin());
    }
}
