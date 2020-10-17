/*
 * WirelessKeyboard
 * Device
 * Copyleft ! 2020, Olddoctor Development Team.
 * Some rights reserved. Use under GPL license.
 */
package net.olddoctor.WirelessKeyboardDevice;

import java.awt.*;
import java.io.InputStream;
import java.net.ServerSocket;

public class Device {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6709);
            InputStream inputStream = serverSocket.accept().getInputStream();

            Robot robot = new Robot();

            int noResponseTime = 0;

            while (true) {
                if(inputStream.available() > 0) {
                    noResponseTime = 0;
                    switch (inputStream.read()) {
                        //Heartbeat
                        case 'H' -> inputStream.read(); //Read '\n'
                        //Press
                        case 'P' -> {
                            StringBuilder pressKey = new StringBuilder();
                            int pressAppend;
                            while ((pressAppend = inputStream.read()) != '\n')
                                pressKey.append(pressAppend);
                            robot.keyPress(Integer.parseInt(pressKey.toString()));
                        }
                        //Release
                        case 'R' -> {
                            StringBuilder releaseKey = new StringBuilder();
                            int releaseAppend;
                            while ((releaseAppend = inputStream.read()) != '\n')
                                releaseKey.append(releaseAppend);
                            robot.keyRelease(Integer.parseInt(releaseKey.toString()));
                        }
                        default -> System.exit(-1);
                    }
                }
                noResponseTime++;
                if(noResponseTime >= 30000) {
                    System.out.println("No response for a long time. Connection stopped. \nWaiting for new Controller...");
                    inputStream = serverSocket.accept().getInputStream();
                }
                Thread.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
