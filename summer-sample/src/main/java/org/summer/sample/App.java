package org.summer.sample;

import org.summer.container.BeanContainer;
import org.summer.container.ContainerStarter;
import org.summer.sample.controller.UserController;
import org.summer.sample.entity.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        ContainerStarter containerStarter = new ContainerStarter();
        containerStarter.start("org.summer.sample");
        UserController userController = (UserController) BeanContainer.getInstance().getBeanByName("userController");
        User user = userController.getUser();
        System.out.println(user);
    }
}
