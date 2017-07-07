/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import Config.AppConfig;
import Config.CallBack;
import entities.Customer;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;

/**
 *
 * @author phamquangkhang
 */
public class CallBackImplement implements CallBack {

    @Override
    public void callBackFunction(File xmlFile) {
        //TODO: Khi có file mới thì sẽ cập nhật vào DB
        if (!xmlFile.getName().toLowerCase().endsWith(AppConfig.CINEMA_ID.toLowerCase() + ".xml") || 
                !xmlHandle.XMLHandler.vefifyXSDvsXML(AppConfig.XSD_FILE_PATH, xmlFile.getPath())) {
            System.err.println("Bỏ qua file " + xmlFile.getName());
            return;
        }
        Document doc = xmlHandle.XMLHandler.readFileXml(xmlFile.getPath());
        String mode = doc.getElementsByTagName("Type").item(0).getTextContent();
        Customer customer = null;
        if (mode.equalsIgnoreCase("INSERT") || mode.equalsIgnoreCase("UPDATE") || mode.equalsIgnoreCase("GETDATA")) {
            String cardID = doc.getElementsByTagName("cardID").item(0).getTextContent();
            String score = doc.getElementsByTagName("score").item(0).getTextContent();
            String fullname = doc.getElementsByTagName("name").item(0).getTextContent();
            String passport = doc.getElementsByTagName("passport").item(0).getTextContent();
            String email = doc.getElementsByTagName("email").item(0).getTextContent();
            String phoneNumber = doc.getElementsByTagName("phoneNumber").item(0).getTextContent();
            String dob = doc.getElementsByTagName("dob").item(0).getTextContent();
            String accType = doc.getElementsByTagName("accountType").item(0).getTextContent();
            String gender = doc.getElementsByTagName("gender").item(0).getTextContent();
            customer = new Customer(fullname, passport, dob, phoneNumber, email, Integer.parseInt(score), accType, gender, cardID);
        }
        if (mode.equalsIgnoreCase("insert")) {
            try {
                if (new CustomerManagement().create(customer)) {
                    util.Util.deleteFile(xmlFile.getPath());
                }
            } catch (Exception ex) {
//                Logger.getLogger(CallBackImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (mode.equalsIgnoreCase("Update")) {
            try {
                if (new CustomerManagement().update(customer)) {
                    System.out.println("Update thang cong roi nhe");
                    util.Util.deleteFile(xmlFile.getPath());
                }
            } catch (Exception ex) {
//                Logger.getLogger(CallBackImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (mode.equalsIgnoreCase("getdata")) {
            try {
                CustomerManagement cm = new CustomerManagement();
                if (cm.getCustomerByCardID(customer.getCardID()) != null) {
                    if (cm.update(customer)) {
                        util.Util.deleteFile(xmlFile.getPath());
                    }
                } else {
                    if (cm.create(cm)) {
                        util.Util.deleteFile(xmlFile.getPath());
                    }
                }
            } catch (Exception ex) {
//                Logger.getLogger(CallBackImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
