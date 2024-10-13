package com.hongtao.base.baseFramwork.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BaseService<T extends BaseEntity> extends IService<T> {
    /**
     * 根据主键id查询实体数据
     *
     * @param id 主键id
     * @return 实体数据
     */
    T getData(Long id);

    /**
     * 根据主键id激活数据
     *
     * @param id      主键id
     * @return 是否成功
     */
    boolean activatedData(Long id);

    /**
     * 根据主键id停用数据
     *
     * @param id      主键id
     * @return 是否成功
     */
    boolean deactivatedData(Long id);

    /**
     * 根据主键id删除数据
     *
     * @param id 主键id
     * @return 是否成功
     */
    boolean deleteData(Long id);

    /**
     * 更新数据
     *
     * @param t       实体数据

     * @return 更新后的数据
     */
    T updateData(T t);

    /**
     * 创建数据
     *
     * @param t       实体数据
     * @return 创建后的数据
     */
    T createData(T t);

    /**
     * 新建或更新数据
     *
     * @param t       实体数据
     * @return 新建或更新后的数据
     */
    T saveOrUpdateData(T t);

    /**
     * 列表查询分页
     *
     * @param searchCondition 查询条件
     * @param queryText       查询文本
     * @param pageSize        每页数量
     * @param pageIndex       页码
     * @return 数据列表
     */
    Page<T> getDataList(List<SearchCondition> searchCondition, String queryText, Integer pageSize, Integer pageIndex);

    /**
     * 根据业务主键更新或
     *
     * @param dataList 待处理的数据
     * @param keyList  业务主键列表
     * @return 是否成功
     */
    boolean bulkCreateOrUpdateData(List<T> dataList, List<String> keyList);
}
