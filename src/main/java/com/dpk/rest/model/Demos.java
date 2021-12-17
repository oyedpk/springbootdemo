package com.dpk.rest.model;

import java.util.ArrayList;
import java.util.List;

public class Demos
{
    private List<Demo> demoList;
    
    public List<Demo> getDemoList() {
        if(demoList == null) {
            demoList = new ArrayList<>();
        }
        return demoList;
    }
 
    public void setDemoList(List<Demo> demoList) {
        this.demoList = demoList;
    }
}
