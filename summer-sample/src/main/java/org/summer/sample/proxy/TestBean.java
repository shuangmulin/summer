package org.summer.sample.proxy;

/**
 * @author 钟宝林
 * @date 2018-11-07 13:16
 **/
public class TestBean implements TestInterface {
    @Override
    public void interfaceMethod() {
        System.out.println("invoke interfaceMethod");
    }

    final void testBeanMethod() {
        System.out.println("invoke testBeanMethod");
    }

}
