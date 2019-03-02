package com.chong.bys.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明：数据源切换aop
 *
 * @author : <a href="mailto:lichong@zjport.gov.cn">lichong</a>
 * @version : 1.0
 * @since 3.0
 */
@Slf4j
@Aspect
@Component
@Order
public class DataSourceAspect {

    private List<String> slaveMethodPattern = new ArrayList<String>();

    private static final String[] defaultSlaveMethodStart = new String[]{ "query", "find", "get","load","select" };

    private String[] slaveMethodStart;

    @Pointcut("execution(* com.chong.bys.*.dao..*.*(..))")
    public void cutDao() {}

    /**
     * 在进入Service方法之前执行
     *
     * @param point 切面对象
     */
    @Before("cutDao()")
    public void before(JoinPoint point) {
        log.info("执行数据库切换aop");
        // 获取到当前执行的方法名
        String methodName = point.getSignature().getName();
        boolean isSlave = false;
        if (slaveMethodPattern.isEmpty()) {
            // 当前Spring容器中没有配置事务策略，采用方法名匹配方式
            isSlave = isSlave(methodName);
        } else {
            // 使用策略规则匹配
            for (String mappedName : slaveMethodPattern) {
                if (isMatch(methodName, mappedName)) {
                    isSlave = true;
                    break;
                }
            }
        }
        if (isSlave) {
            // 标记为读库
            DynamicDataSourceHolder.markSlave();
        } else {
            // 标记为写库
            DynamicDataSourceHolder.markMaster();
        }
    }

    @After("cutDao()")
    private void after(){
        log.info("清除ThreadLocal中数据源标识");
        DynamicDataSourceHolder.clearCurrentDb();
    }

    /**
     * 判断是否为读库
     *
     * @param methodName
     * @return
     */
    private Boolean isSlave(String methodName) {
        // 方法名以query、find、get开头的方法名走从库
        return StringUtils.startsWithAny(methodName, getSlaveMethodStart());
    }

    /**
     * 通配符匹配
     *
     * Return if the given method name matches the mapped name.
     * <p>
     * The default implementation checks for "xxx*", "*xxx" and "*xxx*" matches, as well as direct
     * equality. Can be overridden in subclasses.
     *
     * @param methodName the method name of the class
     * @param mappedName the name in the descriptor
     * @return if the names match
     * @see PatternMatchUtils#simpleMatch(String, String)
     */
    private boolean isMatch(String methodName, String mappedName) {
        return PatternMatchUtils.simpleMatch(mappedName, methodName);
    }

    /**
     * 用户指定slave的方法名前缀
     * @param slaveMethodStart
     */
    public void setSlaveMethodStart(String[] slaveMethodStart) {
        this.slaveMethodStart = slaveMethodStart;
    }

    private String[] getSlaveMethodStart() {
        if(this.slaveMethodStart == null){
            // 没有指定，使用默认
            return defaultSlaveMethodStart;
        }
        return slaveMethodStart;
    }
}
