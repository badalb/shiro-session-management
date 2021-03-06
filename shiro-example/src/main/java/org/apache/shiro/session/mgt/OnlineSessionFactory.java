package org.apache.shiro.session.mgt;

import org.apache.shiro.session.Session;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;

public class OnlineSessionFactory implements SessionFactory {

	public Session createSession(SessionContext initData) {
		OnlineSession session = new OnlineSession();
		if (initData != null && initData instanceof WebSessionContext) {
			WebSessionContext sessionContext = (WebSessionContext) initData;
			HttpServletRequest request = (HttpServletRequest) sessionContext
					.getServletRequest();
			if (request != null) {
				session.setHost(IpUtils.getIpAddr(request));
				session.setUserAgent(request.getHeader("User-Agent"));
				session.setSystemHost(request.getLocalAddr() + ":"
						+ request.getLocalPort());

				// set other attributes
				// session.setAnonymous_user_id("hu_anonymous_user_id");
				// session.setInvestment_amount("1000");
				session.setAttribute("anonymous_user_id",
						"hu_anonymous_user_id");

			}
		}
		return session;
	}
}
