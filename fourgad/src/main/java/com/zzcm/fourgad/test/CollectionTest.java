package com.zzcm.fourgad.test;

import java.util.*;

public class CollectionTest {

	public static void test1(){
		List<Integer> list = new ArrayList<Integer>();  
        list.add(5);  
        list.add(2);  
        list.add(1);  
        list.add(9);  
        list.add(0);  
          
        System.out.println("原序列:");  
        for (Integer integer : list) {  
            System.out.print(integer+" ");  
        }  
        System.out.println();  
          
        /*根据步长进行循环*/  
        Collections.rotate(list, 2);  
        System.out.println("循环后:");  
        for (Integer integer : list) {  
            System.out.print(integer+" ");  
        }  
	}
	
	public static void main(String[] args) {
		test1();
	}
}
