package com.education.classroom.core.page.helper.parser.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;

import com.education.classroom.core.page.helper.Page;
import com.education.classroom.core.page.helper.SqlUtil;
import com.education.classroom.core.page.helper.parser.SqlServer;

/**
 * @author liuzh
 */
public class SqlServerParser extends AbstractParser {
    private static final SqlServer pageSql = new SqlServer();

    //with(nolock)
    protected static final String WITHNOLOCK = ", PAGEWITHNOLOCK";

    @Override
    public String getCountSql(String sql) {
        sql = sql.replaceAll("((?i)with\\s*\\(nolock\\))", WITHNOLOCK);
        sql = super.getCountSql(sql);
        sql = sql.replaceAll(WITHNOLOCK, " with(nolock)");
        return sql;
    }

    @Override
    public boolean isSupportedMappedStatementCache() {
        //由于sqlserver每次分页参数都是直接写入到sql语句中，因此不能缓存MS
        return false;
    }

    @Override
    public List<ParameterMapping> getPageParameterMapping(Configuration configuration, BoundSql boundSql) {
        return boundSql.getParameterMappings();
    }

    @Override
    public String getPageSql(String sql) {
        Page<?> page = SqlUtil.getLocalPage();
        sql = sql.replaceAll("((?i)with\\s*\\(nolock\\))", WITHNOLOCK);
        sql = pageSql.convertToPageSql(sql, page.getStartRow(), page.getPageSize());
        sql = sql.replaceAll(WITHNOLOCK, " with(nolock)");
        return sql;
    }

    @Override
    public Map<String, Object> setPageParameter(MappedStatement ms, Object parameterObject, BoundSql boundSql, Page<?> page) {
        return super.setPageParameter(ms, parameterObject, boundSql, page);
    }
}