package com.demo.novel.controller;

import com.demo.novel.entity.Banner;
import com.demo.novel.entity.Novel;
import com.demo.novel.util.C;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Client {
    @RequestMapping("/")
    private String goIndex(){
        return "index";
    }

    @RequestMapping("/test")
    private String test(){
        return "test";
    }

    @RequestMapping("/home")
    private String home(Model model) {
        List<Banner> bannerList = new ArrayList<>();

        Banner b1 = new Banner();
        b1.setTitle("标题111");
        b1.setImgurl("/images/216.jpeg");
        bannerList.add(b1);

        Banner b2 = new Banner();
        b2.setTitle("标题222");
        b2.setImgurl("/images/217.jpeg");
        bannerList.add(b2);

        Banner b3 = new Banner();
        b3.setTitle("标题333");
        b3.setImgurl("/images/218.jpeg");
        bannerList.add(b3);

        Banner b4 = new Banner();
        b4.setTitle("标题444");
        b4.setImgurl("/images/218.jpeg");
        bannerList.add(b4);

        Banner b5 = new Banner();
        b5.setTitle("标题555");
        b5.setImgurl("/images/218.jpeg");
        bannerList.add(b5);

        model.addAttribute("banners", bannerList);

        List<Novel> novelList = new ArrayList<>();

        Novel f1 = new Novel();
        f1.setIcon("/images/210.jpeg");
        novelList.add(f1);

        Novel f2 = new Novel();
        f2.setIcon("/images/211.jpeg");
        novelList.add(f2);

        Novel f3 = new Novel();
        f3.setIcon("/images/212.jpeg");
        novelList.add(f3);

        Novel f4 = new Novel();
        f4.setIcon("/images/213.jpeg");
        novelList.add(f4);

        Novel f5 = new Novel();
        f5.setIcon("/images/214.jpeg");
        novelList.add(f5);

        Novel f6 = new Novel();
        f6.setIcon("/images/215.jpeg");
        novelList.add(f6);

        model.addAttribute("novels", novelList);

        return "home";
    }

    @RequestMapping("/novelList")
    private String novelList(Model model, int category) {
        List<Novel> novelList = new ArrayList<>();

        Novel f1 = new Novel();
        f1.setIcon("/images/210.jpeg");
        novelList.add(f1);

        Novel f2 = new Novel();
        f2.setIcon("/images/211.jpeg");
        novelList.add(f2);

        Novel f3 = new Novel();
        f3.setIcon("/images/212.jpeg");
        novelList.add(f3);

        Novel f4 = new Novel();
        f4.setIcon("/images/213.jpeg");
        novelList.add(f4);

        Novel f5 = new Novel();
        f5.setIcon("/images/214.jpeg");
        novelList.add(f5);

        Novel f6 = new Novel();
        f6.setIcon("/images/215.jpeg");
        novelList.add(f6);

        Novel f7 = new Novel();
        f7.setIcon("/images/210.jpeg");
        novelList.add(f7);

        Novel f8 = new Novel();
        f8.setIcon("/images/211.jpeg");
        novelList.add(f8);

        Novel f9 = new Novel();
        f9.setIcon("/images/212.jpeg");
        novelList.add(f9);

        Novel f10 = new Novel();
        f10.setIcon("/images/213.jpeg");
        novelList.add(f10);

        model.addAttribute("novels", novelList);

        model.addAttribute("categoryName", C.categorys.get(category));

        return "novelList";
    }

    @RequestMapping(value = "/novelInfo")
    private String novelInfo(int nid) {
        return "novelInfo";
    }

    @RequestMapping("/novel")
    private String novel(Model model, String name) {
        model.addAttribute("name", name);
        model.addAttribute("title", "极品小野医");
        model.addAttribute("icon", "/images/210.jpeg");
        model.addAttribute("content", getContent(name, 1));

        return "novel";
    }

    @RequestMapping(value = "/nextChapter")
    @ResponseBody
    public Object nextChapter(String name, int chapter) {
        Map<String, Object> result = new HashMap<String, Object>();

        result.put(C.C, C.RESULT_SUCCESS);
        result.put(C.M, getContent(name, chapter));

        return result;
    }

    private String getContent(String name, int chapter) {
        String content = "";
        String serverpath = "";
        try {
            serverpath = ResourceUtils.getURL("classpath:static").getPath();
        } catch (Exception e) {

        }

        String filepath = serverpath + "/txt/" + name + ".txt";
        try {
            File file = new File(filepath);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file));
            BufferedReader br = new BufferedReader(reader);

            int nextChapter = chapter + 1;
            String start = "###第" + chapter + "章";
            String end = "###第" + nextChapter + "章";

            String line = br.readLine();
            while (line != null) {
                if (line.contains(start))
                    break;
                line = br.readLine();
            }

            line = br.readLine();
            while (line != null) {
                if (line.contains(end))
                    break;

                content += "<p>" + line + "</p>";
                line = br.readLine();
            }
            br.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return content;
    }
}
