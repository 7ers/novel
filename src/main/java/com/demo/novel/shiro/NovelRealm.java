package com.demo.novel.shiro;

import com.demo.novel.dao.PermsInfoMapper;
import com.demo.novel.dao.RoleInfoMapper;
import com.demo.novel.entity.UserInfo;
import com.demo.novel.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class NovelRealm extends AuthorizingRealm {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    RoleInfoMapper roleInfoMapper;
    @Autowired
    PermsInfoMapper permsInfoMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo  = (UserInfo)principal.getPrimaryPrincipal();
        roleInfoMapper.findRoleByUsername(userInfo.getUsername()).stream().forEach(
                sysRole -> {
                    authorizationInfo.addRole(sysRole.getRoleval());
                    permsInfoMapper.findPermissionByRoleId(sysRole.getRoleid()).stream().forEach(
                            sysPermission -> {
                                authorizationInfo.addStringPermission(sysPermission.getPermstype());
                            }
                    );
                }
        );
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        System.out.println("doGetAuthenticationInfo:"+token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.findByUsername(username);
        System.out.println("----->>userInfo="+userInfo);
        if(userInfo == null){
            ////没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPwd(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );

        if (0==Integer.valueOf(userInfo.getState())) {
            throw new LockedAccountException(); // 帐号锁定
        }

        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", userInfo);
        session.setAttribute("userSessionId", userInfo.getUserid());

        return authenticationInfo;

    }
}
