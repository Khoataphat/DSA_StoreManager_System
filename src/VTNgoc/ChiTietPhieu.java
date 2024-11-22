
package VTNgoc;

import java.util.Objects;
/**
 *
 * @author Vu Tuan Ngoc
 */
public class ChiTietPhieu {

    private String TenMay;
    private int soLuong;
    private double donGia;

    public ChiTietPhieu() {
    }

    public ChiTietPhieu(String tenMay, int soLuong, double donGia) {

        this.TenMay = tenMay;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getTenMay() {
        return TenMay;
    }

    public void setTenMay(String tenMay) {
        this.TenMay = tenMay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChiTietPhieu other = (ChiTietPhieu) obj;
        if (this.soLuong != other.soLuong) {
            return false;
        }
        if (Double.doubleToLongBits(this.donGia) != Double.doubleToLongBits(other.donGia)) {
            return false;
        }

        return Objects.equals(this.TenMay, other.TenMay);
    }

    @Override
    public String toString() {
        return "ChiTietPhieu{" + "maPhieu=" + ", TeMay=" + TenMay + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }

}
