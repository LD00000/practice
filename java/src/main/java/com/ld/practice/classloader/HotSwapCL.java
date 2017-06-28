package com.ld.practice.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;

/**
 * 自定义热加载 ClassLoader
 * 
 * @author ld000
 */
public class HotSwapCL extends ClassLoader {

    private String baseDir; // 该类加载器直接加载的类文件的目录
    private HashSet<String> dynamicClazzs; // 该类加载器直接加载的类的类名集合

    public HotSwapCL(String baseDir, String[] clazzs) {
        super(null); // 空父加载器
        this.baseDir = baseDir;
        dynamicClazzs = new HashSet<>();
        loadClassByMe(clazzs);
    }

    private void loadClassByMe(String[] clazzs) {
        for (String clazz : clazzs) {
            loadDirectly(clazz);
            dynamicClazzs.add(clazz);
        }
    }

    /**
     * 转换类名，并实例化 class
     *
     * @param name
     * @return
     */
    private Class<?> loadDirectly(String name) {
        StringBuilder filePath = new StringBuilder(baseDir);
        String clsName = name.replace('.', File.separatorChar) + ".class";
        filePath.append(File.separatorChar + clsName);
        File file = new File(filePath.toString());
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
        }

        return instantiateClass(name, fileInputStream, file.length());
    }

    /**
     * 实例化 class，将 byte 数组转换为 class 实例
     *
     * @param name
     * @param fin
     * @param len
     * @return
     */
    private Class<?> instantiateClass(String name, InputStream fin, long len) {
        byte[] raw = new byte[(int) len];
        try {
            fin.read(raw);
        } catch (IOException e) {
        } finally {
            try {
                fin.close();
            } catch (IOException e) {
            }
        }
        return defineClass(name, raw, 0, raw.length);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> cls = findLoadedClass(name);
        if (!dynamicClazzs.contains(name) && cls == null)
            cls = getSystemClassLoader().loadClass(name);
        if (cls == null)
            throw new ClassNotFoundException(name);
        if (resolve)
            resolveClass(cls);

        return cls;
    }

}
