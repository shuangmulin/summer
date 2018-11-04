package org.summer.scan;

import java.io.File;
import java.util.Set;

/**
 * 扫描文件
 *
 * @author 钟宝林
 * @date 2018-11-04 16:20
 **/
public interface ScanFile {

    String CLASS_PATH = ScanFile.class.getResource("/").getPath();

    Set<File> scan(String packageName);

}
