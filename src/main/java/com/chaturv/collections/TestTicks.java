package com.chaturv.collections;

public class TestTicks {

    public static void main(String[] args) {
        new TestTicks().test();
    }

    public void test() {
        TicksService service = new TicksService();

        TicksProducer producer = new TicksProducer(service);
        TicksConsumer consumer = new TicksConsumer(service);

        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(consumer);
            t.start();
        }

       new Thread(producer).start();
    }
}
