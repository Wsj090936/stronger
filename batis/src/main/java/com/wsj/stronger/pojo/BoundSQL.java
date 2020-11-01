package com.wsj.stronger.pojo;

import com.wsj.stronger.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Author jiahao
 * @Date 2020/11/1 20:47
 */
public class BoundSQL {
    String parseSql;
    List<ParameterMapping> parameterMappings = new ArrayList<>();

    public BoundSQL(String parseSql, List<ParameterMapping> parameterMappings) {
        this.parseSql = parseSql;
        this.parameterMappings = parameterMappings;
    }

    public String getParseSql() {
        return parseSql;
    }

    public void setParseSql(String parseSql) {
        this.parseSql = parseSql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
