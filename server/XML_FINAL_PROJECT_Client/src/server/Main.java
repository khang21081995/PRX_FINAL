/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Config.AppConfig;
import Config.CallBack;
import bo.CallBackImplement;
import java.io.File;

/**
 *
 * @author khang
 */
public class Main {

    private static File folder = new File(AppConfig.INPUT_PATH);
    private static CallBack cb = new SvCallBack();

    public static boolean startAppService() {
//        System.out.println("1");
        File[] listOfFiles = folder.listFiles();
//        System.out.println("2");
        System.out.println(folder.getAbsolutePath());
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().toLowerCase().endsWith(".xml")) {
                System.out.println("File " + listOfFiles[i].getName());
                cb.callBackFunction(listOfFiles[i]);
            }
//            else if (listOfFiles[i].isDirectory()) {
//                System.out.println("Directory " + listOfFiles[i].getName());
//            }
        }
        return true;
    }

    public static boolean wathching() {
        util.Util.LookupChange(folder.getPath(), cb);
        return true;
    }

    public static void main(String[] args) {
        startAppService();
        wathching();
    }
}
