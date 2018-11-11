package com.chong.bystest.designpattern.creational.simplefactory;

public class ViedoFactory {

    public Viedo getViedo(String type){
        if(type.equals("java")){

            return new JavaViedo();
        }else if("python".equals(type)){

            return new PythonViedo();
        }
        return null;
    }


}
