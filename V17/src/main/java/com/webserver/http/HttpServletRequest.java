package com.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求对象，该类的每一个实例用于标识HTTP协议规定的请求内容
 * 一个请求由三部分构成:
 * 1:请求行
 * 2:消息头
 * 3:消息正文
 */
public class HttpServletRequest {
    private Socket socket;
    //请求行的相关信息
    private String method;//请求方式
    private String uri;//抽象路径
    private String protocol;//协议版本

    private String requestURI;//uri中的请求部分，？左侧内容

    private String queryString;//uri中的参数部分，？右侧内容
    private Map<String,String> headers=new HashMap<>();
    //消息头的相关信息
    private Map<String,String> parameters = new HashMap<>();

    public HttpServletRequest(Socket socket) throws IOException, EmptyRequestException {
        this.socket = socket;
        //1解析请求行
        parseRequestLine();
        //2解析消息头
        parseHeaders();
        //3解析消息正文
        parseContent();

    }

    //解析请求行
    private void parseRequestLine() throws IOException, EmptyRequestException {
        String line = readLine();
        if(line.isEmpty()){//若请求行为空字符串
            //抛出空请求异常
            throw new EmptyRequestException();
        }
        System.out.println("请求行内容:"+line);
        String[] data = line.split("\\s");
        method = data[0];
        uri = data[1];
        protocol = data[2];
        //进一步解析uri
        parseURI();
        System.out.println("method:"+method);
        System.out.println("uri:"+uri);
        System.out.println("protocol:"+protocol);


        System.out.println();
    }
    //解析请求uri
    private void parseURI(){
         /*
            uri有两种情况:
            1:不含有参数的
              例如: /index.html
              直接将uri的值赋值给requestURI即可.

            2:含有参数的
              例如:/regUser?username=fancq&password=&nickname=chuanqi&age=22
              将uri中"?"左侧的请求部分赋值给requestURI
              将uri中"?"右侧的参数部分赋值给queryString
              将参数部分首先按照"&"拆分出每一组参数，再将每一组参数按照"="拆分为参数名与参数值
              并将参数名作为key，参数值作为value存入到parameters中。
         */

        String[] data=uri.split("\\?");
        requestURI=data[0];
        if (data.length>1){
            queryString=data[1];
            //对参数部分转码
            queryString= URLDecoder.decode(queryString, StandardCharsets.UTF_8);
            data = queryString.split("&");
            for (String para:data){
                String[] split = para.split("=",2);
                parameters.put(split[0],split[1]);
                /* if (split.length>1){
                     parameters.put(split[0],split[1]);
                }else {
                    parameters.put(split[0],"");
                }*/
            }
        }
        System.out.println("requestURI:"+requestURI);
        System.out.println("queryString:"+queryString);
        System.out.println("parameters:"+parameters);
    }
    //解析消息头
    private void parseHeaders() throws IOException {
        while(true) {
            String line = readLine();
            if(line.isEmpty()){//如果读取的消息头是空字符串，说明单独读取到了CRLF
                break;
            }
            System.out.println("消息头:"+line);
            //将消息头按照": "拆分为名字和值并作为key，value存入到headers中
            String[] data = line.split(":\\s");
            headers.put(data[0],data[1]);
        }
        System.out.println("headers:"+headers);
    }
    //解析消息正文
    private void parseContent(){}

    private String readLine() throws IOException {
        //调用同一个socket对象若干次getInputStream()方法返回的始终是同一条输入流
        InputStream in = socket.getInputStream();
        StringBuilder builder = new StringBuilder();
        char pre='a',cur='a';//pre表示上次读取的字符，cur表示本次读取的字符
        int d;
        while((d = in.read())!=-1){
            cur = (char)d;//本次读取到的字符
            if(pre==13&&cur==10){//若上次读取的是回车符并且本次读取的是换行符
                break;
            }
            builder.append(cur);//拼接本次读取到的字符
            pre = cur;//进入下次循环前将本次读取的字符记作上次读取的字符
        }
        return builder.toString().trim();
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getProtocol() {
        return protocol;
    }

    /**
     * 根据消息头的名字获取对应的值
     * @param name
     * @return
     */
    public String getHeader(String name) {
        return headers.get(name);
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getParameters(String name) {
        return parameters.get(name);
    }
}
