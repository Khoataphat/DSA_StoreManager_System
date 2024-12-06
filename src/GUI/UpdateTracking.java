package GUI;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import System.Phieu;
import System.TimeComparator;
import System.FunctionWrapper;
public class UpdateTracking extends javax.swing.JDialog {
    private Thongke owner;
    public UpdateTracking(javax.swing.JInternalFrame parent, javax.swing.JFrame owner, boolean modal){
        super(owner, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.owner = (Thongke) parent;
        Phieu phieu = this.owner.getPhieuSelect();

        txtMaKhachHang.setText(phieu.getUsername());
        txtPhone.setText(phieu.getPhone());
        txtNgayMua.setText(String.valueOf(phieu.getThoiGianTao()));
        txtTongTien.setText(String.valueOf(phieu.getTongTien()));
        cmbTrangThaiDon.setSelectedItem(phieu.getTracking());

        txtMaKhachHang.setEditable(false);
        txtPhone.setEditable(false);
        txtNgayMua.setEditable(false);
        txtTongTien.setEditable(false);

        ImageIcon logo = new ImageIcon(getClass().getResource("/Icon/logo.png"));
        setIconImage(logo.getImage());
        setTitle("Cập nhật");
    }
    UpdateTracking() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT modify//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNgayMua = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbTrangThaiDon = new javax.swing.JComboBox<>();
        btnupdate = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sửa tài khoản");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel2.setText("Mã Khách Hàng");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 110, -1));
        jPanel1.add(txtMaKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 298, 38));

        jLabel3.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel3.setText("Phone Number");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, 24));
        jPanel1.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 298, 38));

        btnupdate.setBackground(new java.awt.Color(204, 204, 255));
        btnupdate.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        btnupdate.setForeground(new java.awt.Color(255, 255, 255));
        btnupdate.setText("Cập nhật");
        btnupdate.setBorder(null);
        btnupdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnupdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnupdateMouseClicked(evt);
            }
        });
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, 140, 38));

        jLabel4.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel4.setText("Ngày mua");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));
        jPanel1.add(txtNgayMua, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 298, 38));

        jLabel5.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel5.setText("Tong tiền");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));
        jPanel1.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 298, 38));

        jLabel6.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        jLabel6.setText("Trạng thái đơn");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, -1, -1));

        cmbTrangThaiDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang chờ xử lý", "Đã được xác nhận", "Đang chuẩn bị", "Đang vận chuyển", "Đã nhận hàng", "Đã hủy" }));
        jPanel1.add(cmbTrangThaiDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 298, 38));

        btnClose.setBackground(new java.awt.Color(255, 51, 51));
        btnClose.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("Huỷ");
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        jPanel1.add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 560, 140, 38));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("SF Pro Display", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CẬP NHẬT TRẠNG THÁI ĐƠN HÀNG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel1)
                                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel1)
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE).addGap(20)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnupdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnupdateMouseClicked
        try {
            // Kiểm tra trạng thái được chọn từ combobox
            String selectedStatus = (String) cmbTrangThaiDon.getSelectedItem();
            if (selectedStatus == null || selectedStatus.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái đơn hàng!");
                return;
            }

            // Gán giá trị trạng thái dựa trên lựa chọn
            switch (selectedStatus) {
                case "Đang chờ xử lý":
                    owner.getPhieuSelect().setTracking(1);
                    break;
                case "Đã được xác nhận":
                    owner.getPhieuSelect().setTracking(2);
                    break;
                case "Đang chuẩn bị":
                    owner.getPhieuSelect().setTracking(3);
                    break;
                case "Đang vận chuyển":
                    owner.getPhieuSelect().setTracking(4);
                    break;
                case "Đã nhận hàng":
                    owner.getPhieuSelect().setTracking(5);
                    break;
                case "Đã hủy":
                    owner.getPhieuSelect().setTracking(6);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái đơn hàng hợp lệ!");
                    return;
            }

            // Cập nhật giao diện
            this.dispose();
            List<Phieu> lichsumua = Run.PhieuMuaTree.getInOrderList();
            Collections.sort(lichsumua, new TimeComparator());
            owner.loadDataToTable(lichsumua);

            // Hiển thị thông báo thành công
            JOptionPane.showMessageDialog(this, "Cập nhật trạng thái thành công!");

            // Lưu dữ liệu
            Run.WriteDataPhieuMua();
        } catch (IOException ex) {
            Logger.getLogger(UpdateTracking.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu: " + ex.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_btnupdateMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnupdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> cmbTrangThaiDon;
    private javax.swing.JTextField txtNgayMua;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtMaKhachHang;
}
