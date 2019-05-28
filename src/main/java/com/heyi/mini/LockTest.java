package com.heyi.mini;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public  static int sum=0;
    public static void main(String[] args) {

        for (int i = 1; i < 100; i++) {
            Threda1 a = new Threda1("A");
            a.start();
            Threda1 b = new Threda1("B");
            b.start();


    }
            System.out.println("打印的sum为----------"+sum);


    }

    static class Threda1 extends Thread{
        public Threda1(String name) {
            super(name);
        }

        @Override
        public void run() {

                    sum++;

            notify();
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"我运行了"+sum);
        }
    }

}
