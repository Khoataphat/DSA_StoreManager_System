package Khoa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class ConvertCSVToARFF {
    public static void main(String[] args) {
        String inputFile = "src/Database/product_info.csv"; // Đường dẫn tới file CSV
        String outputFile = "src/Database/product_info.arff"; // Đường dẫn tới file ARFF

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8))) {

            // Ghi tiêu đề cho file ARFF
            bw.write("@RELATION CannedFood");
            bw.newLine();
            bw.newLine();

            // Ghi các thuộc tính
            bw.write("@ATTRIBUTE ProductName STRING");
            bw.newLine();
            bw.write("@ATTRIBUTE Quantity NUMERIC");
            bw.newLine();
            bw.write("@ATTRIBUTE AmountSold NUMERIC");
            bw.newLine();
            bw.write("@ATTRIBUTE StartDate DATE \"yyyy-MM-dd\"");
            bw.newLine();
            bw.write("@ATTRIBUTE LastUpdated DATE \"yyyy-MM-dd\"");
            bw.newLine();
            bw.write("@ATTRIBUTE EndDate DATE \"yyyy-MM-dd\"");
            bw.newLine();
            bw.write("@ATTRIBUTE Price NUMERIC");
            bw.newLine();
            bw.write("@ATTRIBUTE DailyConsumption NUMERIC");
            bw.newLine();

            bw.write("@DATA");
            bw.newLine();

            String line;
            boolean isFirstLine = true; // Biến để bỏ qua tiêu đề

            while ((line = br.readLine()) != null) {
                // Tách dòng theo dấu phẩy
                String[] data = line.split(",");

                // Bỏ qua dòng đầu tiên (tiêu đề)
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Kiểm tra số lượng dữ liệu
                if (data.length == 8) { // Đảm bảo rằng có 8 thuộc tính
                    // Định dạng dữ liệu với dấu nháy kép
                    for (int i = 0; i < data.length; i++) {
                        data[i] = "\"" + data[i].replace("\"", "\"\"") + "\""; // Thay thế dấu nháy kép bên trong
                    }
                    // Ghi dữ liệu vào file ARFF
                    bw.write(String.join(",", data));
                    bw.newLine();
                } else {
                    System.err.println("Dòng không hợp lệ: " + line);
                }
            }

            System.out.println("Đã chuyển đổi thành công thành file " + outputFile);
        } catch (IOException e) {
            System.err.println("Đã xảy ra lỗi khi đọc hoặc ghi file: " + e.getMessage());
        }
    }
}