
package GUI;

import DataStructure.Graph.Edge;
import DataStructure.Graph.ListGraph;
import com.formdev.flatlaf.FlatLightLaf;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import System.Account;
import AVL.AccountManagerTree;
import System.AmountSold;
import AVL.AmountSoldManagerTree;
import System.ChiTietPhieu;
import AVL.PhieuMuaManagerTree;

import System.Product;
import System.Phieu;

import AVL.ProductManagerTree;
import PriorityQueue.SortedLinkedPriorityQueue;

/**
 *
 * @author Ngoc
 * Đây là main class của App
 * khi run App, class này sẽ đc chạy
 * class này có các hàm đọc dữ liệu từ file txt để add vào cây, và ghi dữ liệu từ cây vào file txt
 * sau khi dọc dữ liệu vào thì sẽ khởi tạo một class Login()
 */
public class Run {
    // Khởi tạo các cây rỗng
    public static ProductManagerTree ProductTree = new ProductManagerTree();
    public static AccountManagerTree AccountTree = new AccountManagerTree();
    public static PhieuMuaManagerTree PhieuMuaTree = new PhieuMuaManagerTree();
    public static AmountSoldManagerTree AmountSoldTree = new AmountSoldManagerTree();
    public static SortedLinkedPriorityQueue<Integer, Product> queueSale = new SortedLinkedPriorityQueue();
    public static ListGraph productGraph;
    public static void ReadDatatoQueue() throws FileNotFoundException, IOException {
        Scanner scanner;
        try {

            scanner = new Scanner(new File("src\\Database\\FileDataProduct_CannedFood.txt"));
            System.out.println();
            addQueue(queueSale, scanner);
            //Thêm sản phẩm mẫu vào hàng đợi

            //in ra hàng đợi
            System.out.println("Hàng đợi sản phẩm: ");
            System.out.println(queueSale.toString());
            System.out.println("Số lượng sản phẩm trong hàng đợi: " + queueSale.size());

        } catch (FileNotFoundException e) {
            System.out.println("ReadDatatoQueue???");
        }


    }

    public static void addQueue(SortedLinkedPriorityQueue<Integer, Product> queue, Scanner scanner) {
        //<-----
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (scanner.hasNext()) {
            try {
                String maSanPham = scanner.nextLine();
                String tenSanPham = scanner.nextLine();
                String soLuong = scanner.nextLine();
                String donGia = scanner.nextLine();
                //LocalDate ngaySanXuat = LocalDate.parse(scanner.nextLine(), formatter);
                //LocalDate hanSuDung = LocalDate.parse(scanner.nextLine(), formatter);
                String ngaySanXuat = scanner.nextLine();
                String hanSuDung = scanner.nextLine();
                String thanhPhan = scanner.nextLine();
                String khoiLuong = scanner.nextLine();
                //LocalDate ngayNhapKHo = LocalDate.parse(scanner.nextLine(), formatter);
                String ngayNhapKHo = scanner.nextLine();
                String soNgayGiaoHang = scanner.nextLine();

                // In ra thông tin sản phẩm đọc được
                System.out.println("Đang thêm sản phẩm: " + maSanPham + ", " + tenSanPham + ", " + soLuong);

                Product a = new Product(maSanPham, tenSanPham, Integer.parseInt(soLuong),
                        Double.parseDouble(donGia), ngaySanXuat,
                        hanSuDung, thanhPhan, khoiLuong,
                        ngayNhapKHo, Integer.parseInt(soNgayGiaoHang));

                queue.add(a, 30, queue); // 30 là số lượng hàng trong kho, nếu lớn hơn 30 thì sẽ sale
                System.out.println("Sản phẩm đã được thêm vào hàng đợi: " + a);
                System.out.println("Số lượng sản phẩm trong hàng đợi hiện tại: " + queue.size());
            } catch (Exception e) {
                System.out.println("Lỗi khi đọc sản phẩm: " + e.getMessage());
            }
        }

        //<-----
    }
// đọc dữ liệu phiếu mua và add vào cây phiếu mua


    public static void ReadDataPhieuMua() throws FileNotFoundException, IOException {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src\\Database\\PhieuMua_CannedFood.txt"));
            System.out.println();
            addPhieuMua(PhieuMuaTree, scanner);

        } catch (FileNotFoundException e) {
            System.out.println("ReadDataPhieuMua???");
        }
    }

    public static void addPhieuMua(PhieuMuaManagerTree tree, Scanner scanner) {
        while (scanner.hasNext()) {
            // Read order metadata
            String time = scanner.nextLine();  // Order time
            String phone = scanner.nextLine();  // Customer phone
            String totalStr = scanner.nextLine();  // Total price for the bill
            String address = scanner.nextLine();  // Customer address
            String user = scanner.nextLine();  // Customer username (e.g., "khoa")
            String tracking = scanner.nextLine(); //tracking tình trạng đơn

            // List to hold ChiTietPhieu objects for the current order
            List<ChiTietPhieu> chiTietPhieuList = new ArrayList<>();
            double totalPrice = 0.0;  // Accumulated total price for the order

            // Read products until we hit "------"
            while (scanner.hasNext()) {
                String productName = scanner.nextLine();
                if (productName.equals("------")) {
                    break;
                }

                String quantityStr = scanner.nextLine();
                String priceStr = scanner.nextLine();

                try {
                    int quantity = Integer.parseInt(quantityStr);
                    double price = Double.parseDouble(priceStr);

                    // Create a ChiTietPhieu object for the product
                    ChiTietPhieu chiTietPhieu = new ChiTietPhieu(productName, quantity, price);
                    chiTietPhieuList.add(chiTietPhieu);

                    // Accumulate the total price
                    totalPrice += quantity * price;

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for quantity or price. Skipping this product.");
                    continue;  // Skip invalid products
                }
            }
            // Parse the total price from the input (for verification purposes)
            double inputTotalPrice;
            try {
                inputTotalPrice = Double.parseDouble(totalStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid total price in input. Skipping this bill.");
                continue;
            }
            if (Math.abs(inputTotalPrice - totalPrice) > 0.01) {
                System.out.println("Warning: Calculated total price does not match input total price.");
            }
            // Create a Phieu object with the order data
            Phieu phieu = new Phieu(Timestamp.valueOf(time), phone, address, user, chiTietPhieuList, inputTotalPrice, Integer.parseInt(tracking));

            // Add the Phieu object to the tree with the username as the key
            tree.add(user, phieu);
        }

        // After processing all the input, print the size and tree structure
        System.out.println("- Size - Phieu Mua: " + tree.size());
        tree.printTree();
    }

    //đọc dữ liệu Product và add vào cây product
    public static void ReadDataProduct() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src\\Database\\FileDataProduct_CannedFood.txt"));
            System.out.println();
            addProduct(ProductTree, scanner);
        } catch (FileNotFoundException e) {
            System.out.println("ReadDataProduct???");
        }
    }


    public static void addProduct(ProductManagerTree tree, Scanner scanner) {
        while (scanner.hasNext()) {
            try {
                String maSanPham = scanner.nextLine();
                String tenSanPham = scanner.nextLine();
                int soLuong = Integer.parseInt(scanner.nextLine());
                double donGia = Double.parseDouble(scanner.nextLine());

                // Xử lý ngày sản xuất
                String ngaySanXuatString = scanner.nextLine().trim();
                LocalDate ngaySanXuat = null;
                try {
                    ngaySanXuat = LocalDate.parse(ngaySanXuatString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    System.out.println("Ngày sản xuất: " + ngaySanXuat);
                } catch (DateTimeParseException e) {
                    System.err.println("Lỗi chuyển đổi ngày sản xuất: " + e.getMessage());
                }

                // Xử lý hạn sử dụng
                String hanSuDungString = scanner.nextLine().trim();
                LocalDate hanSuDung = null;
                try {
                    hanSuDung = LocalDate.parse(hanSuDungString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    System.out.println("Hạn sử dụng: " + hanSuDung);
                } catch (DateTimeParseException e) {
                    System.err.println("Lỗi chuyển đổi hạn sử dụng: " + e.getMessage());
                }

                String thanhPhan = scanner.nextLine();
                String khoiLuong = scanner.nextLine();
                String ngayNhapKhoString = scanner.nextLine().trim();
                LocalDate ngayNhapKho = null;
                try {
                    ngayNhapKho = LocalDate.parse(ngayNhapKhoString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    System.out.println("Ngày nhập kho: " + ngayNhapKho);
                } catch (DateTimeParseException e) {
                    System.err.println("Lỗi chuyển đổi ngày nhập kho: " + e.getMessage());
                }

                int soNgayGiaoHang = Integer.parseInt(scanner.nextLine());

                Product a = new Product(maSanPham, tenSanPham, soLuong, donGia,
                        ngaySanXuat.toString(), hanSuDung.toString(), thanhPhan, khoiLuong,
                        ngayNhapKho.toString(), soNgayGiaoHang);

                tree.add(a.getTenSanPham(), a);

            } catch (NumberFormatException e) {
                System.err.println("Lỗi khi chuyển đổi số: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Lỗi khi đọc sản phẩm: " + e.getMessage());
            }
        }
    }


    // đọc dữ liệu lượng bán ra từ file text và add vào cây AmountSold

    public static void ReadDataAmountSold() throws FileNotFoundException, IOException {
        Scanner scanner;
        try {
            File file = new File("src\\Database\\AmountSold_CannedFood.txt");
            if (!file.exists() || file.length() == 0) {
                System.out.println("File không tồn tại hoặc rỗng.");
                return; // Thoát nếu file không hợp lệ
            }

            scanner = new Scanner(file);
            System.out.println();
            addAmountSold(AmountSoldTree, scanner);
        } catch (FileNotFoundException e) {
            System.out.println("ReadDataAmountSold???");
        }
    }

    public static void addAmountSold(AmountSoldManagerTree tree, Scanner scanner) {
        while (scanner.hasNextLine()) {
            String maMay = scanner.nextLine();
            if (scanner.hasNextLine()) {
                int amountSold = Integer.parseInt(scanner.nextLine());
                AmountSold a = new AmountSold(maMay, amountSold);
                tree.add(a.getMaMay(), a);
            } else {
                System.out.println("Dữ liệu không hợp lệ cho mã máy: " + maMay);
            }
        }

        System.out.println("-Size - AmountSold: " + tree.size());
        tree.printTree(); // In cây để xác nhận dữ liệu
    }


    //đọc dữ liệu account từ file text và add vào cây Account


    public static void ReadDataAccount() throws FileNotFoundException, IOException {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src\\Database\\FileDataAccount_CannedFood.txt"));
            System.out.println();
            addAccount(AccountTree, scanner);

        } catch (FileNotFoundException e) {
            System.out.println("ReadDataAccount???");
        }
    }

    public static void addAccount(AccountManagerTree tree, Scanner scanner) {

        while (scanner.hasNext()) {
            String UserName = scanner.nextLine();
            String user = scanner.nextLine();
            String password = scanner.nextLine();
            String role = scanner.nextLine();
            String phone = scanner.nextLine();

            Account a = new Account(UserName, user, password, role, phone);

            tree.add(a.getUser(), a);

        }

        System.out.println("-Size - Account: " + tree.size());
        tree.printTree();
    }


    // lấy dữ liệu từ cây Phiếu mua ghi ra file txt


    public static void WriteDataPhieuMua() throws FileNotFoundException, IOException {
        // Get the list of all Phieu objects
        List<Phieu> PhieuMuaData = Run.PhieuMuaTree.getInOrderList();

        // Initialize the FileWriter with the desired path
        FileWriter fw = new FileWriter("src\\Database\\PhieuMua_CannedFood.txt");

        try {
            // Loop through each Phieu object and write its data to the file
            for (Phieu a : PhieuMuaData) {
                // Write Phieu's main data first
                fw.write(a.getThoiGianTao() + "\n");
                fw.write(a.getPhone() + "\n");
                fw.write(a.getTongTien() + "\n");
                fw.write(a.getAddress() + "\n");
                fw.write(a.getUsername() + "\n");
                fw.write(a.getTracking() + "\n");

                // Loop through each ChiTietPhieu in the Phieu's list and write its data
                for (ChiTietPhieu chiTiet : a.getPhieu()) {
                    fw.write(chiTiet.getTenSanPham() + "\n");
                    fw.write(chiTiet.getSoLuong() + "\n");
                    fw.write(chiTiet.getGia() + "\n");
                }

                // Add a separator between different Phieu records
                fw.write("------\n");
            }

            // Close the FileWriter after writing
            fw.close();
        } catch (IOException e) {
            // Handle exception by printing the error message
            System.out.println("Error writing data: " + e.getMessage());
            throw e;  // Rethrow the exception after logging
        }
    }


    // lấy dữ liệu từ cây Product ghi ra file txt


    public static void WriteDataProduct() throws FileNotFoundException, IOException {

        try {
            List<Product> ProductData = Run.ProductTree.getInOrderList();
            FileWriter fw = new FileWriter("src\\Database\\FileDataProduct_CannedFood.txt");
            fw.write("");
            for (Product product : ProductData) {
                fw.write(product.getMaSanPham() + "\n" + product.getTenSanPham() + "\n" + product.getSoLuong() + "\n" + product.getGiaTien() + "\n" + product.getNgaySanXuat() + "\n" + product.getHanSuDung() + "\n" + product.getThanhPhan() + "\n" + product.getKhoiLuong() + "\n" + product.getNgayNhapKho() + "\n" + product.getSoNgayGiaoHang() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    // lấy dữ liệu từ cây Lượng bán ra ghi ra file txt


    public static void WriteDataAmountSold() throws FileNotFoundException, IOException {

        try {
            List<AmountSold> amountSold = Run.AmountSoldTree.getInOrderList();
            FileWriter fw = new FileWriter("src\\Database\\AmountSold_CannedFood.txt");
            fw.write("");
            for (AmountSold product : amountSold) {
                fw.write(product.getMaMay() + "\n" + product.getAmountSold() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    // lấy dữ liệu từ cây Account ghi ra file txt


    public static void WriteDataAccount() throws FileNotFoundException, IOException {
        List<Account> AccountData = Run.AccountTree.getInOrderList();
        try {

            FileWriter fw = new FileWriter("src\\Database\\FileDataAccount_CannedFood.txt");
            fw.write("");
            for (Account acc : AccountData) {
                fw.write(acc.getFullName() + "\n" + acc.getUser() + "\n" + acc.getPassword() + "\n" + acc.getRole() + "\n" + acc.getPhone() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    private static <T> void print(List<T> list) {
        for (T t : list) {
            System.out.println(t.toString());
        }
    }

    // Lớp để lưu thông tin sản phẩm không khớp
    public static class MismatchInfo {
        String productName;
        int boughtQuantity;
        int soldQuantity;

        public MismatchInfo(String productName, int boughtQuantity, int soldQuantity) {
            this.productName = productName;
            this.boughtQuantity = boughtQuantity;
            this.soldQuantity = soldQuantity;
        }
    }

    public static List<MismatchInfo> checkQuantityMatch() {
        List<MismatchInfo> mismatchedProducts = new ArrayList<>();
        HashMap<String, Integer> totalBoughtQuantities = new HashMap<>();

        List<Phieu> phieuList = PhieuMuaTree.getInOrderList();

        // Tổng hợp số lượng mua cho mỗi sản phẩm
        for (Phieu phieu : phieuList) {
            for (ChiTietPhieu ctPhieu : phieu.getPhieu()) {
                String productName = ctPhieu.getTenSanPham();
                int boughtQuantity = ctPhieu.getSoLuong();
                if (phieu.getTracking() == 6) {
                    totalBoughtQuantities.put(productName, totalBoughtQuantities.getOrDefault(productName, 0) + boughtQuantity);
                }
            }
        }

        // So sánh với số lượng bán
        for (Map.Entry<String, Integer> entry : totalBoughtQuantities.entrySet()) {
            String productName = entry.getKey();
            int totalBoughtQuantity = entry.getValue();

            // Lấy số lượng bán từ cây AmountSold
            AmountSoldManagerTree.AmountSoldNode amountSold = AmountSoldTree.get(productName);
            int soldQuantity = (amountSold != null) ? amountSold.getAmountSold().getAmountSold() : 0;

            // Kiểm tra sự không khớp
            if (soldQuantity != totalBoughtQuantity) {
                mismatchedProducts.add(new MismatchInfo(productName, totalBoughtQuantity, soldQuantity));
            }
        }

        return mismatchedProducts;
    }

    public static void convertTreeToGraph(ProductManagerTree tree, ListGraph graph) {
        List<Product> products = tree.getInOrderList();
        Map<String, Integer> componentVertexMap = new HashMap<>();
        int vertexIndex = 0;

        // Thêm đỉnh sản phẩm
        for (Product product : products) {
            graph.addVertex(vertexIndex, product.getTenSanPham()); // Lưu tên sản phẩm vào đỉnh
            vertexIndex++;
        }

        // Thêm đỉnh cho thành phần và tạo cạnh
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            String[] components = product.getThanhPhan().toLowerCase().split(",");

            for (String component : components) {
                component = component.trim();
                if (!componentVertexMap.containsKey(component)) {
                    componentVertexMap.put(component, vertexIndex);
                    graph.addVertex(vertexIndex, component); // Lưu tên thành phần vào đỉnh
                    vertexIndex++;
                }
                graph.insert(new Edge(i, componentVertexMap.get(component), 1));
            }
        }
    }



    public static List<Product> searchProductsByComponentsInGraph(String componentsString, ListGraph graph, List<Product> products) {
        // Tách các thành phần cần tìm
        String[] searchComponents = componentsString.toLowerCase().trim().split(",");
        List<Integer> componentVertices = new ArrayList<>();
        List<Product> result = new ArrayList<>();

        System.out.println("Các thành phần cần tìm: " + Arrays.toString(searchComponents));

        // Tìm đỉnh tương ứng với từng thành phần
        for (String component : searchComponents) {
            component = component.trim();
            boolean found = false;
            for (int i = products.size(); i < graph.getNumV(); i++) { // Các đỉnh thành phần nằm sau đỉnh sản phẩm
                if (component.equalsIgnoreCase(graph.getVertexName(i))) {
                    componentVertices.add(i);
                    System.out.println("Đã tìm thấy thành phần \"" + component + "\" tại đỉnh: " + i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Không tìm thấy thành phần: " + component);
            }
        }

        if (componentVertices.isEmpty()) {
            System.out.println("Không tìm thấy bất kỳ thành phần nào. Dừng tìm kiếm.");
            return result; // Không có thành phần nào khớp
        }

        // Duyệt qua tất cả các sản phẩm để kiểm tra nếu sản phẩm chứa tất cả các thành phần
        System.out.println("Bắt đầu duyệt qua các sản phẩm...");
        for (int i = 0; i < products.size(); i++) {
            boolean matchesAll = true;

            for (int componentVertex : componentVertices) {
                if (!graph.isEdge(i, componentVertex)) { // Nếu không có cạnh giữa sản phẩm và thành phần
                    System.out.println("Sản phẩm \"" + products.get(i).getTenSanPham() + "\" không chứa thành phần tại đỉnh: " + componentVertex);
                    matchesAll = false;
                    break;
                }
            }

            if (matchesAll) { // Nếu sản phẩm chứa tất cả thành phần
                System.out.println("Sản phẩm khớp: " + products.get(i).getTenSanPham());
                result.add(products.get(i));
            }
        }

        System.out.println("Số sản phẩm khớp tìm thấy: " + result.size());
        return result;
    }


    //<-----
    /*
    public static void checkAndUpdateAmountSold() throws IOException {
        List<Product> products = ProductTree.getInOrderList();
        Set<String> existingProducts = new HashSet<>();

        // Lấy tên sản phẩm đã có trong cây AmountSold
        for (AmountSoldManagerTree.AmountSoldNode node : AmountSoldTree) {
            existingProducts.add(node.getAmountSold().getMaMay());
        }

        // Kiểm tra từng sản phẩm trong ProductTree
        for (Product product : products) {
            String productName = product.getTenSanPham();
            if (!existingProducts.contains(productName)) {
                // Nếu sản phẩm chưa tồn tại trong AmountSold, thêm vào với số lượng 0
                AmountSold newAmountSold = new AmountSold(productName, 0);
                AmountSoldTree.add(productName, newAmountSold);
                System.out.println("Đã thêm sản phẩm vào AmountSold: " + productName + " với số lượng = 0");
            }
        }
    }

     */
    public static void updateAmountSoldFile() throws IOException {
        Set<String> existingProducts = new HashSet<>();

        // Đọc dữ liệu trong file AmountSold
        try (Scanner scanner = new Scanner(new File("src\\Database\\AmountSold_CannedFood.txt"))) {
            while (scanner.hasNextLine()) {
                String productName = scanner.nextLine().trim();
                if (scanner.hasNextLine()) {
                    scanner.nextLine(); // Bỏ qua số lượng
                    if (!productName.isEmpty()) {
                        existingProducts.add(productName);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File AmountSold không tồn tại: " + e.getMessage());
        }

        List<String> productNames = new ArrayList<>(); // Danh sách chỉ chứa tên sản phẩm

        // Đọc dữ liệu sản phẩm từ file
        try (Scanner scanner = new Scanner(new File("src\\Database\\FileDataProduct_CannedFood.txt"))) {
            while (scanner.hasNextLine()) {
                String maSanPham = scanner.nextLine().trim();
                String tenSanPham = scanner.nextLine().trim();
                // Bỏ qua các dòng không cần thiết
                scanner.nextLine(); // Số lượng
                scanner.nextLine(); // Đơn giá
                scanner.nextLine(); // Ngày sản xuất
                scanner.nextLine(); // Hạn sử dụng
                scanner.nextLine(); // Thành phần
                scanner.nextLine(); // Khối lượng
                scanner.nextLine(); // Ngày nhập kho
                scanner.nextLine(); // Số ngày giao hàng

                if (!tenSanPham.isEmpty()) {
                    productNames.add(tenSanPham); // Chỉ thêm tên sản phẩm
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Product không tồn tại: " + e.getMessage());
        }

        // Kiểm tra và thêm sản phẩm chưa có vào file AmountSold
        try (FileWriter fw = new FileWriter("src\\Database\\AmountSold_CannedFood.txt", true)) {
            for (String productName : productNames) {
                if (!existingProducts.contains(productName) && !productName.isEmpty()) {
                    fw.write(productName + "\n0\n"); // Thêm sản phẩm mới với số lượng 0
                    System.out.println("Đã thêm sản phẩm: " + productName + " với số lượng = 0");
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file AmountSold: " + e.getMessage());
        }
    }
    //<-----


    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        ReadDataAccount(); // dọc dữ liệu
        ReadDataProduct(); // dọc dữ liệu

        //updateAmountSoldFile();
        // Lấy danh sách sản phẩm
        List<Product> products = ProductTree.getInOrderList();
        // Thu thập thành phần
        Set<String> componentSet = new HashSet<>();
        for (Product product : products) {
            String[] components = product.getThanhPhan().toLowerCase().split(",");
            for (String comp : components) {
                componentSet.add(comp.trim());
            }
        }
        // Tính tổng số đỉnh
        int totalVertices = products.size() + componentSet.size();
        // In ra thông tin để kiểm tra
        System.out.println("Số lượng sản phẩm: " + products.size());
        System.out.println("Số lượng thành phần: " + componentSet.size());
        System.out.println("Tổng số đỉnh dự kiến: " + totalVertices);
        productGraph = new ListGraph(totalVertices, false);
        // Sau khi khởi tạo productGraph, in ra số đỉnh thực tế
        System.out.println("Số lượng đỉnh trong productGraph: " + productGraph.getNumV());
        convertTreeToGraph(ProductTree, productGraph);

        ReadDataPhieuMua();
        ReadDataAmountSold();
        ReadDatatoQueue();
        // Kiểm tra số lượng mua và bán
        List<MismatchInfo> mismatchedProducts = checkQuantityMatch();
        if (!mismatchedProducts.isEmpty()) {
            System.out.println("Số lượng mua không khớp với số lượng bán cho các sản phẩm sau:");
            for (MismatchInfo info : mismatchedProducts) {
                System.out.printf("- %s: Số lượng mua = %d, Số lượng bán = %d%n", info.productName, info.boughtQuantity, info.soldQuantity);
            }
            return; // Dừng chương trình nếu có sản phẩm không khớp
        }

        Login login = new Login();
        login.setVisible(true);   // khởi tạo jframe Login

    }
}