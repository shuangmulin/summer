package org.summer.sample;

import org.summer.container.ContainerStarter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        ContainerStarter containerStarter = new ContainerStarter();
        containerStarter.start("org.summer.sample");
    }
}
