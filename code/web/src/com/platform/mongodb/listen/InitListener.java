package com.platform.mongodb.listen;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.platform.mongodb.init.DatabaseManager;

public class InitListener implements ServletContextListener {
	public void contextDestroyed(ServletContextEvent arg0) {
		DatabaseManager.destroy();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		DatabaseManager.init();
	}
}
