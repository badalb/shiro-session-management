package com.test.shiro.example.session.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySessionValidationScheduler implements
		SessionValidationScheduler, Runnable {


	private static final Logger log = LoggerFactory
			.getLogger(MySessionValidationScheduler.class);

	ValidatingSessionManager sessionManager;
	private ScheduledExecutorService service;
	private long interval = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;
	private boolean enabled = false;

	public MySessionValidationScheduler() {
		super();
	}

	public ValidatingSessionManager getSessionManager() {
		return sessionManager;
	}

	public void setSessionManager(ValidatingSessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void enableSessionValidation() {
		if (this.interval > 0l) {
			this.service = Executors
					.newSingleThreadScheduledExecutor(new ThreadFactory() {
						public Thread newThread(Runnable r) {
							Thread thread = new Thread(r);
							thread.setDaemon(true);
							return thread;
						}
					});
			this.service.scheduleAtFixedRate(this, interval, interval,
					TimeUnit.MILLISECONDS);
			this.enabled = true;
		}
	}

	public void run() {
		if (log.isDebugEnabled()) {
			log.debug("Executing session validation...");
		}
		long startTime = System.currentTimeMillis();

		// TODO Implement session time out  here

		long stopTime = System.currentTimeMillis();
		if (log.isDebugEnabled()) {
			log.debug("Session validation completed successfully in "
					+ (stopTime - startTime) + " milliseconds.");
		}
	}

	public void disableSessionValidation() {
		this.service.shutdownNow();
		this.enabled = false;
	}
}
