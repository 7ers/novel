package com.demo.novel.service;

import com.demo.novel.entity.PermsInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface PermsInfoService {
    List<PermsInfo> queryAll();
    List<PermsInfo> loadUserPerms(Map<String, Object> map);

    List<PermsInfo> queryPermsListByRoleID(Integer roleid);

    PageInfo<PermsInfo> selectByPage(int start, int length);

    int addPerms(PermsInfo permsInfo);

    int delById(int permsid);

}
