package com.youmeek.java.main;

import java.io.*;

/**
 * Created by gongp on 2018/6/13.
 */
public class FileTest {
    public static void main(String[] args) {
        File file = new File("d://a.txt");
        for(int i=0;i<100 ;i++){
            writeFile(file);
            readFile(file);

        }
    }

    //读取文件
    private static void readFile(File file) {
        System.out.println("开始读取如下：");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            try {
                String out = null;
                while( (out =bufferedReader.readLine())!=null){
                    System.out.println(out);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //写入文件
    private static void writeFile(File file) {
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {


            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
            bufferedWriter.write("hallpy");
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
