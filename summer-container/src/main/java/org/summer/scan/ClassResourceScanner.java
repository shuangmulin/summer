package org.summer.scan;

import org.summer.core.utils.StringUtils;

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
 * @date 2018-11-04 16:20
 **/
public class ClassResourceScanner {

    private static final String CLASS_PATH = ClassResourceScanner.class.getResource("/").getPath();

    private String packageName;

    public Set<ClassResource> scan(String packageName) throws IOException, ClassNotFoundException {
        Set<ClassResource> resources = new HashSet<>();
        if (StringUtils.isBlank(packageName)) {
            return resources;
        }
        this.packageName = packageName;

        // 通过当前线程得到类加载器从而得到URL的枚举
        Enumeration<URL> urlEnumeration = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".", "/").replace("\\", "/"));
        while (urlEnumeration.hasMoreElements()) {
            URL url = urlEnumeration.nextElement();
            String protocol = url.getProtocol();
            if ("jar".equalsIgnoreCase(protocol)) {
                handleJar(url, resources);
            } else if ("file".equalsIgnoreCase(protocol)) {
                String path = url.getPath();
                File file = new File(path);
                handleFile(packageName, file, false, resources);
            }
        }

        return resources;
    }

    /**
     * 处理文件
     */
    private void handleFile(String packageName, File file, boolean recursionFlag, Set<ClassResource> resources) {
        if (file.isDirectory()) {
            // 文件夹我们就递归
            File[] files = file.listFiles();
            if (recursionFlag) {
                packageName = packageName + "." + file.getName();
            }

            if (files == null) {
                return;
            }
            for (File f1 : files) {
                handleFile(packageName, f1, true, resources);
            }
        } else {
            // 标准文件我们就判断是否是class文件
            if (file.getName().endsWith("class")) {
                // 如果是class文件我们就放入我们的集合中
                String classNeme = packageName + "." + file.getName().substring(0, file.getName().lastIndexOf("."));
                resources.add(new ClassResource(classNeme));
            }
        }
    }

    /**
     * 处理jar包
     */
    private void handleJar(URL url, Set<ClassResource> resources) throws IOException, ClassNotFoundException {
        //转换为JarURLConnection
        JarURLConnection connection = (JarURLConnection) url.openConnection();
        if (connection != null) {
            JarFile jarFile = connection.getJarFile();
            if (jarFile != null) {
                // 得到该jar文件下面的类实体
                Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                while (jarEntryEnumeration.hasMoreElements()) {
                    JarEntry entry = jarEntryEnumeration.nextElement();
                    boolean directory = entry.isDirectory();
                    System.out.println(directory);
                    String jarEntryName = entry.getName();
                    // 这里我们需要过滤不是class文件和不在basePack包名下的类
                    if (jarEntryName.contains(".class") && jarEntryName.replaceAll("/", ".").startsWith(packageName)) {
                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
                        resources.add(new ClassResource(className));
                    }
                }
            }
        }
    }

}
