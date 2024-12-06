
package GUI;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import System.Account;
import System.Phieu;
import System.ChiTietPhieu;
import java.util.Collections;
import System.TimeComparator;

/**
 *
 * @author Vu Tuan Ngoc
 */
public class LichSuMuaCustomer extends javax.swing.JInternalFrame {

    private Account currentAcc;

    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public LichSuMuaCustomer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public LichSuMuaCustomer(Account t) {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        tblSanPham.setDefaultEditor(Object.class, null);
        initTable();

        this.currentAcc = t;
        System.out.println(t);
        List<Phieu> lichsumua = Run.PhieuMuaTree.search(currentAcc.getUser());

        Collections.sort(lichsumua, new TimeComparator());
        loadDataToTable(lichsumua);
        System.out.println(lichsumua);
    }

    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{ "Tên máy", "Số lượng", "Đơn giá", "số điện thoại đặt hàng", "địa chỉ", "time"};
        tblModel.setColumnIdentifiers(headerTbl);
        tblSanPham.setModel(tblModel);

    }

    public void loadDataToTable(List<Phieu> PhieuList) {
        try {
            tblModel.setRowCount(0); // Xóa các hàng cũ

            int billNumber = 1; // Biến đếm để đánh số hóa đơn

            for (Phieu phieu : PhieuList) {
                // Hiển thị thông tin đơn hàng (Phieu)
                tblModel.addRow(new Object[]{
                        "Bill: " + billNumber, // Bill ID
                        "SĐT: " + phieu.getPhone(), // Số điện thoại
                        "Địa chỉ: " + phieu.getAddress(), // Địa chỉ
                        "Thời gian: " + phieu.getThoiGianTao(), // Thời gian
                        "Trạng thái đơn hàng: " + phieu.getTrackingStatus(), //Tracking
                        "", // Cột trống
                        ""  // Cột trống
                });

                // Hiển thị thông tin các sản phẩm (ChiTietPhieu) trong đơn hàng
                double total = 0;
                for (ChiTietPhieu ctPhieu : phieu.getPhieu()) {
                    tblModel.addRow(new Object[]{
                            "   " + ctPhieu.getTenSanPham(), // Tên sản phẩm
                            ctPhieu.getSoLuong(),           // Số lượng
                            formatter.format(ctPhieu.getGia()) + "đ", // Đơn giá
                            "", "", "" // Cột trống
                    });
                    total += ctPhieu.getSoLuong() * ctPhieu.getGia(); // Tính tổng tiền

                }

                // Hiển thị tổng tiền của đơn hàng
                tblModel.addRow(new Object[]{
                        "", "", "", "Tổng:", formatter.format(total) + "đ", ""
                });

                billNumber++; // Tăng số hóa đơn
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

//Banh------------------------------------------------
        btnEditTracking = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldSearch.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên máy"));
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });
        jPanel3.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 300, 40));

//Banh------------------------------------------------
        btnEditTracking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_edit_40px.png"))); // NOI18N
        btnEditTracking.setText("Sửa");
        btnEditTracking.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditTracking.setFocusable(false);
        btnEditTracking.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditTracking.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditTracking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditTrackingActionPerformed(evt);
            }
        });
        jPanel3.add(btnEditTracking, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 130, 40));


        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_reset_25px_1.png"))); // NOI18N
        jButton7.setText("Làm mới");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 30, 130, 40));

        tblSanPham.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1209, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//Banh------------------------------------------------
    public Phieu getPhieuSelect() {
        int selectedRow = tblSanPham.getSelectedRow();

        // Kiểm tra nếu không có dòng nào được chọn
        if (selectedRow == -1) {
            throw new IllegalStateException("Vui lòng chọn một dòng trong bảng.");
        }

        // Lấy thông tin từ cột đầu tiên của dòng đã chọn (Bill ID)
        String billInfo = (String) tblSanPham.getValueAt(selectedRow, 0);

        // Kiểm tra nếu không phải là hàng đại diện cho "Bill: [số]"
        if (!billInfo.startsWith("Bill: ")) {
            throw new IllegalStateException("Vui lòng chọn dòng đại diện cho hóa đơn.");
        }

        // Trích xuất số hóa đơn từ chuỗi "Bill: [số]"
        int billNumber = Integer.parseInt(billInfo.replace("Bill: ", "").trim());

        List<Phieu> lichsumua = Run.PhieuMuaTree.search(currentAcc.getUser());
        Collections.sort(lichsumua, new TimeComparator()); // Sort by time

        // Lấy phiếu tương ứng với số hóa đơn (dựa vào thứ tự xuất hiện trong bảng)
        if (billNumber > 0 && billNumber <= lichsumua.size()) {
            return lichsumua.get(billNumber - 1); // Vì `billNumber` bắt đầu từ 1, nên trừ đi 1 để lấy chỉ số
        }

        // Nếu không tìm thấy, ném ngoại lệ
        throw new IllegalStateException("Không tìm thấy phiếu tương ứng với hóa đơn.");
    }
    //Banh--------------------------------------
    private void btnEditTrackingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditTrackingActionPerformed
        // TODO add your handling code here:
        if (tblSanPham.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu cần chỉnh sửa trạng thái!");
        } else {
            if(getPhieuSelect().getTracking() == 6){
                JOptionPane.showMessageDialog(this, "Đơn hàng đã bị hủy. Xin vui lòng liên hệ Admin!");
            }else if (getPhieuSelect().getTracking() == 5) {
                JOptionPane.showMessageDialog(this, "Đơn hàng đã được giao hàng. Không thể cập nhập trạng thái.");
            } else {
                UpdateTrackingCustomer u = new UpdateTrackingCustomer(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled,currentAcc);
                u.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnEditTrackingActionPerformed

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchKeyPressed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {
        // Get all the purchases related to the current account
        List<Phieu> lichsumua = Run.PhieuMuaTree.search(currentAcc.getUser());
        List<Phieu> list = new ArrayList<>();
        Collections.sort(lichsumua, new TimeComparator()); // Sort by time

        // Iterate through each Phieu
        for (Phieu phieu : lichsumua) {
            for (ChiTietPhieu ctPhieu : phieu.getPhieu()) {
                // Check if the product name matches the search query
                if (ctPhieu.getTenSanPham().toLowerCase().contains(jTextFieldSearch.getText().toLowerCase())) {
                    list.add(phieu);
                    break; // Stop checking other items in this Phieu, as it's already added
                }
            }
        }

        // Update the table with the filtered list of Phieu
        loadDataToTable(list);
    }
//GEN-LAST:event_jTextFieldSearchKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LichSuMuaCustomer().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JButton btnEditTracking;
    // End of variables declaration//GEN-END:variables
}
