package org.summer.scan;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 扫描文件
 *
 * @author 钟宝林
 * @date 2018-11-04 16:22
 **/
public class ScanFileImpl implements ScanFile {
    @Override
    public Set<File> scan(String packageName) {
        packageName = packageName.replace(".", File.separator);
        try {
            Set<Class<?>> classes = new HashSet<>();
            //通过当前线程得到类加载器从而得到URL的枚举
            Enumeration<URL> urlEnumeration = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".", "/").replace("\\", "/"));
            while (urlEnumeration.hasMoreElements()) {
                URL url = urlEnumeration.nextElement();//得到的结果大概是：jar:file:/C:/Users/ibm/.m2/repository/junit/junit/4.12/junit-4.12.jar!/org/junit
                String protocol = url.getProtocol();//大概是jar
                if ("jar".equalsIgnoreCase(protocol)) {
                    //转换为JarURLConnection
                    JarURLConnection connection = (JarURLConnection) url.openConnection();
                    if (connection != null) {
                        JarFile jarFile = connection.getJarFile();
                        if (jarFile != null) {
                            //得到该jar文件下面的类实体
                            Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                            while (jarEntryEnumeration.hasMoreElements()) {
                            /*entry的结果大概是这样：
                                    org/
                                    org/junit/
                                    org/junit/rules/
                                    org/junit/runners/*/
                                JarEntry entry = jarEntryEnumeration.nextElement();
                                String jarEntryName = entry.getName();
                                //这里我们需要过滤不是class文件和不在basePack包名下的类
                                if (jarEntryName.contains(".class") && jarEntryName.replaceAll("/", ".").startsWith(packageName)) {
                                    String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
                                    Class cls = Class.forName(className);
                                    classes.add(cls);
                                }
                            }
                        }
                    }
                } else if ("file".equalsIgnoreCase(protocol)) {
                    String path = url.getPath();
                    File file = new File(path);
                    Set<Class<?>> classes1 = new ClassSearcher().doPath(file, "", true);
                    System.out.println(classes1);
                }
            }
        } catch (ClassNotFoundException | IOException e) {

        }

        return null;
    }

    private static class ClassSearcher{
        private Set<Class<?>> classPaths = new HashSet<>();

        private Set<Class<?>> doPath(File file, String packageName, boolean flag) {

            if (file.isDirectory()) {
                //文件夹我们就递归
                File[] files = file.listFiles();
                if(!flag){
                    packageName = packageName+"."+file.getName();
                }

                for (File f1 : files) {
                    doPath(f1,packageName,false);
                }
            } else {//标准文件
                //标准文件我们就判断是否是class文件
                if (file.getName().endsWith("class")) {
                    //如果是class文件我们就放入我们的集合中。
                    try {
                        Class<?> clazz = Class.forName(packageName + "."+ file.getName().substring(0,file.getName().lastIndexOf(".")));
                        classPaths.add(clazz);
                    } catch (ClassNotFoundException e) {
                    }
                }
            }
            return classPaths;
        }
    }
}
