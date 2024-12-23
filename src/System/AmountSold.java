
package System;

import java.util.Objects;

/**
 *
 * @author
 */
public class AmountSold {

    private String maSanPham;
    private int AmountSold;


    public AmountSold(int i) {
        super();
    }

    public AmountSold(String maSanPham, int AmountSold) {
        this.maSanPham = maSanPham;
        this.AmountSold = AmountSold;
        

    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getAmountSold() {
        return AmountSold;
    }

    public void setAmountSold(int AmountSold) {
        this.AmountSold = AmountSold;
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
        final AmountSold other = (AmountSold) obj;
        if (this.maSanPham != other.maSanPham) {
            return false;
        }
        
        return Objects.equals(this.AmountSold, other.AmountSold);
    }

    @Override
    public String toString() {
        return "Product{"
                + "maMay='" + maSanPham + '\''
                
                + ",AmountSold=" + AmountSold + '\''
                + '}';
    }
}
