package com.aynu.data.common.bean;

import com.alibaba.druid.proxy.jdbc.ClobProxyImpl;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
public class GenericBean extends HashMap implements Map, Serializable {
    private static final long serialVersionUID = 7948605344374482056L;
    private Map map;
    private HttpServletRequest request;

    public GenericBean(HttpServletRequest request) {
        this.request = request;
        Map properties = request.getParameterMap();
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();

        String name;
        for(String value = ""; entries.hasNext(); returnMap.put(name, value)) {
            Entry entry = (Entry)entries.next();
            name = (String)entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (!(valueObj instanceof String[])) {
                value = valueObj.toString();
            } else {
                String[] values = (String[])((String[])valueObj);
                String[] var10 = values;
                int var11 = values.length;

                for(int var12 = 0; var12 < var11; ++var12) {
                    String value1 = var10[var12];
                    value = value1 + ",";
                }

                value = value.substring(0, value.length() - 1);
            }
        }

        this.map = returnMap;
    }

    public GenericBean() {
        this.map = new HashMap();
    }

    public GenericBean(Object key, Object value) {
        this.map = new HashMap();
        this.put(key, value);
    }

    public Object get(Object key) {
        Object obj = null;
        if (this.map.get(key) instanceof Object[]) {
            Object[] arr = (Object[])((Object[])this.map.get(key));
            obj = this.request == null ? arr : (this.request.getParameter((String)key) == null ? arr : arr[0]);
        } else {
            obj = this.map.get(key);
        }

        return obj;
    }

    public String getString(Object key) {
        Object val = this.get(key);
        return val != null && !"".equals(val) ? val.toString() : "";
    }

    public Double getDouble(Object key) {
        Object val = this.get(key);
        return val != null && !"".equals(val) ? Double.valueOf(val.toString()) : 0.0D;
    }

    public Integer getInt(Object key) {
        Object val = this.get(key);
        return val != null && !"".equals(val) ? Integer.valueOf(val.toString()) : 0;
    }

    public Boolean getBoolean(Object key) {
        Object val = this.get(key);
        return val != null && !"".equals(val) ? Boolean.parseBoolean(val.toString()) : false;
    }

    public Object put(Object key, Object value) {
        if (value instanceof ClobProxyImpl) {
            try {
                ClobProxyImpl cpi = (ClobProxyImpl)value;
                Reader is = cpi.getCharacterStream();
                BufferedReader br = new BufferedReader(is);
                String str = br.readLine();

                StringBuffer sb;
                for(sb = new StringBuffer(); str != null; str = br.readLine()) {
                    sb.append(str);
                    sb.append("\n");
                }

                value = sb.toString();
            } catch (Exception var8) {
                var8.printStackTrace();
            }
        }

        return this.map.put(key, value);
    }

    public Object remove(Object key) {
        return this.map.remove(key);
    }

    public void clear() {
        this.map.clear();
    }

    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    public Set entrySet() {
        return this.map.entrySet();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Set keySet() {
        return this.map.keySet();
    }

    public void putAll(Map t) {
        this.map.putAll(t);
    }

    public int size() {
        return this.map.size();
    }

    public Collection values() {
        return this.map.values();
    }
}

