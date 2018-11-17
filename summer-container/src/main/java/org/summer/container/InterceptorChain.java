package org.summer.container;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 方法上下文
 *
 * @author 钟宝林
 * @date 2018-11-17 13:21
 **/
public class InterceptorChain {

    private List<BeanMethodInterceptor> beanMethodInterceptors;
    /**
     * 方法
     */
    private Method method;
    /**
     * 方法参数
     */
    private Object[] args;
    /**
     * 方法返回值, 提供给开发者修改
     */
    private Object returnValue;
    /**
     * 实际方法调用
     */
    private InvokeCallback invokeCallback;

    private int currentIndex;

    InterceptorChain(Method method,
                     Object[] args,
                     List<BeanMethodInterceptor> beanMethodInterceptors,
                     InvokeCallback invokeCallback) {
        this.method = method;
        this.args = args;
        this.beanMethodInterceptors = beanMethodInterceptors;
        this.invokeCallback = invokeCallback;
    }

    public InterceptorChain doChain(InterceptorChain chain) throws Throwable {
        if (currentIndex == beanMethodInterceptors.size())
            this.returnValue = invokeCallback.invoke();
        else {
            currentIndex++;
            beanMethodInterceptors.get(currentIndex - 1).doIntercept(this);
        }
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public Object[] getArgs() {
        return args;
    }
}
