package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import System.Account;

/**
 *
 * @author
 */
public class ChangeInfo extends javax.swing.JDialog {

    /**
     * Creates new form AddAccount
     */
    private Account accCur;

    public Account getAccCur() {
        return accCur;
    }

    public void setAccCur(Account accCur) {
        this.accCur = accCur;
    }

    public ChangeInfo(javax.swing.JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        //ImageIcon logo = new ImageIcon(getClass().getResource("/Icon/logo.png"));
        //setIconImage(logo.getImage());
        setTitle("Tài khoản của tôi");
    }

    public ChangeInfo(javax.swing.JFrame parent, boolean modal, Account t) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.accCur = t;
        tenTaiKhoan.setText(this.accCur.getFullName());
        phone.setText(this.accCur.getPhone());

    }

    ChangeInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static boolean isOnlyDigits(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tenTaiKhoan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        phone = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        panel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        passCom = new javax.swing.JPasswordField();
        passCur = new javax.swing.JPasswordField();
        passAft = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        passCurEr = new javax.swing.JLabel();
        passAftEr = new javax.swing.JLabel();
        passComEr = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cập nhật thông tin tài khoản");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("SF Pro Display", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("THAY ĐỔI THÔNG TIN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Họ và tên");

        jLabel3.setText("Số điện thoại");

        jButton1.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jButton1.setText("Lưu thay đổi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(tenTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                            .addComponent(phone)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        jTabbedPane1.addTab("Thông tin", panel);

        panel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Mật khẩu hiện tại ");

        jLabel5.setText("Mật khẩu mới");

        jLabel6.setText("Nhập lại mật khẩu mới");

        jButton2.setText("Đổi mật khẩu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        passCurEr.setForeground(new java.awt.Color(255, 51, 102));

        passAftEr.setForeground(new java.awt.Color(255, 51, 102));

        passComEr.setForeground(new java.awt.Color(255, 51, 102));

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(passCom, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                        .addComponent(passAft)
                        .addComponent(passCur)
                        .addComponent(passCurEr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passAftEr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passComEr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passCur, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(passCurEr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passAft, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passAftEr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passCom, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passComEr)
                .addGap(29, 29, 29)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mật khẩu", panel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("Thông tin tài khoản");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:     
        String nameAccount = tenTaiKhoan.getText();
        String phoneAccount = phone.getText();

        if (nameAccount.equals("") || phoneAccount.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
        } else {
            if (isOnlyDigits(phoneAccount)) {
                String newSL = JOptionPane.showInputDialog(this, "Nhập mật khẩu", "Change information", QUESTION_MESSAGE);
                if (Run.AccountTree.get(this.accCur.getUser()).getAccount().getPassword().equals(newSL)) {
                    accCur.setFullName(nameAccount);
                    accCur.setPhone(phoneAccount);
                    Run.AccountTree.get(this.accCur.getUser()).getAccount().setFullName(accCur.getFullName());
                    Run.AccountTree.get(this.accCur.getUser()).getAccount().setPhone(accCur.getPhone());

                    try {
                        Run.WriteDataAccount();
                    } catch (IOException ex) {
                        Logger.getLogger(ChangeInfo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(this, "Đã thay đổi thành công !");
                } else {
                    JOptionPane.showMessageDialog(this, "Sai mật khẩu !");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng !");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        boolean check = true;
        String curPass = passCur.getText();
        String newPass = passAft.getText();
        String newPassConf = passCom.getText();

        if (curPass.length() == 0) {
            check = false;
            passCurEr.setText("Vui lòng nhập mật khẩu hiện tại");
        } else {
            passCurEr.setText("");
        }
        if (newPass.length() == 0) {
            check = false;
            passAftEr.setText("Vui lòng nhập mật khẩu mới");
        } else {
            passAftEr.setText("");
        }
        if (newPassConf.length() == 0) {
            check = false;
            passComEr.setText("Vui lòng xác nhận lại mật khẩu");
        } else {
            passComEr.setText("");
        }
        if (check == true) {
            if (Run.AccountTree.get(this.accCur.getUser()).getAccount().getPassword().equals(curPass)) {
                if (newPass.length() < 6) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới lớn bằng 6 kí tự");
                } else {
                    if (newPass.equals(newPassConf)) {
                        accCur.setPassword(newPass);
                        Run.AccountTree.get(this.accCur.getUser()).getAccount().setPassword(newPass);
                        JOptionPane.showMessageDialog(this, "Thay đổi mật khẩu thành công!");
                        passCur.setText("");
                        passAft.setText("");
                        passCom.setText("");
                        try {
                            Run.WriteDataAccount();
                        } catch (IOException ex) {
                            Logger.getLogger(ChangeInfo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Mật khẩu mới không khớp");
                        passCur.setText("");
                        passAft.setText("");
                        passCom.setText("");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mật khẩu hiện tại không đúng ");
                passCur.setText("");
                passAft.setText("");
                passCom.setText("");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel2;
    private javax.swing.JPasswordField passAft;
    private javax.swing.JLabel passAftEr;
    private javax.swing.JPasswordField passCom;
    private javax.swing.JLabel passComEr;
    private javax.swing.JPasswordField passCur;
    private javax.swing.JLabel passCurEr;
    private javax.swing.JTextField phone;
    private javax.swing.JTextField tenTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
