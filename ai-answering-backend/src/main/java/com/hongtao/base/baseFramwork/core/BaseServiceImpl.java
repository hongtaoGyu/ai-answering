package com.hongtao.base.baseFramwork.core;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtao.base.baseFramwork.core.constants.QueryConstant;
import com.hongtao.base.common.ErrorCode;
import com.hongtao.base.baseFramwork.core.constants.PluginConstant;
import com.hongtao.base.exception.BusinessException;
import com.hongtao.base.exception.ThrowUtils;
import com.hongtao.base.baseFramwork.user.model.entity.User;
import com.hongtao.base.baseFramwork.user.service.UserService;
import com.hongtao.base.utils.ReflectUtils;
import com.hongtao.base.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

@Transactional
@Slf4j
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {

    @Autowired
    protected M baseDao;

    @Resource
    private UserService userService;

    protected List<String> queryList;

    @Override
    public T getData(Long id) {
        ThrowUtils.throwIf(id == null, new BusinessException(ErrorCode.PARAMS_ERROR, "id为空"));

        T data = getById(id);
        ThrowUtils.throwIf(data == null, new BusinessException(ErrorCode.NOT_FOUND_ERROR, "未查询到数据"));
        return data;
    }

    @Override
    public boolean activatedData(Long id) {
        ThrowUtils.throwIf(id == null, new BusinessException(ErrorCode.PARAMS_ERROR, "id为空"));
        T data = getById(id);
        ThrowUtils.throwIf(data == null, new BusinessException(ErrorCode.NOT_FOUND_ERROR, "未查询到数据"));
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
        User loginUser = userService.getLoginUser();
        updateWrapper.eq(getTableName() + "Id", id).set("stateCode", "0").set("updateUserId", loginUser.getUserId())
                .set("updateUserName", loginUser.getUserName()).set("updateTime", System.currentTimeMillis());
        String pluginName = com.hongtao.base.utils.StringUtils.toCamelCase(data.getTableName()) + "Plugin";
        DataPlugin<T> dataPlugin = SpringContextUtils.getBean(pluginName, DataPlugin.class);
        if (dataPlugin == null) {
            ThrowUtils.throwIf(!update(updateWrapper), new BusinessException(ErrorCode.OPERATION_ERROR, "激活失败"));
        } else {
            dataPlugin.execute(PluginConstant.PRE_ACTIVATED, data);
            ThrowUtils.throwIf(!update(updateWrapper), new BusinessException(ErrorCode.OPERATION_ERROR, "激活失败"));
            dataPlugin.execute(PluginConstant.ACTIVATED, data);
        }

        return true;
    }

    @Override
    public boolean deactivatedData(Long id) {
        ThrowUtils.throwIf(id == null, new BusinessException(ErrorCode.PARAMS_ERROR, "id为空"));
        T data = getById(id);
        ThrowUtils.throwIf(data == null, new BusinessException(ErrorCode.NOT_FOUND_ERROR, "未查询到数据"));
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
        User loginUser = userService.getLoginUser();
        updateWrapper.eq(getTableName() + "Id", id).set("stateCode", "1").set("updateUserId", loginUser.getUserId())
                .set("updateUserName", loginUser.getUserName()).set("updateTime", LocalDateTime.now());
        String pluginName = com.hongtao.base.utils.StringUtils.toCamelCase(data.getTableName()) + "Plugin";
        DataPlugin<T> dataPlugin = SpringContextUtils.getBean(pluginName, DataPlugin.class);
        if (dataPlugin == null) {
            ThrowUtils.throwIf(!update(updateWrapper), new BusinessException(ErrorCode.OPERATION_ERROR, "禁用失败"));
        } else {
            dataPlugin.execute(PluginConstant.PRE_DEACTIVATED, data);
            ThrowUtils.throwIf(!update(updateWrapper), new BusinessException(ErrorCode.OPERATION_ERROR, "禁用失败"));
            dataPlugin.execute(PluginConstant.DEACTIVATED, data);
        }

        return true;
    }

    @Override
    public boolean deleteData(Long id) {
        ThrowUtils.throwIf(id == null, new BusinessException(ErrorCode.PARAMS_ERROR, "id为空"));
        T data = getById(id);
        ThrowUtils.throwIf(data == null, new BusinessException(ErrorCode.NOT_FOUND_ERROR, "未查询到数据"));
        String pluginName = com.hongtao.base.utils.StringUtils.toCamelCase(data.getTableName()) + "Plugin";
        DataPlugin<T> dataPlugin = SpringContextUtils.getBean(pluginName, DataPlugin.class);
        if (dataPlugin == null) {
            ThrowUtils.throwIf(!removeById(data), new BusinessException(ErrorCode.OPERATION_ERROR, "删除失败"));
        } else {
            dataPlugin.execute(PluginConstant.PRE_DELETE, data);
            ThrowUtils.throwIf(!removeById(data), new BusinessException(ErrorCode.OPERATION_ERROR, "删除失败"));
            dataPlugin.execute(PluginConstant.DELETED, data);
        }

        return true;
    }

    @Override
    public T updateData(T t) {
        ThrowUtils.throwIf(t == null || t.getId() == null, new BusinessException(ErrorCode.PARAMS_ERROR, "未传入数据"));
        QueryWrapper<T> whereWrapper = new QueryWrapper<>();
        whereWrapper.eq(t.getTableName() + "Id", t.getId());
        User loginUser = userService.getLoginUser();
        t.setUpdateTime(new Date());
        t.setUpdateUserId(loginUser.getUserId());
        t.setUpdateUserName(loginUser.getUserName());

        String pluginName = t.getTableName() + "Plugin";
        DataPlugin<T> dataPlugin = SpringContextUtils.getBean(pluginName, DataPlugin.class);
        if (dataPlugin == null) {
            ThrowUtils.throwIf(!update(t, whereWrapper), new BusinessException(ErrorCode.OPERATION_ERROR, "保存失败"));
        } else {
            dataPlugin.execute(PluginConstant.PRE_UPDATE, t);
            ThrowUtils.throwIf(!update(t, whereWrapper), new BusinessException(ErrorCode.OPERATION_ERROR, "更新失败"));
            dataPlugin.execute(PluginConstant.UPDATED, t);
        }

        return t;
    }

    @Override
    public T createData(T t) {
        ThrowUtils.throwIf(t == null, new BusinessException(ErrorCode.PARAMS_ERROR, "未传入数据"));
        User loginUser = userService.getLoginUser();
        t.setCreateTime(new Date());
        t.setCreateUserId(loginUser.getUserId());
        t.setCreateUserName(loginUser.getUserName());
        String pluginName = com.hongtao.base.utils.StringUtils.toCamelCase(t.getTableName()) + "Plugin";
        DataPlugin<T> dataPlugin = SpringContextUtils.getBean(pluginName, DataPlugin.class);
        if (dataPlugin == null) {
            ThrowUtils.throwIf(!save(t), new BusinessException(ErrorCode.OPERATION_ERROR, "保存失败"));
        } else {
            dataPlugin.execute(PluginConstant.PRE_CREATE, t);
            ThrowUtils.throwIf(!save(t), new BusinessException(ErrorCode.OPERATION_ERROR, "保存失败"));
            dataPlugin.execute(PluginConstant.CREATED, t);
        }

        return t;
    }

    @Override
    public T saveOrUpdateData(T t) {
        ThrowUtils.throwIf(t == null, new BusinessException(ErrorCode.PARAMS_ERROR, "未传入数据"));
        if (t.getId() == null) {
            createData(t);
        } else {
            T data = getById(t.getId());
            if (data == null) {
                createData(t);
            } else {
                updateData(t);
            }
        }
        return t;
    }

    private String getTableName() {
        Class<T> tClass = (Class<T>) ReflectUtils.getTypeByClassGeneric(this.getClass(), 1);
        T t = ReflectUtils.newInstance(tClass);
        return t.getTableName();
    }

    @Override
    public Page<T> getDataList(List<SearchCondition> searchCondition, String queryText, Integer pageSize, Integer pageIndex) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(queryText) && queryList != null && !queryList.isEmpty()) {
            queryWrapper.and(qw -> {
                for (int i = 0; i < queryList.size(); i++) {
                    if (i != 0) {
                        qw.or();
                    }
                    qw.like(queryList.get(i), queryText);
                }
            });

        }
        if (searchCondition != null && !searchCondition.isEmpty()) {
            for (SearchCondition condition : searchCondition) {
                switch (condition.getType()) {
                    case QueryConstant.LIKE:
                        queryWrapper.like(condition.getName(), condition.getValue());
                        break;
                    case QueryConstant.EQUALS:
                        queryWrapper.eq(condition.getName(), condition.getValue());
                        break;
                    case QueryConstant.IN:
                        queryWrapper.in(condition.getName(), JSONUtil.parseArray(condition.getValue()));
                        break;
                    case QueryConstant.BETWEEN:
                        String[] values = condition.getValue().replace("[", "").replace("]", "").split(",");
                        queryWrapper.between(condition.getName(), values[0], values[1]);
                        break;
                    case QueryConstant.NOT_EQUALS:
                        queryWrapper.ne(condition.getName(), condition.getValue());
                        break;
                }
            }
        }
        return page(new Page<>(pageIndex, pageSize), queryWrapper);
    }

    @Override
    public boolean bulkCreateOrUpdateData(List<T> dataList, List<String> keyList) {
        // 假设T实现了必要的getter和setter方法
        List<T> allDataList = list();  // 获取数据库中所有记录

        // 创建一个Map，键为联合主键的字符串表示形式，值为对应的数据库记录
        Map<String, T> existingDataMap = new HashMap<>();
        for (T data : allDataList) {
            String key = getKeyString(data, keyList);
            existingDataMap.put(key, data);
        }

        List<T> updateList = new ArrayList<>();  // 预备更新的数据列表
        List<T> insertList = new ArrayList<>();  // 预备新建的数据列表

        for (T newData : dataList) {
            String key = getKeyString(newData, keyList);
            if (existingDataMap.containsKey(key)) {
                // 更新现有记录
                T existingData = existingDataMap.get(key);
                updateFields(existingData, newData);  // 更新除主键外的所有字段
                updateList.add(existingData);  // 放入预备更新的数据列表
            } else {
                // 新建记录
                insertList.add(newData);  // 放入预备新建的数据列表
            }
        }

        // 执行批量更新操作
        updateBatchById(updateList);

        // 执行批量插入操作
        saveBatch(insertList);

        return true;  // 根据实际需求返回适当的值
    }

    private String getKeyString(T data, List<String> keyList) {
        StringBuilder keyBuilder = new StringBuilder();
        for (String key : keyList) {
            Object keyValue = ReflectUtils.getFieldValue(data, key);  // 假设ReflectionUtil提供反射获取字段值的功能
            keyBuilder.append(keyValue).append("-");
        }
        return keyBuilder.toString();
    }

    // 假设的方法，用于更新除主键外的所有字段
    private void updateFields(T existingData, T newData) {
        // 实现具体逻辑，这里简化处理
        // 示例：更新所有字段
        for (Field field : newData.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object newValue = field.get(newData);
                field.set(existingData, newValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
