package com.chaturv.fx;


import java.util.*;

public class RateValidity implements Comparable<RateValidity> {

    private float rate;
    private Date validUntil;

    public RateValidity(float rate, Date validUntil) {
        this.rate = rate;
        this.validUntil = validUntil;
    }

    @Override
    public int compareTo(RateValidity rv) {
        return Long.compare(this.validUntil.getTime(), rv.validUntil.getTime());
    }

    @Override
    public String toString() {
        return "RateValidity{" +
                "rate=" + rate +
                ", validUntil=" + validUntil +
                '}';
    }

    public static void main(String[] args) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        c1.set(Calendar.HOUR_OF_DAY, 9);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        c2.set(Calendar.HOUR_OF_DAY, 11);
        c2.set(Calendar.MINUTE, 30);
        c2.set(Calendar.SECOND, 0);

        Calendar c3 = Calendar.getInstance();
        c3.setTime(new Date());
        c3.set(Calendar.HOUR_OF_DAY, 12);
        c3.set(Calendar.MINUTE, 30);
        c3.set(Calendar.SECOND, 0);

        RateValidity rv1 = new RateValidity(0.2012f, c1.getTime());
        RateValidity rv2 = new RateValidity(0.2013f, c2.getTime());
        RateValidity rv3 = new RateValidity(0.2014f, c3.getTime());

        List<RateValidity> list = new ArrayList<RateValidity>();
        list.add(rv2);
        list.add(rv3);
        list.add(rv1);

        Calendar c4 = Calendar.getInstance();
        c4.setTime(new Date());
        c4.set(Calendar.HOUR_OF_DAY, 10);
        c4.set(Calendar.MINUTE, 17);
        c4.set(Calendar.SECOND, 0);

        //System.out.println(list);
        Collections.sort(list);
        //System.out.println(list);

        RateValidity rvTest = new RateValidity(0.20125f, c4.getTime());
        int idx = Collections.binarySearch(list, rvTest);
        System.out.println(idx);

        RateValidity rvLookup = list.get(-idx - 1);
        System.out.println("---" + rvLookup);

        PriorityQueue<RateValidity > pq = new PriorityQueue<RateValidity>();
        pq.add(rv3);
        pq.add(rv2);
        pq.add(rv1);

        RateValidity rv;
        while ((rv = pq.poll()) != null) {
            System.out.println(rv);
        }

        SortedSet<RateValidity> set = new TreeSet<RateValidity>();
        set.add(rv2);
        set.add(rv3);
        set.add(rv1);
        System.out.println(set);

        Iterator<RateValidity> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
