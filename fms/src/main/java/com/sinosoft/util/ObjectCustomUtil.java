package com.sinosoft.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectCustomUtil {
    public static Field[] getAllFields(Class<?> cl) {
        Field[] field = cl == null ? null : cl.getDeclaredFields();
        return field;
    }

    public static Field getFieldByName(Class<?> cl, String name) throws Exception {
        if (name == null || name.equals(""))
            return null;

        Field field = cl == null ? null : cl.getDeclaredField(name);
        return field;
    }

    /**
     * 根据属性对象获取属性值
     * 
     * @param field
     * @param obj
     * @return
     */
    public static Object getValueByField(Field field, Object obj) {
        Object result = null;
        try {
            String name = field.getName();
            String stringLetter = name.substring(0, 1).toUpperCase();
            String getName = "get" + stringLetter + name.substring(1);
            Method getmethod0 = obj.getClass().getMethod(getName);
            result = getmethod0.invoke(obj);

        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据属性名称获取属性值
     * 
     * @param name
     * @param obj
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static Object getValueByFieldName(String name, Object obj) {
        Object result = null;
        try {
            if (obj.getClass().getName().equals(Map.class.getName()) || obj.getClass().getName().equals(HashMap.class.getName())) {
                Map dataMap = (Map)obj;
            	if(dataMap.containsKey(name)){
            		return dataMap.get(name);
                }else{
                	return "";
                }
            }
            String stringLetter = name.substring(0, 1).toUpperCase();
            String getName = "get" + stringLetter + name.substring(1);
            Method getmethod0 = obj.getClass().getMethod(getName);
            result = getmethod0.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据属性,给对象赋值
     * 
     * @param name
     * @param obj
     * @return
     */
    public static Object setValueByField(Field field, Object bean, Object value) {
        Object result = null;
        try {
            String name = field.getName();
            String stringLetter = name.substring(0, 1).toUpperCase();
            String setName = "set" + stringLetter + name.substring(1);
            Method method = bean.getClass().getMethod(setName, field.getType());
            if (method != null) {
                if (value.getClass().getName().equals("org.json.JSONObject$Null")) {
                } else if (field.getType().getName().equals(String.class.getName())) {
                    method.invoke(bean, value.toString());
                } else if (field.getType().getName().equals(int.class.getName()) || field.getType().getName().equals(Integer.class.getName())) {
                    method.invoke(bean, Integer.parseInt(StringUtils.isNull(value.toString()) ? "0" : value.toString()));
                } else if (field.getType().getName().equals(float.class.getName()) || field.getType().getName().equals(Float.class.getName())) {
                    method.invoke(bean, Float.parseFloat(StringUtils.isNull(value.toString()) ? "0" : value.toString()));
                } else if (field.getType().getName().equals(double.class.getName()) || field.getType().getName().equals(Double.class.getName())) {
                    method.invoke(bean, Double.parseDouble(StringUtils.isNull(value.toString()) ? "0" : value.toString()));
                } else if (field.getType().getName().equals(long.class.getName()) || field.getType().getName().equals(Long.class.getName())) {
                    method.invoke(bean, Long.parseLong(StringUtils.isNull(value.toString()) ? "0" : value.toString()));
                } else if (field.getType().getName().equals(Date.class.getName())) {
                    method.invoke(bean, StringUtils.isNull(value.toString()) ? null : DateFormat.getDateInstance().parse(value.toString()));
                } else if (field.getType().getName().equals(BigDecimal.class.getName())) {
                    method.invoke(bean, StringUtils.isNull(value.toString()) ? null : BigDecimal.valueOf(Double.parseDouble(value.toString())));
                } else {
                    method.invoke(bean, value);
                }
            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }

    public static String getSimpleNameByClassName(String classname) {

        try {
            return Class.forName(classname).getSimpleName();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] arg) {
        // Field[] fields = getAllFields(Aircrafttype.class);
        //
        // for (Field f : fields) {
        // System.out.println("name=\"" + f.getName() + "\"");
        // }
        // Aircrafttype arg0 = new Aircrafttype();
        // arg0.setAircraftModel("aaa");
        // arg0.setAircrafttypeId(1234);
        //
        // Aircrafttype arg1 = new Aircrafttype();
        // // arg1.setAircraftModel("aaa");
        // arg1.setAircrafttypeId(1235);
        // // arg0.getAircraftSize()
        // copyNotNull(arg0, arg1);
        // try {
        // // System.out.println(ObjectPropertyCompare(arg0, arg1));
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }

    /**
     * 对象属性比较，确保两对象属于同一类型
     * 
     * @param arg0
     * @param arg1
     * @return
     */
    public static List<String> ObjectPropertyCompare(Object arg0, Object arg1) throws Exception {
        List<String> result = null;

        if (!arg0.getClass().getName().equals(arg1.getClass().getName())) {
            throw new Exception("两个对象不是同一类型，没法比较");
        }
        Field[] fields = getAllFields(arg0.getClass());
        if (fields != null && fields.length > 0)
            result = new ArrayList<String>();
        for (Field f : fields) {
            String name = f.getName();
            String stringLetter = name.substring(0, 1).toUpperCase();
            String getName = "get" + stringLetter + name.substring(1);
            Method method = arg0.getClass().getMethod(getName);

            Object value0 = method.invoke(arg0);
            Object value1 = method.invoke(arg1);

            if (value0 == null && value1 == null) {
            } else if (value0 == null && value1 != null) {
                if (value1.getClass().getName().equals(String.class.getName())) {
                    if (StringUtils.isNull(value1.toString())) {
                        continue;
                    }
                }
                result.add(name);
            } else if (value0 != null && value1 == null) {
                if (value0.getClass().getName().equals(String.class.getName())) {
                    if (StringUtils.isNull(value0.toString())) {
                        continue;
                    }
                }
                result.add(name);
            } else if (!value0.equals(value1))
                result.add(name);
        }

        return result;
    }

    /**
     * 对象属性复制(只为为空的属性复制)
     * 
     * @param arg0
     *            被复制对象
     * @param arg1
     *            复制原对象
     * @return
     */
    public static Object copyNotNull(Object arg0, Object arg1) {
        // arg1.getClass().getDeclaredFields();
        Field[] fields = getAllFields(arg1.getClass());

        try {
            for (Field f : fields) {
                String typename = f.getType().getName();
                Field f0 = null;
                try {
                    f0 = arg0.getClass().getDeclaredField(f.getName());
                } catch (NoSuchFieldException e) {
                    continue;
                }
                String name = f.getName();
                String stringLetter = name.substring(0, 1).toUpperCase();

                String getName = "get" + stringLetter + name.substring(1);
                String setName = "set" + stringLetter + name.substring(1);
                try {
                    Method setmethod1 = arg1.getClass().getMethod(setName, f.getType());
                    Method getmethod1 = arg1.getClass().getMethod(getName);

                    Method getmethod0 = arg0.getClass().getMethod(getName);

                    if (typename.equals(int.class.getName()) || typename.equals(Integer.class.getName()) || typename.equals(float.class.getName()) || typename.equals(Float.class.getName()) || typename.equals(double.class.getName()) || typename.equals(Double.class.getName())) {

                        if (getmethod1.invoke(arg1).equals(0) && f0 != null && f.getType().getName().equals(f0.getType().getName())) {
                            setmethod1.invoke(arg1, getmethod0.invoke(arg0));
                        }
                    } else if (getmethod1.invoke(arg1) == null) {
                        if (f0 != null && f.getType().getName().equals(f0.getType().getName())) {
                            setmethod1.invoke(arg1, getmethod0.invoke(arg0));
                        }
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return arg1;
    }
}