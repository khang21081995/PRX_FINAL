package entities;

import encrypt.MD5Library;
import util.ValidationAndNormalizingText;

/**
 * Created by khang on 7/1/2017.
 */
public class Staff {

    private String username;
    private String password;
    private String name;
    private String dob;
    private String email;
    private String address;
    private String phoneNumber;
    private boolean isManager;
    private String gender;
    private boolean isBlock;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Staff() {
//        this.username = "";
//        this.password = "";
//        this.name = "";
//        this.dob = "";
//        this.email = "";
//        this.address = "";
//        this.phoneNumber = "";
//        this.isManager = false;
//        this.gender = "";
    }

    public boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public Staff(String username, String password, String name, String dob, String email, String address, String phoneNumber, boolean isManager, String gender, boolean isBlock) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isManager = isManager;
        this.gender = gender;
        this.isBlock = isBlock;
    }

    public boolean getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception {
        if (ValidationAndNormalizingText.validateUsername(username, 5, 30)) {
            this.username = username;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        if (password.equalsIgnoreCase("")) {
            throw new Exception("Mật khẩu không được để trống!!");
        }
        this.password = MD5Library.md5Encrypt(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) throws Exception {
        String format = "dd/MM/yyyy";
        String temp;
        if (!(temp = ValidationAndNormalizingText.validationDate(dob, format)).equalsIgnoreCase(format)) {
            this.dob = temp;
        } else {
            throw new Exception("Ngày sinh phải ở định dạng " + temp);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (!email.equals("")) {
            if ((this.email = ValidationAndNormalizingText.validationEmail(email)).equals("")) {
                throw new Exception("Email sai định dạng!!!");
            }
        } else {
            this.email = "";
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws Exception {

        if (phoneNumber.trim().equalsIgnoreCase("")) {
            this.phoneNumber = "";
        } else {
            this.phoneNumber = ValidationAndNormalizingText.validAndNomalPhoneNumber(phoneNumber, " ");
        }
    }
}
