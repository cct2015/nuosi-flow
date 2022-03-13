package com.nuosi.flow.logic.inject.function.impl;

import com.ai.ipu.database.dao.IpuDaoManager;
import com.ai.ipu.database.dao.impl.CrudDao;
import com.nuosi.flow.logic.inject.function.AbstractDomainFunction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>desc: 数据持久域的函数功能实现 </p>
 * <p>date: 2021/5/17 23:43 </p>
 *
 * @author nuosi fsofs@163.com
 * @version v1.0.0
 */
public class DbDomainFunction extends AbstractDomainFunction {
    static final String lowercase = "lowercase";
    static final String uppercase = "uppercase";

    public Map<String, Object> select(String connName, String tableName, Map<String, Object> data, boolean isMatchColumn, String conver) throws Exception {
        if (uppercase.equals(conver)) {
            data = keyToUpperCase(data);
        } else if (lowercase.equals(conver)) {
            data = keyToLowerCase(data);
        }
        CrudDao dao = IpuDaoManager.takeCrudDao(connName);
        return dao.select(tableName, data, isMatchColumn);
    }

    public Map<String, Object> select(String connName, String tableName, Map<String, Object> data, String conver) throws Exception {
        return select(connName, tableName, data, true, conver);
    }

    public Map<String, Object> select(String connName, String tableName, Map<String, Object> data, boolean isMatchColumn) throws Exception {
        CrudDao dao = IpuDaoManager.takeCrudDao(connName);
        return dao.select(tableName, data, isMatchColumn);
    }

    public Map<String, Object> select(String connName, String tableName, Map<String, Object> data) throws Exception {
        return select(connName, tableName, data, true);
    }

    public int insert(String connName, String tableName, Map<String, Object> data, String conver) throws Exception {
        if (uppercase.equals(conver)) {
            data = keyToUpperCase(data);
        } else if (lowercase.equals(conver)) {
            data = keyToLowerCase(data);
        }
        CrudDao dao = IpuDaoManager.takeCrudDao(connName);
        return dao.insert(tableName, data);
    }

    public int insert(String connName, String tableName, Map<String, Object> data) throws Exception {
        return insert(connName, tableName, data, null);
    }

    public int update(String connName, String tableName, Map<String, Object> data, boolean isMatchColumn, String conver) throws Exception {
        if (uppercase.equals(conver)) {
            data = keyToUpperCase(data);
        } else if (lowercase.equals(conver)) {
            data = keyToLowerCase(data);
        }
        CrudDao dao = IpuDaoManager.takeCrudDao(connName);
        return dao.update(tableName, data, isMatchColumn);
    }

    public int update(String connName, String tableName, Map<String, Object> data, String conver) throws Exception {
        return update(connName, tableName, data, conver);
    }

    public int update(String connName, String tableName, Map<String, Object> data, boolean isMatchColumn) throws Exception {
        CrudDao dao = IpuDaoManager.takeCrudDao(connName);
        return dao.update(tableName, data, isMatchColumn);
    }

    public int update(String connName, String tableName, Map<String, Object> data) throws Exception {
        return update(connName, tableName, data, true);
    }

    public int delete(String connName, String tableName, Map<String, Object> data, boolean isMatchColumn, String conver) throws Exception {
        if (uppercase.equals(conver)) {
            data = keyToUpperCase(data);
        } else if (lowercase.equals(conver)) {
            data = keyToLowerCase(data);
        }
        CrudDao dao = IpuDaoManager.takeCrudDao(connName);
        return dao.delete(tableName, data, isMatchColumn);
    }

    public int delete(String connName, String tableName, Map<String, Object> data, String conver) throws Exception {
        return delete(connName, tableName, data, conver);
    }

    public int delete(String connName, String tableName, Map<String, Object> data, boolean isMatchColumn) throws Exception {
        CrudDao dao = IpuDaoManager.takeCrudDao(connName);
        return dao.delete(tableName, data, isMatchColumn);
    }

    public int delete(String connName, String tableName, Map<String, Object> data) throws Exception {
        return delete(connName, tableName, data, true);
    }

    public int deleteByCond(String connName, String tableName, Map<String, Object> condition, String conver) throws Exception {
        if (uppercase.equals(conver)) {
            condition = keyToUpperCase(condition);
        } else if (lowercase.equals(conver)) {
            condition = keyToLowerCase(condition);
        }
        CrudDao dao = IpuDaoManager.takeCrudDao(connName);
        return dao.deleteByCond(tableName, condition);
    }

    public int deleteByCond(String connName, String tableName, Map<String, Object> condition) throws Exception {
        CrudDao dao = IpuDaoManager.takeCrudDao(connName);
        return dao.deleteByCond(tableName, condition);
    }

    public List<Map<String, Object>> selectByCond(String connName, String tableName, Map<String, Object> condition, String conver) throws Exception {
        if (uppercase.equals(conver)) {
            condition = keyToUpperCase(condition);
        } else if (lowercase.equals(conver)) {
            condition = keyToLowerCase(condition);
        }
        CrudDao dao = IpuDaoManager.takeCrudDao(connName);
        return dao.selectByCond(tableName, condition);
    }

    public List<Map<String, Object>> selectByCond(String connName, String tableName, Map<String, Object> condition) throws Exception {
        CrudDao dao = IpuDaoManager.takeCrudDao(connName);
        return dao.selectByCond(tableName, condition);
    }

    public int updateByCond(String connName, String tableName, Map<String, Object> data, Map<String, Object> condition, String conver) throws Exception {
        if (uppercase.equals(conver)) {
            data = keyToUpperCase(data);
            condition = keyToUpperCase(condition);
        } else if (lowercase.equals(conver)) {
            data = keyToLowerCase(data);
            condition = keyToLowerCase(condition);
        }
        CrudDao dao = IpuDaoManager.takeCrudDao(connName);
        return dao.updateByCond(tableName, data, condition);
    }

    public int updateByCond(String connName, String tableName, Map<String, Object> data, Map<String, Object> condition) throws Exception {
        CrudDao dao = IpuDaoManager.takeCrudDao(connName);
        return dao.updateByCond(tableName, data, condition);
    }

    public static Map<String, Object> keyToLowerCase(Map<String, Object> orgMap) {
        Map<String, Object> resultMap = new HashMap<>();

        if (orgMap == null || orgMap.isEmpty()) {
            return resultMap;
        }
        Set<Map.Entry<String, Object>> entrySet = orgMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            resultMap.put(key.toLowerCase(), value);
        }
        return resultMap;
    }

    public static Map<String, Object> keyToUpperCase(Map<String, Object> orgMap) {
        Map<String, Object> resultMap = new HashMap<>();
        if (orgMap == null || orgMap.isEmpty()) {
            return resultMap;
        }
        Set<Map.Entry<String, Object>> entrySet = orgMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            resultMap.put(key.toUpperCase(), value);
        }
        return resultMap;
    }


}
