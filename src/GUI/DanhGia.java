package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DanhGia extends JFrame {

    private JTextField txtProductName;
    private JComboBox<String> cbRating;
    private JTextArea txtReview;
    private JButton btnSubmit;

    public DanhGia() {
        setTitle("Đánh Giá Sản Phẩm");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // Nhập tên sản phẩm
        panel.add(new JLabel("Tên sản phẩm:"));
        txtProductName = new JTextField();
        panel.add(txtProductName);

        // Chọn đánh giá
        panel.add(new JLabel("Đánh giá (1-5 sao):"));
        String[] ratings = {"1", "2", "3", "4", "5"};
        cbRating = new JComboBox<>(ratings);
        panel.add(cbRating);

        // Nhập ý kiến đánh giá
        panel.add(new JLabel("Ý kiến đánh giá:"));
        txtReview = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(txtReview);
        panel.add(scrollPane);

        // Nút gửi đánh giá
        btnSubmit = new JButton("Gửi đánh giá");
        panel.add(btnSubmit);

        // Thêm hành động cho nút
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitReview();
            }
        });

        add(panel);
    }

    private void submitReview() {
        String productName = txtProductName.getText();
        String rating = (String) cbRating.getSelectedItem();
        String review = txtReview.getText();

        if (productName.isEmpty() || review.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm và ý kiến đánh giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Hiển thị thông tin đánh giá
        String message = String.format("Tên sản phẩm: %s\nĐánh giá: %s sao\nÝ kiến: %s", productName, rating, review);
        JOptionPane.showMessageDialog(this, message, "Đánh giá đã gửi", JOptionPane.INFORMATION_MESSAGE);

        // Xóa các trường nhập liệu
        txtProductName.setText("");
        cbRating.setSelectedIndex(0);
        txtReview.setText("");
    }

    public void loadDataPhieuMua (){

    }
    public void loadDataAccount() {
        Run.AccountTree.getInOrderList();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DanhGia app = new DanhGia();
            app.setVisible(true);
        });
    }
}