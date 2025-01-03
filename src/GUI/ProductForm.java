
package GUI;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import System.Function;
import System.FunctionWrapper;
import System.Product;
import AVL.ProductManagerTree;

/**
 *
 * @author
 */
public class ProductForm extends javax.swing.JInternalFrame {

    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    Stack<FunctionWrapper<Product>> functionStack = new Stack<>();
    public ProductForm() {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        tblSanPham.setDefaultEditor(Object.class, null);
        initTable();
        System.out.println("Trong ProductForm constructor, số lượng sản phẩm trong ProductTree: " + Run.ProductTree.size());
        loadDataToTable(Run.ProductTree);
    }

    public final void initTable() {
        tblModel = new DefaultTableModel();
//        String[] headerTbl = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Còn lại", "Đơn giá", "Ngày sản xuất", "Hạn sản xuất", "Khối lượng", "Thành phần", "Ngày nhập kho", "Số ngày giao","Đã bán"};
        String[] headerTbl = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Còn lại", "Đơn giá", "Ngày sản xuất", "Hạn sản xuất", "Thành phần","Khối lượng",  "Ngày nhập kho", "Số ngày giao","Đã bán"};

        tblModel.setColumnIdentifiers(headerTbl);
        tblSanPham.setModel(tblModel);
        tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(3);
        tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblSanPham.getColumnModel().getColumn(3).setPreferredWidth(3);
        tblSanPham.getColumnModel().getColumn(4).setPreferredWidth(5);
        tblSanPham.getColumnModel().getColumn(5).setPreferredWidth(5);
        tblSanPham.getColumnModel().getColumn(6).setPreferredWidth(5);
        tblSanPham.getColumnModel().getColumn(7).setPreferredWidth(5);
        tblSanPham.getColumnModel().getColumn(8).setPreferredWidth(5);
        tblSanPham.getColumnModel().getColumn(9).setPreferredWidth(5);
    }
/*
    public void loadDataToTable(ProductManagerTree tree) {
        try {
            List<Product> acc = Run.ProductTree.getInOrderList();
            tblModel.setRowCount(0);
            for (Product i : acc) {
                tblModel.addRow(new Object[]{
                    i.getMaSanPham(),
                    i.getTenSanPham(),
                    i.getSoLuong(), 
                    formatter.format(i.getGiaTien()) + "đ",
                    i.getNgaySanXuat(),
                    i.getHanSuDung(),
                    i.getKhoiLuong(),
                    i.getThanhPhan(),
                    Run.AmountSoldTree.get(i.getTenSanPham()).getAmountSold().getAmountSold()
                });
               
            }
        } catch (Exception e) {
            System.out.println("x");
        }

    }

 */

    public void loadDataToTable(ProductManagerTree tree) {
        try {
            List<Product> acc = Run.ProductTree.getInOrderList();
            System.out.println("loadDataToTable: Số lượng sản phẩm lấy được: " + (acc == null ? 0 : acc.size()));

            if (acc == null || acc.isEmpty()) {
                System.out.println("No products found in ProductTree.");
                return;
            }

            tblModel.setRowCount(0);
            for (Product i : acc) {
                try {
                    Integer amountSold = 0;
                    if (Run.AmountSoldTree.get(i.getTenSanPham()) != null) {
                        amountSold = Run.AmountSoldTree.get(i.getTenSanPham()).getAmountSold().getAmountSold();
                    }

                    // In ra thông tin sản phẩm để chắc chắn dữ liệu đọc đúng
                    System.out.println("Đang thêm sản phẩm vào bảng: " + i.getTenSanPham());

                    tblModel.addRow(new Object[]{
                            i.getMaSanPham(),
                            i.getTenSanPham(),
                            i.getSoLuong(),
                            formatter.format(i.getGiaTien()) + "đ",
                            i.getNgaySanXuat(),
                            i.getHanSuDung(),
                            i.getThanhPhan(), // Đảm bảo thứ tự đã sửa lại
                            i.getKhoiLuong(),
                            i.getNgayNhapKho(),
                            i.getSoNgayGiaoHang(),
                            amountSold
                    });
                } catch (Exception ex) {
                    System.err.println("Error adding row for product: " + i.getTenSanPham());
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public class AddProductStack implements Function<Product> {

        @Override
        public void execute(Product product) {
            Run.ProductTree.add(product.getTenSanPham(), product);
            try {
                Run.WriteDataProduct();
            } catch (IOException ex) {
                Logger.getLogger(ProductForm.class.getName()).log(Level.SEVERE, null, ex);
            }
             loadDataToTable(Run.ProductTree);
        }
    
    }
     public class DelProductStack implements Function<Product> {

        @Override
        public void execute(Product product) {
            Run.ProductTree.remove(product.getTenSanPham());
            try {
                Run.WriteDataProduct();
            } catch (IOException ex) {
                Logger.getLogger(AccountForm.class.getName()).log(Level.SEVERE, null, ex);
            }
             loadDataToTable(Run.ProductTree);
        }
    
    }
    public class UpdProductStack implements Function<Product> {

        @Override
        public void execute(Product product) {
            Run.ProductTree.get(product.getTenSanPham()).getProduct().setKhoiLuong(product.getKhoiLuong());
            Run.ProductTree.get(product.getTenSanPham()).getProduct().setGiaTien(product.getGiaTien());
            Run.ProductTree.get(product.getTenSanPham()).getProduct().setMaSanPham(product.getMaSanPham());
            Run.ProductTree.get(product.getTenSanPham()).getProduct().setHanSuDung(product.getHanSuDung());
            Run.ProductTree.get(product.getTenSanPham()).getProduct().setThanhPhan(product.getThanhPhan());
            Run.ProductTree.get(product.getTenSanPham()).getProduct().setSoLuong(product.getSoLuong());
            Run.ProductTree.get(product.getTenSanPham()).getProduct().setNgaySanXuat(product.getNgaySanXuat());
            Run.ProductTree.get(product.getTenSanPham()).getProduct().setNgayNhapKho(product.getNgayNhapKho());
            Run.ProductTree.get(product.getTenSanPham()).getProduct().setSoNgayGiaoHang(product.getSoNgayGiaoHang());


            try {
                Run.WriteDataProduct();
            } catch (IOException ex) {
                Logger.getLogger(AccountForm.class.getName()).log(Level.SEVERE, null, ex);
            }
             loadDataToTable(Run.ProductTree);
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
        jToolBar1 = new javax.swing.JToolBar();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDetail = new javax.swing.JButton();
        btnEdit1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jComboBoxLuaChon = new javax.swing.JComboBox<>();
        jTextFieldSearch = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));
        jToolBar1.setRollover(true);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_add_40px.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setFocusable(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAdd);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_delete_40px.png"))); // NOI18N
        btnDelete.setText("Xoá");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDelete);

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_edit_40px.png"))); // NOI18N
        btnEdit.setText("Sửa");
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }});
        jToolBar1.add(btnEdit);

        btnDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_eye_40px.png"))); // NOI18N
        btnDetail.setText("Xem chi tiết");
        btnDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetail.setFocusable(false);
        btnDetail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDetail.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDetail);

        btnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_reset_25px_1.png"))); // NOI18N
        btnEdit1.setText("Undo");
        btnEdit1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit1.setFocusable(false);
        btnEdit1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdit1ActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEdit1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBoxLuaChon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã sản phẩm", "Tên sản phẩm", "Còn lại", "Đơn giá", "Ngày sản xuất", "Hạn sản xuất", "Thành phần", "Khối lượng", "Ngày nhập kho", "Đã bán"}));

        jComboBoxLuaChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLuaChonActionPerformed(evt);
            }
        });
        jComboBoxLuaChon.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBoxLuaChonPropertyChange(evt);
            }
        });
        jPanel3.add(jComboBoxLuaChon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, 40));

        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });
        jPanel3.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 330, 40));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_reset_25px_1.png"))); // NOI18N
        jButton7.setText("Làm mới");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 140, 40));

        tblSanPham.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblSanPham);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Lọc"));
        jPanel6.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanel6PropertyChange(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mặc định", "Giá cao -> thấp", "Giá thấp -> cao" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox1PropertyChange(evt);
            }
        });
        jComboBox1.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jComboBox1VetoableChange(evt);
            }
        });

        jButton1.setText("Lọc");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1198, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        AddProduct a = new AddProduct(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled);
        a.setVisible(true);

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (tblSanPham.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xoá");
        } else {

            int i_row = tblSanPham.getSelectedRow();
        String s = (String) tblSanPham.getValueAt(i_row, 1);     
        Product p = Run.ProductTree.get((String) tblSanPham.getValueAt(i_row, 1)).getProduct();
        System.out.println("1111");
        int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá sản phẩm này?", "Xoá sản phẩm",
                JOptionPane.YES_NO_OPTION);
        if (luaChon == JOptionPane.YES_OPTION) {
            if (getMayTinhSelect() == -1) {
                JOptionPane.showMessageDialog(this, "lỗi");
            } else {
                  functionStack.push(new FunctionWrapper<Product>(new AddProductStack(),p ));
                Run.ProductTree.remove(s);
                
                try {
                    Run.WriteDataProduct();
                    Run.WriteDataAmountSold();
                } catch (IOException ex) {
                    Logger.getLogger(ProductForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(this, "Xóa thành công");
            }
        }

        loadDataToTable(Run.ProductTree);
        

        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (tblSanPham.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần sửa");
        } else {
            UpdateProduct a = new UpdateProduct(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled);
            a.setVisible(true);
            System.out.println("access edit button");
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        if (tblSanPham.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm !");
        } else {
            DetailProduct a = new DetailProduct(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled);
            a.setVisible(true);
        }
    }//GEN-LAST:event_btnDetailActionPerformed

    private void jComboBoxLuaChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLuaChonActionPerformed

    }//GEN-LAST:event_jComboBoxLuaChonActionPerformed

    private void jComboBoxLuaChonPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxLuaChonPropertyChange
        // TODO add your handling code here:
        String luaChon = jComboBoxLuaChon.getSelectedItem().toString();
        String content = jTextFieldSearch.getText();
        List<Product> result = searchFn(luaChon, content);
        loadDataToTableSearch(result);
    }//GEN-LAST:event_jComboBoxLuaChonPropertyChange

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchKeyPressed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        String luaChon = jComboBoxLuaChon.getSelectedItem().toString();
        String content = jTextFieldSearch.getText();
        List<Product> result = searchFn(luaChon, content);
        loadDataToTableSearch(result);
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jComboBoxLuaChon.setSelectedIndex(0);
        jTextFieldSearch.setText("");
        loadDataToTable(Run.ProductTree);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jPanel6PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanel6PropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel6PropertyChange

    private void jComboBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox1PropertyChange

        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PropertyChange

    private void jComboBox1VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jComboBox1VetoableChange

    }//GEN-LAST:event_jComboBox1VetoableChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String luaChon = jComboBox1.getSelectedItem().toString();
        List<Product> result = Run.ProductTree.getInOrderList();

        switch (luaChon) {
            case "Mặc định":
                break;
            case "Giá cao -> thấp":
                result = Run.ProductTree.sortPriceList(result, true);
                break;
            case "Giá thấp -> cao":
                result = Run.ProductTree.sortPriceList(result, false);
                break;

        }

        loadDataToTableSearch(result);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdit1ActionPerformed
       try {
            FunctionWrapper<Product> funcWrapper = functionStack.pop();          
            funcWrapper.executeFunction();         
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã hoàn tác hết các bước gần đây");
        }
    }//GEN-LAST:event_btnEdit1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            Run.ReadDataProduct(); // Đọc dữ liệu sản phẩm vào ProductTree
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Sau khi đọc dữ liệu, số lượng sản phẩm trong ProductTree: " + Run.ProductTree.size());

        // Bây giờ mới tạo và hiển thị ProductForm
        ProductForm pf = new ProductForm();
        pf.setVisible(true);
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
            Logger.getLogger(ProductForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ProductForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProductForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ProductForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductForm().setVisible(true);
            }
        });
    }

    public List<Product> searchFn(String luaChon, String content) {
        List<Product> result = new ArrayList<>();



        switch (luaChon) {
            case "Tất cả":
                result = Run.ProductTree.search(content);
                break;
            case "Mã sản phẩm":
                result = Run.ProductTree.searchMaSanPham(content);
                break;
            case "Tên sản phẩm":
                result = result = Run.ProductTree.search(content);
                break;
            case "Còn lại":
                result = result = Run.ProductTree.searchSoLuong(content);
                break;
            case "Đơn giá":
                result = result = Run.ProductTree.searchDonGia(content);
                break;
            case "Ngày sản xuất":
                result = result = Run.ProductTree.searchNgaySanXuat(content);
                break;
            case "Hạn sản xuất":
                result = result = Run.ProductTree.searchHanSuDung(content);
                break;
            case "Thành phần":
                result = result = Run.ProductTree.searchThanhPhan(content);
                break;
            case "Khối lượng":
                result = result = Run.ProductTree.searchKhoiLuong(content);
                break;
            case "Ngày nhập kho":
                result = result = Run.ProductTree.searchNNK(content);
                break;
            case "Đã bán":
                result = result = Run.ProductTree.searchKhoiLuong(content);
                break;

        }
        return result;
    }

    public Product getProductSelect() {      
        return Run.ProductTree.search((String) tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 1)).get(0);
    }

    public void xoaMayTinhSelect() {

        int i_row = tblSanPham.getSelectedRow();
        String s = (String) tblSanPham.getValueAt(i_row, 1);
        String s1 = (String) tblSanPham.getValueAt(i_row, 0);
        Product p = Run.ProductTree.get((String) tblSanPham.getValueAt(i_row, 1)).getProduct();
        System.out.println("1111");
        int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xoá sản phẩm này?", "Xoá sản phẩm",
                JOptionPane.YES_NO_OPTION);
        if (luaChon == JOptionPane.YES_OPTION) {
            if (getMayTinhSelect() == -1) {
                JOptionPane.showMessageDialog(this, "lỗi");
            } else {
                  functionStack.push(new FunctionWrapper<Product>(new AddProductStack(),p ));
                Run.ProductTree.remove(s);
                Run.AmountSoldTree.remove(s1);
                try {
                    Run.WriteDataProduct();
                    Run.WriteDataAmountSold();
                } catch (IOException ex) {
                    Logger.getLogger(ProductForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        loadDataToTable(Run.ProductTree);
        try {
            Run.WriteDataProduct();
        } catch (IOException ex) {
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getMayTinhSelect() {
        int i_row = tblSanPham.getSelectedRow();
//     
//       
        return i_row;
    }

    public void loadDataToTableSearch(List<Product> result) {
        tblModel.setRowCount(0);

        for (Product i : result) {
            Integer amountSold = 0;
            // Kiểm tra tránh NullPointerException
            if (Run.AmountSoldTree.get(i.getTenSanPham()) != null) {
                amountSold = Run.AmountSoldTree.get(i.getTenSanPham()).getAmountSold().getAmountSold();
            }

            // Đưa dữ liệu theo đúng thứ tự cột:
            // Mã sp, Tên sp, Còn lại, Đơn giá, Ngày sản xuất, Hạn sản xuất, Thành phần, Khối lượng, Ngày nhập kho, Đã bán
            tblModel.addRow(new Object[]{
                    i.getMaSanPham(),
                    i.getTenSanPham(),
                    i.getSoLuong(),
                    formatter.format(i.getGiaTien()) + "đ",
                    i.getNgaySanXuat(),
                    i.getHanSuDung(),
                    i.getThanhPhan(),   // Thành phần (cột 6)
                    i.getKhoiLuong(),   // Khối lượng  (cột 7)
                    i.getNgayNhapKho(), // Ngày nhập kho (cột 8)
                    i.getSoNgayGiaoHang(),
                    amountSold          // Đã bán (cột 9)
            });
        }
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEdit1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxLuaChon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblSanPham;
    // End of variables declaration//GEN-END:variables
}
