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
     * @param category -类别 0-通用，1-男频，2-女频，n-推荐
     * @param start -开始页
     * @param length -单页条数
     * @return Json对象{"code":"","msg":"","obj":"
     * list:novel_id-小说id;bookname-小说名称;category-类别;cover-封面
     * "}
     * 2019-05-25
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
     * 查询小说详情及章节列表
     * @Param novel_id  小说id
     * @return Json对象{"code":"","msg":"","obj":"
     * novelDetail:小说基本信息对象{id-无意义, bookname-小说名, author-小说作者, category-类别, status-状态, updatetime-更新时间, novel_id-小说id, cover-封面, counts-字数, readtimes-阅读次数, abstract-摘要}
     * novelChapterList:章节列表{id-无意义, sectionid-小说id, chapterid-章节id, chaptername-章节名称, paymoney-收费价格}
     * "}
     * 2019-05-25
     */
    @GetMapping("/novelDetail")
    public JsonResult novelDetail(String novel_id){
        NovelBase novelDetail = appNovelManualService.selectByNovelId(novel_id);
        JsonResult jr = new JsonResult(Constants.RET_CODE_00000,Constants.RET_DESC_00000);
        Map<String, Object> map = new HashMap<>();
        map.put("novelDetail",novelDetail);
        List<NovelChapter> novelChapterList = appNovelManualService.qryChapterListByNovelId(novel_id);
        map.put("novelChapterList",novelChapterList);
        jr.setObj(novelDetail);
        return jr;
    }

    /**
     * 通过查询小说章节列表详情
     * @param novel_id
     * @param start -开始章节
     * @param length -单页条数
     * @return Json对象{"code":"","msg":"","obj":"
     * data:sectionid-小说id;chapterid-章节id;chaptername-章节名称;paymoney-收费价格;content-章节内容
     * "}
     * 2019-05-25
     */
    @GetMapping("/chapterListDetail")
    public JsonResult chapterListDetail(String novel_id,int start,int length){
        PageInfo<NovelChapter> chapterList = appNovelManualService.chapterListDetailByNovelId(novel_id, start, length);
        JsonResult jr = new JsonResult(Constants.RET_CODE_00000,Constants.RET_DESC_00000);
        Map<String, Object> map = new HashMap<>();
        map.put("recordsTotal", chapterList.getTotal());//总条数
        map.put("recordsFiltered", chapterList.getTotal());
        map.put("data", chapterList.getList());//数据
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
