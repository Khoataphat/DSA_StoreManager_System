
package GUI;

import System.Account;
import System.Phieu;
import System.TimeComparator;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import System.ChiTietPhieu;
import System.Product;
import System.AmountSold;

/**
 *
 * @author Vu Tuan Ngoc
 */
/*
public class ThongKeQuanLyKho extends javax.swing.JInternalFrame {

    private Account currentAcc;

    private DefaultTableModel tblModel;
    private DefaultTableModel tblBestSellers;
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public ThongKeQuanLyKho() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ThongKeQuanLyKho(Account t) {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        tblSanPham.setDefaultEditor(Object.class, null);
        tblBestSellers.setDefaultEditor(Object.class, null);
        initTable();

        this.currentAcc = t;
        
        List<Phieu> lichsumua = Run.PhieuMuaTree.getInOrderList();

        Collections.sort(lichsumua, new TimeComparator());
        loadDataToTable(lichsumua);
        loadDataProduct();
        //loadDataAccount();
        loadDataDoanhSo(lichsumua);
        loadDataBestSeller();
    }

    public final void initTable() {
        /*
        tblModel = new DefaultTableModel();
        String[] headerTbl = new String[]{ "Tên sản phẩm", "Số lượng", "Tổng giá", "Số điện thoại đặt hàng", "Địa chỉ"};
        tblModel.setColumnIdentifiers(headerTbl);
        tblSanPham.setModel(tblModel);

        // Khởi tạo bảng cho lịch sử bán hàng
        tblSanPham = new JTable();
        DefaultTableModel historyModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Tên sản phẩm", "Số lượng", "Tổng giá", "Số điện thoại đặt hàng", "Địa chỉ"}
        );
        tblSanPham.setModel(historyModel);
        JScrollPane jScrollPane1 = new JScrollPane(tblSanPham);

        // Khởi tạo bảng cho sản phẩm bán chạy
        tblBestSellers = new JTable();
        DefaultTableModel bestSellerModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mã máy", "Số lượng bán"}
        );
        tblBestSellers.setModel(bestSellerModel);
        JScrollPane jScrollPane2 = new JScrollPane(tblBestSellers);

        // Thêm cả hai bảng vào layout
        jPanel1.add(jScrollPane1);
        jPanel1.add(jScrollPane2);

        // Cài đặt kích thước và vị trí cho các bảng
        jScrollPane1.setBounds(20, 100, 600, 300); // Vị trí cho bảng lịch sử bán hàng
        jScrollPane2.setBounds(650, 100, 400, 300); // Vị trí cho bảng sản phẩm bán chạy

        // Gọi các phương thức để nạp dữ liệu vào bảng
        loadDataToTable(Run.PhieuMuaTree.getInOrderList()); // Giả sử bạn có phương thức này
        loadDataBestSeller(); // Cập nhật bảng sản phẩm bán chạy


    }

    public void loadDataToTable(List<Phieu> PhieuList) {
        /*
        try {

            tblModel.setRowCount(0);
            for (Phieu i : PhieuList) {

                tblModel.addRow(new Object[]{
                   // Run.ProductTree.get(i.getChitieuphieu().getTenMay()).getProduct().getMaMay(),
                        /*
                    i.getChitieuphieu().getTenSanPham(),
                    i.getChitieuphieu().getSoLuong(),

                        i.getPhieu().stream()
                                .map(ctPhieu -> ctPhieu.getTenSanPham())
                                .findFirst().orElse("N/A"),  // Get the first product name or "N/A" if no products are available
                        i.getPhieu().stream()
                                .mapToInt(ChiTietPhieu::getSoLuong)
                                .sum(),
                    formatter.format(i.getTongTien()) + "đ",
                    i.getPhone(),
                    i.getAddress(),
                    i.getThoiGianTao(),
                 //   Integer.toString(Run.AmountSoldTree.get(Run.ProductTree.get(i.getChitieuphieu().getTenMay()).getProduct().getMaMay()).getAmountSold().getAmountSold())
                });

                
            }
        } catch (Exception e) {
        }


        
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
                            //phieu.getThoiGianTao()
                    });
                }
            }
        } catch (Exception e) {
        }

    }

    public void loadDataProduct() {
        String s = "";
        s = s + Run.ProductTree.size();
        txtQuantityProduct.setText(s);

    }

    public void loadDataAccount() {
        String s = "";
        s = s + Run.AccountTree.size();
        //txtQuantityUser.setText(s);

    }

    public void loadDataDoanhSo(List<Phieu> PhieuList) {
        try {
            Double tong = 0.0;
            for (Phieu i : PhieuList) {
                tong += i.getTongTien();

            }
            txtQuantityNcc.setText(formatter.format(tong) + "đ");
            tongTien1.setText(formatter.format(tong) + "đ");

            soLuong1.setText(formatter.format(Run.PhieuMuaTree.size()));

        } catch (Exception e) {
        }

    }
    //<----
    public class BestSellingProductFinder {
        public static List<AmountSold> findBestSellingProducts(List<AmountSold> products) {
            List<AmountSold> bestSellingProducts = new ArrayList<>();
            if (products == null || products.isEmpty()) {
                return bestSellingProducts; // Trả về danh sách rỗng
            }

            int maxAmountSold = 0; // Biến lưu số lượng bán cao nhất

            // Duyệt qua danh sách sản phẩm để tìm số lượng bán cao nhất
            for (AmountSold product : products) {
                if (product.getAmountSold() > maxAmountSold) {
                    maxAmountSold = product.getAmountSold(); // Cập nhật số lượng bán cao nhất
                }
            }

            // Duyệt lại để tìm tất cả sản phẩm có số lượng bán bằng maxAmountSold
            for (AmountSold product : products) {
                if (product.getAmountSold() == maxAmountSold) {
                    bestSellingProducts.add(product); // Thêm sản phẩm vào danh sách bán chạy nhất
                }
            }

            return bestSellingProducts;
        }
    }
    public void loadDataBestSeller() {
        try {
            List<AmountSold> products = Run.AmountSoldTree.getInOrderList();
            List<AmountSold> bestSellingProducts = BestSellingProductFinder.findBestSellingProducts(products);

            // Xóa dữ liệu cũ trong bảng nếu có
            tblBestSellers.setRowCount(0);

            for (AmountSold product : bestSellingProducts) {
                tblBestSellers.addRow(new Object[]{
                        product.getMaMay(), // Mã máy
                        product.getAmountSold() // Số lượng bán
                });
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra thông tin lỗi nếu có
        }
    }
    //<----

 */
public class ThongKeQuanLyKho extends javax.swing.JInternalFrame {

    private Account currentAcc;
    private DefaultTableModel tblModel;
    private DefaultTableModel tblBestSellers;
    private DefaultTableModel tblCombinedWarning;
    private DefaultTableModel tblPurchaseStatus;
    DecimalFormat formatter = new DecimalFormat("###,###,###");

    public ThongKeQuanLyKho() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ThongKeQuanLyKho(Account t) {
        initComponents();
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        initTable();
        this.currentAcc = t;

        List<Phieu> lichsumua = Run.PhieuMuaTree.getInOrderList();
        Collections.sort(lichsumua, new TimeComparator());
        loadDataToTable(lichsumua);
        loadDataProduct();
        loadDataDoanhSo(lichsumua);
        loadDataBestSeller();
        checkStockAndExpiry();
        loadDataPhieuMua();
    }

    public final void initTable() {
        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

// Khởi tạo bảng cho lịch sử bán hàng
        tblModel = createTableModel(new String[]{"Tên sản phẩm", "Số lượng", "Tổng giá", "Số điện thoại", "Địa chỉ"});
        tblSanPham.setModel(tblModel);
        JScrollPane jScrollPane1 = new JScrollPane(tblSanPham);
        tblSanPham.setPreferredScrollableViewportSize(new Dimension(500, 150)); // Điều chỉnh kích thước

// Khởi tạo bảng cho sản phẩm bán chạy
        tblBestSellers = createTableModel(new String[]{"Sản phẩm bán chạy", "Số lượng bán"});
        JTable tblBestSellersTable = new JTable(tblBestSellers);
        JScrollPane jScrollPane2 = new JScrollPane(tblBestSellersTable);
        tblBestSellersTable.setPreferredScrollableViewportSize(new Dimension(300, 150)); // Điều chỉnh kích thước

// Khởi tạo bảng cho cảnh báo tồn kho
        tblCombinedWarning = createTableModel(new String[]{"Sản phẩm cần nhập", "Lý do"});
        JTable tblStockWarningTable = new JTable(tblCombinedWarning);
        JScrollPane jScrollPane3 = new JScrollPane(tblStockWarningTable);
        tblStockWarningTable.setPreferredScrollableViewportSize(new Dimension(600, 150)); // Điều chỉnh kích thước

// Thêm các panel thông tin lên trên cùng
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3; // Chiếm 30% không gian cho jPanel9
        gbc.weighty = 0.1; // Chiếm 10% không gian cho chiều cao
        jPanel1.add(jPanel9, gbc); // Sản phẩm trong kho

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.3; // Chiếm 30% không gian cho jPanel10
        gbc.weighty = 0.1; // Chiếm 10% không gian cho chiều cao
        jPanel1.add(jPanel10, gbc); // Doanh số

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.3; // Chiếm 30% không gian cho jPanel11
        gbc.weighty = 0.1; // Chiếm 10% không gian cho chiều cao
        jPanel1.add(jPanel11, gbc); // Tổng đơn

// Thiết lập vị trí cho bảng lịch sử bán hàng
        gbc.gridx = 0;
        gbc.gridy = 1; // Đặt xuống hàng dưới
        gbc.weightx = 1.0; // Chiếm toàn bộ không gian chiều rộng
        gbc.weighty = 0.4; // Chiếm 40% không gian chiều cao
        gbc.gridwidth = 3; // Chiếm toàn bộ chiều rộng
        jPanel1.add(jScrollPane1, gbc);

// Thiết lập vị trí cho bảng sản phẩm bán chạy
        gbc.gridx = 0;
        gbc.gridy = 2; // Đặt xuống hàng dưới
        gbc.weightx = 0.5; // Chiếm 50% không gian cho bảng sản phẩm bán chạy
        gbc.weighty = 0.2; // Chiếm 20% không gian cho chiều cao
        gbc.gridwidth = 1; // Chiếm 1 cột
        jPanel1.add(jScrollPane2, gbc);

// Thiết lập vị trí cho bảng cảnh báo tồn kho
        gbc.gridx = 1;
        gbc.gridy = 2; // Cùng hàng với bảng sản phẩm bán chạy
        gbc.weightx = 0.5; // Chiếm 50% không gian cho bảng cảnh báo tồn kho
        gbc.weighty = 0.2; // Chiếm 20% không gian cho chiều cao
        gbc.gridwidth = 1; // Chiếm 1 cột
        jPanel1.add(jScrollPane3, gbc);

// Khởi tạo bảng cho trạng thái phiếu mua
        tblPurchaseStatus = createTableModel(new String[]{" ", "Trạng thái"});
        JTable tblPurchaseStatusTable = new JTable(tblPurchaseStatus);
        JScrollPane jScrollPanePurchaseStatus = new JScrollPane(tblPurchaseStatusTable);
        tblPurchaseStatusTable.setPreferredScrollableViewportSize(new Dimension(300, 150)); // Điều chỉnh kích thước

// Thiết lập vị trí cho bảng trạng thái phiếu mua
        gbc.gridx = 0;
        gbc.gridy = 3; // Đặt xuống hàng dưới
        gbc.weightx = 1.0; // Chiếm toàn bộ không gian chiều rộng
        gbc.weighty = 0.5; // Chiếm 50% không gian chiều cao
        gbc.gridwidth = 3; // Chiếm toàn bộ chiều rộng
        jPanel1.add(jScrollPanePurchaseStatus, gbc);


    }

    private DefaultTableModel createTableModel(String[] columnNames) {
        return new DefaultTableModel(columnNames, 0);
    }

    public void loadDataToTable(List<Phieu> PhieuList) {
        if (PhieuList == null || PhieuList.isEmpty()) {
            return;
        }

        tblModel.setRowCount(0);
        for (Phieu phieu : PhieuList) {
            for (ChiTietPhieu ctPhieu : phieu.getPhieu()) {
                tblModel.addRow(new Object[]{
                        ctPhieu.getTenSanPham(),
                        ctPhieu.getSoLuong(),
                        formatter.format(ctPhieu.getGia()) + "đ",
                        phieu.getPhone(),
                        phieu.getAddress(),
                });
            }
        }
    }

    public void loadDataProduct() {
        String s = String.valueOf(Run.ProductTree.size());
        txtQuantityProduct.setText(s);
    }

    public void loadDataDoanhSo(List<Phieu> PhieuList) {
        try {
            Double tongTien = 0.0;
            int tongDon = 0;
            //Chi tính đơn đã nhận thành công nha
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
            e.printStackTrace(); // Log lỗi
        }
    }


//<-----
    public class BestSellingProductFinder {
        public static List<AmountSold> findBestSellingProducts(List<AmountSold> products) {
            List<AmountSold> topSellingProducts = new ArrayList<>();
            if (products == null || products.isEmpty()) {
                return topSellingProducts;
            }

            products.sort((p1, p2) -> Integer.compare(p2.getAmountSold(), p1.getAmountSold()));

            for (int i = 0; i < Math.min(5, products.size()); i++) {
                topSellingProducts.add(products.get(i));
            }

            return topSellingProducts;
        }
    }

    public void loadDataBestSeller() {
        try {
            List<AmountSold> products = Run.AmountSoldTree.getInOrderList();
            List<AmountSold> bestSellingProducts = BestSellingProductFinder.findBestSellingProducts(products);

            tblBestSellers.setRowCount(0);
            for (AmountSold product : bestSellingProducts) {
                tblBestSellers.addRow(new Object[]{
                        product.getMaMay(),
                        product.getAmountSold()
                });
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi
        }
    }

    // Kiểm tra số lượng và hạn sử dụng
    public void checkStockAndExpiry() {
        List<Product> products = Run.ProductTree.getInOrderList(); // Lấy danh sách sản phẩm
        List<Phieu> phieulist = Run.PhieuMuaTree.getInOrderList();

        tblCombinedWarning.setRowCount(0); // Đặt lại dữ liệu bảng
        LocalDate currentDate = LocalDate.now();

        for (Product product : products) {
            int stockQuantity = product.getSoLuong(); // Số lượng tồn kho
            LocalDate expiryDate = product.getHanSuDung(); // Hạn sử dụng

            // Kiểm tra hạn sử dụng hợp lệ
            if (expiryDate == null) {
                tblCombinedWarning.addRow(new Object[]{
                        product.getTenSanPham(),
                        "Hạn sử dụng không hợp lệ."
                });
                continue; // Bỏ qua sản phẩm này
            }

            // Lấy thông tin tiêu thụ
            double dailyConsumption = product.getDailyConsumption(phieulist); // Tiêu thụ hàng ngày
            int leadTimeDays = product.getSoNgayGiaoHang(); // Số ngày giao hàng

            long daysUntilExpiry = ChronoUnit.DAYS.between(currentDate, expiryDate);
            long totalShelfLife = ChronoUnit.DAYS.between(product.getNgaySanXuat(), expiryDate); // Tổng thời gian sử dụng

            // Kiểm tra tổng thời gian sử dụng hợp lệ
            if (totalShelfLife <= 0) {
                tblCombinedWarning.addRow(new Object[]{
                        product.getTenSanPham(),
                        "Thông tin ngày sản xuất hoặc hạn sử dụng không hợp lệ."
                });
                continue; // Bỏ qua sản phẩm này
            }

            // Tính mức tối thiểu
            double minStockLevel = dailyConsumption * leadTimeDays;
            BigDecimal minStockLevelRounded = BigDecimal.valueOf(minStockLevel).setScale(2, RoundingMode.HALF_UP); // Làm tròn đến 2 chữ số thập phân
            // Kiểm tra số lượng tồn kho
            if (stockQuantity < minStockLevelRounded.doubleValue()) {
                tblCombinedWarning.addRow(new Object[]{
                        product.getTenSanPham(),
                        "Số lượng hàng tồn kho còn lại: " + stockQuantity + " (Mức tối thiểu: " + minStockLevel + ")"
                });
            }
            System.out.println("dailyConsumption: " + dailyConsumption + " leadTimeDays: " + leadTimeDays );
            System.out.println("Số lượng hàng tồn kho còn lại: " + stockQuantity + " (Mức tối thiểu: " + minStockLevel);

            // Kiểm tra hạn sử dụng
            if (daysUntilExpiry <= (0.3 * totalShelfLife)) {
                tblCombinedWarning.addRow(new Object[]{
                        product.getTenSanPham(),
                        "Hạn sử dụng sắp hết: " + daysUntilExpiry + " ngày (30% hạn sử dụng)"
                });
            }
        }
    }

    public void loadDataPhieuMua(){
        List<Phieu> phieuList = Run.PhieuMuaTree.getInOrderList(); // Lấy danh sách phiếu mua
        tblPurchaseStatus.setRowCount(0); // Đặt lại dữ liệu bảng

        int soDonDaNhan = 0;
        int soDonBiHuy = 0;
        int soDonChoXuLy = 0;
        int soDonDaDuocXacNhan = 0;
        int soDonDangChuanBi = 0;
        int soDonDangVanChuyen = 0;
        int soDonDangHoanHangVeKho = 0;
        // Đếm trạng thái đơn hàng
        for (Phieu phieu : phieuList) {
            String status = phieu.getTrackingStatus();
            switch (status) {
                case "Đã nhận hàng":
                    soDonDaNhan++;
                    break;
                case "Đã hủy":
                    soDonBiHuy++;
                    break;
                case "Đang chờ xử lý":
                    soDonChoXuLy++;
                    break;
                case "Đã được xác nhận":
                    soDonDaDuocXacNhan++;
                    break;
                case "Đang chuẩn bị":
                    soDonDangChuanBi++;
                    break;
                case "Đang vận chuyển":
                    soDonDangVanChuyen++;
                    break;
                case "Đã hoàn hàng về kho":
                    soDonDangHoanHangVeKho++;
                    break;
            }
        }

        // Thêm một dòng tổng hợp trạng thái đơn hàng
        tblPurchaseStatus.addRow(new Object[]{
                "Tổng số đơn đã nhận:", soDonDaNhan
        });
        tblPurchaseStatus.addRow(new Object[]{
                "Tổng số đơn đã hủy:", soDonBiHuy
        });
        tblPurchaseStatus.addRow(new Object[]{
                "Số đơn đang chờ xử lý:", soDonChoXuLy
        });
        tblPurchaseStatus.addRow(new Object[]{
                "Số đơn đã được xác nhận:", soDonDaDuocXacNhan
        });
        tblPurchaseStatus.addRow(new Object[]{
                "Số đơn đang chuẩn bị:", soDonDangChuanBi
        });
        tblPurchaseStatus.addRow(new Object[]{
                "Số đơn đang vận chuyển:", soDonDangVanChuyen
        });
        tblPurchaseStatus.addRow(new Object[]{
                "Số đơn đã hoàn hàng về kho:", soDonDangHoanHangVeKho
        });
    }

//<-----


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
        tblBestSellers = new DefaultTableModel();
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
        //txtQuantityUser = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        soLuong1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tongTien1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldSearch.setBorder(javax.swing.BorderFactory.createTitledBorder("Tên Sản Phẩm"));
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });
        jPanel3.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 980, 40));


/*
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8_reset_25px_1.png"))); // NOI18N
        jButton7.setText("Làm mới");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 20, 130, 40));

 */


        tblSanPham.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblSanPham);
        //<----
        /*
        tblBestSellers.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {

                }
        ));
        jScrollPane1.setViewportView(tblBestSellers);

         */
        //<----

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

        //txtQuantityUser.setFont(new java.awt.Font("SF Pro Display", 1, 36)); // NOI18N
        //txtQuantityUser.setForeground(new java.awt.Color(255, 255, 255));
        soLuong1.setFont(new java.awt.Font("SF Pro Display", 1, 36)); // NOI18N
        soLuong1.setForeground(new java.awt.Color(255, 255, 255));
        soLuong1.setText("0");

        jLabel17.setFont(new java.awt.Font("SF Pro Display", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tổng đơn");



        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(soLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(soLuong1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)))
                .addGap(10, 10, 10))
        );



        //jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        //jLabel1.setText("Lịch sử bán hàng:");

        jLabel24.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        //jLabel24.setText("TỔNG ĐƠN");

        //soLuong1.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        //soLuong1.setText("0");

        jLabel23.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        //jLabel23.setText("TỔNG TIỀN");

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
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            )
                            //.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                )
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
                        //.addComponent(tongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                //.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            //.addComponent(tongTien1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        ))
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

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchKeyPressed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        List<Phieu> lichsumua = Run.PhieuMuaTree.getInOrderList();
        List<Phieu> list = new ArrayList<>();
        Collections.sort(lichsumua, new TimeComparator());
        //<-----
        /*
        for (Phieu phieu : lichsumua) {
            if (phieu.getChitieuphieu().getTenSanPham().contains(jTextFieldSearch.getText())) {
                list.add(phieu);
            }
        }

         */
        //<----
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
            java.util.logging.Logger.getLogger(ThongKeQuanLyKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeQuanLyKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeQuanLyKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeQuanLyKho.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ThongKeQuanLyKho().setVisible(true);
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
    //private javax.swing.JTable tblBestSellers;
    private javax.swing.JLabel tongTien1;
    private javax.swing.JLabel txtQuantityNcc;
    private javax.swing.JLabel txtQuantityProduct;
    //private javax.swing.JLabel txtQuantityUser;
    // End of variables declaration//GEN-END:variables
}
