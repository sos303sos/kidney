package com.platform.mongodb;

import java.lang.reflect.Field;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.platform.common.ReflectUtil;
import com.platform.exception.BusinessException;
import com.platform.mongodb.init.DatabaseManager;
import com.platform.mongodb.test.Comment;
import com.platform.mongodb.test.CommentDao;
import com.platform.mongodb.tools.Entity;



public class MongoBaseDao<T> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseManager.init();
		Comment comment = new Comment();
		comment.setCommentText("111");
		comment.setCommentTime(new Date().getTime());
		comment.setHtml("dajsak");
		new CommentDao().insert(comment);
		DatabaseManager.destroy();
	}

	public MongoBaseDao() {
	}

	public DBCollection getCollection(T object) {
		String collectionName = "";
		if (object.getClass().isAnnotationPresent(Entity.class)) {
			collectionName = object.getClass().getAnnotation(Entity.class).name();
		} else {
			throw new BusinessException("此对象不存在Entity注解");
		}
		return DatabaseManager.getDb().getCollection(collectionName);
	}

	public boolean hasIdField(T object) {
		return ReflectUtil.hasField(object.getClass(), "id");
	}

	public void insert(T object) {
		getCollection(object).insert(getMongodbObject(object));
	}

	public void update(T object) {
		if (!hasIdField(object)) {
			throw new BusinessException("此对象不存在id属性");
		}
		getCollection(object).update(new BasicDBObject("_id", new ObjectId((String) ReflectUtil.getter(object, "id"))), getMongodbObject(object));
	}

	public void removeById(T object) {
		if (!hasIdField(object)) {
			throw new BusinessException("此对象不存在id属性");
		}
		getCollection(object).remove(new BasicDBObject("_id", new ObjectId((String) ReflectUtil.getter(object, "id"))));
	}

	public T findById(T object) {
		if (!hasIdField(object)) {
			throw new BusinessException("此对象不存在id属性");
		}
		return fromDBObject(object, getCollection(object).findOne(new BasicDBObject("_id", new ObjectId((String) ReflectUtil.getter(object, "id")))));
	}

	public List<T> findByPage(T object, DBObject orderBy, int skip, int limit) {
		DBCursor cursor = getCollection(object).find(getMongodbObject(object)).sort(orderBy).skip(skip).limit(limit);
		List<T> ts = new ArrayList<T>();
		while (cursor.hasNext()) {
			ts.add(fromDBObject(object, cursor.next()));
		}
		return ts;
	}

	public DBObject getMongodbObject(T object) {
		DBObject dbObject = new BasicDBObject();
		Class objectClass = object.getClass();
		Field[] fields = objectClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getType().equals(String.class) || fields[i].getType().equals(Long.class)) {
				Object value = ReflectUtil.getter(object, fields[i].getName());
				if (value != null) {
					dbObject.put(fields[i].getName(), ReflectUtil.getter(object, fields[i].getName()));
				}
			}
		}
		return dbObject;
	}

	public T fromDBObject(T object, DBObject dbObject) {
		try {
			for (String key : dbObject.keySet()) {
				if (ReflectUtil.hasField(object.getClass(), key)) {
					Object value = dbObject.get(key);
					ReflectUtil.setter(object, key, value, value.getClass());
				}
			}
		} catch (Exception e) {
			throw new BusinessException("1", "实体不存在无参构造函数");
		}
		return object;
	}
}
