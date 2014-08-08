package com.test.shiro.example;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.OnlineSession;

import com.google.gson.Gson;

public class SerializableUtils {

	public static String serialize(Session session) {
		try {
			return new Gson().toJson(session);
		} catch (Exception e) {
			throw new RuntimeException("serialize session error", e);
		}
	}

	public static Session deserialize(String sessionStr, Serializable sessionId) {
		try {
			OnlineSession session = new Gson().fromJson(sessionStr,
					OnlineSession.class);
			session.setId(sessionId);
			return session;
		} catch (Exception e) {
			throw new RuntimeException("deserialize session error", e);
		}
	}
}
