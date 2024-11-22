
package VTNgoc;

import java.sql.Timestamp;
import java.util.Objects;
/**
 *
 * @author Vu Tuan Ngoc
 */
public class Phieu {

    private Timestamp thoiGianTao;
    private String phone;
    private ChiTietPhieu chitieuphieu;
    private double tongTien;
    private String address;
    private String username;
    private int stt;

    public Phieu() {
    }

    public Phieu(Timestamp thoiGianTao, String phone, ChiTietPhieu chitietphieu, double tongTien, String address, String user) {
        this.thoiGianTao = thoiGianTao;
        this.phone = phone;
        this.chitieuphieu = chitietphieu;
        this.tongTien = tongTien;
        this.address = address;
        this.username = user;

    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Timestamp thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ChiTietPhieu getChitieuphieu() {
        return chitieuphieu;
    }

    public void setChitieuphieu(ChiTietPhieu chitieuphieu) {
        this.chitieuphieu = chitieuphieu;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Phieu other = (Phieu) obj;
        return Objects.equals(this.username, other.username);
    }

    @Override
    public String toString() {
        return "Phieu{" + "Phone=" + phone+ ", thoiGianTao=" + thoiGianTao + ", nguoiMua" + ", CTPhieu=" + ", tongTien=" + tongTien + '}';
    }

}
