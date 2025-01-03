
package System;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author
 */
public class Phieu {

    private Timestamp thoiGianTao;
    private String phone;
    private String address;
    private String username;
    private List<ChiTietPhieu> phieu;
    private double tongTien;
    private int tracking;
    //Banh private int stt;
/*
    public Phieu() {
        this.phieu = new ArrayList<>();
    }
*/
    public Phieu(Timestamp thoiGianTao, String phone, String address,String user,List<ChiTietPhieu> chitietphieu, double tongTien, int tracking) {
        this.thoiGianTao = thoiGianTao;
        this.phone = phone;
        this.address = address;
        this.username = user;
        this.phieu = chitietphieu;
        this.tongTien = tongTien;
        this.tracking = tracking;
    }
    //<--------
    public Phieu(int tracking) {
        this.tracking = tracking;
    }
    //<--------
//Banh----------------------------------------------------------------
/*    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
*/
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

    public List<ChiTietPhieu> getPhieu() {
        return phieu;
    }

    public void setPhieu(List<ChiTietPhieu> phieu) {
        this.phieu = phieu;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
//Banh------------------------------------------------
    public int getTracking() {
        return tracking;
    }
    public void setTracking(int tracking) {
        this.tracking = tracking;
    }
    public String getTrackingStatus(){
        switch (tracking) {
            case 1:
                return "Đang chờ xử lý";
            case 2:
                return "Đã được xác nhận";
            case 3:
                return "Đang chuẩn bị";
            case 4:
                return "Đang vận chuyển";
            case 5:
                return "Đã nhận hàng";
            case 6:
                return "Đã hủy";
            case 7:
                return "Đã hoàn hàng về kho";
            default:
                return "Đang chờ xử lý";
        }
    }
//----------------------------------------------------------------
    public void capNhatTongTien() {
        this.tongTien = phieu.stream()
                .mapToDouble(ct -> ct.getGia() * ct.getSoLuong())
                .sum();
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
        return "Phieu{" + "Phone=" + phone+ ", thoiGianTao=" + thoiGianTao + ", nguoiMua" + username + ", tongTien=" + tongTien + ", tracking=" + tracking + '}';
    }

}
