
package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import System.Account;
import System.Product;
import System.ChiTietPhieu;
import System.Function;
import System.Phieu;
import AVL.ProductManagerTree;
import System.FunctionWrapper;
import System.AmountSold;
/**
 *
 * @author
 */
public class GioHang extends javax.swing.JInternalFrame {
    
    private Account currentAcc;
    private Customer owner;
    Stack<FunctionWrapper<ChiTietPhieu>> functionStack = new Stack<>();
   // private javax.swing.JComboBox<String> jComboBoxWeight;
  //  private javax.swing.JButton jButtonWeight;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JButton btnSearchComponent;
    /**
     * Creates new form NhapHang
     */
    private DefaultTableModel tblModel;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    private ArrayList<ChiTietPhieu> CTPhieu;


    private GioHang() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public GioHang(Account t, JFrame parent, JFrame owner, boolean modal) {
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        initComponents();
        initTable();
        this.currentAcc = t;
        this.owner =(Customer) parent;

        tblSanPham.setDefaultEditor(Object.class, null);
        tblNhapHang.setDefaultEditor(Object.class, null);
        loadDataToTableProduct(Run.ProductTree);
        CTPhieu = new ArrayList<ChiTietPhieu>();

    }

    public final void initTable() {
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá"};
        tblModel.setColumnIdentifiers(headerTbl);
        tblSanPham.setModel(tblModel);
        tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblSanPham.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblSanPham.getColumnModel().getColumn(2).setPreferredWidth(5);
        tblNhapHang.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblNhapHang.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblNhapHang.getColumnModel().getColumn(2).setPreferredWidth(250);
    }

    private void loadDataToTableProduct(ProductManagerTree tree) {
        try {
            List<Product> ProductData = Run.ProductTree.getInOrderList();
            tblModel.setRowCount(0);
            for (var i : ProductData) {
                tblModel.addRow(new Object[]{
                    i.getMaSanPham(), i.getTenSanPham(), i.getSoLuong(), formatter.format(i.getGiaTien()) + "đ"
                });
            }
        } catch (Exception e) {
        }
    }

    public void loadDataToTableSearch(List<Product> result) {
        try {
            tblModel.setRowCount(0);
            for (Product i : result) {
                tblModel.addRow(new Object[]{
                    i.getMaSanPham(), i.getTenSanPham(), i.getSoLuong(), formatter.format(i.getGiaTien()) + "đ"
                });
            }
        } catch (Exception e) {
        }
    }

    public double tinhTongTien() {
        double tt = 0;
        for (var i : CTPhieu) {
            tt += i.getGia() * i.getSoLuong();
        }
        return tt;
    }

    public Product findMayTinh(String maMay) {
        List<Product> ProductData = Run.ProductTree.getInOrderList();
        for (Product i : ProductData) {
            if (maMay.equals(i.getMaSanPham())) {
                return i;
            }
        }
        return null;
    }

    public ChiTietPhieu findCTPhieu(String maMay) {
        for (ChiTietPhieu i : CTPhieu) {
            if (maMay.equals(Run.ProductTree.get(i.getTenSanPham()).getProduct().getMaSanPham())) {
                return i;
            }
        }
        return null;
    }

    public void loadDataToTableNhapHang() {
        double sum = 0;
        try {
            DefaultTableModel tblNhapHangmd = (DefaultTableModel) tblNhapHang.getModel();
            tblNhapHangmd.setRowCount(0);

            for (int i = 0; i < CTPhieu.size(); i++) {
                tblNhapHangmd.addRow(new Object[]{
                    i + 1,
                    Run.ProductTree.get(CTPhieu.get(i).getTenSanPham()).getProduct().getMaSanPham(),
                    CTPhieu.get(i).getTenSanPham(),
                    CTPhieu.get(i).getSoLuong(),
                    formatter.format(CTPhieu.get(i).getGia()) + "đ"
                });
                sum +=  checkOnSale(CTPhieu.get(i).getTenSanPham()) * CTPhieu.get(i).getGia() * CTPhieu.get(i).getSoLuong() ;
                
            }
        } catch (Exception e) {
        }
        textTongTien.setText(formatter.format(sum) + "đ");
        
    }

    public Product getDetailProductCustomer() {
        Product a = Run.ProductTree.get((String) tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 1)).getProduct();
        return a;
    }

    public int getMayTinhSelect() {
        int i_row = tblSanPham.getSelectedRow();
//     
//       
        return i_row;
    }

    public Account setNguoiMua(String name) {
        return this.currentAcc;
    }

    public static boolean isOnlyDigits(String str) {
        return str.chars().allMatch(Character::isDigit);
    }
     public class AddPhieuStack implements Function<ChiTietPhieu> {

        @Override
        public void execute(ChiTietPhieu phieu) {
            CTPhieu.add(phieu);
            
             loadDataToTableNhapHang();
        }
    
    }
     public class DelPhieuStack implements Function<ChiTietPhieu> {

        @Override
        public void execute(ChiTietPhieu phieu) {
            CTPhieu.remove(phieu);
            
            loadDataToTableNhapHang();
        }
    
    }

    //check giảm giá, nếu nằm đầu hàng đợi thì giảm giá
    public double checkOnSale(String tenSanPham) {
        // Kiểm tra nếu top() trả về null
        if (Run.queueSale.top() != null && Run.queueSale.top().getValue().getTenSanPham().equals(tenSanPham)) {
            return 0.8;
        } else {
            return 1.0;
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
      //  jComboBoxWeight = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhapHang = new javax.swing.JTable();
        btnNhapHang = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        textTongTien = new javax.swing.JLabel();
        deleteProduct = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        addProduct = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        btnDetail = new javax.swing.JButton();
        btnDetail1 = new javax.swing.JButton();
     //   jButtonWeight = new javax.swing.JButton();
        setBorder(null);
        txtSearch1 = new javax.swing.JTextField();
        btnSearchComponent = new javax.swing.JButton();
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblNhapHang.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng", "Đơn giá"
            }
        ));
        jScrollPane1.setViewportView(tblNhapHang);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 560, 410));

        btnNhapHang.setBackground(new java.awt.Color(204, 204, 255));
        btnNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapHang.setText("Đặt hàng");
        btnNhapHang.setBorder(null);
        btnNhapHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangActionPerformed(evt);
            }
        });
        jPanel2.add(btnNhapHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 690, 123, 37));

        jLabel5.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        jLabel5.setText("Tổng tiền:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 690, 120, 30));

        textTongTien.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        textTongTien.setForeground(new java.awt.Color(255, 153, 153));
        textTongTien.setText("0đ");
        jPanel2.add(textTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 690, 190, 30));

        deleteProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_delete_25px_1.png"))); // NOI18N
        deleteProduct.setText("Xoá sản phẩm khỏi giỏ hàng");
        deleteProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProductActionPerformed(evt);
            }
        });
        jPanel2.add(deleteProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 470, 220, 40));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_edit_25px.png"))); // NOI18N
        jButton1.setText("Sửa số lượng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 470, -1, 40));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("GIỎ HÀNG");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(jLabel6)
                .addContainerGap(214, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 560, 40));

        txtPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneNumberActionPerformed(evt);
            }
        });
        jPanel2.add(txtPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 590, 390, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("SDT nhận hàng: ");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, 120, 40));

        txtAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddressActionPerformed(evt);
            }
        });
        jPanel2.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 530, 390, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Địa chỉ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 530, 60, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 600, 750));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblSanPham.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá"
            }
        ));
        jScrollPane2.setViewportView(tblSanPham);

        jLabel4.setText("Số lượng");

        txtSoLuong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSoLuong.setText("1");

        addProduct.setBackground(new java.awt.Color(204, 204, 255));
        addProduct.setForeground(new java.awt.Color(255, 255, 255));
        addProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_add_25px_5.png"))); // NOI18N
        addProduct.setText("Thêm");
        addProduct.setBorder(null);
        addProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        txtSearch1.setToolTipText("Nhập thành phần cần tìm");

        btnSearchComponent.setText("Tìm thành phần");
        btnSearchComponent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchComponentActionPerformed(evt);
            }

            private void btnSearchComponentActionPerformed(java.awt.event.ActionEvent evt) {
                String components = txtSearch1.getText().trim().toLowerCase();
                if (components.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập các thành phần cần tìm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Gọi hàm tìm kiếm sử dụng đồ thị
                List<Product> result = Run.searchProductsByComponentsInGraph(components, Run.productGraph, Run.ProductTree.getInOrderList());
                if (result.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm chứa các thành phần: " + components, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    loadDataToTableSearch(result); // Hiển thị kết quả lên bảng
                }
            }
            /**
                     * Tìm sản phẩm theo thành phần trong đồ thị
                     *
                     * @param component Thành phần cần tìm
                     * @return Danh sách sản phẩm chứa thành phần
                     */
            /**
             * Tìm sản phẩm theo nhiều thành phần
             *
             * @param componentsString Chuỗi các thành phần, cách nhau bằng dấu phẩy
             * @return Danh sách sản phẩm chứa tất cả các thành phần
             */
            private List<Product> searchProductsByComponent(String componentsString) {
                List<Product> result = new ArrayList<>();

                // Tách thành danh sách các thành phần, loại bỏ khoảng trắng
                String[] searchComponents = componentsString.toLowerCase().trim().split(",");
                List<String> searchKeywords = new ArrayList<>();
                for (String keyword : searchComponents) {
                    searchKeywords.add(keyword.trim());
                }

                // Duyệt qua từng sản phẩm trong ProductTree
                for (Product product : Run.ProductTree.getInOrderList()) {
                    String[] productComponents = product.getThanhPhan().toLowerCase().split(","); // Tách thành phần sản phẩm
                    List<String> productComponentList = new ArrayList<>();
                    for (String comp : productComponents) {
                        productComponentList.add(comp.trim());
                    }

                    // Kiểm tra xem sản phẩm có chứa tất cả các thành phần không
                    boolean matchesAll = true;
                    for (String searchKeyword : searchKeywords) {
                        if (!productComponentList.contains(searchKeyword)) {
                            matchesAll = false;
                            break; // Nếu thiếu một thành phần, bỏ qua sản phẩm này
                        }
                    }

                    // Nếu sản phẩm chứa tất cả các thành phần, thêm vào kết quả
                    if (matchesAll) {
                        result.add(product);
                    }
                }

                return result;
            }


        });
        txtSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearch1KeyReleased(evt);
            }

            private void txtSearch1KeyReleased(java.awt.event.KeyEvent evt) {
                String textSearch = txtSearch1.getText().trim();

                // Nếu ô tìm kiếm trống, load lại bảng dữ liệu chưa lọc
                if (textSearch.isEmpty()) {
                    loadDataToTableProduct(Run.ProductTree);  // Đây là cây sản phẩm gốc chưa bị lọc
                } else {
                    // Nếu ô tìm kiếm có giá trị, thực hiện tìm kiếm
                    loadDataToTableSearch(Run.searchProductsByComponentsInGraph(textSearch, Run.productGraph, Run.ProductTree.getInOrderList()));
                }
            }
        });

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });

//        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_reset_25px_1.png"))); // NOI18N
//        btnReset.setText("Làm mới");
//        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        btnReset.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                btnResetActionPerformed(evt);
//            }
//        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Lọc"));
        jPanel7.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanel7PropertyChange(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mặc định", "Giá cao -> thấp", "Giá thấp -> cao" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jComboBox2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox2PropertyChange(evt);
            }
        });
        jComboBox2.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jComboBox2VetoableChange(evt);
            }
        });

        
        jButton3.setText("Lọc");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                             //   .addComponent(jComboBoxWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE) // Thêm ComboBoxWeight
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                             //   .addComponent(jButtonWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE) // Thêm ButtonWeight
                                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                     //   .addComponent(jComboBoxWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                     //   .addComponent(jButtonWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );


        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch,javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                //.addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnSearchComponent)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch1,  javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchComponent))
                   // .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
            ));

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

        btnDetail1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_reset_25px_1.png"))); // NOI18N
        btnDetail1.setText("Undo");
        btnDetail1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDetail1.setFocusable(false);
        btnDetail1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDetail1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDetail1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetail1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel4)
                .addGap(27, 27, 27)
                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDetail)
                .addGap(18, 18, 18)
                .addComponent(btnDetail1)
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnDetail)
                    .addComponent(btnDetail1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 750));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 744, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btnNhapHangActionPerformed(java.awt.event.ActionEvent evt) {
        if (CTPhieu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm để đặt hàng!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else if ("".equals(txtAddress.getText()) || "".equals(txtPhoneNumber.getText())) {
            JOptionPane.showMessageDialog(this, "Không được để trống địa chỉ và số điện thoại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else if (isOnlyDigits(txtPhoneNumber.getText())) {
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đặt hàng?", "Xác nhận đặt hàng", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                // Lấy thời gian hiện tại
                long now = System.currentTimeMillis();
                Timestamp sqlTimestamp = new Timestamp(now);

                try {
                    // Tính tổng tiền cho phiếu
                    double tongTien = 0;
                    List<ChiTietPhieu> danhSachChiTiet = new ArrayList<>();

                    for (ChiTietPhieu chiTietPhieu : CTPhieu) {
                        // Kiểm tra hàng sale và cập nhật giá
                        chiTietPhieu.setDonGia(chiTietPhieu.getGia() * checkOnSale(chiTietPhieu.getTenSanPham()));

                        // Kiểm tra và cập nhật lượng bán vào AmountSoldTree
                        var amountSoldNode = Run.AmountSoldTree.get(chiTietPhieu.getTenSanPham());
                        if (amountSoldNode == null) {
                            // Nếu chưa có sản phẩm trong AmountSoldTree, thêm mới với giá trị ban đầu là 0
                            Run.AmountSoldTree.add(chiTietPhieu.getTenSanPham(), new AmountSold(0));
                            amountSoldNode = Run.AmountSoldTree.get(chiTietPhieu.getTenSanPham());
                        }

                        // Cập nhật số lượng bán ra
                        amountSoldNode.getAmountSold().setAmountSold(
                                amountSoldNode.getAmountSold().getAmountSold() + chiTietPhieu.getSoLuong()
                        );

                        // Cập nhật tổng tiền
                        tongTien += chiTietPhieu.getSoLuong() * chiTietPhieu.getGia();

                        // Thêm vào danh sách chi tiết phiếu
                        danhSachChiTiet.add(chiTietPhieu);

                        // Cập nhật số lượng sản phẩm trong ProductTree
                        Run.ProductTree.get(chiTietPhieu.getTenSanPham())
                                .getProduct()
                                .setSoLuong(
                                        Run.ProductTree.get(chiTietPhieu.getTenSanPham())
                                                .getProduct()
                                                .getSoLuong() - chiTietPhieu.getSoLuong()
                                );
                    }

                    // Tạo đối tượng phiếu
                    Phieu phieu = new Phieu(
                            sqlTimestamp,
                            txtPhoneNumber.getText(),
                            txtAddress.getText(),
                            currentAcc.getUser(),
                            danhSachChiTiet,
                            tongTien,
                            1
                    );

                    // Thêm phiếu vào cây quản lý phiếu
                    Run.PhieuMuaTree.add(currentAcc.getUser(), phieu);

                    // Ghi dữ liệu phiếu mua vào file
                    Run.WriteDataPhieuMua();

                    // Cập nhật bestseller
                    owner.loadDtatatoBestSeller();

                    // Hiển thị thông báo thành công
                    JOptionPane.showMessageDialog(this, "Đặt hàng thành công!");
                    try {
                        Run.WriteDataProduct();   // Ghi dữ liệu sản phẩm sau khi cập nhật
                        Run.queueSale.clear();
                        Run.ReadDatatoQueue();    // Đọc lại dữ liệu vào queue sau thay đổi về số lượng sản phẩm
                        owner.loadDtatatoSale();  // Cập nhật lại danh sách sale
                        Run.WriteDataAmountSold(); // Ghi lại dữ liệu lượng bán ra
                    } catch (IOException ex) {
                        Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("lỗi");
                    }

                    // Cập nhật lại giao diện sau khi đặt hàng thành công
                    loadDataToTableProduct(Run.ProductTree);
                    DefaultTableModel l = (DefaultTableModel) tblNhapHang.getModel();
                    l.setRowCount(0);
                    CTPhieu = new ArrayList<ChiTietPhieu>();
                    txtSoLuong.setText("1");
                    textTongTien.setText("0đ");
                    txtAddress.setText("");
                    txtPhoneNumber.setText("");

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

            }
        } else {
            JOptionPane.showMessageDialog(this, "Số điện thoại không đúng định dạng!");
        }
    }
//GEN-LAST:event_btnNhapHangActionPerformed

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed
        // Xử lý frondend thao tác người dùng thêm sản phẩm vào giỏ hàng add dần sản phẩm vào ArrayList<ChiTietPhieu> CTPhieu

        int i_row = tblSanPham.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để nhập hàng !");
        } else {
            int soluong;
            try {
                soluong = Integer.parseInt(txtSoLuong.getText().trim());
                System.out.println("line 624: " + soluong);
                if (soluong > 0) {
                    if (soluong <= Run.ProductTree.get((String) tblSanPham.getValueAt(i_row, 1)).getProduct().getSoLuong()) {
                        System.out.println("sinh-Gio hang");
                        
                            Product mt = Run.ProductTree.get((String) tblSanPham.getValueAt(i_row, 1)).getProduct();

                            ChiTietPhieu ctp = new ChiTietPhieu(mt.getTenSanPham(), soluong, mt.getGiaTien());
                            functionStack.push(new FunctionWrapper<ChiTietPhieu>(new DelPhieuStack(),ctp ));
                            CTPhieu.add(ctp);
                        
                        loadDataToTableNhapHang();
                        textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
                    } else {
                        JOptionPane.showMessageDialog(this, "Vượt quá lượng tồn kho");
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ở dạng số nguyên!");
            }
        }

        /*
        //<-----
        // Xử lý thao tác người dùng thêm sản phẩm vào giỏ hàng
        int selectedRow = tblSanPham.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để nhập hàng !");
            return;
        }

        String productId = (String) tblSanPham.getValueAt(selectedRow, 1);
        Product product = Run.ProductTree.get(productId).getProduct();

        int quantity;
        try {
            quantity = Integer.parseInt(txtSoLuong.getText().trim());

            // Kiểm tra số lượng nhập vào
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0");
                return;
            }

            // Kiểm tra tồn kho
            if (quantity > product.getSoLuong()) {
                JOptionPane.showMessageDialog(this, "Vượt quá lượng tồn kho");
                return;
            }

            // Thêm sản phẩm vào giỏ hàng
            ChiTietPhieu ctp = new ChiTietPhieu(product.getTenSanPham(), quantity, product.getGiaTien());
            functionStack.push(new FunctionWrapper<>(new DelPhieuStack(), ctp));
            CTPhieu.add(ctp);

            // Cập nhật giao diện
            loadDataToTableNhapHang();
            textTongTien.setText(formatter.format(tinhTongTien()) + "đ");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ở dạng số nguyên!");
        }
        //<-----

         */
    }//GEN-LAST:event_addProductActionPerformed

    private void deleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteProductActionPerformed
        // TODO add your handling code here:
        int i_row = tblNhapHang.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xoá khỏi giỏ hàng !");
        } else {
            ChiTietPhieu ctp = CTPhieu.get(i_row);
            functionStack.push(new FunctionWrapper<ChiTietPhieu>(new AddPhieuStack(),ctp ));
            CTPhieu.remove(i_row);
            loadDataToTableNhapHang();
            textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
        }
    }//GEN-LAST:event_deleteProductActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int i_row = tblNhapHang.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xoá sửa số lượng !");
        } else {
            String newSL = JOptionPane.showInputDialog(this, "Nhập số lượng cần thay đổi", "Thay đổi số lượng", QUESTION_MESSAGE);
            if (newSL != null) {
                int soLuong;
                try {
                    soLuong = Integer.parseInt(newSL);
                    if (soLuong > 0) {
                        CTPhieu.get(i_row).setSoLuong(soLuong);
                        loadDataToTableNhapHang();
                        textTongTien.setText(formatter.format(tinhTongTien()) + "đ");
                    } else {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng ở dạng số nguyên!");
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased

        DefaultTableModel tblsp = (DefaultTableModel) tblSanPham.getModel();
        

        String textSearch = txtSearch.getText();

        loadDataToTableSearch(Run.ProductTree.search(textSearch));
    }//GEN-LAST:event_txtSearchKeyReleased



//    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
//        txtSearch.setText("");
//        loadDataToTableProduct(Run.ProductTree);
//    }//GEN-LAST:event_btnResetActionPerformed

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchKeyTyped

    private void txtPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneNumberActionPerformed

    private void txtAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddressActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        if (tblSanPham.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm !");
        } else {
            DetailProductCustomer a = new DetailProductCustomer(this, (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), rootPaneCheckingEnabled);
            a.setVisible(true);

        }
    }//GEN-LAST:event_btnDetailActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {
    //GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox2PropertyChange

        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2PropertyChange

    private void jComboBox2VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jComboBox2VetoableChange

    }//GEN-LAST:event_jComboBox2VetoableChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String luaChon = jComboBox2.getSelectedItem().toString();
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

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jPanel7PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanel7PropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel7PropertyChange

    private void btnDetail1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetail1ActionPerformed
         try {
            FunctionWrapper<ChiTietPhieu> funcWrapper = functionStack.pop();          
            funcWrapper.executeFunction(); //gọi lại cái giá trị functionStack.pop() trước đó
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã hoàn tác hết các bước gần đây");
        }
    }//GEN-LAST:event_btnDetail1ActionPerformed

    public static void main(String[] args) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProduct;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnDetail1;
    private javax.swing.JButton btnNhapHang;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton deleteProduct;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblNhapHang;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JLabel textTongTien;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
