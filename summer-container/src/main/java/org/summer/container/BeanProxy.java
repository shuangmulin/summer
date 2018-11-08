package org.summer.container;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Bean代理类
 *
 * @author 钟宝林
 * @date 2018-11-07 14:37
 **/
public class BeanProxy implements MethodInterceptor {

    private Object bean;

    public BeanProxy(Object bean) {
        this.bean = bean;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        Object invokeResult = methodProxy.invokeSuper(bean, args);

        return invokeResult;
    }

}
