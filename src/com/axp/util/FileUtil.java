package com.axp.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class FileUtil {
	
    /**
     * 根据包名，获取该包及其子包下所有的class文件；
     *
     * @param packageName
     * @return
     * @throws Exception
     */
    public static List<Class<?>> getClassFromPackage(String packageName) throws Exception {

        //返回值集合；
        List<Class<?>> classes = new ArrayList<Class<?>>();

        //获取包的名字 并进行替换
        String packageDirName = packageName.replace('.', File.separator.charAt(0));

        //定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            //循环迭代下去
            while (dirs.hasMoreElements()) {
                //获取下一个元素
                URL url = dirs.nextElement();
                //得到协议的名称
                String protocol = url.getProtocol();
                //如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)||"vfs".equals(protocol)) {
                    //获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    //以文件的方式扫描整个包下的文件 并添加到集合中
                    getAllClassFromPath(packageName, filePath, classes);
                } else{
                    throw new Exception("不是项目的包路径");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    /**
     * 将给定包中及其子包中所有类的字节码文件放到给定的集合中；
     *
     * @param packageName 需要遍历的包的名字；
     * @param packagePath 需要遍历的包的路径；
     * @param clazz       装字节码文件的集合；
     * @throws Exception
     */
    public static void getAllClassFromPath(String packageName, String packagePath, List<Class<?>> clazz) throws Exception {
        //获取此包的目录 建立一个File
        File dir = new File(packagePath);
        //如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            throw new Exception("不存在的文件目录");
        }
        //如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            //自定义过滤规则;
            public boolean accept(File file) {
                return file.isDirectory() || (file.getName().endsWith(".class"));
            }
        });
        //循环所有文件
        for (File file : dirfiles) {
            //如果是目录 则继续扫描
            if (file.isDirectory()) {
                getAllClassFromPath(packageName + "." + file.getName(), file.getAbsolutePath(), clazz);
            } else {
                //如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    //添加到集合中去
                    clazz.add(Class.forName(packageName + "." + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
