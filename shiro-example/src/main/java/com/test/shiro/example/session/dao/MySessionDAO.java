package com.test.shiro.example.session.dao;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import com.test.shiro.example.SerializableUtils;
import com.test.shiro.memcached.MemcachedUtil;

public class MySessionDAO extends CachingSessionDAO {

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		MemcachedUtil.put(sessionId.toString(),
				SerializableUtils.serialize(session));
		return session.getId();
	}

	@Override
	protected void doUpdate(Session session) {
		if (session instanceof ValidatingSession
				&& !((ValidatingSession) session).isValid()) {
			return;
		}
		MemcachedUtil.put(session.getId().toString(),
				SerializableUtils.serialize(session));
	}

	@Override
	protected void doDelete(Session session) {
		MemcachedUtil.remove(session.getId().toString());
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		String sessionStr = (String) MemcachedUtil.get(sessionId.toString());
		if (sessionStr == null)
			return null;
		return SerializableUtils.deserialize(sessionStr, sessionId);
	}
}
