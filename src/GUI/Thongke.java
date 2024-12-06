
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
public class Thongke extends javax.swing.JInternalFrame {

    private Account currentAcc;

    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public Thongke() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Thongke(Account t) {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        tblSanPham.setDefaultEditor(Object.class, null);
        initTable();

        this.currentAcc = t;
        
        List<Phieu> lichsumua = Run.PhieuMuaTree.getInOrderList();

        Collections.sort(lichsumua, new TimeComparator());
        loadDataToTable(lichsumua);
        loadDataProduct();
        loadDataAccount();
        loadDataDoanhSo(lichsumua);
    }

    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{ "Tên máy", "Số lượng", "Tổng giá", "số điện thoại đặt hàng", "địa chỉ", "time"};
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

        // Duyệt danh sách đã sắp xếp để tìm phiếu tương ứng với số hóa đơn
        List<Phieu> lichsumua = Run.PhieuMuaTree.getInOrderList();
        Collections.sort(lichsumua, new TimeComparator()); // Đảm bảo danh sách đã được sắp xếp

        // Lấy phiếu tương ứng với số hóa đơn (dựa vào thứ tự xuất hiện trong bảng)
        if (billNumber > 0 && billNumber <= lichsumua.size()) {
            return lichsumua.get(billNumber - 1); // Vì `billNumber` bắt đầu từ 1, nên trừ đi 1 để lấy chỉ số
        }

        // Nếu không tìm thấy, ném ngoại lệ
        throw new IllegalStateException("Không tìm thấy phiếu tương ứng với hóa đơn.");
    }


    public void loadDataProduct() {
        String s = "";
        s = s + Run.ProductTree.size();
        txtQuantityProduct.setText(s);

    }

    public void loadDataAccount() {
        String s = "";
        s = s + Run.AccountTree.size();
        txtQuantityUser.setText(s);

    }

    public void loadDataDoanhSo(List<Phieu> PhieuList) {
        try {
            Double tongTien = 0.0;
            int tongDon = 0;

            for (Phieu i : PhieuList) {
                if(i.getTracking() == 5) {
                    tongTien += i.getTongTien();
                    tongDon++;
                }

            }
            txtQuantityNcc.setText(formatter.format(tongTien) + "đ");
            tongTien1.setText(formatter.format(tongTien) + "đ");

            soLuong1.setText(formatter.format(tongDon));
        } catch (Exception e) {
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
        jPanel9 = new javax.swing.JPanel();
        txtQuantityProduct = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtQuantityNcc = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtQuantityUser = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        soLuong1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tongTien1 = new javax.swing.JLabel();

        //Banh------------------------------------------------
        btnEditTracking = new javax.swing.JButton();
      //  jSeparator1 = new javax.swing.JToolBar.Separator();

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
        jPanel3.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 300, 40));

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
        jPanel3.add(btnEditTracking, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 130, 40));
        // jPanel3.add(jSeparator1);


        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_reset_25px_1.png"))); // NOI18N
        jButton7.setText("Làm mới");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 20, 130, 40));

        tblSanPham.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblSanPham);

        jPanel9.setBackground(new java.awt.Color(255, 204, 0));

        txtQuantityProduct.setFont(new java.awt.Font("SF Pro Display", 1, 36)); // NOI18N
        txtQuantityProduct.setForeground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("SF Pro Display", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sản phẩm trong kho");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-monitor-80.png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuantityProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(73, 73, 73))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtQuantityProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addGap(10, 10, 10))
        );

        jPanel10.setBackground(new java.awt.Color(255, 102, 0));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-supplier-80.png"))); // NOI18N

        txtQuantityNcc.setFont(new java.awt.Font("SF Pro Display", 1, 36)); // NOI18N
        txtQuantityNcc.setForeground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("SF Pro Display", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Doanh số");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel13)
                .addGap(43, 43, 43)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtQuantityNcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtQuantityNcc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)))
                .addGap(10, 10, 10))
        );

        jPanel11.setBackground(new java.awt.Color(0, 204, 204));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-account-80.png"))); // NOI18N

        txtQuantityUser.setFont(new java.awt.Font("SF Pro Display", 1, 36)); // NOI18N
        txtQuantityUser.setForeground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("SF Pro Display", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tài khoản người dùng");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQuantityUser, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(73, 73, 73))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtQuantityUser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)))
                .addGap(10, 10, 10))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Đơn xuất");

        jLabel24.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel24.setText("TỔNG ĐƠN GIAO THÀNH CÔNG");

        soLuong1.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        soLuong1.setText("0");

        jLabel23.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel23.setText("TỔNG TIỀN");

        tongTien1.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        tongTien1.setForeground(new java.awt.Color(255, 0, 51));
        tongTien1.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(soLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(soLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//Banh--------------------------------------
    private void btnEditTrackingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditTrackingActionPerformed
        // TODO add your handling code here:
        if (tblSanPham.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu cần chỉnh sửa trạng thái!");
        } else if(getPhieuSelect().getTracking() == 6) {
            JOptionPane.showMessageDialog(this, "Đơn hàng đã bị hủy. Không thể thay đổi trạng thái.");
        } else {
            UpdateTracking u = new UpdateTracking(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled);
            u.setVisible(true);
        }
    }//GEN-LAST:event_btnEditTrackingActionPerformed
//-------------------------------------------
    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchKeyPressed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        List<Phieu> lichsumua = Run.PhieuMuaTree.getInOrderList();
        List<Phieu> list = new ArrayList<>();
        Collections.sort(lichsumua, new TimeComparator());

        for (Phieu phieu : lichsumua) {
            for (ChiTietPhieu ctPhieu : phieu.getPhieu()) {
                if (ctPhieu.getTenSanPham().toLowerCase().contains(jTextFieldSearch.getText().toLowerCase())) {
                    list.add(phieu);
                    break;
                }
            }
        }
        loadDataToTable(list);
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        List<Phieu> lichsumua = Run.PhieuMuaTree.getInOrderList();
        jTextFieldSearch.setText("");
        Collections.sort(lichsumua, new TimeComparator());
        loadDataToTable(lichsumua);
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Thongke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Thongke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Thongke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Thongke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Thongke().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JLabel soLuong1;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JLabel tongTien1;
    private javax.swing.JLabel txtQuantityNcc;
    private javax.swing.JLabel txtQuantityProduct;
    private javax.swing.JLabel txtQuantityUser;
    private javax.swing.JButton btnEditTracking;
    //private javax.swing.JToolBar.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
