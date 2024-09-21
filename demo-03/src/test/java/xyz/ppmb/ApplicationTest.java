package xyz.ppmb;

import cn.dev33.satoken.stp.StpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {
    @Test
    void permission() {
        StpUtil.login(10010);
        // 获取：当前账号所拥有的权限集合
        System.out.println(StpUtil.getPermissionList());

        // 判断：当前账号是否含有指定权限, 返回 true 或 false
        System.out.println(StpUtil.hasPermission("user.add"));

        // 校验：当前账号是否含有指定权限, 如果验证未通过，则抛出异常: NotPermissionException
        StpUtil.checkPermission("user.add");
        // 校验：当前账号是否含有指定权限 [指定多个，只要其一验证通过即可]
        StpUtil.checkPermissionOr("user.add", "user.delete", "user.get");
        // 校验：当前账号是否含有指定权限 [指定多个，必须全部验证通过]
        StpUtil.checkPermissionAnd("user.add", "user.delete", "user.get");
    }
    @Test
    void role() {
        StpUtil.login(10010);
        // 获取：当前账号所拥有的角色集合
        StpUtil.getRoleList();

        // 判断：当前账号是否拥有指定角色, 返回 true 或 false
        StpUtil.hasRole("super-admin");

        // 校验：当前账号是否含有指定角色标识, 如果验证未通过，则抛出异常: NotRoleException
        StpUtil.checkRole("super-admin");

        // 校验：当前账号是否含有指定角色标识 [指定多个，必须全部验证通过]
        StpUtil.checkRoleAnd("super-admin", "shop-admin");

        // 校验：当前账号是否含有指定角色标识 [指定多个，只要其一验证通过即可]
        StpUtil.checkRoleOr("super-admin", "shop-admin");
    }
    @Test
    void wildcard(){
        StpUtil.login(10010);
        System.out.println(StpUtil.hasPermission("art.add"));        // true
        System.out.println(StpUtil.hasPermission("art.update"));     // true
        System.out.println(StpUtil.hasPermission("goods.add"));      // false

        // 当拥有 *.delete 权限时
        System.out.println(StpUtil.hasPermission("art.delete"));      // true
        System.out.println(StpUtil.hasPermission("user.delete"));     // true
        System.out.println(StpUtil.hasPermission("user.update"));     // false

        // 当拥有 *.js 权限时
        System.out.println(StpUtil.hasPermission("index.js"));        // true
        System.out.println(StpUtil.hasPermission("index.css"));       // false
        System.out.println(StpUtil.hasPermission("index.html"));      // false
    }
}
