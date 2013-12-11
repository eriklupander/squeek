package com.squeed.squeek.testable;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2013-12-10
 * Time: 17:02
 * To change this template use File | Settings | File Templates.
 */
public class StringUtil {
    public String capitalize(String str) {
        return str.substring(0, 1).toUpperCase()+str.substring(1);
    }
}
