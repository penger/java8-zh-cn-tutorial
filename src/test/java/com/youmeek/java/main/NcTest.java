package com.youmeek.java.main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by gongp on 2018/6/13.
 */
public class NcTest {
    public static void main(String[] args) throws IOException {
//        sendTCP("hello world");
        Socket socket = new Socket("192.168.8.101", 2222);
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.write("hello nc");
        pw.flush();
        socket.shutdownInput();
        os.close();
        pw.close();
        socket.close();
    }

    public static void sendTCP(String sendStr){
        int port = 2222;
        try {
            ServerSocket server = new ServerSocket(port);
            Socket client = server.accept();

            System.out.println(client.getInetAddress() + "已建立连接！");
            // 输入流
            InputStream is = client.getInputStream();
            BufferedReader bri = new BufferedReader(new InputStreamReader(is));
            // 输出流
            OutputStream os = client.getOutputStream();

            PrintWriter pw = new PrintWriter(os);
            // PrintWriter把数据写到目的地
            pw.print(sendStr);
            //关闭资源
            is.close();
            bri.close();
            os.close();
            pw.close();
            client.close();
            server.close();
            System.out.println("send success! The length:" + sendStr.length());
        } catch (Exception e) {
            System.out.println("connection exit!");
            System.out.println();
        } finally {

        }
    }
}
