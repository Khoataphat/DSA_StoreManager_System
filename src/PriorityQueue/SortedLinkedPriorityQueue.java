/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PriorityQueue;

import VTNgoc.Product;

public class SortedLinkedPriorityQueue<K extends Comparable, E> {

    private NodeProduct<K, E> head;
    private NodeProduct<K, E> tail;
    private int n = 0;


    public SortedLinkedPriorityQueue() {
    }

    public NodeProduct<K, E> getHead() {
        return head;
    }

    public int size() {
        return n;
    }


    public boolean isEmpty() {
        return n == 0;
    }


    public void insert(K k, E e) {
        NodeProduct<K, E> node = new NodeProduct<>(k, e);
        insert(node);
    }

    // thêm 1 node vào priority Queue
    private void insert(NodeProduct<K, E> entry) {
        if (isEmpty()) {
            head = entry;
            tail = head;
            n++;
            return;
        } else {
            NodeProduct<K, E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = entry;
            tail = entry;
        }
        //check xem phần tử vừa thêm với phần tử trước nó nếu key của nó lớn hơn thì đổi chỗ
        n++;
        boolean swapped;
        NodeProduct<K, E> current;
        if (head == null)
            return;
        do {
            swapped = false;
            current = head;

            while (current.next != null) {
                if (current.getKey().compareTo(current.next.getKey()) < 0) {
                    swap(current, current.next);
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }
    
     public static void add(Product product, int a, SortedLinkedPriorityQueue<Integer,Product> productSortedLinkedPriorityQueue){
        if(product.getSoLuong() > a){
            productSortedLinkedPriorityQueue.insert(product.getSoLuong(),product);
        }
    }
     
     
    private void swap(NodeProduct<K, E> ptr1, NodeProduct<K, E> ptr2) {
        K tmp1 = ptr2.getKey();
        E tmp2 = ptr2.getValue();
        ptr2.key = ptr1.getKey();
        ptr2.element = ptr1.getValue();
        ptr1.key = tmp1;
        ptr1.element = tmp2;
    }
public void clear(){
        int size = size();
        for (int i = 0; i < size; i++) {
            dequeue();
        }
    }
// xóa phần tử đầu của priority queue

    public NodeProduct<K, E> dequeue() {
        if (head == null) {
            return null;
        } else {
            NodeProduct<K,E> current = head;
            head = current.next;
            n--;
            return current;
        }

    }

    // trả về phần tử đầu tiên của re
    public NodeProduct<K, E> top() {
        return head;
    }

    @Override
    public String toString() {
        StringBuilder myStr = new StringBuilder();
        NodeProduct<K, E> tmp = head;
        while (tmp != null) {
            myStr.append(tmp);
            tmp = tmp.next;
            myStr.append("\n");
        }
        return myStr.toString();
    }

    public class NodeProduct<K, E> {
         public K key;
         public E element;
         public NodeProduct<K, E> next;

        public NodeProduct(K k, E e) {
            this.key = k;
            this.element = e;
        }


        public K getKey() {
            return key;
        }


        public E getValue() {
            return element;
        }

        public String toString() {
            return "[" + getKey() + ',' + getValue() + "]";
        }
    }
}