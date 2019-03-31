package com.demo.novel.control;

import com.demo.novel.entity.Person;
import com.demo.novel.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringBoot {
    @Autowired
    private PersonService personService;

    @RequestMapping(path={"/"})
    public String helloSpring(){
        String str = "Hello Spring Boot";
        System.out.println(str);
        return str;
    }

    //访问形式localhost:8080/1
    @RequestMapping(path={"/{id}"})
    public String getParamByUrl(@PathVariable("id") int userId){
        System.out.println("get param " + userId);
        return personService.select(userId).getUsername();
    }

    //访问形式localhost:8080/getParamByKey?id=001
    @RequestMapping(path = {"/getParamByKey"})
    public String getParamByKey(@RequestParam(value="id",required = false) int userId){
        System.out.println("get param " + userId);
        Person select = personService.select(userId);
        return select.getUsername();
    }
}
