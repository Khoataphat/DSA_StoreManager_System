package Khoa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConvertToCSV {
    public static void main(String[] args) {
        String inputFile = "src/Database/product_info.txt";
        String outputFile = "src/Database/product_info.csv"; // Đường dẫn đầy đủ

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            // Ghi tiêu đề cho file CSV
            bw.write("ProductName,Quantity,AmountSold,StartDate,LastUpdated,EndDate,Price,DailyConsumption");
            bw.newLine();

            String line;
            String[] data = new String[8]; // Mảng để lưu dữ liệu của mỗi sản phẩm
            int index = 0;

            while ((line = br.readLine()) != null) {
                data[index] = line.trim(); // Lưu từng dòng vào mảng
                index++;

                // Khi đã đọc đủ 8 dòng, ghi vào file CSV
                if (index == 8) {
                    // Ghi dữ liệu vào file CSV
                    bw.write(String.join(",", data));
                    bw.newLine();
                    index = 0; // Đặt lại chỉ số
                }
            }

            // Kiểm tra nếu vẫn còn dữ liệu trong mảng sau khi đọc
            if (index > 0) {
                String[] remainingData = new String[index];
                System.arraycopy(data, 0, remainingData, 0, index);
                bw.write(String.join(",", remainingData));
                bw.newLine();
            }

            System.out.println("Đã chuyển đổi thành công thành file " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}