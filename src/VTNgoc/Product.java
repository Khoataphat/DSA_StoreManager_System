
package VTNgoc;

import java.util.Objects;

/**
 *
 * @author Vu Tuan Ngoc
 */
public class Product {

    private String maMay;
    private String tenMay;
    private int soLuong;
    private double gia;
    private String tenCpu;
    private String ram;
    private String cardManHinh;
    private String Rom;

    public Product() {
        super();
    }

    public Product(String maMay, String tenMay, int soLuong, double gia, String tenCpu, String ram, String cardManHinh, String rom) {
        this.maMay = maMay;
        this.tenMay = tenMay;
        this.soLuong = soLuong;
        this.gia = gia;
        this.tenCpu = tenCpu;
        this.ram = ram;
        this.cardManHinh = cardManHinh;
        Rom = rom;

    }

    public String getMaMay() {
        return maMay;
    }

    public void setMaMay(String maMay) {
        this.maMay = maMay;
    }

    public String getTenMay() {
        return tenMay;
    }

    public void setTenMay(String tenMay) {
        this.tenMay = tenMay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getTenCpu() {
        return tenCpu;
    }

    public void setTenCpu(String tenCpu) {
        this.tenCpu = tenCpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCardManHinh() {
        return cardManHinh;
    }

    public void setCardManHinh(String cardManHinh) {
        this.cardManHinh = cardManHinh;
    }

    public String getRom() {
        return Rom;
    }

    public void setRom(String rom) {
        Rom = rom;
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
        final Product other = (Product) obj;
        if (this.maMay != other.maMay) {
            return false;
        }
        if (!Objects.equals(this.tenMay, other.tenMay)) {
            return false;
        }
        if (!Objects.equals(this.soLuong, other.soLuong)) {
            return false;
        }
        if (!Objects.equals(this.gia, other.gia)) {
            return false;
        }
        if (!Objects.equals(this.tenCpu, other.tenCpu)) {
            return false;
        }
        if (!Objects.equals(this.ram, other.ram)) {
            return false;
        }
        if (!Objects.equals(this.cardManHinh, other.cardManHinh)) {
            return false;
        }
        return Objects.equals(this.Rom, other.Rom);
    }

    @Override
    public String toString() {
        return "Product{"
                + "maMay='" + maMay + '\''
                + ", tenMay='" + tenMay + '\''
                + ", soLuong=" + soLuong
                + ", gia=" + gia
                + ", tenCpu='" + tenCpu + '\''
                + ", ram='" + ram + '\''
                + ", cardManHinh='" + cardManHinh + '\''
                + ", Rom='" + Rom + '\''
                + '}';
    }
}
