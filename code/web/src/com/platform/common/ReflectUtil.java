package com.platform.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * 反射公共类
 * 
 * @author 孙化彬
 * 
 */
public class ReflectUtil {
	/**
	 * 通过get方法获取属性值
	 * 
	 * @param obj
	 *            操作的对象
	 * @param att
	 *            操作的属性名称
	 * */
	public static Object getter(Object obj, String att) {
		try {
			Method method = obj.getClass().getDeclaredMethod("get" + firstChatToBig(att));
			return method.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取属性的类型
	 * 
	 * @param obj
	 *            操作的对象
	 * @param att
	 *            操作的属性名称
	 * */
	public static Class getFieldType(Class cls, String fieldName) {
		Class clsReturn = null;
		try {
			clsReturn = cls.getDeclaredField(fieldName).getType();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clsReturn;
	}

	/**
	 * 是否含有此field
	 * 
	 * @param obj
	 *            操作的对象
	 * @param fieldName
	 *            操作的属性名称
	 * */
	public static boolean hasField(Class cls, String fieldName) {
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals(fieldName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 通过set方法设置属性名
	 * 
	 * @param obj
	 *            操作的对象
	 * @param att
	 *            操作的属性
	 * @param value
	 *            设置的值
	 * @param type
	 *            参数的属性
	 * */
	public static void setter(Object obj, String att, Object value, Class<?> type) {
		try {
			Method method = obj.getClass().getDeclaredMethod("set" + firstChatToBig(att), type);
			method.invoke(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String firstChatToBig(String str) {
		StringBuffer sbn = new StringBuffer(str);
		StringBuffer ss = new StringBuffer("");
		String s = sbn.toString();
		String[] sb = s.split(" ");
		for (int i = 0; i < sb.length; i++) {
			sb[i] = sb[i].substring(0, 1).toUpperCase() + sb[i].substring(1);
		}
		for (int i = 0; i < sb.length; i++) {
			ss.append(sb[i]);
			ss.append(" ");
		}
		return ss.toString().trim();
	}

	/**
	 * 获取类上的annotation对象
	 * 
	 * @param type
	 * @param annotationName
	 * @return
	 */
	public static Annotation getAnnotation(Class type, String annotationName) {
		Annotation[] annotations = type.getDeclaredAnnotations();
		for (Annotation anno : annotations) {
			if (anno.annotationType().getName().equals(annotationName)) {
				return anno;
			}
		}
		return null;
	}

	/**
	 * 获取变量上的annotation对象
	 * 
	 * @param field
	 * @param annotationName
	 * @return
	 */
	public static Annotation getAnnotation(Field field, String annotationName) {
		Annotation[] annotations = field.getDeclaredAnnotations();
		for (Annotation anno : annotations) {
			if (anno.annotationType().getName().equals(annotationName)) {
				return anno;
			}
		}
		return null;

	}

}
