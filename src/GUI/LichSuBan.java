
package GUI;




import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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
public class LichSuBan extends javax.swing.JInternalFrame {
   private Account currentAcc;
  
    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    public LichSuBan() {
          throw new UnsupportedOperationException("Not supported yet."); 
    }
    public LichSuBan(Account t) {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        tblSanPham.setDefaultEditor(Object.class, null);
        initTable();
        
        this.currentAcc=t;
          System.out.println(t); 
           List<Phieu> lichsumua = Run.PhieuMuaTree.getInOrderList();
          
           Collections.sort(lichsumua, new TimeComparator());
           loadDataToTable( lichsumua);
    }
    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{ "Tên máy", "Số lượng", "Đơn giá", "số điện thoại đặt hàng","địa chỉ","time"};
        tblModel.setColumnIdentifiers(headerTbl);
        tblSanPham.setModel(tblModel);
       
       
    }
    public void loadDataToTable(List<Phieu> PhieuList) {
       try {
           if (PhieuList == null || PhieuList.isEmpty()) {
               return;
           }

            tblModel.setRowCount(0);
           for (Phieu phieu : PhieuList) {
               // Iterate through each item in the "phieu" list (which is a List<ChiTietPhieu>)
               for (ChiTietPhieu ctPhieu : phieu.getPhieu()) {
                   tblModel.addRow(new Object[]{
                           ctPhieu.getTenSanPham(),  // Assuming you have this in ChiTietPhieu
                           ctPhieu.getSoLuong(),
                           formatter.format(ctPhieu.getGia()) + "đ",  // Assuming you have price in ChiTietPhieu
                           phieu.getPhone(),
                           phieu.getAddress(),
                           phieu.getThoiGianTao()
                   });
               }
           }
        } catch (Exception e) {
        }
       
    }
      

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();

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
        jPanel3.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 960, 40));

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
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                    break;  // Exit the inner loop once a match is found for this Phieu
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
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LichSuBan().setVisible(true);
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
    // End of variables declaration//GEN-END:variables
}
