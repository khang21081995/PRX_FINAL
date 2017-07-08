/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Config.AppConfig;
import Config.CallBack;
import bo.CustomerManagement;
import entities.Customer;
import java.io.File;
import java.util.Date;
import org.w3c.dom.Document;

/**
 *
 * @author khang
 */
public class SvCallBack implements CallBack {

    @Override
    public void callBackFunction(File xmlFile) {
//        System.out.println(xmlFile.getName().split("_").length);
        if (!xmlFile.getName().toLowerCase().endsWith(".xml")
                || !xmlHandle.XMLHandler.vefifyXSDvsXML(AppConfig.XSD_FILE_PATH, xmlFile.getPath())
                || xmlFile.getName().split("_").length != 2) {
            System.err.println("Bỏ qua file " + xmlFile.getName());
            return;
        }
        String[] element = {"name", "passport", "dob", "phoneNumber", "email", "score", "accountType", "gender", "cardID"};
        String clientPath = xmlFile.getName().split("_")[1].replace(".", "~").split("~")[0];
        System.out.println(clientPath);
        Document doc = xmlHandle.XMLHandler.readFileXml(xmlFile.getPath());
        String mode = doc.getElementsByTagName("Type").item(0).getTextContent();
        Customer c = null;

        if (mode.equalsIgnoreCase("INSERT") || mode.equalsIgnoreCase("UPDATE")) {
            String cardID = doc.getElementsByTagName("cardID").item(0).getTextContent();
            String score = doc.getElementsByTagName("score").item(0).getTextContent();
            String fullname = doc.getElementsByTagName("name").item(0).getTextContent();
            String passport = doc.getElementsByTagName("passport").item(0).getTextContent();
            String email = doc.getElementsByTagName("email").item(0).getTextContent();
            String phoneNumber = doc.getElementsByTagName("phoneNumber").item(0).getTextContent();
            String dob = doc.getElementsByTagName("dob").item(0).getTextContent();
            String accType = doc.getElementsByTagName("accountType").item(0).getTextContent();
            String gender = doc.getElementsByTagName("gender").item(0).getTextContent();
            c = new Customer(fullname, passport, dob, phoneNumber, email, Integer.parseInt(score), accType, gender, cardID);
        }

        if (mode.equalsIgnoreCase("insert")) {
            try {
                if (new CustomerManagement().createWithNoXML(c)) {
                    util.Util.deleteFile(xmlFile.getPath());
                }
            } catch (Exception ex) {
//                Logger.getLogger(CallBackImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (mode.equalsIgnoreCase("Update")) {
            try {
                if (new CustomerManagement().updateWithNoXML(c)) {
                    System.out.println("Update thang cong roi nhe");
                    util.Util.deleteFile(xmlFile.getPath());
                }
            } catch (Exception ex) {
//                Logger.getLogger(CallBackImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (mode.equalsIgnoreCase("getdata")) {
            try {
                String cardID = doc.getElementsByTagName("cardID").item(0).getTextContent();
                CustomerManagement cm = new CustomerManagement();
                c = cm.getCustomerByCardID(cardID);
                String fileCreate = AppConfig.Output_PATH + util.Util.getOsPath() + new Date().getTime() + "_" + clientPath + ".xml";
                if (c != null) {
                    String[] val = {c.getName(), c.getPassport(), c.getDob(), c.getPhoneNumber(),
                        c.getEmail(), c.getScore() + "", c.getAccountType(), c.getGender(), c.getCardID()};
                    xmlHandle.XMLHandler.createXMLFile(AppConfig.Output_PATH, "Action", element, val, "Type", "GETDATA");
                }
            } catch (Exception ex) {
//                Logger.getLogger(CallBackImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (mode.equalsIgnoreCase("increase")) {
            try {
                System.out.println("INCREASE");
                String cardID = doc.getElementsByTagName("cardID").item(0).getTextContent();
                int score = Integer.parseInt(doc.getElementsByTagName("score").item(0).getTextContent());
                CustomerManagement cm = new CustomerManagement();
                c = cm.getCustomerByCardID(cardID);
                String fileCreate = AppConfig.Output_PATH + util.Util.getOsPath() + new Date().getTime() + "_" + clientPath + ".xml";
                if (c != null) {
                    c.setScore(c.getScore() + score);

                    String[] val = {c.getName(), c.getPassport(), c.getDob(), c.getPhoneNumber(),
                        c.getEmail(), c.getScore() + "", c.getAccountType(), c.getGender(), c.getCardID()};
                    xmlHandle.XMLHandler.createXMLFile(AppConfig.Output_PATH, "Action", element, val, "Type", "GETDATA");
                }
            } catch (Exception ex) {
//                Logger.getLogger(CallBackImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (mode.equalsIgnoreCase("decrease")) {
            try {
                String cardID = doc.getElementsByTagName("cardID").item(0).getTextContent();
                int score = Integer.parseInt(doc.getElementsByTagName("score").item(0).getTextContent());
                CustomerManagement cm = new CustomerManagement();
                c = cm.getCustomerByCardID(cardID);
                String fileCreate = AppConfig.Output_PATH + util.Util.getOsPath() + new Date().getTime() + "_" + clientPath + ".xml";
                if (c != null) {
                    c.setScore(c.getScore() + score);

                    String[] val = {c.getName(), c.getPassport(), c.getDob(), c.getPhoneNumber(),
                        c.getEmail(), c.getScore() + "", c.getAccountType(), c.getGender(), c.getCardID()};
                    xmlHandle.XMLHandler.createXMLFile(AppConfig.Output_PATH, "Action", element, val, "Type", "GETDATA");
                }
            } catch (Exception ex) {
//                Logger.getLogger(CallBackImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
