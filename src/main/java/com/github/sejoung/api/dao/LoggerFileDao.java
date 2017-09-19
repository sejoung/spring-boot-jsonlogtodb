package com.github.sejoung.api.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoggerFileDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    
    public int insertAdlinkRcdStatusView(Map<String, Object> data) {
        return this.sqlSessionTemplate.insert("insertAdlinkRcdStatusView", data);
    }

    public int insertAdlinkRcdStatusClick(Map<String, Object> data) {
        return this.sqlSessionTemplate.insert("insertAdlinkRcdStatusClick", data);
    }
    
    
}
