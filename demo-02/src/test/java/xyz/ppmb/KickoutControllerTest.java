package xyz.ppmb;

import cn.dev33.satoken.stp.StpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KickoutControllerTest {
    @Test
    void shutdown() {
        StpUtil.login(10001);
        System.out.println(StpUtil.isLogin());
        StpUtil.logout(10001);
        System.out.println(StpUtil.isLogin());
        StpUtil.login(10001);// 强制指定账号注销下线
        System.out.println(StpUtil.isLogin());
        // StpUtil.logout(10001, "PC");// 强制指定账号指定端注销下线
        // System.out.println(StpUtil.isLogin());
        StpUtil.logoutByTokenValue(StpUtil.getTokenValue());      // 强制指定 Token 注销下线
        System.out.println(StpUtil.isLogin());
    }
    @Test
    void kick() {
        StpUtil.login(10001);
        System.out.println(StpUtil.isLogin());
        StpUtil.kickout(10001);                    // 将指定账号踢下线
        System.out.println(StpUtil.isLogin());
        StpUtil.login(10001);// 强制指定账号注销下线
        System.out.println(StpUtil.isLogin());
        // StpUtil.kickout(10001, "PC");              // 将指定账号指定端踢下线
        // System.out.println(StpUtil.isLogin());
        StpUtil.kickoutByTokenValue(StpUtil.getTokenValue());      // 将指定 Token 踢下线
        System.out.println(StpUtil.isLogin());
    }
}
