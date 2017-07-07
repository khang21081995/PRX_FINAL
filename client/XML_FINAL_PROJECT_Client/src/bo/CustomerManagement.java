/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import Config.AppConfig;
import db.DataAccessObject;
import entities.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phamquangkhang
 */
public class CustomerManagement implements ManageAction {

    private DataAccessObject dao;
    private final String table_name = "Customer";
    private String[] element = {"name", "passport", "dob", "phoneNumber", "email", "score", "accountType", "gender", "cardID"};

    public CustomerManagement() throws Exception {
        this.dao = new DataAccessObject();
    }

    @Override
    public boolean create(Object o) throws SQLException, Exception {
        String sqlInsert = "INSERT INTO " + table_name
                + " (name,passport,dob,phoneNumber,email, score,accountType,gender,cardID) "
                + "VALUES (?,?,?,?,?,?,?,?,?) ";

        Customer c = o instanceof Customer ? (Customer) o : null;
        if (c == null) {
            return false;
        }
        String values[] = {c.getName(), c.getPassport(), c.getDob(), c.getPhoneNumber(), c.getEmail(), c.getScore() + "",
            c.getAccountType(), c.getGender(), c.getCardID()};
        String fileCreate = AppConfig.Output_PATH + util.Util.getOsPath() + new Date().getTime() + AppConfig.CINEMA_ID + ".xml";
        if (xmlHandle.XMLHandler.createXMLFile(fileCreate, "Action", element, values, "Type", "INSERT")) {
            try {
                return dao.executeSQLwithParams(sqlInsert, DataAccessObject.MODE_UPDATE.SMALL_UPDATE, values);
            } catch (Exception ex) {
                util.Util.deleteFile(fileCreate);
            }
        }
        return false;
    }

    @Override
    public boolean update(Object o) throws SQLException, Exception {
        String sqlUpdate = "UPDATE " + table_name
                + " SET name = ?,passport=?,dob=?,phoneNumber=?,email=?, score=?,accountType=?,gender=?"
                + " WHERE cardID=?";
        Customer c = o instanceof Customer ? (Customer) o : null;
        if (c == null) {
            return false;
        }
        String values[] = {c.getName(), c.getPassport(), c.getDob(), c.getPhoneNumber(), c.getEmail(), c.getScore() + "",
            c.getAccountType(), c.getGender(), c.getCardID()};
//        return dao.executeSQLwithParams(sqlUpdate, DataAccessObject.MODE_UPDATE.SMALL_UPDATE,
//                c.getName(), c.getPassport(), c.getDob(), c.getPhoneNumber(), c.getEmail(), c.getScore() + "",
//                c.getAccountType(), c.getGender(), c.getCardID());
        String fileCreate = AppConfig.Output_PATH + util.Util.getOsPath() + new Date().getTime() + AppConfig.CINEMA_ID + ".xml";
        if (xmlHandle.XMLHandler.createXMLFile(fileCreate, "Action", element, values, "Type", "UPDATE")) {
            try {
                return dao.executeSQLwithParams(sqlUpdate, DataAccessObject.MODE_UPDATE.SMALL_UPDATE, values);
            } catch (Exception ex) {
                util.Util.deleteFile(fileCreate);
            }
        }
        return false;

    }

    @Override
    public ResultSet select(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Customer getCustomerByCardID(String cardID) throws Exception {
        Customer customer = null;

        String sqlSelect = "SELECT [name],[passport],[dob],[phoneNumber],[email],[score],[accountType],[gender],[cardID]  FROM [Customer] where cardID = ?";
        ResultSet rs = dao.getResulSet(sqlSelect, cardID);
        if (rs.next()) {
            return new Customer(rs.getString("name"), rs.getString("passport"), rs.getString("dob"), rs.getString("phoneNumber"), rs.getString("email"),
                    Integer.parseInt(rs.getString("score")), rs.getString("accountType"), rs.getString("gender"), rs.getString("cardID"));
        }
        return customer;
    }

    public boolean makeXMLQuery(String cardID, String type, String[] elements, String[] values) throws Exception {
        String fileCreate = AppConfig.Output_PATH + util.Util.getOsPath() + new Date().getTime() + AppConfig.CINEMA_ID + ".xml";
        return xmlHandle.XMLHandler.createXMLFile(fileCreate, "Action", elements, values, "cardID", cardID, "Type", type);
    }

    public boolean updateWithNoXML(Object o) throws SQLException, Exception {
        String sqlUpdate = "UPDATE " + table_name
                + " SET name = ?,passport=?,dob=?,phoneNumber=?,email=?, score=?,accountType=?,gender=?"
                + " WHERE cardID=?";
        Customer c = o instanceof Customer ? (Customer) o : null;
        if (c == null) {
            return false;
        }
        String values[] = {c.getName(), c.getPassport(), c.getDob(), c.getPhoneNumber(), c.getEmail(), c.getScore() + "",
            c.getAccountType(), c.getGender(), c.getCardID()};
//        return dao.executeSQLwithParams(sqlUpdate, DataAccessObject.MODE_UPDATE.SMALL_UPDATE,
//                c.getName(), c.getPassport(), c.getDob(), c.getPhoneNumber(), c.getEmail(), c.getScore() + "",
//                c.getAccountType(), c.getGender(), c.getCardID());
        return dao.executeSQLwithParams(sqlUpdate, DataAccessObject.MODE_UPDATE.SMALL_UPDATE, values);
    }

    public boolean inceaseScore(String cardID, int scoreInc) throws Exception {
        Customer c = getCustomerByCardID(cardID);
        String[] ele = {"score"};
        String[] value = {scoreInc + ""};
        makeXMLQuery(cardID, "INCREASE", ele, value);
        if (c != null) {
            c.setScore(scoreInc + c.getScore());
            return updateWithNoXML(c);
        } else {
            throw new Exception("Điểm đã được cộng dồn trên server tổng!!!");
        }
    }

    public boolean deceaseScore(String cardID, int scoreDec) throws Exception {
        String[] ele = {"score"};
        String[] value = {scoreDec + ""};
//        makeXMLQuery(cardID, "GETDATA", ele, value);
//        Thread.sleep(100);

        Customer c = getCustomerByCardID(cardID);

        if (c.getScore() < scoreDec || scoreDec < 0) {
            throw new Exception("Điểm đổi thưởng là số nguyên không âm và không được lớn hơn số điểm hiện có. ");
        }
        makeXMLQuery(cardID, "DECREASE", ele, value);
        if (c == null) {
        } else {
            c.setScore(c.getScore() - scoreDec);
            updateWithNoXML(c);
        }
        return true;
    }
//

    public static void main(String[] args) {
        String[] element = {"name", "passport", "dob", "phoneNumber", "email", "score", "accountType", "gender", "cardID"};
        String temp = Arrays.toString(element);
        System.err.println(temp.substring(1, temp.length() - 1));
//        try {
//            Customer c = new Customer();
//            c.setAccountType("Premium");
//            c.setCardID("0000000001");
//            c.setDob("21/08/1995");
//            c.setEmail("KhangPQ.vn@gmail.com");
//            c.setGender("Nam");
//            c.setName("KhangPQ");
//            c.setPassport("001095002587");
//            c.setPhoneNumber("0981604050");
//            c.setScore(0);
//            new CustomerManagement().create(c);
//            System.out.println("OK");
//        } catch (Exception ex) {
//            System.err.println(ex.getMessage());
//        }
    }

}
