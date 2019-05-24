package com.demo.novel.controller;

import com.demo.novel.entity.Dic;
import com.demo.novel.entity.NovelBase;
import com.demo.novel.service.CommonDicService;
import com.demo.novel.service.NovelManualService;
import com.demo.novel.util.Constants;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NovelManualController {

    @Autowired
    NovelManualService novelManualService;

    @Resource
    CommonDicService commonDicService;

    @RequestMapping("/queryNovelList")
    public Map<String, Object> getAll(
            @RequestParam(required = false, defaultValue = "1") String draw,
            @RequestParam(required = false, defaultValue = "1") int start,
            @RequestParam(required = false, defaultValue = "10") int length) {
        Map<String, Object> map = new HashMap<>();
        List<NovelBase> novelList = novelManualService.getAll();

        map.put("draw", draw);
        map.put("recordsTotal", novelList.size());
        map.put("recordsFiltered", novelList.size());
        map.put("data", novelList);
        return map;
    }

    @RequestMapping(value="/queryNovelListByKeyword")
    public Map<String, Object> queryByKeyword(
            @RequestParam(required = false, defaultValue = "1") String draw,
            @RequestParam(required = false, defaultValue = "1") int start,
            @RequestParam(required = false, defaultValue = "10") int length,
            @RequestParam(required=false) String bookname,
            @RequestParam(required = false) String status) {
        System.out.println("querybykeyword");
        Map<String, Object> map = new HashMap<>();
        NovelBase novelBase = new NovelBase();
        novelBase.setBookname(bookname);
        novelBase.setStatus(status);
        PageInfo<NovelBase> pageInfo = novelManualService.selectByEntity(novelBase,start,length);
        List<Dic> dicList = commonDicService.queryDicList("category");
        Map<String, String> hm = new HashMap<>();
        for (Dic dic:dicList) {
            hm.put(dic.getValue(),dic.getDesc());
        }

        for(NovelBase nb: pageInfo.getList()){
            nb.setCategory(hm.get(nb.getCategory()));
        }
        map.put("draw", draw);
        map.put("recordsTotal", pageInfo.getTotal());
        map.put("recordsFiltered", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    @RequestMapping(value="/novel/add",method = RequestMethod.POST)
    public String addNovel(@RequestParam(value="bookname",required=true) String bookname,
                           @RequestParam(value="author",required=true) String author,
                           @RequestParam(value="abstracts",required=true) String abstracts,
                           @RequestParam(value="category",required=true) String category,
                           @RequestParam(value="status",required=true) String status){
        NovelBase novelBase = new NovelBase();
        novelBase.setBookname(bookname);
        novelBase.setAuthor(author);
        novelBase.setCategory(category);
        novelBase.setStatus(status);
        novelBase.setAbstract(abstracts);
        NovelBase nl = novelManualService.selectByBookName(novelBase.getBookname());
        if(nl != null)
            return Constants.RET_CODE_ERROR;
        try {
            int res = novelManualService.insert(novelBase);
            return Constants.RET_CODE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constants.RET_CODE_FAIL;
        }
    }

    @RequestMapping(value="/novel/delete",method = RequestMethod.POST)
    public String delNovel(@RequestParam(required=true) int id){
        if(StringUtils.isEmpty(id)){
            return Constants.RET_CODE_FAIL;
        }
        try {
            novelManualService.deleteByPrimaryKey(id);
            return Constants.RET_CODE_SUCCESS;
        } catch (Exception e){
            return Constants.RET_CODE_FAIL;
        }
    }
}
