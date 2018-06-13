package com.youmeek.java.main;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gongp on 2018/5/28.
 */
public class A {



    public static  class DButil{
        public static  void insertVisit(String path ,String date){
//            com.sun.management.GarbageCollectorMXBean
        }
    }

    public  static class DateUtil{
        public static  String currentDate(){
            return "";
        }
    }



    public static void main(String[] args) {
        long l1 = 1518341952996l;
        long l2 = 8900619900l;
        long ls = 8891274849l;
        long le = 8891349902l;
        System.out.println(System.currentTimeMillis());
        System.out.println(l1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println(sdf.format(new Date(l1)));
        System.out.println(sdf.format(new Date(l1 + l2)));

        System.out.println(sdf.format(new Date(l1 + ls)));
        System.out.println(sdf.format(new Date(l1 + le)));


    }




}


