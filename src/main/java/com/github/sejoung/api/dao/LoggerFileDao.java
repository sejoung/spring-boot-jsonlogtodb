package com.github.sejoung.api.dao;

import java.util.List;
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
    
    public int insertMobCnvrsStats(Map<String, Object> data) {
        return this.sqlSessionTemplate.insert("insertMobCnvrsStats", data);
    }
    
    public int updatetest(Map<String, Object> data) {
        return this.sqlSessionTemplate.update("updatetest", data);
    }
    public int insertTest(Map<String, Object> data) {
        return this.sqlSessionTemplate.insert("insertTest", data);
    }
    
    public int insertWebpcode(Map<String, Object> data) {
        return this.sqlSessionTemplate.insert("insertWebpcode", data);
    }
    
    public int insertPcode(Map<String, Object> data) {
        return this.sqlSessionTemplate.insert("insertPcode", data);
    }
    
    public int insertMopcode(Map<String, Object> data) {
        return this.sqlSessionTemplate.insert("insertMopcode", data);
    }
    
    public List<String> selectPcode(Map<String, Object> data) {
        return this.sqlSessionTemplate.selectList("selectPcode", data);

    }
    
    public List<Map<String,Object>> selectPcodes(String data) {
        return this.sqlSessionTemplate.selectList("selectPcodes", data);

    }
    
}
