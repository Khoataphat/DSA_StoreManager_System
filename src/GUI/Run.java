
package GUI;

import com.formdev.flatlaf.FlatLightLaf;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
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

    public static void ReadDatatoQueue() throws FileNotFoundException, IOException {
        Scanner scanner;
        try {

            scanner = new Scanner(new File("src\\Database\\FileDataProduct_CannedFood.txt"));
            System.out.println();
            addQueue(queueSale , scanner);

            //in ra hàng đợi
            System.out.println("Hàng đợi sản phẩm: ");
            System.out.println(queueSale.toString());

        } catch (FileNotFoundException e) {
            System.out.println("ReadDatatoQueue???");
        }
    }

    public static void addQueue(SortedLinkedPriorityQueue<Integer, Product> queue, Scanner scanner) {

        while (scanner.hasNext()) {
            String maSanPham = scanner.nextLine();
            String tenmay = scanner.nextLine();
            String soluong = scanner.nextLine();
            String dongia = scanner.nextLine();
            String boxuli = scanner.nextLine();
            String ram = scanner.nextLine();
            String card = scanner.nextLine();
            String bonho = scanner.nextLine();

            Product a = new Product(maSanPham, tenmay, Integer.parseInt(soluong), Double.parseDouble(dongia), boxuli, ram, card, bonho);

            queue.add(a, 30, queue); //mức độ ưu tiên cho tất cả sản phẩm là 30

        }


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
            Phieu phieu = new Phieu(Timestamp.valueOf(time), phone, address, user, chiTietPhieuList, inputTotalPrice);

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
            String mamay = scanner.nextLine();
            String tenmay = scanner.nextLine();
            String soluong = scanner.nextLine();
            String dongia = scanner.nextLine();
            String boxuli = scanner.nextLine();
            String ram = scanner.nextLine();
            String card = scanner.nextLine();
            String bonho = scanner.nextLine();

            Product a = new Product(mamay, tenmay, Integer.parseInt(soluong), Double.parseDouble(dongia), boxuli, ram, card, bonho);

            tree.add(a.getTenSanPham(), a);

        }

        System.out.println("-Size - Product: " + tree.size());
        tree.printTree();
    }
     
     
     // đọc dữ liệu lượng bán ra từ file text và add vào cây AmountSold
     
     public static void ReadDataAmountSold() throws FileNotFoundException, IOException {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src\\Database\\AmountSold_CannedFood.txt"));
            System.out.println();
            addAmountSold(AmountSoldTree, scanner);

        } catch (FileNotFoundException e) {
            System.out.println("ReadDataAmountSold???");
        }
    }
     public static void addAmountSold(AmountSoldManagerTree tree, Scanner scanner) {

        while (scanner.hasNext()) {
            String mamay = scanner.nextLine();
            int AmountSold = scanner.nextInt();
            scanner.nextLine();

            AmountSold a = new AmountSold(mamay, AmountSold);

            tree.add(a.getMaMay(), a);

        }

        System.out.println("-Size - AmountSold: " + tree.size());
        tree.printTree();
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
                fw.write(product.getMaSanPham() + "\n" + product.getTenSanPham() + "\n" + product.getSoLuong() + "\n" + product.getGiaTien() + "\n" + product.getNgaySanXuat() + "\n" + product.getHanSuDung() + "\n" + product.getKhoiLuong() + "\n" + product.getThanhPhan() + "\n");
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
                fw.write(product.getMaMay() +  "\n"+product.getAmountSold()+"\n");
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
        HashSet<String> checkedProducts = new HashSet<>();
        List<Phieu> phieuList = PhieuMuaTree.getInOrderList();

        // Tổng hợp số lượng mua cho mỗi sản phẩm
        for (Phieu phieu : phieuList) {
            for (ChiTietPhieu ctPhieu : phieu.getPhieu()) {
                String productName = ctPhieu.getTenSanPham();
                int boughtQuantity = ctPhieu.getSoLuong();

                totalBoughtQuantities.put(productName, totalBoughtQuantities.getOrDefault(productName, 0) + boughtQuantity);
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

    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        ReadDataAccount(); // dọc dữ liệu
        ReadDataProduct(); // dọc dữ liệu
        ReadDataPhieuMua(); // dọc dữ liệu
        ReadDataAmountSold(); // dọc dữ liệu
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
