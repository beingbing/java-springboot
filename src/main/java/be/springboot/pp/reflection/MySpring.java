package be.springboot.pp.reflection;

import be.springboot.pp.reflection.beans.WebApi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
* Every framework has a convention, my convention is i will create beans of class which
* are present in beans package
* */
public class MySpring {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Set<Class> classes = findClasses("be.springboot.pp.reflection.beans");
        System.out.println("classes: " + classes);
//        Set<Class> classesToScan = new HashSet<>();
//
//        for (Class c : classes) {
//            if (c.isAnnotationPresent(MyBean.class))
//                classesToScan.add(c);
//        }

//        HashMap<String, Object> registry = initBeans(classesToScan);
        HashMap<String, Object> registry = initBeans(classes);
        for (Map.Entry<String, Object> entry : registry.entrySet()) {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }

        WebApi webApi = (WebApi) registry.get("webApi");
        System.out.println("webApi: " + webApi);
    }

    private static HashMap<String, Object> initBeans(Set<Class> classes) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        HashMap<String, Object> registry = new HashMap<>();
        for (Class c : classes) {
            String key = c.getName();
            if (!registry.containsKey(key)) {
                Object val = c.getConstructor().newInstance();
                injectFields(val, registry);
                registry.put(key, val);
            }
        }
        return registry;
    }

    private static void injectFields(Object object, HashMap<String, Object> registry) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class c = object.getClass();
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            if (!Modifier.isStatic(f.getModifiers())) {
                f.setAccessible(true);
                if (registry.containsKey(f.getType().getName())) {
                    f.set(object, registry.get(f.getType().getName()));
                } else {
                    Class fieldClass = f.getType();
                    Object val = fieldClass.getConstructor().newInstance();
                    injectFields(val, registry);
                    f.set(object, val);
                }
            }
        }
    }

    private static Set<Class> findClasses(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader
                .lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

