package org.summer.container.scan.exception;

/**
 * 资源扫描异常
 *
 * @author 钟宝林
 * @date 2018-11-05 11:22
 **/
public class ResourceScanException extends RuntimeException {

    public ResourceScanException() {
    }

    public ResourceScanException(String message) {
        super(message);
    }

    public ResourceScanException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceScanException(Throwable cause) {
        super(cause);
    }

    public ResourceScanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
