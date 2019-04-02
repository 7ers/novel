package com.demo.novel.control;

import com.demo.novel.entity.Person;
import com.demo.novel.service.PersonService;
import com.demo.novel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringBoot {
    @Autowired
    private PersonService personService;

    //访问形式localhost:8080/1
    @RequestMapping(value="/{id}")
    public JsonResult getParamByUrl(@PathVariable("id") int userId){
        System.out.println("get param " + userId);
        JsonResult jr = new JsonResult();
        jr.setCode(200);
        jr.setMsg("查询姓名");
        jr.setObj(personService.select(userId).getUsername());
        return jr;
    }

    //访问形式localhost:8080/getParamByKey?id=001
    @RequestMapping(path = {"/getParamByKey"})
    public String getParamByKey(@RequestParam(value="id",required = false) int userId){
        System.out.println("get param " + userId);
        Person select = personService.select(userId);
        return select.getUsername();
    }
}
