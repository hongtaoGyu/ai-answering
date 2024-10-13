package com.hongtao.base.baseFramwork.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCondition {
    public String value;
    public String name;
    public String type;
}
