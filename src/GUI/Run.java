
package GUI;

import com.formdev.flatlaf.FlatLightLaf;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
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
            
            scanner = new Scanner(new File("src\\Database\\FileDataProduct.txt"));
            System.out.println();
            addQueue(queueSale , scanner);

            //in ra hàng đợi
            System.out.println("Hàng đợi sản phẩm: ");
            System.out.println(queueSale.toString());

        } catch (FileNotFoundException e) {
            System.out.println("???");
        }
    }

    public static void addQueue(SortedLinkedPriorityQueue<Integer, Product> queue, Scanner scanner) {

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

            queue.add(a, 30, queue); //mức độ ưu tiên cho tất cả sản phẩm là 30

        }

        
    }
// đọc dữ liệu phiếu mua và add vào cây phiếu mua
    
    
    public static void ReadDataPhieuMua() throws FileNotFoundException, IOException {    
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src\\Database\\PhieuMua.txt"));
            System.out.println();
            addPhieuMua(PhieuMuaTree, scanner);

        } catch (FileNotFoundException e) {
            System.out.println("???");
        }
    }

    public static void addPhieuMua(PhieuMuaManagerTree tree, Scanner scanner) {

        while (scanner.hasNext()) {
            String time = scanner.nextLine();
            String phone = scanner.nextLine();
            String tenMay = scanner.nextLine();
            String soluong = scanner.nextLine();
            String dongia = scanner.nextLine();
            String tongtien = scanner.nextLine();
            String address = scanner.nextLine();
            String user = scanner.nextLine();
            ChiTietPhieu a = new ChiTietPhieu(tenMay, Integer.parseInt(soluong), Double.parseDouble(dongia));

            Phieu b = new Phieu(Timestamp.valueOf(time), phone, a, Double.parseDouble(tongtien), address, user);

            tree.add(user, b);

        }


        System.out.println("-Size: " + tree.size());
        tree.printTree();
    }



    
    
    
//đọc dữ liệu Product và add vào cây product
    
    
    
    public static void ReadDataProduct() {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src\\Database\\FileDataProduct.txt"));
            System.out.println();
            addProduct(ProductTree, scanner);

        } catch (FileNotFoundException e) {
            System.out.println("???");
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

        System.out.println("-Size: " + tree.size());
        tree.printTree();
    }
     
     
     // đọc dữ liệu lượng bán ra từ file text và add vào cây AmountSold
     
     public static void ReadDataAmountSold() throws FileNotFoundException, IOException {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src\\Database\\AmountSold.txt"));
            System.out.println();
            addAmountSold(AmountSoldTree, scanner);

        } catch (FileNotFoundException e) {
            System.out.println("???");
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

        System.out.println("-Size: " + tree.size());
        tree.printTree();
    }

    
     
     //đọc dữ liệu account từ file text và add vào cây Account
     
     
     
     public static void ReadDataAccount() throws FileNotFoundException, IOException {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("src\\Database\\FileDataAccount_1.txt"));
            System.out.println();
            addAccount(AccountTree, scanner);

        } catch (FileNotFoundException e) {
            System.out.println("???");
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

        System.out.println("-Size: " + tree.size());
        tree.printTree();
    }

   

   // lấy dữ liệu từ cây Phiếu mua ghi ra file txt
    

    public static void WriteDataPhieuMua() throws FileNotFoundException, IOException {

        try {
            List<Phieu> PhieuMuaData = Run.PhieuMuaTree.getInOrderList();
            FileWriter fw = new FileWriter("src\\Database\\PhieuMua.txt");
            fw.write("");
            for (Phieu a : PhieuMuaData) {
                fw.write(a.getThoiGianTao() + "\n" + a.getPhone() + "\n" + a.getChitieuphieu().getTenMay() + "\n" + a.getChitieuphieu().getSoLuong() + "\n" + a.getChitieuphieu().getGia() + "\n" + a.getTongTien() + "\n" + a.getAddress() + "\n" + a.getUsername() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // lấy dữ liệu từ cây Product ghi ra file txt
    
    
    public static void WriteDataProduct() throws FileNotFoundException, IOException {

        try {
            List<Product> ProductData = Run.ProductTree.getInOrderList();
            FileWriter fw = new FileWriter("src\\Database\\FileDataProduct.txt");
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
            FileWriter fw = new FileWriter("src\\Database\\AmountSold.txt");
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

            FileWriter fw = new FileWriter("src\\Database\\FileDataAccount_1.txt");
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

    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        ReadDataAccount(); // dọc dữ liệu
        ReadDataProduct(); // dọc dữ liệu
        ReadDataPhieuMua(); // dọc dữ liệu
        ReadDataAmountSold(); // dọc dữ liệu
        ReadDatatoQueue();
        Login login = new Login(); 
        login.setVisible(true);   // khởi tạo jframe Login

        
    }
}
