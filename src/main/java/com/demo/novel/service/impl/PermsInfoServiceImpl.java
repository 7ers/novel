package com.demo.novel.service.impl;

import com.demo.novel.dao.PermsInfoMapper;
import com.demo.novel.entity.PermsInfo;
import com.demo.novel.entity.UserInfo;
import com.demo.novel.service.PermsInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PermsInfoServiceImpl implements PermsInfoService {

    @Resource
    private PermsInfoMapper permsInfoMapper;

    @Override
    public List<PermsInfo> queryAll() {
        return permsInfoMapper.queryAll();
    }

    @Override
    public List<PermsInfo> loadUserPerms(Map<String, Object> map) {
        return permsInfoMapper.loadUserPerms(map);
    }

    @Override
    public List<PermsInfo> queryPermsListByRoleID(Integer roleid) {
        return permsInfoMapper.queryPermsListByRoleID(roleid);
    }

    @Override
    public PageInfo<PermsInfo> selectByPage(int start, int length) {
        int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        List<PermsInfo> permsInfoList = permsInfoMapper.queryAll();
        return new PageInfo<>(permsInfoList);
    }

    @Override
    public int addPerms(PermsInfo permsInfo) {
        return permsInfoMapper.addPerms(permsInfo);
    }

    @Override
    public int delById(int permsid) {
        return permsInfoMapper.delById(permsid);
    }
}
