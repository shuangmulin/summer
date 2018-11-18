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
class BeanProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        InvokeCallback invokeCallback = () -> methodProxy.invokeSuper(proxy, args);
        InterceptorChain interceptorChain = new InterceptorChain(method, args, BeanContainer.getInstance().getBeanMethodInterceptors(), invokeCallback);
        return interceptorChain.doChain(interceptorChain).getReturnValue();
    }

}
