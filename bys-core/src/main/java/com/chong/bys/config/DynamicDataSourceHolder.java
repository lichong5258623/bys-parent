package com.chong.bys.config;

public class DynamicDataSourceHolder {

    private static ThreadLocal<Boolean> currentDb = new ThreadLocal<>();

    public static void clearCurrentDb(){
        currentDb.remove();
    }

    public static void markSlave() {
        currentDb .set(Boolean.TRUE);
    }

    public static void markMaster() {
        currentDb .set(Boolean.FALSE);
    }

    public static Boolean useSlave(){
        return currentDb.get();
    }

}
