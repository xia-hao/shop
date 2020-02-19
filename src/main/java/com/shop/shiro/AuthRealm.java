package com.shop.shiro;

import com.shop.domain.User;
import com.shop.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @PACKAGE_NAME: com.shop.shiro
 * @author: XIA
 * @NAME: AuthRealm
 * @DATE: 2020/1/16
 **/

public class AuthRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        String username = usernamePasswordToken.getUsername();
        User user = userService.selectByName(username);
        return new SimpleAuthenticationInfo(user, user.getPassword(),this.getClass().getName());
    }
}
