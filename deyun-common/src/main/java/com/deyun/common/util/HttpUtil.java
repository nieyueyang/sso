package com.deyun.common.util;

import com.alibaba.fastjson.JSON;
import com.deyun.common.dto.Result;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.Enumeration;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/5/1 11:32
 * @Version 1.0
 * @Description:
 */
public class HttpUtil {

    public static <T> void ResponseError(HttpServletResponse response,T t) throws IOException {
        OutputStream out = null;
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8"); // 避免乱码
        try{
            String json = JSON.toJSONString(t);
            out = response.getOutputStream();
            out.write(json.getBytes("UTF-8"));
            out.flush();
        }finally {
            out.flush();
            out.close();
        }
    }

    public static void ResponseError(HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8"); // 避免乱码
        response.flushBuffer();
    }

    public static <T> void responseWriteJson(HttpServletResponse response, T t) {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(JSON.toJSONString(t));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void responseWriteJson(HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        // ipAddress = this.getRequest().getRemoteAddr();
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }


    /**
     * 获取本机IP地址
     * @return
     */
    public static String getLocalIpAddr(){
        String ipAddress = "";
        Enumeration allNetInterfaces = null;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements())
            {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements())
                {
                    ip = (InetAddress) addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address)
                    {
                        ipAddress = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            return null;
        }
        return ipAddress;

    }



}
