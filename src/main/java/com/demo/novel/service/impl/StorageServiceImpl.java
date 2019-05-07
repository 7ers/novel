package com.demo.novel.service.impl;

import com.demo.novel.config.StorageConfig;
import com.demo.novel.dao.NovelChapterMapper;
import com.demo.novel.entity.NovelChapter;
import com.demo.novel.entity.NovelSection;
import com.demo.novel.service.StorageService;
import com.demo.novel.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootlocation;

    @Autowired
    private NovelChapterMapper novelChapterMapper;

    @Autowired
    public StorageServiceImpl(StorageConfig storageConfig) {
        this.rootlocation = Paths.get(storageConfig.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootlocation);
        }
        catch (IOException e) {
            System.out.println("Could not initialize storage" + e.toString());
        }
    }

    @Override
    public String store(MultipartFile multipartFile, int id) {
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        try {
            if (multipartFile.isEmpty()) {
                System.out.println("Failed to store empty file" + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                System.out.println("Cannot store file with relative path outside current directory " + filename);
            }
            Files.copy(multipartFile.getInputStream(), this.rootlocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

            //存入数据库
            List<NovelChapter> list = splitFile(this.rootlocation.resolve(filename).toString());
            if(novelChapterMapper.selectByNovelId(id)>0){
                return Constants.RET_CODE_ERROR;
            }
            for (NovelChapter nc:list) {
                nc.setSectionid(id);
                novelChapterMapper.insert(nc);
            }
            return Constants.RET_CODE_SUCCESS;
        } catch (IOException e) {
            System.out.println("Failed to store file" + filename + e.toString());
            return Constants.RET_CODE_FAIL;
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootlocation, 1)
                    .filter(path -> !path.equals(this.rootlocation))
                    .map(path->this.rootlocation.relativize(path));
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootlocation.toFile());
    }

    @Override
    public List<NovelChapter> splitFile(String filename) {
        List<NovelChapter> novelChapterList;
        novelChapterList = new ArrayList<>();
        try {
            // 文件路径
            File file = new File(filename);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                // 输入流
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;//当前行内容
                String chapterName = "";//当前章节名称
                String newChapterName = null;//新章节名称
                //小说内容类
                NovelChapter novelChapter;
                NovelSection novelSection;

                StringBuffer sbContent = new StringBuffer();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    // 正则表达式
                    Pattern p = Pattern.compile("(^\\s*)([#]{3}\\s*第)(.{1,9})[章节卷集部篇回](\\s{1,})(.*)(($\\s*)|($\\s*[\r|\n|\r\n]))");
                    Matcher matcher = p.matcher(lineTxt);
                    Matcher matcher1 = p.matcher(lineTxt);

                    if (matcher.find()) {
                        newChapterName = matcher.group().trim();
                        if("".equals(chapterName)){
                            chapterName = newChapterName;
                            sbContent = new StringBuffer("");
                        }
                    }else{
                        sbContent.append(lineTxt);
                    }
                    if(matcher1.find()){
                        if(!chapterName.equals(newChapterName)){
                            novelChapter = new NovelChapter();
                            chapterName.replaceAll("#{3}","");
                            novelChapter.setChaptername(chapterName);
                            novelChapter.setContent(sbContent.toString());
                            novelChapterList.add(novelChapter);
                            chapterName = newChapterName;
                            sbContent = new StringBuffer("");
                        }
                    }
                }
                novelChapter = new NovelChapter();
                novelChapter.setChaptername(chapterName.replaceAll("#{3}",""));
                novelChapter.setContent(sbContent.toString());
                novelChapterList.add(novelChapter);
                bufferedReader.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
        }
        return novelChapterList;
    }
}
