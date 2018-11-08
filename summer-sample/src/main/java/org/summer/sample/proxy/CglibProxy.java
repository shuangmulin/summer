package org.summer.sample.proxy;

import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 钟宝林
 * @date 2018-11-07 14:03
 **/
public class CglibProxy implements MethodInterceptor {

    private Object bean;

    public CglibProxy(Object bean) {
        this.bean = bean;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName());
        String superName = methodProxy.getSuperName();
        System.out.println(superName);
        int superIndex = methodProxy.getSuperIndex();
        System.out.println(superIndex);
        Signature signature = methodProxy.getSignature();
        String name = signature.getName();
        System.out.println(name);
        return method.invoke(bean, objects);
    }
}
