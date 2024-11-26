package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try {
            // Kiểm tra và tạo file config.txt nếu không có
            File configFile = new File("config.txt");
            if (!configFile.exists()) {
                configFile.createNewFile(); // Tạo file nếu chưa có
            }
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(configFile));

            // Full screen
            if(gp.fullScreenOn) {
                bw.write("On");
            } else {
                bw.write("Off");
            }
            bw.newLine();

            // Music volume
            bw.write(String.valueOf(gp.music.VolumeScale));
            bw.newLine();

            // SE volume
            bw.write(String.valueOf(gp.se.VolumeScale));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            // Kiểm tra file config.txt có tồn tại không
            File configFile = new File("config.txt");
            if (!configFile.exists()) {
                System.out.println("config.txt not found, creating a new one");
                saveConfig(); // Tạo file mới nếu không tìm thấy
                return; // Không tiếp tục nếu không tìm thấy file
            }

            BufferedReader br = new BufferedReader(new FileReader(configFile));

            String s = br.readLine();

            // Fullscreen
            if(s.equals("On")) {
                gp.fullScreenOn = true;
            } else if(s.equals("Off")) {
                gp.fullScreenOn = false;
            }

            // Music volume
            s = br.readLine();
            gp.music.VolumeScale = Integer.parseInt(s);

            // SE volume
            s = br.readLine();
            gp.se.VolumeScale = Integer.parseInt(s);

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
