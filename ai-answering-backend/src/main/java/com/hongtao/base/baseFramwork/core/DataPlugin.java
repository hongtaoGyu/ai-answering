package com.hongtao.base.baseFramwork.core;

public interface DataPlugin<T extends BaseEntity> {

    void execute(String executeMode, T t);

}
