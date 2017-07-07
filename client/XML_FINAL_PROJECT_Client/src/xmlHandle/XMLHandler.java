/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlHandle;

/**
 *
 * @author phamquangkhang
 */
import entities.Customer;
import javax.xml.validation.*;
import org.xml.sax.SAXException;
//import java.io.File; // if you use File

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.xml.XMLConstants;

import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import org.jdom2.Document;

public class XMLHandler {

    public static org.w3c.dom.Document readFileXml(String fileXml) {
        org.w3c.dom.Document document = null;
        File file = new File(fileXml);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    public static boolean createXMLFile(String outputPath, String rootNode, String[] elements, String[] value, String... plusElement) throws IOException, Exception {
        if (plusElement.length % 2 != 0 || elements.length != value.length) {
            throw new Exception("Lỗi trong quá trình tạo file xml");
        }
        Element rootElement = new Element(rootNode);
        Document doc = new Document(rootElement);
        int count = 0;
        for (String element : elements) {
            Element ele = new Element(element);
            ele.setText(value[count++]);
            doc.getRootElement().addContent(ele);
        }

        for (int i = 0; i < plusElement.length; i += 2) {
            Element ele = new Element(plusElement[i]);
            ele.setText(plusElement[i + 1]);
            doc.getRootElement().addContent(ele);
        }

        XMLOutputter xmlOutput = new XMLOutputter();

        // display ml
        FileOutputStream fos = new FileOutputStream(new File(outputPath));
        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(doc, fos);
        fos.close();
        return true;
    }

    public static boolean vefifyXSDvsXML(String XSDFilePath, String XMLFilePath) {
        File schemaFile = new File(XSDFilePath); // etc.
        System.err.println(XSDFilePath);
        System.err.println(schemaFile.isFile());
        Source xmlFile = new StreamSource(new File(XMLFilePath));
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            return true;
        } catch (Exception e) {
            System.err.println("Verified failed");
            System.err.println(e.getMessage());
            return false;
        }

    }

    public static void main(String[] args) {
        String root = "Action";
        String[] elements = {"Type", "cardID"};
        String[] elementsValue = {"Insert", "000000001"};
        try {
            createXMLFile(new Date().getTime() + ".xml", root, elements, elementsValue);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }
}
