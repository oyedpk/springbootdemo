package com.dpk.rest.dao;

import com.dpk.rest.model.Demos;
import org.springframework.stereotype.Repository;

import com.dpk.rest.model.Demo;

@Repository
public class DemoDAO
{
    private static Demos list = new Demos();
    
    static 
    {
        list.getDemoList().add(new Demo(1, "ABC", "XYZ", "java@gmail.com"));
        list.getDemoList().add(new Demo(2, "Alex", "lex", "abc@gmail.com"));
        list.getDemoList().add(new Demo(3, "titanic", "123", "titanic@gmail.com"));
    }
    
    public Demos getAllDemos()
    {
        return list;
    }
    
    public void addDemo(Demo demo) {
        list.getDemoList().add(demo);
    }
}
