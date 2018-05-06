package test;

import java.net.URL;
import java.net.URLClassLoader;
 
/**
 * quick program that prints out my classpath
 */
public class ClassLoaderDisplay {
 
    public static void main(String args[]) {
        URL[] urls = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();
        for (URL url : urls) {
            System.out.println(url);
        }
    }
}