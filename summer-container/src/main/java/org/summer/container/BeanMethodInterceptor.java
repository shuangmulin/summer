package org.summer.container;

/**
 * Bean方法拦截器
 *
 * @author 钟宝林
 * @date 2018-11-09 13:33
 **/
public interface BeanMethodInterceptor extends Order {

    void doIntercept(InterceptorChain interceptorChain);

}
