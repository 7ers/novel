package com.demo.novel.service.impl;

import com.demo.novel.dao.RoleInfoMapper;
import com.demo.novel.entity.RoleInfo;
import com.demo.novel.service.RoleInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Resource
    private RoleInfoMapper roleInfoMapper;
    @Override
    public List<RoleInfo> queryRoleListWithSelected(int roleid) {
        return roleInfoMapper.queryRoleListWithSelected(roleid);
    }

    @Override
    public PageInfo<RoleInfo> queryRoleList(int start, int length) {
        int page = start/length+1;
        //分页查询
        PageHelper.startPage(page, length);
        List<RoleInfo> roleInfoList = roleInfoMapper.queryRoleList();
        return new PageInfo<>(roleInfoList);
    }

    @Override
    public int save(RoleInfo roleInfo) {
        return roleInfoMapper.addRole(roleInfo);
    }

    @Override
    public int delete(Integer id) {
        return roleInfoMapper.deleteByRoleId(id);
    }

    @Override
    public RoleInfo queryRoleByRoleDesc(String roledesc) {
        return roleInfoMapper.queryRoleByRoleDesc(roledesc);
    }


}
