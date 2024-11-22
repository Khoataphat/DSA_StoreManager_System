package AVL;

import VTNgoc.Phieu;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PhieuMuaManagerTree {

    public static class PhieuNode {

        String key;
        Phieu phieu;
        PhieuNode parent;
        PhieuNode left;
        PhieuNode right;

        public PhieuNode(String key, Phieu pheu, PhieuNode parent, PhieuNode left, PhieuNode right) {
            this.key = key;
            this.phieu = pheu;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public String getKey() {
            return key;
        }

        public Phieu getPhieu() {
            return phieu;
        }

        public PhieuNode getParent() {
            return parent;
        }

        public PhieuNode getLeft() {
            return left;
        }

        public PhieuNode getRight() {
            return right;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setPhieu(Phieu phieu) {
            this.phieu = phieu;
        }

        public void setParent(PhieuNode parent) {
            this.parent = parent;
        }

        public void setLeft(PhieuNode left) {
            this.left = left;
        }

        public void setRight(PhieuNode right) {
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
            return phieu.toString();
        }

        private void swap(PhieuNode node) {
            String tempKey = this.key;
            Phieu tempPhieu = this.phieu;

            this.setKey(node.getKey());
            this.setPhieu(node.getPhieu());

            node.setKey(tempKey);
            node.setPhieu(tempPhieu);

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

        private PhieuNode findMin() {
            if (this.left != null) {
                return this.left.findMin();
            }

            return this;
        }

        private PhieuNode findMax() {
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

    private PhieuNode root = null;

    public PhieuMuaManagerTree() {
    }

    public boolean isEmpty() {
        return root == null;
    }

    public PhieuNode getRoot() {
        return root;
    }

    public PhieuNode get(String key) {
        PhieuNode curNode = getRoot();

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

    private Phieu get(int i) {
        if (i < 0 || i >= size()) {
            return null;
        }

        if (isEmpty()) {
            return null;
        }

        return get(i, root).getPhieu();
    }

    private PhieuNode get(int i, PhieuNode curNode) {
        LinkedList<PhieuNode> list = new LinkedList<>();
        getFirstINode(i + 1, curNode, list);

        return list.get(i);
    }

    public Phieu findMin() {
        if (isEmpty()) {
            return null;
        }

        return root.findMin().getPhieu();
    }

    public Phieu findMax() {
        if (isEmpty()) {
            return null;
        }

        return root.findMax().getPhieu();
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

    private boolean isContain(PhieuNode node, String key) {
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

    public void add(String key, Phieu data) {
        PhieuNode node = new PhieuNode(key, data, null, null, null);

        if (root == null) {
            root = node;
        } else {
            PhieuNode addedNode = add(root, node);

            checkDown(addedNode);
        }
    }

    private PhieuNode add(PhieuNode parent, PhieuNode PhieuNode) {

        if (PhieuNode.key.compareTo(parent.key) < 0) {
            if (parent.left == null) {
                parent.setLeft(PhieuNode);
                PhieuNode.setParent(parent);

                return parent.left;
            } else {
                return add(parent.left, PhieuNode);
            }
        } else if (PhieuNode.key.compareTo(parent.key) >= 0) {
            if (parent.right == null) {
                parent.right = PhieuNode;
                PhieuNode.parent = parent;

                return parent.right;
            } else {
                return add(parent.right, PhieuNode);
            }
        }

        return parent;
    }

    public void remove(String key) {
        if (!isEmpty()) {

            PhieuNode node = remove(key, root);
            if (node == null) {
                return;
            }

            checkDown(node);
        }
    }

    private PhieuNode remove(String key, PhieuNode curNode) {

        if (curNode.key.compareTo(key) == 0) {

            if (curNode.isLeaf()) {
                //0 child
                PhieuNode p = curNode.parent;
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

                PhieuNode min = curNode.right.findMin();
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

    public List<Phieu> getInOrderList() {
        return getInOrderList(getRoot(), new ArrayList<>());
    }

    private List<Phieu> getInOrderList(PhieuNode node, List<Phieu> accountList) {
        if (node == null) {
            return accountList;
        }

        if (node.getLeft() != null) {
            getInOrderList(node.getLeft(), accountList);
        }

        accountList.add(node.phieu);

        if (node.getRight() != null) {
            getInOrderList(node.getRight(), accountList);
        }

        return accountList;
    }

    public void displayInOrderList() {
        List<Phieu> accountList = getInOrderList();

        System.out.println(accountList);
    }

    public List<Phieu> search(String name) {
        return new ArrayList<>(searchElementName(name));
    }

    private List<Phieu> searchElementName(String name) {
        List<Phieu> list = getInOrderList();
        List<Phieu> PhieuList = new ArrayList<>();

        for (Phieu phieu : list) {
            if (phieu.getUsername().toLowerCase().contains(name.toLowerCase())) {
                PhieuList.add(phieu);
            }
        }

        return PhieuList;
    }

    private List<Phieu> searchName(String name) {
        List<Phieu> list = getInOrderList();
        List<Phieu> PhieuList = new ArrayList<>();

        for (Phieu phieu : list) {
            if (phieu.getChitieuphieu().getTenMay().toLowerCase().contains(name.toLowerCase())) {
                PhieuList.add(phieu);
            }
        }

        return PhieuList;
    }

    private void checkDown(PhieuNode node) {

        while (node != null) {
            int curDistance = node.getDistance();
            if (curDistance == 2) {
                PhieuNode left = node.left;
                int leftDistance = left.getDistance();
                if (leftDistance == -1) {
                    rotateLeft(left);
                }
                rotateRight(node);

            } else if (curDistance == -2) {
                PhieuNode right = node.right;
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

    private void rotateLeft(PhieuNode node) {
        PhieuNode parent = node.getParent();

        PhieuNode right = node.getRight();
        PhieuNode leftOfRight = right.left;

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

    private void rotateRight(PhieuNode node) {
        PhieuNode parent = node.getParent();

        PhieuNode left = node.getLeft();
        PhieuNode rightOfLeft = left.right;

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

    private void getFirstINode(final int i, PhieuNode curNode, List<PhieuNode> list) {
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
}
