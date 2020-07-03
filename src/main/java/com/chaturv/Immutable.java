package com.chaturv;

final class A {
    private String s;

    public A(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }

    public void setA(String a) {
        this.s = a;
    }
}

final class Immutable {
    private final A a;

    public Immutable(A a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return a.toString();
    }

    public static void main(String[] args) {
        A a = new A("Bye");
        Immutable im = new Immutable(a);
        System.out.print(im);

        a.setA(" bye");
        System.out.println("fpp" + 1);
        System.out.print(a);
    }
}