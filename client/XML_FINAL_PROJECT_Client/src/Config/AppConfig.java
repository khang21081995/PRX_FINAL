package Config;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author phamquangkhang
 */
public class AppConfig {

    public AppConfig() {
    }

    public static String INPUT_PATH = (xmlHandle.XMLHandler.readFileXml("config.xml").getElementsByTagName("InputPath").item(0).getTextContent().trim());
    public static String Output_PATH = (xmlHandle.XMLHandler.readFileXml("config.xml").getElementsByTagName("OutputPath").item(0).getTextContent().trim());
    public static String CLIENT_PUBLIC_KEY = (xmlHandle.XMLHandler.readFileXml("config.xml").getElementsByTagName("ClientPublicKey").item(0).getTextContent().trim());
    public static String CLIENT_PRIVATE_KEY = (xmlHandle.XMLHandler.readFileXml("config.xml").getElementsByTagName("ClientPrivateKey").item(0).getTextContent().trim());
    public static String PUBLIC_KEY_TO_ENCRYPT = (xmlHandle.XMLHandler.readFileXml("config.xml").getElementsByTagName("PublicKeyToEncrypt").item(0).getTextContent().trim());
    public static String XSD_FILE_PATH = (xmlHandle.XMLHandler.readFileXml("config.xml").getElementsByTagName("xsdFilePath").item(0).getTextContent().trim());
    public static String CINEMA_ID = (xmlHandle.XMLHandler.readFileXml("config.xml").getElementsByTagName("Cid").item(0).getTextContent().trim());

}
