package com.chaturv.datastructures.trees;


import com.chaturv.datastructures.Node;

import java.util.LinkedList;
import java.util.ListIterator;

public class FindMyBoss1 {

    Boolean match = false;

    public void preOrder(Node ceo, Node emp, LinkedList<Node> queue) {
        while (!match) {
            if (ceo == null) {
                return;
            }

            queue.offer(ceo);

            match = isEqual(ceo, emp);
            if (match) {
                return;
            }

            preOrder(ceo.left, emp, queue);
            preOrder(ceo.right, emp, queue);

            if (!match) {
                queue.removeLast();
                return;
            }
        }
    }

    public boolean isEqual(Node node, Node emp) {
        return node.data == emp.data;
    }

    public void print(LinkedList<Node> lst) {
        for (Node aLst : lst) {
            System.out.println(aLst.data);
        }
    }

    public void printCommonRoot(LinkedList<Node> lstA, LinkedList<Node> lstB) {
        ListIterator<Node> itrA = lstA.listIterator();
        ListIterator<Node> itrB = lstB.listIterator();


        while (itrA.hasNext() && itrB.hasNext()
                && (itrA.next().data == itrB.next().data)) {
            //no op
        }

        itrA.previous();
        Node previous = itrA.previous();
        System.out.println("common root: " + previous.data);
    }

    public static void main(String[] args) {
        Node<Integer> four = new Node<Integer>(4, null, null);
        Node<Integer> five = new Node<Integer>(5, null, null);
        Node<Integer> two = new Node<Integer>(2, four, five);

        Node<Integer> six = new Node<Integer>(6, null, null);
        Node<Integer> seven = new Node<Integer>(7, null, null);
        Node<Integer> three = new Node<Integer>(3, six, seven);

        Node<Integer> root = new Node<Integer>(1, two, three);

        FindMyBoss1 findMyBoss1 = new FindMyBoss1();

        LinkedList<Node> lsst_empa = new LinkedList<Node>();
        findMyBoss1.preOrder(root, five, lsst_empa);


        LinkedList<Node> lsst_empb = new LinkedList<Node>();
        findMyBoss1.match = false;
        findMyBoss1.preOrder(root, six, lsst_empb);

        findMyBoss1.print(lsst_empa);
        findMyBoss1.print(lsst_empb);

        findMyBoss1.printCommonRoot(lsst_empa, lsst_empb);
    }
}
