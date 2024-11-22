package AVL;

import VTNgoc.Account;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AccountManagerTree {

    public static class AccountNode {

        String key;
        Account account;
        AccountNode parent;
        AccountNode left;
        AccountNode right;

        public AccountNode(String key, Account account, AccountNode parent, AccountNode left, AccountNode right) {
            this.key = key;
            this.account = account;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public String getKey() {
            return key;
        }

        public Account getAccount() {
            return account;
        }

        public AccountNode getParent() {
            return parent;
        }

        public AccountNode getLeft() {
            return left;
        }

        public AccountNode getRight() {
            return right;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public void setAccount(Account account) {
            this.account = account;
        }

        public void setParent(AccountNode parent) {
            this.parent = parent;
        }

        public void setLeft(AccountNode left) {
            this.left = left;
        }

        public void setRight(AccountNode right) {
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
            return account.toString();
        }

        private void swap(AccountNode node) {
            String tempKey = this.key;
            Account tempAccount = this.account;

            this.setKey(node.getKey());
            this.setAccount(node.getAccount());

            node.setKey(tempKey);
            node.setAccount(tempAccount);

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

        private AccountNode findMin() {
            if (this.left != null) {
                return this.left.findMin();
            }

            return this;
        }

        private AccountNode findMax() {
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

    private AccountNode root = null;

    public AccountManagerTree() {
    }

    /* @description:
        - Kiểm tra xem cây có rỗng không
     */
    public boolean isEmpty() {
        return root == null;
    }

    public AccountNode getRoot() {
        return root;
    }

    public AccountNode get(String key) {
        AccountNode curNode = getRoot();

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

    private Account get(int i) {
        if (i < 0 || i >= size()) {
            return null;
        }

        if (isEmpty()) {
            return null;
        }

        return get(i, root).getAccount();
    }

    private AccountNode get(int i, AccountNode curNode) {
        LinkedList<AccountNode> list = new LinkedList<>();
        getFirstINode(i + 1, curNode, list);

        return list.get(i);
    }

    public Account findMin() {
        if (isEmpty()) {
            return null;
        }

        return root.findMin().getAccount();
    }

    public Account findMax() {
        if (isEmpty()) {
            return null;
        }

        return root.findMax().getAccount();
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

    private boolean isContain(AccountNode node, String key) {
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

    public void add(String key, Account data) {
        AccountNode node = new AccountNode(key, data, null, null, null);

        if (root == null) {
            root = node;
        } else {
            AccountNode addedNode = add(root, node);

            checkDown(addedNode);
        }
    }

    private AccountNode add(AccountNode parent, AccountNode accountNode) {

        if (accountNode.key.compareTo(parent.key) < 0) {
            if (parent.left == null) {
                parent.setLeft(accountNode);
                accountNode.setParent(parent);

                return parent.left;
            } else {
                return add(parent.left, accountNode);
            }
        } else if (accountNode.key.compareTo(parent.key) > 0) {
            if (parent.right == null) {
                parent.right = accountNode;
                accountNode.parent = parent;

                return parent.right;
            } else {
                return add(parent.right, accountNode);
            }
        }

        return parent;
    }

    public void remove(String key) {
        if (!isEmpty()) {

            AccountNode node = remove(key, root);
            if (node == null) {
                return;
            }

            checkDown(node);
        }
    }

    private AccountNode remove(String key, AccountNode curNode) {

        if (curNode.key.compareTo(key) == 0) {

            if (curNode.isLeaf()) {
                //0 child
                AccountNode p = curNode.parent;
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

                AccountNode min = curNode.right.findMin();
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

    public List<Account> getInOrderList() {
        return getInOrderList(getRoot(), new ArrayList<>());
    }

    private List<Account> getInOrderList(AccountNode node, List<Account> accountList) {
        if (node == null) {
            return accountList;
        }

        if (node.getLeft() != null) {
            getInOrderList(node.getLeft(), accountList);
        }

        accountList.add(node.account);

        if (node.getRight() != null) {
            getInOrderList(node.getRight(), accountList);
        }

        return accountList;
    }

    public void displayInOrderList() {
        List<Account> accountList = getInOrderList();

        System.out.println(accountList);
    }

    public List<Account> search(String name) {
        return new ArrayList<>(searchElementName(name));
    }

    public List<Account> searchElementName(String name) {
        List<Account> list = getInOrderList();
        List<Account> productList = new ArrayList<>();

        for (Account account : list) {
            if (account.getUser().toLowerCase().contains(name.toLowerCase())) {
                productList.add(account);
            }
        }

        return productList;
    }

    public List<Account> searchUserName(String name) {
        List<Account> list = getInOrderList();
        List<Account> productList = new ArrayList<>();

        for (Account account : list) {
            if (account.getFullName().toLowerCase().contains(name.toLowerCase())) {
                productList.add(account);
            }
        }

        return productList;

    }

    public List<Account> searchRole(String name) {
        List<Account> list = getInOrderList();
        List<Account> productList = new ArrayList<>();

        for (Account account : list) {
            if (account.getRole().toLowerCase().contains(name.toLowerCase())) {
                productList.add(account);
            }
        }

        return productList;

    }

    public List<Account> searchPhone(String name) {
        List<Account> list = getInOrderList();
        List<Account> productList = new ArrayList<>();

        for (Account account : list) {
            if (account.getPhone().toLowerCase().contains(name.toLowerCase())) {
                productList.add(account);
            }
        }

        return productList;

    }

    private void checkDown(AccountNode node) {

        while (node != null) {
            int curDistance = node.getDistance();
            if (curDistance == 2) {
                AccountNode left = node.left;
                int leftDistance = left.getDistance();
                if (leftDistance == -1) {
                    rotateLeft(left);
                }
                rotateRight(node);

            } else if (curDistance == -2) {
                AccountNode right = node.right;
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

    private void rotateLeft(AccountNode node) {
        AccountNode parent = node.getParent();

        AccountNode right = node.getRight();
        AccountNode leftOfRight = right.left;

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

    private void rotateRight(AccountNode node) {
        AccountNode parent = node.getParent();

        AccountNode left = node.getLeft();
        AccountNode rightOfLeft = left.right;

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

    private void getFirstINode(final int i, AccountNode curNode, List<AccountNode> list) {
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
