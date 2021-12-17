package com.dpk.rest.controller;

import java.net.URI;

import com.dpk.rest.dao.DemoDAO;
import com.dpk.rest.model.Demo;
import com.dpk.rest.model.Demos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/demo")
public class DemoController
{
    @Autowired
    private DemoDAO demoDao;
    
    @GetMapping(path="/", produces = "application/json")
    public Demos getDemos()
    {
        return demoDao.getAllDemos();
    }
    
    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addDemo(
                        @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
                        @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
                        @RequestBody Demo demo)
                 throws Exception 
    {       
        //Generate resource id
        Integer id = demoDao.getAllDemos().getDemoList().size() + 1;
        demo.setId(id);
        
        //add resource
        demoDao.addDemo(demo);
        
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(demo.getId())
                                    .toUri();
        
        //Send location in response
        return ResponseEntity.created(location).build();
    }
}
