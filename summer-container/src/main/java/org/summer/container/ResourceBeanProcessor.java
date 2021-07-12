package org.summer.container;

import org.summer.core.utils.ReflectionUtils;
import org.summer.core.utils.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 处理Resource注解
 *
 * @author 钟宝林
 * @date 2018-11-06 14:12
 **/
public class ResourceBeanProcessor implements BeanProcessor {
    @Override
    public Object process(BeanProcessContext context) {
        Object bean = context.getBean();
        Class<?> clazz = bean.getClass();
        List<Field> fields = ReflectionUtils.listAllField(clazz);
        for (Field field : fields) {
            Resource resourceAnnotation = field.getAnnotation(Resource.class);
            if (resourceAnnotation == null) {
                continue;
            }
            Object injectBean = findInjectBean(clazz, resourceAnnotation, field);
            try {
                field.set(bean, injectBean);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new IllegalStateException("类" + clazz.getName() + "的字段" + field.getName() + "注入失败");
            }
        }
        return bean;
    }

    private Object findInjectBean(Class<?> clazz, Resource resource, Field field) {
        Object injectBean = null;
        // 先按指定的名称找bean
        String name = resource.name();
        if (StringUtils.isNotBlank(name)) {
            injectBean = BeanContainer.getInstance().getBeanByName(name);
        }
        // 再按字段名找
        if (injectBean == null) {
            String fieldName = StringUtils.toLowerCaseFirstOne(field.getName());
            injectBean = BeanContainer.getInstance().getBeanByName(fieldName);
        }
        // 再按类型找
        if (injectBean == null) {
            Class<?> type = field.getType();
            List<?> beans = BeanContainer.getInstance().getByType(type);
            if (beans != null && beans.size() > 0) {
                if (beans.size() > 1) {
                    throw new IllegalStateException("类" + clazz.getName() + "的字段" + field.getName() + "注入失败，因为存在多个候选");
                }
                injectBean = beans.get(0);
            }
        }
        if (injectBean == null) {
            throw new IllegalStateException("类" + clazz.getName() + "的字段" + field.getName() + "注入失败，因为没有找到候选");
        }
        return injectBean;
    }
}
