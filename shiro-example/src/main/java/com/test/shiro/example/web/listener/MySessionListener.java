package com.test.shiro.example.web.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class MySessionListener implements SessionListener {

	public void onStart(Session session) {
		System.out.println("session.getId()" + session.getId());
	}

	public void onExpiration(Session session) {
		System.out.println("session.getId()" + session.getId());
	}

	public void onStop(Session session) {
		System.out.println("session.getId()" + session.getId());
	}

}
