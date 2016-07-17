package com.platform.mongodb.init;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.platform.common.CommonConfig;

public class DatabaseManager {
	private static final Log logger = LogFactory.getLog(DatabaseManager.class);

	private static Mongo mongo = null;

	private static Lock lock = new ReentrantLock();

	public static final void destroy() {
		lock.lock();
		try {
			if (mongo != null) {
				mongo.close();
				mongo = null;
			}
		} finally {
			lock.unlock();
		}
		logger.error("mongo连接池释放");
	}

	public static final Mongo getMongo() {
		lock.lock();
		try {
			if (mongo == null)
				init();
		} finally {
			lock.unlock();
		}
		return mongo;
	}

	public static final DB getDb() {
		String dbname = CommonConfig.getString("database.mongodb.dbName");
		return getMongo().getDB(dbname);
	}

	public static synchronized void init() {
		String host = CommonConfig.getString("database.mongodb.host");
		String poolsize = CommonConfig.getString("database.mongodb.connectionsPerHost");
		String threads = CommonConfig.getString("database.mongodb.threadsAllowedToBlockForConnectionMultiplier");
		String autoConnectRetry = CommonConfig.getString("database.mongodb.autoConnectRetry");
		String connectTimeout = CommonConfig.getString("database.mongodb.connectTimeout");
		String maxAutoConnectRetryTime = CommonConfig.getString("database.mongodb.maxAutoConnectRetryTime");
		try {
			MongoOptions options = new MongoOptions();
			options.connectionsPerHost = Integer.parseInt(poolsize);
			options.threadsAllowedToBlockForConnectionMultiplier = Integer.parseInt(threads);
			options.autoConnectRetry = getAutoConnectRetry(autoConnectRetry);
			options.connectTimeout = getConnectTimeout(connectTimeout);
			options.maxAutoConnectRetryTime = getMaxAutoConnectRetryTime(maxAutoConnectRetryTime);
			mongo = new Mongo(host, options);
		} catch (Exception e) {
			logger.error("can't get mongo's connection", e);
			throw new RuntimeException(e);
		}
		logger.error("mongo启动成功");
	}

	private static boolean getAutoConnectRetry(String param) {
		if ((param == null) || (param.trim().equals(""))) {
			return true;
		}
		return Boolean.parseBoolean(param);
	}

	private static int getConnectTimeout(String param) {
		if ((param == null) || (param.trim().equals(""))) {
			return 10000;
		}
		return Integer.parseInt(param);
	}

	private static long getMaxAutoConnectRetryTime(String param) {
		if ((param == null) || (param.trim().equals(""))) {
			return 15000L;
		}
		return Long.parseLong(param);
	}
}
