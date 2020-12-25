package com.demo.springcloud.alibaba.test;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.openjdk.jol.info.ClassLayout;

public class TestController {

    private String name;

    public static void main(String[] args) {
        new TestController().showInfo("good luck");
//        TestController.Student student = new TestController.Student();
//        synchronized (student){
//            System.out.println(ClassLayout.parseInstance(student).toPrintable());
//            System.out.println(ObjectSizeCalculator.getObjectSize(student));
//        }
    }


    public  void showInfo(String name) {
        this.name = name;
        System.out.println(name);

    }

    public static class Student{

        private boolean bo;
        private byte by;
        private short st;
        private double d;
        private  int  i;
        private long l;
        private  Object obj;
        private char ch;
        private Object[] objAr;
    }
}
