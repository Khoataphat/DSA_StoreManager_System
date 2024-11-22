
package VTNgoc;

import java.util.Comparator;
/**
 *
 * @author Vu Tuan Ngoc
 * tạo hàm TimeComparator để so sánh time giữa 2 đối tượng Phieu(phiếu)
 */
public class TimeComparator implements Comparator<Phieu> {

    @Override
    public int compare(Phieu phieu1, Phieu phieu2) {
        return phieu1.getThoiGianTao().compareTo(phieu2.getThoiGianTao());
    }

}
