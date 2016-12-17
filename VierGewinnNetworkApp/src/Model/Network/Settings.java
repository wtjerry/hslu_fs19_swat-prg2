package Model.Network;

import java.io.File;

public class Settings {

    public static int getPort() {
        return 5400;
    }

    public static String getBaseIpAddress() {
        return "192.168.1.";
    }

    public static int getGameFieldHeight() {
        return 4;
    }

    public static int getGameFieldWidth() {
        return 6;
    }

    public static String getSaveGamePath() {
        String homeDirectory = System.getProperty("user.home");
        String gameFilePath = homeDirectory + File.separator + "saveGame.ser";
        return gameFilePath;
    }
}
