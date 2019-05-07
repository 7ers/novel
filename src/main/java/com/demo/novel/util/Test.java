package com.demo.novel.util;

import com.demo.novel.entity.NovelChapter;
import com.demo.novel.entity.NovelSection;
import org.thymeleaf.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String args[]){
        String filename = "/Users/eipwork/Documents/Dev/GitHub/novel/target/classes/static/txt/hyhlbfn.txt";
        String str = "###第206章 许总想和你复合";
        System.out.println("jieguo:"+str.replaceAll("#{3}",""));
//        List<NovelChapter> novelChapterList;
//        novelChapterList = new ArrayList<>();
//        try {
//            // 文件路径
//            File file = new File(filename);
//            if (file.isFile() && file.exists()) { // 判断文件是否存在
//                // 输入流
//                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
//                BufferedReader bufferedReader = new BufferedReader(read);
//                String lineTxt = null;//当前行内容
//                String chapterName = "";//当前章节名称
//                String newChapterName = null;//新章节名称
//                //小说内容类
//                NovelChapter novelChapter;
//                NovelSection novelSection;
//
//                StringBuffer sbContent = new StringBuffer();
//                while ((lineTxt = bufferedReader.readLine()) != null) {
//                    // 正则表达式
//                    Pattern p = Pattern.compile("(^\\s*)([#]{3}\\s*第)(.{1,9})[章节卷集部篇回](\\s{1,})(.*)(($\\s*)|($\\s*[\r|\n|\r\n]))");
//                    Matcher matcher = p.matcher(lineTxt);
//                    Matcher matcher1 = p.matcher(lineTxt);
//
//                    if (matcher.find()) {
//                        newChapterName = matcher.group().trim();
//                        if("".equals(chapterName)){
//                            chapterName = newChapterName;
//                            sbContent = new StringBuffer("");
//                        }
//                    }else{
//                        sbContent.append(lineTxt);
//                    }
//                    if(matcher1.find()){
//                        if(!chapterName.equals(newChapterName)){
//                            novelChapter = new NovelChapter();
//                            novelChapter.setChaptername(chapterName);
//                            novelChapter.setContent(sbContent.toString());
//                            novelChapterList.add(novelChapter);
//                            chapterName = newChapterName;
//                            sbContent = new StringBuffer("");
//                        }
//                    }
//                }
//                novelChapter = new NovelChapter();
//                novelChapter.setChaptername(chapterName);
//                novelChapter.setContent(sbContent.toString());
//                novelChapterList.add(novelChapter);
//                bufferedReader.close();
//            } else {
//                System.out.println("找不到指定的文件");
//            }
//        } catch (Exception e) {
//            System.out.println("读取文件内容出错");
//        }
    }
}
