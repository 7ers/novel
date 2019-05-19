package com.demo.novel.controller.app;

import com.demo.novel.entity.Dic;
import com.demo.novel.entity.NovelBase;
import com.demo.novel.entity.NovelChapter;
import com.demo.novel.service.CommonDicService;
import com.demo.novel.service.NovelManualService;
import com.demo.novel.shiro.GlobalExceptionResolver;
import com.demo.novel.util.Constants;
import com.demo.novel.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class NovelController {
    private static Logger logger = LoggerFactory.getLogger(NovelController.class);

    @Resource
    NovelManualService appNovelManualService;

    @Resource
    CommonDicService commonDicService;

    /**
     * 查询小说列表
     * @return Json对象{"code":"","msg":"","obj":""}
     */
    @GetMapping("/novellist")
    public JsonResult novelList(){
        List<NovelBase> novelList = appNovelManualService.getAll();
        JsonResult jr = new JsonResult(Constants.RET_CODE_00000,Constants.RET_DESC_00000);
        jr.setObj(novelList);
        return jr;
    }

    /**
     * 通过类型查询小说列表
     * @param category -类别
     * @param start -开始页
     * @param length -单页条数
     * @return Json对象{"code":"","msg":"","obj":""}
     */
    @GetMapping("/novellistByCategory")
    public JsonResult novelListByCategory(String category,int start,int length){
        PageInfo<NovelBase> novelList = appNovelManualService.getNovelListByCategory(category, start, length);
        JsonResult jr = new JsonResult(Constants.RET_CODE_00000,Constants.RET_DESC_00000);
        Map<String, Object> map = new HashMap<>();
        map.put("recordsTotal", novelList.getTotal());//总条数
        map.put("recordsFiltered", novelList.getTotal());
        map.put("data", novelList.getList());//数据
        jr.setObj(map);
        return jr;
    }

    /**
     * 查询小说类别
     * @return Json对象{"code":"","msg":"","obj":""}
     */
    @GetMapping("/novelcategory")
    public JsonResult novelcategory(){
        List<Dic> categoryList = commonDicService.queryDicList(Constants.DIC_TYPE_CATEGORY);
        JsonResult jr = new JsonResult(Constants.RET_CODE_00000,Constants.RET_DESC_00000);
        jr.setObj(categoryList);
        return jr;
    }

    /**
     * 查询id为**小说的章节列表
     * @param id-小说id
     * @return 章节列表，Json对象{"code":"","msg":"","obj":""}
     */
    @GetMapping("/chapterlist")
    public JsonResult chapterlist(Integer id){
        List<NovelChapter> novelList = appNovelManualService.selectChapterList(id);
        JsonResult jr = new JsonResult(Constants.RET_CODE_00000,Constants.RET_DESC_00000);
        jr.setObj(novelList);
        return jr;
    }


    /**
     * 查询用户书架
     * @param username
     * @return 书架中的列表
     */
    @GetMapping("/bookstack")
    public JsonResult bookstack(String username){
        //TODO
        return new JsonResult();
    }

    /**
     * 查询推荐列表
     * @param username
     * @return 书架列表
     */
    @GetMapping("/recommend")
    public JsonResult recommend(String username){
        //TODO
        return new JsonResult();
    }


}
