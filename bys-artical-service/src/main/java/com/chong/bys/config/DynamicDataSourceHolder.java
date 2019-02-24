package com.chong.bys.config;

public class DynamicDataSourceHolder {


    private static ThreadLocal<String> currentDb = new ThreadLocal<>();

    public static final String MASTER = "MASTER";

    public static String getCurrentDb(){

        if(currentDb.get()==null){
            return MASTER;
        }
        return currentDb.get();
    }

    public static void setCurrentDb(String action){
        currentDb.set(action);
    }

    public static void clearCurrentDb(){
        currentDb.remove();
    }

}
