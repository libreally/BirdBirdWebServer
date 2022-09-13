package com.webserver.test;

import java.util.Arrays;

public class SplitDemo {
    public static void main(String[] args) {
        //limit为拆分的项数
        String line="1=2=3=4=5=6====";
        String[] arr0=line.split("=",2);//arr = [1, 2=3=4=5=6====]

        String[] arr1=line.split("=",3);//arr = [1, 2, 3=4=5=6====]

        String[] arr2=line.split("=",9);//arr = [1, 2, 3, 4, 5, 6, , , =]
        //limmit为0时，与split传一个参数的结果一致
        String[] arr3=line.split("=",0);//arr = [1, 2, 3, 4, 5, 6]
        //应拆尽拆，所有都保留
        String[] arr4=line.split("=",-1);//arr = [1, 2, 3, 4, 5, 6, , , , ]
        System.out.println("arr = " + Arrays.toString(arr0));
        System.out.println("arr = " + Arrays.toString(arr1));
        System.out.println("arr = " + Arrays.toString(arr2));
        System.out.println("arr = " + Arrays.toString(arr3));
        System.out.println("arr = " + Arrays.toString(arr4));
    }
}
