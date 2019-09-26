package com.trzewik.TomcatWordCloud;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Agnieszka Trzewik
 */
class AddObservers {

    private static WordCloud wordCloud = new WordCloud();

    static void addObservers(Object object) {
        if (object == null) return;
        Class<?> servletClass = object.getClass();
        if (servletClass == null) return;
        AddObserver observer;
        for (Field field : servletClass.getDeclaredFields()) {
            observer = field.getAnnotation(AddObserver.class);
            if (observer != null) {
                List<Observer> observerList = new ArrayList<>();
                try {
                    observerList.add(wordCloud);
                    field.setAccessible(true);
                    field.set(object, observerList);
                } catch (IllegalAccessException e) {
                    Logger.getLogger(AddObservers.class.getName()).log(Level.WARNING, e.getMessage(), e);
                }
            }

        }
    }

}
