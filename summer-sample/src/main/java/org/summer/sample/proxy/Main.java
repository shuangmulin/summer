package org.summer.sample.proxy;

import net.sf.cglib.core.DefaultNamingPolicy;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * @author 钟宝林
 * @date 2018-11-07 13:23
 **/
public class Main {

    public static void main(String[] args) {
        TestBean testBean = new TestBean();
        TestInterface o = (TestInterface) Proxy.newProxyInstance(TestBeanProxy.class.getClassLoader(), TestBean.class.getInterfaces(), new TestBeanProxy(new TestBean()));
        o.interfaceMethod();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(testBean.getClass());
        enhancer.setCallback(new CglibProxy(testBean));
        enhancer.setNamingPolicy(new DefaultNamingPolicy() {
            public String getTag() {
                return "BySummer";
            }
            public String toString() {
                return getTag();
            }
        });
        TestInterface testBeanf =(TestInterface) enhancer.create();
        testBeanf.interfaceMethod();
    }

}
