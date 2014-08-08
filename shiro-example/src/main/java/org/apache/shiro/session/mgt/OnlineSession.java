package org.apache.shiro.session.mgt;

import org.apache.shiro.session.mgt.SimpleSession;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class OnlineSession extends SimpleSession {

	private static final long serialVersionUID = -7125642695178165650L;

	private static final int USER_AGENT_BIT_MASK = 1 << bitIndexCounter++;

	private static final int STATUS_BIT_MASK = 1 << bitIndexCounter++;

	private String userAgent;

	private OnlineStatus status = OnlineStatus.on_line;

	private String systemHost;

	private String anonymous_user_id;

	private String investment_amount;

	private String no_of_recalc;

	private String profile_score;

	public static enum OnlineStatus {
		on_line("online"), hidden("hidden"), force_logout("forcelogout");
		private final String info;

		private OnlineStatus(String info) {
			this.info = info;
		}

		public String getInfo() {
			return info;
		}
	}

	public OnlineSession() {
		super();
	}

	public OnlineSession(String host) {
		super(host);
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public OnlineStatus getStatus() {
		return status;
	}

	public void setStatus(OnlineStatus status) {
		this.status = status;
	}

	public String getSystemHost() {
		return systemHost;
	}

	public void setSystemHost(String systemHost) {
		this.systemHost = systemHost;
	}

	private transient boolean attributeChanged = false;

	public void markAttributeChanged() {
		this.attributeChanged = true;
	}

	public void resetAttributeChanged() {
		this.attributeChanged = false;
	}

	public boolean isAttributeChanged() {
		return attributeChanged;
	}

	@Override
	public void setAttribute(Object key, Object value) {
		super.setAttribute(key, value);
	}

	@Override
	public Object removeAttribute(Object key) {
		return super.removeAttribute(key);
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		short alteredFieldsBitMask = getAlteredFieldsBitMask();
		out.writeShort(alteredFieldsBitMask);
		if (userAgent != null) {
			out.writeObject(userAgent);
		}
		if (status != null) {
			out.writeObject(status);
		}

	}

	@SuppressWarnings({ "unchecked" })
	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		short bitMask = in.readShort();

		if (isFieldPresent(bitMask, USER_AGENT_BIT_MASK)) {
			this.userAgent = (String) in.readObject();
		}
		if (isFieldPresent(bitMask, STATUS_BIT_MASK)) {
			this.status = (OnlineStatus) in.readObject();
		}
	}

	private short getAlteredFieldsBitMask() {
		int bitMask = 0;
		bitMask = userAgent != null ? bitMask | USER_AGENT_BIT_MASK : bitMask;
		bitMask = status != null ? bitMask | STATUS_BIT_MASK : bitMask;
		return (short) bitMask;
	}

	private static boolean isFieldPresent(short bitMask, int fieldBitMask) {
		return (bitMask & fieldBitMask) != 0;
	}

	public String getAnonymous_user_id() {
		return anonymous_user_id;
	}

	public void setAnonymous_user_id(String anonymous_user_id) {
		this.anonymous_user_id = anonymous_user_id;
	}

	public String getInvestment_amount() {
		return investment_amount;
	}

	public void setInvestment_amount(String investment_amount) {
		this.investment_amount = investment_amount;
	}

	public String getNo_of_recalc() {
		return no_of_recalc;
	}

	public void setNo_of_recalc(String no_of_recalc) {
		this.no_of_recalc = no_of_recalc;
	}

	public String getProfile_score() {
		return profile_score;
	}

	public void setProfile_score(String profile_score) {
		this.profile_score = profile_score;
	}

	public void setAttributeChanged(boolean attributeChanged) {
		this.attributeChanged = attributeChanged;
	}

}
