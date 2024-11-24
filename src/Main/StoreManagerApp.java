package Main;

import GUI.Run;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class StoreManagerApp {
    public static void main(String[] args) {
        try {
            // Thiết lập giao diện người dùng
            UIManager.setLookAndFeel(new FlatLightLaf());

            // Khởi động ứng dụng
            Run.main(args);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
