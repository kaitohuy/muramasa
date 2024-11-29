package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	
	public static JFrame window;

    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Ngăn việc đóng mặc định
        window.setResizable(false);
        window.setTitle("Muramasa");
        new Main().setIcon();

        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);

        gamepanel.config.loadConfig();
        if (gamepanel.fullScreenOn) {
            window.setUndecorated(true);
        }

        window.pack();

        window.setLocationRelativeTo(null); // Center
        window.setVisible(true);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (gamepanel.currentMap >= 1) {
                    gamepanel.gameState = gamepanel.pauseState;
                }

                // Hiển thị hộp thoại xác nhận
                int confirm = JOptionPane.showConfirmDialog(
                        window,
                        "Bạn có chắc chắn muốn thoát game?",
                        "Xác nhận thoát",
                        JOptionPane.YES_NO_OPTION
                );

                // Nếu người dùng chọn "Yes", thoát chương trình
                if (confirm == JOptionPane.YES_OPTION) {
                    if (gamepanel.currentMap >= 1) {
                        gamepanel.saveLoad.save();
                    }
                    System.exit(0);
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            	if (gamepanel.gameState != gamepanel.titleState) {
            		gamepanel.gameState = gamepanel.pauseState;
                }else {
                	gamepanel.gameState = gamepanel.titleState;
                }
                
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            	if (gamepanel.currentMap >= 1) {
            		gamepanel.gameState = gamepanel.playState;
                }else {
                	gamepanel.gameState = gamepanel.titleState;
                }
            }
        });


        gamepanel.setupGame();
        gamepanel.startGameThread();
    }
	
	public void setIcon() {
		
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/player/king_down_1.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		window.setIconImage(icon.getImage());
	}
}


