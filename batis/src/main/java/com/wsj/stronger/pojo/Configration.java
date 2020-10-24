package com.wsj.stronger.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @Author jiahao
 * @Date 2020/10/24 14:59
 */
public class Configration {

    private DataSource dataSource;

    /**
     * key namespace.id  value 每个标签
     */
    Map<String,MappedStatement> statementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getStatementMap() {
        return statementMap;
    }

    public void setStatementMap(Map<String, MappedStatement> statementMap) {
        this.statementMap = statementMap;
    }
}
