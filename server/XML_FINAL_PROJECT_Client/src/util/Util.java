/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Config.CallBack;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

/**
 *
 * @author phamquangkhang
 */
public class Util {

    public static String getOsPath() {
        String windowsPath = "\\";
        String nonWindowsPath = "/";
        return System.getProperty("os.name").startsWith("Windows") ? windowsPath : nonWindowsPath;
    }

    public static String modifyToGetCorrectOSPath(String path) {
        String windowsPath = "\\";
        String nonWindowsPath = "/";
        return System.getProperty("os.name").startsWith("Windows") ? path.replaceAll(nonWindowsPath, windowsPath) : path.replaceAll(windowsPath, nonWindowsPath);
    }

    public static void LookupChange(String folderPath, CallBack callback) {

        File inFolder = new File(
//                modifyToGetCorrectOSPath
        (folderPath));
        Path path = inFolder.toPath();
        System.out.println("Watching path: " + path);

        // We obtain the file system of the Path
        FileSystem fs = path.getFileSystem();

        // We create the new WatchService using the new try() block
        try (WatchService service = fs.newWatchService()) {

            // We register the path to the service
            // We watch for creation events
            path.register(service, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
            // Start the infinite polling loop
            WatchKey key = null;
            while (true) {
//                    Thread.sleep(2000);
                key = service.take();

                // Dequeueing events
                WatchEvent.Kind<?> kind = null;
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
//                        Thread.sleep(2000);
                    // Get the type of the event
                    kind = watchEvent.kind();
                    if (OVERFLOW == kind) {
                        continue; // loop
                    } else if (ENTRY_CREATE == kind) {
                        // A new Path was created
                        Path newPath = ((WatchEvent<Path>) watchEvent).context();
                        // Output
                        System.out.println("New path created: " + newPath);
                        if (newPath.toString().toLowerCase().endsWith(".xml")) {
//                            joinAndRun(new File(inFolder.getPath() + getOsPath() + newPath));
                            callback.callBackFunction(new File(inFolder.getPath() + getOsPath() + newPath));
                        }
                    } else if (ENTRY_MODIFY == kind) {
                        // modified
                        Path newPath = ((WatchEvent<Path>) watchEvent).context();
                        // Output
                        System.out.println("New path modified: " + newPath);
                        if (newPath.toString().toLowerCase().endsWith(".xml")) {
//                            joinAndRun(new File(inFolder.getPath() + getOsPath() + newPath));
                            callback.callBackFunction(new File(inFolder.getPath() + getOsPath() + newPath));
                        }

                    } else if (ENTRY_DELETE == kind) {
                        // modified
                        Path newPath = ((WatchEvent<Path>) watchEvent).context();
                        // Output
                        System.out.println("New path delete: " + newPath);
                    }
                }

                if (!key.reset()) {
                    break; // loop
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public static void openNewFormAndDestroyCloseForm(JFrame in, JFrame out) {
        in.setVisible(false);
        in.dispose();
        out.setVisible(true);
    }

    public static void openNewFormAndInvisibleForm(JFrame in, JFrame out) {
        in.setVisible(false);
        out.setVisible(true);
    }

    public static String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    public static boolean setSelectedButtonText(String text, JRadioButton... params) {
        for (int i = 0; i < params.length; i++) {
            if (params[i].getText().equalsIgnoreCase(text)) {
                System.err.println("Vao day");
                params[i].setSelected(true);
                return true;
            }
        }

        return false;
    }

    public static boolean deleteFile(String filePath) throws IOException {
        Path filePath1;
        filePath1 = Paths.get(filePath);
        return Files.deleteIfExists(filePath1);
    }
}
