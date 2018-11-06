package org.summer.container.scan;

import org.summer.core.utils.StringUtils;

public class ClassResource {

    private Class<?> clazz;
    private final String className;
    private boolean initialization;

    public ClassResource(String className) {
        this.className = className;
    }

    public Class<?> getClazz() {
        if (StringUtils.isNotBlank(className)) {
            try {
                this.clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("获取Class异常, " + className);
            }
        }
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getClassName() {
        return className;
    }

    public boolean isInitialization() {
        return initialization;
    }

    public void setInitialization(boolean initialization) {
        this.initialization = initialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassResource that = (ClassResource) o;

        return className != null ? className.equals(that.className) : that.className == null;
    }

    @Override
    public int hashCode() {
        return className != null ? className.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ClassResource{" +
                "className='" + className + '\'' +
                '}';
    }
}