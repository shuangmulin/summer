package org.summer.sample.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 钟宝林
 * @date 2018-11-07 13:23
 **/
public class TestBeanProxy implements InvocationHandler {

    private TestBean testBean;

    public TestBeanProxy(TestBean testBean) {
        this.testBean = testBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());

        method.invoke(testBean, args);
        return null;
    }
}
