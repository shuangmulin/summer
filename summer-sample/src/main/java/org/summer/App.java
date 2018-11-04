package org.summer;

import org.summer.scan.ScanFile;
import org.summer.scan.ScanFileImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ScanFile scanFile = new ScanFileImpl();
        scanFile.scan("org.summer");
    }
}
