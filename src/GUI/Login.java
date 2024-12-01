package GUI;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Login extends javax.swing.JFrame {

    public Login() throws IOException {
        initComponents();
        this.setLocationRelativeTo(null);
        ImageIcon logo = new ImageIcon(getClass().getResource("/Icon/logo.png"));
        setIconImage(logo.getImage());
        setTitle("Đăng nhập");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        loginUser = new javax.swing.JTextField();
        passwordUser = new javax.swing.JPasswordField();
        eyeIcon = new javax.swing.JLabel();
        lineUnderUsername = new javax.swing.JLabel(); // Dòng trắng dưới username
        lineUnderPassword = new javax.swing.JLabel(); // Dòng trắng dưới password
        JPaneLogin = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(13, 39, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 65));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Đăng nhập");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 370, 80));

        jLabel7.setIcon(new ImageIcon(getClass().getResource("/Icon/Untitled-1.png")));
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 260, 260));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 580));

        jLabel4.setFont(new java.awt.Font("SF Pro Display", 1, 18));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tên đăng nhập");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 210, 40));

        jLabel8.setFont(new java.awt.Font("SF Pro Display", 1, 18));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Mật khẩu");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 130, 40));

        loginUser.setBackground(new java.awt.Color(13, 39, 51));
        loginUser.setFont(new java.awt.Font("SF Pro Display", 1, 18));
        loginUser.setForeground(new java.awt.Color(255, 255, 255));
        loginUser.setBorder(null);
        loginUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                loginUserKeyPressed(evt);
            }
        });
        jPanel1.add(loginUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 200, 260, 30));

        // Dòng trắng dưới username
        lineUnderUsername.setOpaque(true);
        lineUnderUsername.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lineUnderUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, 260, 1));

        passwordUser.setBackground(new java.awt.Color(13, 39, 51));
        passwordUser.setForeground(new java.awt.Color(255, 255, 255));
        passwordUser.setBorder(null);
        passwordUser.setEchoChar('\u2022');
        passwordUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                passwordUserKeyPressed(evt);
            }
        });
        jPanel1.add(passwordUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 260, 30));

        // Dòng trắng dưới password
        lineUnderPassword.setOpaque(true);
        lineUnderPassword.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lineUnderPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, 260, 1));

        eyeIcon.setIcon(new ImageIcon(getClass().getResource("/Icon/eye_closed.png")));
        eyeIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eyeIcon.addMouseListener(new MouseAdapter() {
            private boolean isPasswordVisible = false;

            @Override
            public void mouseClicked(MouseEvent e) {
                isPasswordVisible = !isPasswordVisible;
                if (isPasswordVisible) {
                    passwordUser.setEchoChar((char) 0); // Show password
                    eyeIcon.setIcon(new ImageIcon(getClass().getResource("/Icon/eye_open.png")));
                } else {
                    passwordUser.setEchoChar('\u2022'); // Hide password
                    eyeIcon.setIcon(new ImageIcon(getClass().getResource("/Icon/eye_closed.png")));
                }
            }
        });
        jPanel1.add(eyeIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 290, 30, 30));

        JPaneLogin.setBackground(new java.awt.Color(204, 204, 255));
        JPaneLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JPaneLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkLogin();
            }
        });

        jLabel2.setFont(new java.awt.Font("SF Pro Display", 1, 18));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Đăng nhập");

        javax.swing.GroupLayout JPaneLoginLayout = new javax.swing.GroupLayout(JPaneLogin);
        JPaneLogin.setLayout(JPaneLoginLayout);
        JPaneLoginLayout.setHorizontalGroup(
                JPaneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPaneLoginLayout.createSequentialGroup()
                                .addContainerGap(88, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77))
        );
        JPaneLoginLayout.setVerticalGroup(
                JPaneLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPaneLoginLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(JPaneLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 360, 270, 40));

        jButton1.setBackground(new java.awt.Color(13, 39, 51));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Đăng ký");
        jButton1.addActionListener(evt -> {
            Signup signup = new Signup();
            signup.setVisible(true);
            this.dispose();
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 410, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    private void loginUserKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            checkLogin();
        }
    }

    private void passwordUserKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            checkLogin();
        }
    }

    public void checkLogin() {
        String user = loginUser.getText().trim().toLowerCase();
        String password = new String(passwordUser.getPassword()).trim();

        if (user.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        var accountNode = Run.AccountTree.get(user.toLowerCase());
        if (accountNode == null) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (accountNode.getAccount().getPassword().equals(password)) {
            String role = accountNode.getAccount().getRole();
            if (role != null) {
                role = role.trim().toLowerCase();
                switch (role) {
                    case "admin":
                        new Admin(accountNode.getAccount()).setVisible(true);
                        this.dispose();
                        break;
                    case "quản lý kho":
                        new QuanLyKho(accountNode.getAccount()).setVisible(true);
                        this.dispose();
                        break;
                    case "customer":
                        new Customer(accountNode.getAccount()).setVisible(true);
                        this.dispose();
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Quyền truy cập không hợp lệ!", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Quyền truy cập không hợp lệ!", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sai mật khẩu!", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());

        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Login().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private javax.swing.JPanel JPaneLogin;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel eyeIcon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField loginUser;
    private javax.swing.JPasswordField passwordUser;
    private javax.swing.JLabel lineUnderUsername;
    private javax.swing.JLabel lineUnderPassword;
}
