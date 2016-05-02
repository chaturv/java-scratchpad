package com.chaturv.collections.setnavigate;

import java.util.*;
import java.util.concurrent.*;

public class TestSetNavigate {

    public static void main(String[] args) {
        Set integers = new TreeSet();


        System.out.println("foo" == "foo");

        ToDos t1 = new ToDos("Monday");
        System.out.println(t1.hashCode());
        ToDos t2 = new ToDos("Monday");
        System.out.println(t2.hashCode());

        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 40, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000)) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                if (t == null && r instanceof FutureTask) {
                    try {
                        ((FutureTask) r).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        t = e.getCause();
                    }
                }

                if (t != null) {

                }
            }
        };
    }

    static class ToDos {
        String day;
        ToDos(String d) { day = d; }
        public boolean equals(Object o) {
            return ((ToDos)o).day == this.day;
        }
        // public int hashCode() { return 9; }
    }
}

