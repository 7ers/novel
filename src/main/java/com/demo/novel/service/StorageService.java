package com.demo.novel.service;

import com.demo.novel.entity.NovelChapter;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {
    void init();
    String store(MultipartFile multipartFile,String id);
    Stream<Path> loadAll();
    void deleteAll();
    List<NovelChapter> splitFile(String filename);
}
