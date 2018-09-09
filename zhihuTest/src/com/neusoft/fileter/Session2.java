package com.neusoft.fileter;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Application Lifecycle Listener implementation class Session2
 *
 */
@WebListener
public class Session2 implements HttpSessionBindingListener {

	
	//HttpSession中的对象状态：绑定→解除绑定；钝化→活化
	String username;
    
    public Session2(String username){
        this.username=username;
    }
    /**
     * Default constructor. 
     */
    public Session2() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionBindingListener#valueBound(HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("HttpSessionBindingListenervalueBound---"+username);
    }

	/**
     * @see HttpSessionBindingListener#valueUnbound(HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("HttpSessionBindingListenervalueUnbound---"+username);
    }
	
}
