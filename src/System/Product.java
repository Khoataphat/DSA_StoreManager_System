package System;

import java.util.Objects;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Vu Tuan Ngoc
 */
public class Product {

    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private double giaTien;
    private LocalDate ngaySanXuat;
    private LocalDate hanSuDung;
    private String khoiLuong;
    private String thanhPhan;

    public Product() {
        super();
    }

    public Product(String maSanPham, String tenSanPham, int soLuong, double giaTien,
                   String ngaySanXuat, String hanSuDung, String khoiLuong, String thanhPhan) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        setSoLuong(soLuong);  // Sử dụng phương thức set
        setGiaTien(giaTien);  // Sử dụng phương thức set
        this.ngaySanXuat = LocalDate.parse(ngaySanXuat, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.hanSuDung = LocalDate.parse(hanSuDung, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.khoiLuong = khoiLuong;
        this.thanhPhan = thanhPhan;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong < 0) {
            throw new IllegalArgumentException("Số lượng không thể âm.");
        }
        this.soLuong = soLuong;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        if (giaTien < 0) {
            throw new IllegalArgumentException("Giá tiền không thể âm.");
        }
        this.giaTien = giaTien;
    }

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(LocalDate ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat; // Nhận LocalDate trực tiếp
    }

    public LocalDate getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(LocalDate hanSuDung) {
        this.hanSuDung = hanSuDung; // Nhận LocalDate trực tiếp
    }

    public String getKhoiLuong() {
        return khoiLuong;
    }

    public void setKhoiLuong(String khoiLuong) {
        this.khoiLuong = khoiLuong;
    }

    public String getThanhPhan() {
        return thanhPhan;
    }

    public void setThanhPhan(String thanhPhan) {
        this.thanhPhan = thanhPhan;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product other = (Product) obj;
        return soLuong == other.soLuong &&
                Double.compare(other.giaTien, giaTien) == 0 &&
                Objects.equals(maSanPham, other.maSanPham) &&
                Objects.equals(tenSanPham, other.tenSanPham) &&
                Objects.equals(ngaySanXuat, other.ngaySanXuat) &&
                Objects.equals(hanSuDung, other.hanSuDung) &&
                Objects.equals(khoiLuong, other.khoiLuong) &&
                Objects.equals(thanhPhan, other.thanhPhan);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Product{" +
                "maSanPham='" + maSanPham + '\'' +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", soLuong=" + soLuong +
                ", giaTien=" + giaTien +
                ", ngaySanXuat=" + ngaySanXuat.format(formatter) +
                ", hanSuDung=" + hanSuDung.format(formatter) +
                ", khoiLuong='" + khoiLuong + '\'' +
                ", thanhPhan='" + thanhPhan + '\'' +
                '}';
    }
}