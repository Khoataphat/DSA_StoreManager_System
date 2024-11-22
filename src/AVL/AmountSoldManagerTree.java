package AVL;

import VTNgoc.AmountSold;
import java.util.*;


public class AmountSoldManagerTree {

    public static class AmountSoldNode {

        String key;
        AmountSold AmountSold;
        AmountSoldNode parent;
        AmountSoldNode left;
        AmountSoldNode right;

        public AmountSoldNode(String key, AmountSold AmountSold, AmountSoldNode parent, AmountSoldNode left, AmountSoldNode right) {
            this.key = key;
            this.AmountSold = AmountSold;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public String getKey() {
            return key;
        }

        public AmountSold getAmountSold() {
            return AmountSold;
        }

        public AmountSoldNode getParent() {
            return parent;
        }

        public AmountSoldNode getLeft() {
            return left;
        }

        public AmountSoldNode getRight() {
            return right;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setAmountSold(AmountSold AmountSold) {
            this.AmountSold = AmountSold;
        }

        public void setParent(AmountSoldNode parent) {
            this.parent = parent;
        }

        public void setLeft(AmountSoldNode left) {
            this.left = left;
        }

        public void setRight(AmountSoldNode right) {
            this.right = right;
        }

        private boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        private boolean hasParent() {
            return this.parent != null;
        }

        @Override
        public String toString() {
            return AmountSold.toString();
        }

        private void swap(AmountSoldNode node) {
            String tempKey = this.key;
            AmountSold tempAmountSold = this.AmountSold;

            this.setKey(node.getKey());
            this.setAmountSold(node.getAmountSold());

            node.setKey(tempKey);
            node.setAmountSold(tempAmountSold);

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

        private AmountSoldNode findMin() {
            if (this.left != null) {
                return this.left.findMin();
            }

            return this;
        }

        private AmountSoldNode findMax() {
            if (this.right != null) {
                return this.right.findMax();
            }

            return this;
        }

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

    private AmountSoldNode root = null;

    public AmountSoldManagerTree() {
    }

    public boolean isEmpty() {
        return root == null;
    }

    public AmountSoldNode getRoot() {
        return root;
    }

    public AmountSoldNode get(String key) {
        AmountSoldNode curNode = getRoot();

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

    private AmountSold get(int i) {
        if (i < 0 || i >= size()) {
            return null;
        }

        if (isEmpty()) {
            return null;
        }

        return get(i, root).getAmountSold();
    }

    private AmountSoldNode get(int i, AmountSoldNode curNode) {
        LinkedList<AmountSoldNode> list = new LinkedList<>();
        getFirstINode(i + 1, curNode, list);

        return list.get(i);
    }

    public AmountSold findMin() {
        if (isEmpty()) {
            return null;
        }

        return root.findMin().getAmountSold();
    }

    public AmountSold findMax() {
        if (isEmpty()) {
            return null;
        }

        return root.findMax().getAmountSold();
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        }

        return root.size();
    }

    public boolean isContain(String key) {
        return isContain(root, key);
    }

    private boolean isContain(AmountSoldNode node, String key) {
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

    public void add(String key, AmountSold data) {
        AmountSoldNode node = new AmountSoldNode(key, data, null, null, null);

        if (root == null) {
            root = node;
        } else {
            AmountSoldNode addedNode = add(root, node);

            // addedNode ở đây là node lá (nếu là node được thêm) hoặc node cuối cùng mà hàm add(...) chạy đến.
            // Nếu là node cuối cùng, tức là không có gì thay đổi, hàm checkDown(...) ở dưới sẽ thực hiện nhưng không làm thay đổi gì.
            // Nếu là node mới, hàm checkDown() sẽ kiểm tra xem có sự thay đổi nào không và cân bằng lại nếu cần.
            checkDown(addedNode);
        }
    }

    private AmountSoldNode add(AmountSoldNode parent, AmountSoldNode AmountSoldNode) {

        if (AmountSoldNode.key.compareTo(parent.key) < 0) {
            if (parent.left == null) {
                parent.setLeft(AmountSoldNode);
                AmountSoldNode.setParent(parent);

                return parent.left;
            } else {
                return add(parent.left, AmountSoldNode);
            }
        } else if (AmountSoldNode.key.compareTo(parent.key) > 0) {
            if (parent.right == null) {
                parent.right = AmountSoldNode;
                AmountSoldNode.parent = parent;

                return parent.right;
            } else {
                return add(parent.right, AmountSoldNode);
            }
        }

        return parent;
    }

    public void remove(String key) {
        if (!isEmpty()) {

            AmountSoldNode node = remove(key, root);
            if (node == null) {
                return;
            }

            checkDown(node);
        }
    }

    private AmountSoldNode remove(String key, AmountSoldNode curNode) {

        if (curNode.key.compareTo(key) == 0) {

            if (curNode.isLeaf()) {
                AmountSoldNode p = curNode.parent;
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

                AmountSoldNode min = curNode.right.findMin();
                curNode.swap(min);
                remove(key, curNode.right);
            } else {

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

    public List<AmountSold> getInOrderList() {
        return getInOrderList(getRoot(), new ArrayList<>());
    }

    private List<AmountSold> getInOrderList(AmountSoldNode node, List<AmountSold> AmountSoldList) {
        if (node == null) {
            return AmountSoldList;
        }

        if (node.getLeft() != null) {
            getInOrderList(node.getLeft(), AmountSoldList);
        }

        AmountSoldList.add(node.AmountSold);

        if (node.getRight() != null) {
            getInOrderList(node.getRight(), AmountSoldList);
        }

        return AmountSoldList;
    }

    public void displayInOrderList() {
        List<AmountSold> AmountSoldList = getInOrderList();

        System.out.println(AmountSoldList);
    }

    public static void sortAmountSold(List<AmountSold> list, boolean desc) {

        radixSort(list, desc);
    }

    public static List<AmountSold> sortAmountSoldList(List<AmountSold> list, boolean desc) {

        radixSort(list, desc);
        return list;
    }

    private static void sort(List<AmountSold> list, int low, int high, boolean desc) {

        if (low < high) {
            int mid = (low + high) / 2;

            sort(list, low, mid, desc);
            sort(list, mid + 1, high, desc);

            merge(list, low, mid, high, desc);
        }
    }

    private static void merge(List<AmountSold> list, int low, int mid, int high, boolean desc) {
        int n = high - low + 1;
        AmountSold[] b = new AmountSold[n];
        int left = low, right = mid + 1, bIdx = 0;

        while (left <= mid && right <= high) {
            if (!desc) {
                if (list.get(left).getAmountSold() <= list.get(right).getAmountSold()) {
                    b[bIdx++] = list.get(left++);
                } else {
                    b[bIdx++] = list.get(right++);
                }
            } else {
                if (list.get(left).getAmountSold() >= list.get(right).getAmountSold()) {
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

    public List<AmountSold> search(String name) {
        return new ArrayList<>(searchElementName(name));
    }

    public List<AmountSold> searchElementName(String name) {
        List<AmountSold> list = getInOrderList();
        List<AmountSold> AmountSoldList = new ArrayList<>();

        for (AmountSold product : list) {
            if (product.getMaMay().toLowerCase().contains(name.toLowerCase())) {
                AmountSoldList.add(product);
            }
        }

        return AmountSoldList;
    }

    private void checkDown(AmountSoldNode node) {

        while (node != null) {
            int curDistance = node.getDistance();
            if (curDistance == 2) {
                AmountSoldNode left = node.left;
                int leftDistance = left.getDistance();
                if (leftDistance == -1) {
                    rotateLeft(left);
                }
                rotateRight(node);

            } else if (curDistance == -2) {
                AmountSoldNode right = node.right;
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

    private void updateRoot() {
        if (isEmpty()) {
            return;
        }

        while (root.hasParent()) {
            root = root.getParent();
        }
    }

    private void rotateLeft(AmountSoldNode node) {
        AmountSoldNode parent = node.getParent();

        AmountSoldNode right = node.getRight();
        AmountSoldNode leftOfRight = right.left;

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

    private void rotateRight(AmountSoldNode node) {
        AmountSoldNode parent = node.getParent();

        AmountSoldNode left = node.getLeft();
        AmountSoldNode rightOfLeft = left.right;

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

    private void getFirstINode(final int i, AmountSoldNode curNode, List<AmountSoldNode> list) {
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

            list.add(curNode);
        }

        if (i > list.size() && curNode.right != null) {
            getFirstINode(i, curNode.right, list);
        }

    }

    public static void radixSort(List<AmountSold> list, boolean desc) {
        int max = maxSold(list);

        for (int exp = 1; max >= exp; exp *= 10) { // k = log10(max)
            countingSort(list, exp, desc);
        }
    }

    private static void countingSort(List<AmountSold> list, int exp, boolean desc) {
        AmountSold[] outputArray = new AmountSold[list.size()];
        int[] countArray = new int[10];
        int quantity;

        for (AmountSold amountSold : list) { //(1)
            quantity = amountSold.getAmountSold();
            countArray[(quantity / exp) % 10]++;
        }

        if (desc) { //sắp xếp theo thứ tự giảm dần
            for (int i = countArray.length - 1; i > 0; i--) { //(2.1)
                countArray[i - 1] += countArray[i];
            }
        } else { //sắp xếp theo thứ tự tăng dần
            for (int i = 1; i < countArray.length; i++) { //(2.2)
                countArray[i] += countArray[i - 1];
            }
        }

        for (int i = list.size() - 1; i >= 0; i--) { //(3)
            quantity = list.get(i).getAmountSold();
            outputArray[--countArray[(quantity / exp) % 10]] = list.get(i);
        }

        for (int i = 0; i < list.size(); i++) { //(4)
            list.set(i, outputArray[i]);
        }
    }

    private static int maxSold(List<AmountSold> list) {
        int maxSold = 0;

        for (AmountSold amountSold : list) {
            int quantity = amountSold.getAmountSold();
            if (maxSold < quantity) {
                maxSold = quantity;
            }
        }

        return maxSold;
    }
}
