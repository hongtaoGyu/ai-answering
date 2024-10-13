package com.hongtao.base.baseFramwork.core.dto;

import com.hongtao.base.baseFramwork.core.SearchCondition;
import lombok.Data;

import java.util.List;

@Data
public class GetDataListRequest {
    public List<SearchCondition> searchCondition;
    public String queryText;
    public Integer pageSize;
    public Integer pageIndex;
}
