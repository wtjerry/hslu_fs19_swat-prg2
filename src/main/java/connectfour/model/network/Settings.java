package connectfour.model.network;

import java.io.File;

public class Settings {

    private static Settings instance;
    
    private final int port;
    private final String baseIpAddress;
    private final String saveGamePath;
    
    private int gameFieldHeight;
    private int gameFieldWidth;

    private Settings() {
        this.port = 5400;
        this.baseIpAddress = "192.168.1.";
        this.gameFieldHeight = 4;
        this.gameFieldWidth = 6;
        this.saveGamePath = System.getProperty("user.home") + File.separator + "saveGame.ser";
    }

    public static int getPort() {
        return instance.port;
    }

    public static String getBaseIpAddress() {
        return instance.baseIpAddress;
    }

    public static int getGameFieldHeight() {
        return instance.gameFieldHeight;
    }

    public static int getGameFieldWidth() {
        return instance.gameFieldWidth;
    }

    public static String getSaveGamePath() {
        return instance.saveGamePath;
    }

    public static void setGameFieldHeight(int height) {
        instance.gameFieldHeight = height;
    }

    public static void setGameFieldWidth(int width) {
        instance.gameFieldWidth = width;
    }

    public static void init() {
        instance = new Settings();
    }
}
