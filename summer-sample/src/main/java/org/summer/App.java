package org.summer;

import org.summer.scan.ClassResource;
import org.summer.scan.ClassResourceScanner;

import java.io.IOException;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, ClassNotFoundException {
        ClassResourceScanner scanFile = new ClassResourceScanner();
        Set<ClassResource> classResources = scanFile.scan("org.jsoup");
        System.out.println(classResources);
    }
}
