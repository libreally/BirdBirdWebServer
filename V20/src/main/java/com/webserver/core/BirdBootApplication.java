package com.webserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 项目的主类，用于启动WebServer
 * 该项目主要功能是实现Tomcat这个WebServer的核心功能。了解HTTP协议以及浏览器与服务端
 * 之间的处理细节。
 * 了解SpringMVC核心类的设计与实现
 */
public class BirdBootApplication {
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    public BirdBootApplication(){
        try {
            System.out.println("正在启动服务端...");
            serverSocket = new ServerSocket(8088);
            threadPool = Executors.newFixedThreadPool(50);
            System.out.println("服务端启动完毕!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        try {
            while(true) {
                System.out.println("等待客户端连接...");
                Socket socket = serverSocket.accept();
                System.out.println("一个客户端连接了!");
                //启动一个线程处理该客户端交互
                ClientHandler handler = new ClientHandler(socket);
                threadPool.execute(handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    public static void main(String[] args) {
        BirdBootApplication application = new BirdBootApplication();
        application.start();
    }
}
