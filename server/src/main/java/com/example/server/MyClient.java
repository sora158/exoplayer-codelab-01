package com.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MyClient {

    public static void main(String[]args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        boolean isExit = false;

        MyClass.startServer();
        while (!isExit){
            line = br.readLine();
            if (line.startsWith("exit")){
                System.out.println("退出命令");
                break;
            }

            if (line.startsWith("send")){
                sendMessage(line);
            }else if (line.startsWith("list")){
                printTotal();
            }else if (line.startsWith("all")){
                allSendMsg(line);
            }else {
                System.out.println("输入错误 请重新输入");
            }

        }
        // 关闭 清空
        MyClass.shutDown();

    }

    private static void allSendMsg(String line) {
        String[] field = line.split("//");
        if (field.length == 2){
            MyClass.sendMsgAll(field[1]);
            System.out.println("all message result：" + MyClass.sendMsgAll(field[1]) );
        }else {
            System.out.println("example：all//message");
        }
    }

    private static void printTotal() {
        List<String> totalClients = MyClass.getTotalClients();
        System.out.println("connecting Number：" + totalClients.size());
        for (String totalClient : totalClients) {
            System.out.println(totalClient);
        }
    }

    private static void sendMessage(String line) {
        String[] field = line.split("//");
        if (field.length == 3){
            // 格式正确
            MyClass.sendMessage(field[1],field[2]);
            System.out.println("send:" + MyClass.sendMessage(field[1],field[2]));
        }else {
            System.out.println("命令不正确。例子：send//name//msg");
        }
    }

}