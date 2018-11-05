package org.summer.test;

import java.io.IOException;
import java.util.List;
public interface PackageScanner {
    public List<String> getFullyQualifiedClassNameList() throws IOException;
}