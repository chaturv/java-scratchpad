package com.chaturv.datastructures.trees;


import com.chaturv.datastructures.Employee;

import java.util.LinkedList;
import java.util.ListIterator;

public class FindMyBoss2 {

    private Boolean match;

    public Employee findMyBoss(Employee ceo, Employee empA, Employee empB) {
        match = false;
        LinkedList<Employee> linkageEmpA = new LinkedList<Employee>();
        createLinkage(ceo, empA, linkageEmpA);

        match = false;
        LinkedList<Employee> linkageEmpB = new LinkedList<Employee>();
        createLinkage(ceo, empB, linkageEmpB);

        return findPointOfDiff(linkageEmpA, linkageEmpB);
    }

    private Employee findPointOfDiff(LinkedList<Employee> linkageEmpA, LinkedList<Employee> linkageEmpB) {
        ListIterator<Employee> itrA = linkageEmpA.listIterator();
        ListIterator<Employee> itrB = linkageEmpB.listIterator();


        while (itrA.hasNext() && itrB.hasNext()
                && (itrA.next().getId() == itrB.next().getId())) {
            //no op
        }

        if (itrA.hasPrevious()) {
            itrA.previous();
        }
        if (itrA.hasPrevious()) {
            return itrA.previous();
        } else {
            return itrA.next();
        }
    }

    private void createLinkage(Employee manager, Employee emp, LinkedList<Employee> linkage) {
        while (!match) {
            if (manager == null) {
                return;
            }

            linkage.offer(manager);

            match = isEqual(manager, emp);
            if (match) {
                return;
            }

            for (Employee one : manager.getEmployees()) {
                createLinkage(one, emp, linkage);
            }

            if (!match) {
                linkage.removeLast();
                return;
            }
        }
    }

    private boolean isEqual(Employee one, Employee other) {
        return one.getId() == other.getId();
    }

    public static void main(String[] args) {
        Employee ceo = constructTree();
        FindMyBoss2 findMyBoss2 = new FindMyBoss2();

        Employee myBoss;

//        myBoss = findMyBoss.findMyBoss(ceo, five, seven);
//        System.out.println(myBoss);
//
//        myBoss = findMyBoss.findMyBoss(ceo, seven, eight);
//        System.out.println(myBoss);
//
//        myBoss = findMyBoss.findMyBoss(ceo, four, five);
//        System.out.println(myBoss);
//
//        myBoss = findMyBoss.findMyBoss(ceo, two, three);
//        System.out.println(myBoss);
//
//        myBoss = findMyBoss.findMyBoss(ceo, eight, nine);
//        System.out.println(myBoss);

        myBoss = findMyBoss2.findMyBoss(ceo, one, three);
        System.out.println(myBoss);
    }

    private static Employee constructTree() {

        one.getEmployees().add(two);
        one.getEmployees().add(three);
        one.getEmployees().add(four);

        two.getEmployees().add(five);
        two.getEmployees().add(six);
        two.getEmployees().add(seven);

        three.getEmployees().add(eight);
        three.getEmployees().add(nine);

        return one;
    }

    static Employee one = new Employee(1);
    static Employee two = new Employee(2);
    static Employee three = new Employee(3);
    static Employee four = new Employee(4);
    static Employee five = new Employee(5);
    static Employee six = new Employee(6);
    static Employee seven = new Employee(7);
    static Employee eight = new Employee(8);
    static Employee nine = new Employee(9);


}
