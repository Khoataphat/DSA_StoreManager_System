package AVL;

import VTNgoc.Product;
import java.util.*;

public class ProductManagerTree {

    /*
    - Cây lưu node có khóa là tên sản phẩm
    - Các node của cây lưu giá trị là 1 đối tượng sản phẩm
    - Khi thực hiện quá trình tìm kiếm, sau khi đã ghi đủ tên sp, vd: AB thì cây sẽ
    + thực hiện hàm get(key) để lấy ra List sản phẩm có tên AB - duy nhất.
    - Ngoài ra, đối với từ khóa tìm kiếm là thành phần con của tập tên sản phẩm, thực hiện lọc qua list - lấy ra từ duyệt In-order của cây.
    + Kết quả trả ra là một list các sản phẩm.
     * @update:
     */

    public static class ProductNode {

        String key;
        Product product;
        ProductNode parent;
        ProductNode left;
        ProductNode right;

        public ProductNode(String key, Product product, ProductNode parent, ProductNode left, ProductNode right) {
            this.key = key;
            this.product = product;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public String getKey() {
            return key;
        }

        public Product getProduct() {
            return product;
        }

        public ProductNode getParent() {
            return parent;
        }

        public ProductNode getLeft() {
            return left;
        }

        public ProductNode getRight() {
            return right;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        public void setParent(ProductNode parent) {
            this.parent = parent;
        }

        public void setLeft(ProductNode left) {
            this.left = left;
        }

        public void setRight(ProductNode right) {
            this.right = right;
        }

        /*
         * @description: các hàm phục vụ hoạt động của cây
            - isLeaf(): kiểm tra nếu cây không có con, là lá
            - hasParent(): kiểm tra cây có phải root không
            - swap(): thực hiện trao đổi value giữa 2 nodes, thực hiện khi cần cân bằng cây hoặc xóa giá trị
            - size(): tính kích cỡ của cây con (subtree) với node hiện tại là node cha của tất cả các node trong đó.
         * @update:
         */
        private boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        private boolean hasParent() {
            return this.parent != null;
        }

        @Override
        public String toString() {
            return product.toString();
        }

        private void swap(ProductNode node) {
            String tempKey = this.key;
            Product tempProduct = this.product;

            this.setKey(node.getKey());
            this.setProduct(node.getProduct());

            node.setKey(tempKey);
            node.setProduct(tempProduct);

        }

        private int size() {
            int total = 0;
            if (this.left != null) {
                total += this.left.size();
            }
            if (this.right != null) {
                total += this.right.size();
            }

            return 1 + total;
        }

        // @description: findMin() tìm đến node có giá trị key nhỏ nhất kể từ node hiện tại.
        private ProductNode findMin() {
            if (this.left != null) {
                return this.left.findMin();
            }

            return this;
        }

        // @description: findMax() tìm đến node có giá trị key lớn nhất kể từ node hiện tại.
        private ProductNode findMax() {
            if (this.right != null) {
                return this.right.findMax();
            }

            return this;
        }

        // @description: tính độ chênh lệch giữa cây con trái và phải của node hiện tại, để cân bằng cây
        private int getDistance() {
            if (isLeaf()) {
                return 0;
            }

            if (left != null && right != null) {
                return left.height() - right.height();
            }

            if (left != null) {
                return left.height();
            }

            return -right.height();
        }

        // @description: tính độ cao của cây kể từ node hiện tại
        private int height() {
            if (isLeaf()) {
                return 1;
            }
            if (this.left != null && this.right != null) {
                return 1 + Math.max(left.height(), right.height());
            }
            if (left != null) {
                return 1 + left.height();
            }
            return 1 + right.height();
        }
    }

    private ProductNode root = null;

    /* @description:
        - Hàm khởi tạo
     */
    public ProductManagerTree() {
    }

    /* @description:
        - Kiểm tra xem cây có rỗng không
     */
    public boolean isEmpty() {
        return root == null;
    }

    /* @description:
        - Trả ra root của cây, root của cây có thể là null nếu chưa có phần tử nào trong cây
     */
    public ProductNode getRoot() {
        return root;
    }

    /* @description:
        - Lấy ra node chứa key(tên) theo yêu cầu, trả ra null nếu không tìm thấy.
     */
    public ProductNode get(String key) {
        ProductNode curNode = getRoot();

        while (curNode != null) {
            if (curNode.getKey().equals(key)) {
                return curNode;
            } else if (curNode.getKey().compareTo(key) < 0) {
                curNode = curNode.right;
            } else if (curNode.getKey().compareTo(key) > 0) {
                curNode = curNode.left;
            }
        }

        return null;
    }

    private Product get(int i) {
        if (i < 0 || i >= size()) {
            return null;
        }

        if (isEmpty()) {
            return null;
        }

        return get(i, root).getProduct();
    }

    private ProductNode get(int i, ProductNode curNode) {
        LinkedList<ProductNode> list = new LinkedList<>();
        getFirstINode(i + 1, curNode, list);

        return list.get(i);
    }

    /* @description:
        - Trả ra giá trị nhỏ nhất hay sản phẩm có tên mang giá trị nhỏ nhất khi so sánh trong cây (đầu dãy in-order)
     */
    public Product findMin() {
        if (isEmpty()) {
            return null;
        }

        return root.findMin().getProduct();
    }

    /* @description:
        - Trả ra giá trị lớn nhất hay sản phẩm có tên mang giá trị lớn nhất khi so sánh trong cây (cuối dãy in-order)
     */
    public Product findMax() {
        if (isEmpty()) {
            return null;
        }

        return root.findMax().getProduct();
    }

    /* @description:
        - Trả ra lượng sản phẩm có trong cây.
     */
    public int size() {
        if (isEmpty()) {
            return 0;
        }

        return root.size();
    }

    /* @description:
        - Đây là hàm quan trọng cần được thực hiện trước mỗi lần add(...) vì nếu trong cây đã có node chứa key đó thì việc thêm vào một giá trị tương tự là không cần thiết.
        - Nếu muốn sửa giá trị, sẽ được thực hiện trực tiếp bởi người bán (Seller) chứ không phải trong này.
     */
    public boolean isContain(String key) {
        return isContain(root, key);
    }

    private boolean isContain(ProductNode node, String key) {
        if (isEmpty()) {
            return false;
        }

        if (node.getKey().compareTo(key) == 0) {
            return true;
        }

        if (node.getKey().compareTo(key) > 0) {
            if (node.getLeft() != null) {
                return isContain(node.getLeft(), key);
            }
            return false;
        }

        if (node.getRight() != null) {
            return isContain(node.getRight(), key);
        }
        return false;
    }

    /* @description:
        - Đầu vào: key là tên sản phẩm, product là đối tượng sản phẩm mang tên đó được thêm vào.

        - Hàm nhận thêm giá trị vào cây
        + Nếu cây chưa có root thì hàm sẽ tạo và thêm root
        + Nếu cây chưa có node mang key đó thì sẽ tạo node đó và thêm vào cây, cân bằng cây.
        + Nếu đã có node mang key đó, không thêm vào.

        - Chú ý: cần kiểm tra xem đã có sản phẩm trong cây trước khi thêm vào.
     */
    public void add(String key, Product data) {
        ProductNode node = new ProductNode(key, data, null, null, null);

        if (root == null) {
            root = node;
        } else {
            ProductNode addedNode = add(root, node);

            // addedNode ở đây là node lá (nếu là node được thêm) hoặc node cuối cùng mà hàm add(...) chạy đến.
            // Nếu là node cuối cùng, tức là không có gì thay đổi, hàm checkDown(...) ở dưới sẽ thực hiện nhưng không làm thay đổi gì.
            // Nếu là node mới, hàm checkDown() sẽ kiểm tra xem có sự thay đổi nào không và cân bằng lại nếu cần.
            checkDown(addedNode);
        }
    }

    /* @description:
        - Thực hiện kiểm tra key của node được thêm vào với node hiện tại.
        - Theo tính chất của cây BST, node nếu được thêm vào sẽ là con trái hoặc phải của node hiện tại.
        - Hàm trả về node hiện tại nếu node mới không được thêm vào (đã có).
        - Hàm trả về node được thêm vào trong các trường hợp còn lại.

        - Nếu khóa node được thêm vào nhỏ hơn node hiện tại và node hiện tại chưa có con trái, node mới trở thành con trái của node hiện tại.
        - Nếu không, tiếp tục kiểm tra node được thêm với con trái của node hiện tại.
        - Tương tự với con phải khi khóa của node cần thêm có giá trị lớn hơn.
     */
    private ProductNode add(ProductNode parent, ProductNode productNode) {

        if (productNode.key.compareTo(parent.key) < 0) {
            if (parent.left == null) {
                parent.setLeft(productNode);
                productNode.setParent(parent);

                return parent.left;
            } else {
                return add(parent.left, productNode);
            }
        } else if (productNode.key.compareTo(parent.key) > 0) {
            if (parent.right == null) {
                parent.right = productNode;
                productNode.parent = parent;

                return parent.right;
            } else {
                return add(parent.right, productNode);
            }
        }

        //Ngoài 2 trường hợp lớn trên, cây sẽ tự hiểu là đã có node cần thêm và trả về node hiện tại (node parent).
        return parent;
    }

    /* @description:
        - Hàm xóa node theo giá trị key được yêu cầu
        - Kết quả node từ remove(key, root) sẽ được đưa vào checkDown() để duyệt lại cân bằng cây.
     */
    public void remove(String key) {
        if (!isEmpty()) {

            ProductNode node = remove(key, root);
            if (node == null) {
                return;
            }

            checkDown(node);
        }
    }

    /* @description:
        - Hàm trả về:
         + Node cha của node hiện tại nếu node hiện tại là node cần bỏ và không có con.
         + Node con của node bị loại bỏ nếu có 1 hoặc 2 con.
         + Null nếu không tìm thấy

        - Nếu node hiện tại có 1 hoặc 2 con, đổi giá trị của node với node min của con phải. Sau đó, duyệt trên cây, cho đến khi tìm đến node min lúc đầu và xóa.
        - Trong trường hợp trên, ta đưa từ trường hợp node có con về node lá.
     */
    private ProductNode remove(String key, ProductNode curNode) {

        if (curNode.key.compareTo(key) == 0) {
            //3 cases: 0 child, 1 child, 2 children
            //if having 1 or 2, swap with its child or min of its right child

            if (curNode.isLeaf()) {
                //0 child
                ProductNode p = curNode.parent;
                if (p != null) {
                    if (p.left == curNode) {
                        p.left = null;
                    } else {
                        p.right = null;
                    }
                    curNode.parent = null;

                    if (root == curNode) {
                        root = null;
                    }
                    return p;
                }
            } else if (curNode.left != null && curNode.right != null) {
                //2 children
                //Swap and turn to 0 child situation
                ProductNode min = curNode.right.findMin();
                curNode.swap(min);
                remove(key, curNode.right);
            } else {
                //1 child
                if (curNode.left != null) {
                    curNode.swap(curNode.left);
                    return remove(key, curNode.left);
                } else {
                    curNode.swap(curNode.right);
                    return remove(key, curNode.right);
                }
            }

        } else if (curNode.key.compareTo(key) > 0 && curNode.left != null) {
            return remove(key, curNode.left);
        } else if (curNode.key.compareTo(key) < 0 && curNode.right != null) {
            return remove(key, curNode.right);
        }

        return null;
    }

    /* @description:
        - Trả ra list là kết quả của quá trình duyệt cây theo kiểu in-order, list được sắp xếp tăng dần theo key (tên - name)
     */
    public List<Product> getInOrderList() {
        return getInOrderList(getRoot(), new ArrayList<>());
    }

    private List<Product> getInOrderList(ProductNode node, List<Product> productList) {
        if (node == null) {
            return productList;
        }

        if (node.getLeft() != null) {
            getInOrderList(node.getLeft(), productList);
        }

        productList.add(node.product);

        if (node.getRight() != null) {
            getInOrderList(node.getRight(), productList);
        }

        return productList;
    }

    /* @description:
        - Thực hiện in ra kết quả là dãy sản phẩm được xếp theo chiều ngang.
     */
    public void displayInOrderList() {
        List<Product> productList = getInOrderList();

        System.out.println(productList);
    }

    /* @description:
        - Hàm nhận đầu vào là list cần sort, desc cho biết hàm sẽ sort giảm dần hay không
        - Hàm sử dụng một thuật toán sort ổn định
     */
    public static void sortPrice(List<Product> list, boolean desc) {

        sort(list, 0, list.size() - 1, desc);
    }

    public static List<Product> sortPriceList(List<Product> list, boolean desc) {

        sort(list, 0, list.size() - 1, desc);
        return list;
    }

    private static void sort(List<Product> list, int low, int high, boolean desc) {

        if (low < high) {
            int mid = (low + high) / 2;

            sort(list, low, mid, desc);
            sort(list, mid + 1, high, desc);

            merge(list, low, mid, high, desc);
        }
    }

    private static void merge(List<Product> list, int low, int mid, int high, boolean desc) {
        int n = high - low + 1;
        Product[] b = new Product[n];
        int left = low, right = mid + 1, bIdx = 0;

        while (left <= mid && right <= high) {
            if (!desc) {
                if (list.get(left).getGia() <= list.get(right).getGia()) {
                    b[bIdx++] = list.get(left++);
                } else {
                    b[bIdx++] = list.get(right++);
                }
            } else {
                if (list.get(left).getGia() >= list.get(right).getGia()) {
                    b[bIdx++] = list.get(left++);
                } else {
                    b[bIdx++] = list.get(right++);
                }
            }
        }

        while (left <= mid) {
            b[bIdx++] = list.get(left++);
        }
        while (right <= high) {
            b[bIdx++] = list.get(right++);
        }

        for (int i = 0; i < n; i++) {
            list.set(low + i, b[i]);
        }
    }

    /* @description:
        - Hàm nhận đầu vào là từ khóa tên cần tìm
        - Thực hiện tìm từ khóa hoặc tên chứa từ khóa trên tập list trả ra ở getInOrderList()
     */
    public List<Product> search(String name) {
        return new ArrayList<>(searchElementName(name));
    }

    public List<Product> searchElementName(String name) {
        List<Product> list = getInOrderList();
        List<Product> productList = new ArrayList<>();

        for (Product product : list) {
            if (product.getTenMay().toLowerCase().contains(name.toLowerCase())) {
                productList.add(product);
            }
        }

        return productList;
    }

    public List<Product> searchMaMay(String name) {
        List<Product> list = getInOrderList();
        List<Product> productList = new ArrayList<>();

        for (Product product : list) {
            if (product.getMaMay().toLowerCase().contains(name.toLowerCase())) {
                productList.add(product);
            }
        }

        return productList;
    }

    public List<Product> searchSoLuong(String name) {
        List<Product> list = getInOrderList();
        List<Product> productList = new ArrayList<>();

        for (Product product : list) {
            if (Integer.toString(product.getSoLuong()).toLowerCase().contains(name.toLowerCase())) {
                productList.add(product);
            }
        }

        return productList;
    }

    public List<Product> searchRam(String name) {
        List<Product> list = getInOrderList();
        List<Product> productList = new ArrayList<>();

        for (Product product : list) {
            if (product.getRam().toLowerCase().contains(name.toLowerCase())) {
                productList.add(product);
            }
        }

        return productList;
    }

    public List<Product> searchCPU(String name) {
        List<Product> list = getInOrderList();
        List<Product> productList = new ArrayList<>();

        for (Product product : list) {
            if (product.getTenCpu().toLowerCase().contains(name.toLowerCase())) {
                productList.add(product);
            }
        }

        return productList;
    }

    public List<Product> searchRom(String name) {
        List<Product> list = getInOrderList();
        List<Product> productList = new ArrayList<>();

        for (Product product : list) {
            if (product.getRom().toLowerCase().contains(name.toLowerCase())) {
                productList.add(product);
            }
        }

        return productList;
    }

    public List<Product> searchGPU(String name) {
        List<Product> list = getInOrderList();
        List<Product> productList = new ArrayList<>();

        for (Product product : list) {
            if (product.getCardManHinh().toLowerCase().contains(name.toLowerCase())) {
                productList.add(product);
            }
        }

        return productList;
    }

    /* @description:
        - Hàm nhận đầu vào là node cuối cùng của một quá trình chỉnh sửa cây (thêm hoặc bớt) (khác null)
        - Hàm sẽ duyệt từ node hiện tại, kiểm tra sự cân bằng của từng cây con.
        - Hàm sẽ duyệt lên từ node hiện tại.

        - Có cụ thể 4 trường hợp cần phải chỉnh cây - bằng thao tác xoay trái (rotate left) hoặc xoay phải (rotate right)
        - Nếu node hiện tại có độ lệch bằng +2, và node con trái có độ lệch bằng 1 hoặc 0 -> thực hiện xoay phải node hiện tại.
        - Nếu node hiện tại có độ lệch bằng +2, và node con trái có độ lệch bằng -1 -> thực hiện xoay trái node con, đưa về trường hợp trên.
        - Nếu node hiện tại có độ lệch bằng -2, và node con phải có độ lệch bằng -1 hoặc 0 -> thực hiện xoay trái node hiện tại.
        - Nếu node hiện tại có độ lệch bằng -2, và node con phải có độ lệch bằng 1 -> thực hiện xoay phải node con, đưa về trường hợp ngay trên.
     */
    private void checkDown(ProductNode node) {

        while (node != null) {
            int curDistance = node.getDistance();
            if (curDistance == 2) {
                ProductNode left = node.left;
                int leftDistance = left.getDistance();
                if (leftDistance == -1) {
                    rotateLeft(left);
                }
                rotateRight(node);

            } else if (curDistance == -2) {
                ProductNode right = node.right;
                int rightDistance = right.getDistance();
                if (rightDistance == 1) {
                    rotateRight(right);
                }
                rotateLeft(node);
            }

            if (node.hasParent()) {
                node = node.getParent();
            } else {
                break;
            }
        }
    }

    /* @description:
        - Hàm được thực hiện sau mỗi lần xoay cây, vì quá trình xoay cây có thể bao gồm cả root.
     */
    private void updateRoot() {
        if (isEmpty()) {
            return;
        }

        while (root.hasParent()) {
            root = root.getParent();
        }
    }

    /* @description:
        - Có tất cả tối đa 4 node tham gia vào quá trình xoay:
         + Node hiện tại.
         + Node cha node hiện tại (nếu có).
         + Node phải hoặc node trái của node hiện tại (tùy vào xoay trái hay xoay phải) (*).
         + Node con trái của node phải hay node con phải của node trái. (**).

        - Quá trình bao gồm chỉnh lại mối quan hệ giữa các node.
         + Node hiện tại trở thành con trái hoặc phải của node (*).
         + Node (*) trở thành con của node cha và là cha của node hiện tại.
         + Node (**) trở thành con của node hiện tại.
     */
    private void rotateLeft(ProductNode node) {
        ProductNode parent = node.getParent();

        ProductNode right = node.getRight();
        ProductNode leftOfRight = right.left;

        right.setParent(parent);
        if (parent != null) {
            if (node == parent.left) {
                parent.setLeft(right);
            } else {
                parent.setRight(right);
            }
        }

        node.setParent(right);
        right.setLeft(node);

        node.setRight(leftOfRight);
        if (leftOfRight != null) {
            leftOfRight.setParent(node);

        }

        updateRoot();
    }

    private void rotateRight(ProductNode node) {
        ProductNode parent = node.getParent();

        ProductNode left = node.getLeft();
        ProductNode rightOfLeft = left.right;

        left.setParent(parent);
        if (parent != null) {
            if (node == parent.left) {
                parent.setLeft(left);
            } else {
                parent.setRight(left);
            }
        }

        node.setParent(left);
        left.setRight(node);

        node.setLeft(rightOfLeft);
        if (rightOfLeft != null) {
            rightOfLeft.setParent(node);
        }

        updateRoot();
    }

    /* @description:
        - Hàm duyệt theo kiểu in-order, lấy ra i số đầu tiên trong cây, cơ bản phục vụ cho hàm get(int idx)
        - Hàm trả về list các node.
     */
    private void getFirstINode(final int i, ProductNode curNode, List<ProductNode> list) {
        if (curNode == null) {
            return;
        }

        if (i == list.size() - 1) {
            return;
        }

        if (curNode.left != null) {
            getFirstINode(i, curNode.left, list);
        }

        if (i > list.size()) {
            //Fact: position of the curNode in list equals to list.size before add
            //Luôn đúng: vị trí của curNode luôn bằng với độ dài của list (list.size()) trước khi được thêm vào.
            list.add(curNode);
        }

        if (i > list.size() && curNode.right != null) {
            getFirstINode(i, curNode.right, list);
        }

    }
}
