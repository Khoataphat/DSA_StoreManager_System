
package VTNgoc;

import java.util.Objects;

/**
 *
 * @author Vu Tuan Ngoc
 */
public class AmountSold {

    private String maMay;
    private int AmountSold;
    ;

    public AmountSold() {
        super();
    }

    public AmountSold(String maMay, int AmountSold) {
        this.maMay = maMay;
        this.AmountSold = AmountSold;
        

    }

    public String getMaMay() {
        return maMay;
    }

    public void setMaMay(String maMay) {
        this.maMay = maMay;
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
        if (this.maMay != other.maMay) {
            return false;
        }
        
        return Objects.equals(this.AmountSold, other.AmountSold);
    }

    @Override
    public String toString() {
        return "Product{"
                + "maMay='" + maMay + '\''
                
                + ",AmountSold" + AmountSold + '\''
                + '}';
    }
}
