package kdb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/elec")
public class FrontController extends HttpServlet {
	private Map<String, Action> map = new HashMap<>();
	
	
	@Override
	public void init() throws ServletException {
		//1. properties���� �ε�
		Properties pro = new Properties(); 
		try{
			pro.load(super.getServletContext()
					.getResourceAsStream("/WEB-INF/classes/action.properties"));
			
			//2. key�� value�� �и��ؼ� value�� ��ü�� ��ȯ
               Set<Object> keys = pro.keySet();
               Iterator<Object> it= keys.iterator();
			
			while(it.hasNext()){
				String key = (String)it.next(); //key
				String value = pro.getProperty(key);
				System.out.println(key+"=" + value);
				
				map.put(key, (Action)Class.forName(value).newInstance());
			}
			
			//3. map �����Ѵ�.
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("command");
		//System.out.println(key);
		 map.get(key).execute(request, response);
	}

}




