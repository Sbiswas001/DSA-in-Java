package com.sayan.dsa;

public class TimeComplexityDemo {
    public static void main(String[] args) {
        double now = System.currentTimeMillis();
        TimeComplexityDemo timeComplexityDemo = new TimeComplexityDemo();
        System.out.println(timeComplexityDemo.findSum(999999));
        System.out.println("Time taken = " + (System.currentTimeMillis() - now) + "millisecs");
    }
//    public int findSum(int n) {
//        return n*(n+1)/2;
//    }
    public int findSum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
}
