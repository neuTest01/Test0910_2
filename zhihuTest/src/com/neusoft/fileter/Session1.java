package com.neusoft.fileter;

import java.io.Serializable;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * Application Lifecycle Listener implementation class Session1
 *
 */
@WebListener
public class Session1 implements HttpSessionActivationListener,Serializable{

    /**
     * Default constructor. 
     */
	//HttpSession中的对象状态：绑定→解除绑定；钝化→活化
		String username;
	    
	    public Session1(String username){
	        this.username=username;
	    }
	
    public Session1() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
     */
    public void sessionDidActivate(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("HttpSessionActivationListenersessionDidActivate"+username);
    }

	/**
     * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
     */
    public void sessionWillPassivate(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	System.out.println("HttpSessionActivationListenersessionWillPassivate"+username);
    }
	
}
