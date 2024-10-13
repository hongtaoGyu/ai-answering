package com.hongtao.base.baseFramwork.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongtao.base.baseFramwork.core.dto.GetDataListRequest;
import com.hongtao.base.common.BaseResponse;
import com.hongtao.base.common.ErrorCode;
import com.hongtao.base.common.ResultUtils;
import com.hongtao.base.exception.BusinessException;
import com.hongtao.base.exception.ThrowUtils;
import com.hongtao.base.utils.ReflectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Transactional
public abstract class BaseController<T extends BaseEntity, E extends BaseVO, U extends BaseService<T>> {
    @Autowired
    protected BaseService<T> baseService;

    @GetMapping("/getData")
    public BaseResponse<E> getData(@RequestParam Long id) {
        ThrowUtils.throwIf(id == null, new BusinessException(ErrorCode.PARAMS_ERROR, "id为空"));
        T data = baseService.getData(id);
        E vo = getVO(data);

        return ResultUtils.success(vo);
    }

    @PostMapping("/createData")
    public BaseResponse<E> createData(@RequestBody E e, HttpServletRequest request) {
        T t = getEntity(e);
        BeanUtils.copyProperties(e, t);
        T data = baseService.createData(t);
        E vo = getVO(data);

        return ResultUtils.success(vo);
    }

    @PostMapping("/updateData")
    public BaseResponse<E> updateData(@RequestBody E e, HttpServletRequest request) {
        T t = getEntity(e);
        BeanUtils.copyProperties(e, t);
        T data = baseService.updateData(t);
        E vo = getVO(data);
        return ResultUtils.success(vo);
    }

    @PostMapping("/saveOrUpdateData")
    public BaseResponse<E> saveOrUpdateData(@RequestBody E e, HttpServletRequest request) {
        T t = getEntity(e);
        BeanUtils.copyProperties(e, t);
        T data = baseService.saveOrUpdateData(t);
        E vo = getVO(data);
        return ResultUtils.success(vo);
    }

    @GetMapping("/deleteData")
    public BaseResponse<E> deleteData(@RequestParam Long id) {
        ThrowUtils.throwIf(id == null, new BusinessException(ErrorCode.PARAMS_ERROR, "id为空"));
        ThrowUtils.throwIf(!baseService.deleteData(id), new BusinessException(ErrorCode.OPERATION_ERROR, "删除失败"));

        return ResultUtils.success(null);
    }

    @GetMapping("/activatedData")
    public BaseResponse<E> activatedData(@RequestParam Long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id == null, new BusinessException(ErrorCode.PARAMS_ERROR, "id为空"));
        ThrowUtils.throwIf(!baseService.activatedData(id), new BusinessException(ErrorCode.OPERATION_ERROR, "删除失败"));

        return ResultUtils.success(null);
    }

    @GetMapping("/deactivatedData")
    public BaseResponse<E> deactivatedData(@RequestParam Long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id == null, new BusinessException(ErrorCode.PARAMS_ERROR, "id为空"));
        ThrowUtils.throwIf(!baseService.deactivatedData(id), new BusinessException(ErrorCode.OPERATION_ERROR, "删除失败"));

        return ResultUtils.success(null);
    }

    @PostMapping("/getDataList")
    public BaseResponse<Page<T>> getDataList(@RequestBody GetDataListRequest getDataListRequest) {
        Page<T> dataList = baseService.getDataList(getDataListRequest.getSearchCondition(), getDataListRequest.getQueryText(), getDataListRequest.getPageSize(), getDataListRequest.getPageIndex());
        return ResultUtils.success(dataList);
    }


    protected E getVO(T t) {
        if (t == null) {
            return null;
        }
        Class<E> typeByClassGeneric = (Class<E>) ReflectUtils.getTypeByClassGeneric(this.getClass(), 1);
        E vo = ReflectUtils.newInstance(typeByClassGeneric);
        BeanUtils.copyProperties(t, vo);
        return vo;
    }

    protected T getEntity(E e) {
        if (e == null) {
            return null;
        }
        Class<T> typeByClassGeneric = (Class<T>) ReflectUtils.getTypeByClassGeneric(this.getClass(), 0);
        T t = ReflectUtils.newInstance(typeByClassGeneric);
        BeanUtils.copyProperties(e, t);
        return t;
    }
}
