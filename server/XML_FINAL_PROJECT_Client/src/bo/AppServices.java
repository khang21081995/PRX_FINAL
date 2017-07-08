/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import Config.AppConfig;
import Config.CallBack;
import java.io.File;

/**
 *
 * @author phamquangkhang
 */
public class AppServices {

    private static File folder = new File(AppConfig.INPUT_PATH);
    private static CallBack cb = new CallBackImplement();

    public static boolean startAppService() {
        System.out.println("1");
        File[] listOfFiles = folder.listFiles();
        System.out.println(folder.getAbsolutePath());
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().toLowerCase().endsWith(AppConfig.CINEMA_ID.toLowerCase() + ".xml")) {
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
