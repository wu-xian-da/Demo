package com.jianfei.w.base.interceptor;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.jianfei.w.base.filter.page.PageContext;
import com.jianfei.w.entity.common.Page;
import com.jianfei.w.entity.common.PageParam;

/**
 * 分页拦截器
 * 拦截执行SQL，加入分页参数
* @author ZhangBo   
* @date 2017年3月29日 上午9:18:07
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PageInterceptor implements Interceptor {
    
    private final String COUNT_SQL = "select count(0) from (%s) _tmp_";
    private final String PAGE_SQL = "%s limit %s,%s";

    private final Set<String> cacheMapperPageId = new HashSet<>();
    private final Set<String> cacheMapperNotPageId = new HashSet<>();
    

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        String sql = statementHandler.getBoundSql().getSql();
        
        PageParam param = PageContext.getPageParam();
        int start = (param.getPn() - 1) * param.getPs();
        
        MetaObject routingMeta = SystemMetaObject.forObject(invocation.getTarget());
        ParameterHandler parameterHandler = (ParameterHandler)routingMeta.getValue("delegate.parameterHandler");
        
        Connection connection = (Connection)invocation.getArgs()[0];
        PreparedStatement countStatement = connection.prepareStatement(String.format(COUNT_SQL, sql));
        parameterHandler.setParameters(countStatement);
        ResultSet rs = countStatement.executeQuery();
        if(rs.next()){
            param.setTotalRecord(rs.getLong(1));
        }
        
        routingMeta.setValue("delegate.boundSql.sql", String.format(PAGE_SQL, sql, start, param.getPs()));
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        if (target instanceof RoutingStatementHandler) {
            MetaObject routingMeta = SystemMetaObject.forObject(target);
            MappedStatement mappedStatement = (MappedStatement)routingMeta.getValue("delegate.mappedStatement");
            String id = mappedStatement.getId(); //Mapper Select Id

            if (cacheMapperPageId.contains(id)) {
                return Plugin.wrap(target, this);
            }

            if (cacheMapperNotPageId.contains(id)) {
                return target;
            }

            String className = id.substring(0, id.lastIndexOf("."));
            String methodName = id.substring(id.lastIndexOf(".") + 1);
            try {
                Class<?> daoClass = Class.forName(className);
                Method daoClassMethods[] = daoClass.getMethods();
                for (Method method : daoClassMethods) {
                    if (method.getName().equals(methodName) && method.getReturnType() == Page.class) {
                        //针对Mybatis Mapper 返回值为Page类型的 进行分页拦截代理
                        cacheMapperPageId.add(id);
                        return Plugin.wrap(target, this);
                    }
                }
                cacheMapperNotPageId.add(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return target;
    }

    public void setProperties(Properties properties) {

    }
}
