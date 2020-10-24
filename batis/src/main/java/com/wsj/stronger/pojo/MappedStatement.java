package com.wsj.stronger.pojo;

/**
 *
 * @Author jiahao
 * @Date 2020/10/24 14:52
 */
public class MappedStatement {
    private String id;
    private String parameterType;
    private String resultType;
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParmaterType(String parmaterType) {
        this.parameterType = parmaterType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
