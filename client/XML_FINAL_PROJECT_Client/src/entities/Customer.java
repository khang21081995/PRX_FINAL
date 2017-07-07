/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import encrypt.MD5Library;
import util.ValidationAndNormalizingText;

/**
 * @author khang
 */
public class Customer {

    private String name;
    private String passport;
    private String dob;
    private String phoneNumber;
    private String email;
    private int score;
    private String accountType;
    private String gender;
    private String cardID;

    public Customer(String name, String passport, String dob, String phoneNumber, String email, int score, String accountType, String gender, String cardID) {
        this.name = name;
        this.passport = passport;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.score = score;
        this.accountType = accountType;
        this.gender = gender;
        this.cardID = cardID;
    }

    public Customer() {
//        this.name = "";
//        this.passport = "";
        this.dob = "01/01/0001";
//        this.phoneNumber = "";
        this.score = 0;
        this.accountType = "Normal";
        this.gender = "Khác";
//        this.cardID = "";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) throws Exception {
        String format = "dd/MM/yyyy";
        String temp;
        if ((temp = ValidationAndNormalizingText.validationDate(dob, format)) != format) {
            this.dob = temp;
        } else {
            this.dob = temp;
            throw new Exception("Ngày sinh phải ở định dạng " + temp);
        }
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) throws Exception {
        if (score < 0) {
            throw new Exception("Điểm tích không được nhỏ hơn 0.");
        } else {
            this.score = score;
        }
    }

    public void increaseScore(int score) throws Exception {
        setScore(getScore() + score);
    }

    public void decreaseScore(int score) throws Exception {
        setScore(getScore() - score);
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = (cardID);
    }
}
