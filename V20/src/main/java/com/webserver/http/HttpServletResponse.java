package com.webserver.http;


import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 响应对象
 * 该类的每一个实例用于表示HTTP协议规定的响应。
 * 一个响应由三部分构成:状态行，响应头，响应正文。
 */
public class HttpServletResponse {
    private static MimetypesFileTypeMap mime = new MimetypesFileTypeMap();

    private Socket socket;

    //状态行相关信息
    private int statusCode = 200;//状态代码
    private String statusReason = "OK";//状态描述

    //响应头相关信息
    private Map<String,String> headers = new HashMap<>();

    //响应正文相关信息
    private File contentFile;//响应正文对应的实体文件


    public HttpServletResponse(Socket socket){
        this.socket = socket;
    }

    /**
     * 该方法会将当前响应对象内容以标准的HTTP响应格式通过socket获取的输出流发送给
     * 对应的客户端。
     */
    public void response() throws IOException {
        //发送状态行
        sendStatusLine();
        //发送响应头
        sendHeaders();
        //发送响应正文
        sendContent();
    }
    //发送状态行
    private void sendStatusLine() throws IOException {
        println("HTTP/1.1"+" "+statusCode+" "+statusReason);
    }
    //发送响应头
    private void sendHeaders() throws IOException {
        /*
            headers
            key                 value
            Content-Type        text/html
            Content-Length      245
            Server              BirdWebServer
            ...                 ...
         */
        //遍历headers发送每一个响应头
        Set<Map.Entry<String,String>> entrySet = headers.entrySet();
        for(Map.Entry<String,String> e : entrySet){
            String key = e.getKey();
            String value = e.getValue();
            println(key+": "+value);
        }
        //单独发送回车+换行表达响应头发送完毕
        println("");
    }
    //发送响应正文
    private void sendContent() throws IOException {
        OutputStream out = socket.getOutputStream();
        if (contentFile!=null){//正文文件不为空才发送
            try(
                    FileInputStream fis = new FileInputStream(contentFile);
            ) {
                byte[] data = new byte[1024 * 10];
                int len;
                while ((len = fis.read(data)) != -1) {
                    out.write(data, 0, len);
                }
            }
        }

    }


    private void println(String line) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write(line.getBytes(StandardCharsets.ISO_8859_1));
        out.write(13);
        out.write(10);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public File getContentFile() {
        return contentFile;
    }

    public void setContentFile(File contentFile) {
        this.contentFile = contentFile;
        addHeader("Content-Type",mime.getContentType(contentFile));
        addHeader("Content-Length",contentFile.length()+"");

    }

    /**
     * 添加一个响应头
     * @param name  响应头的名字
     * @param value 响应头的值
     */
    public void addHeader(String name,String value){
        headers.put(name,value);
    }

    /**
     * 重定向到指定路径
     * @param location
     */
    public void sendRedirect(String location){
        /*
         *响应要求
         * 状态码302
         * 包含响应头location  对应的本地页面
         *
         */
        statusCode=302;
        statusReason="Moved Temporarily";
        addHeader("location",location);

    }
}
