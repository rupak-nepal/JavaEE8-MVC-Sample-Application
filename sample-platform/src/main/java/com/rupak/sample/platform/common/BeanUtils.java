package com.rupak.sample.platform.common;

import java.lang.reflect.Field;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rupak
 */
public class BeanUtils {

    private static Logger logger = Logger.getLogger(BeanUtils.class.getName());

    private BeanUtils() {
    }

    public static void trimBean(Object object) {
        if (object == null)
            return;
        if (object instanceof String)
            StringUtils.trimToNull((String) object);
        Field[] fields = object.getClass().getDeclaredFields();
        if (fields == null)
            return;
        try {
            for (Field field : fields) {
                if (field.getType().equals(String.class)) {
                    field.setAccessible(true);
                    String val = (String) field.get(object);
                    field.set(object, StringUtils.trimToNull(val));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

