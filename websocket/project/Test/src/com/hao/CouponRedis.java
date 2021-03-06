package com.hao;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

public class CouponRedis  extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.setContentType("text/html;charset=GB2312"); // 这条语句指明了向客户端发送的内容格式和采用的字符编码．
		Jedis jedis = new Jedis("haoning.net",6379); //连接池略
		
		try {   
			String machine_product_id = request.getParameter("machine_product_id"); 
			System.out.println("---------/coupon/check:"+machine_product_id);
			String result ="0";
			if(machine_product_id==null){
				 result="0";
			}else{
				 result = jedis.get(machine_product_id); 
				 if(result==null){
					 result="0";
				 }
			}
		    try {
				response.getWriter().write(result);
				response.getWriter().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    
		} catch (Exception e) {  
		    e.printStackTrace();  
		} finally {  
			
		}  
	}

	// 用于处理客户端发送的POST请求
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response); // 这条语句的作用是，当客户端发送POST请求时，调用doGet()方法进行处理
	}
	public static void main(String[] args) {
		Jedis jedis = new Jedis("haoning.net",6379); 
		try {   
		    jedis.set("abc", "1");  
		    String ss = jedis.get("abc");  
		    System.out.println(ss);
		} catch (Exception e) {  
		    e.printStackTrace();  
		} finally {  
			
		}  
	}

}
