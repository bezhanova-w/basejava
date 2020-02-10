package com.basejava.webapp;

import com.basejava.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resume r = new Resume();
        System.out.println(r.getClass().getDeclaredMethod("toString").invoke(r));
    }
}
