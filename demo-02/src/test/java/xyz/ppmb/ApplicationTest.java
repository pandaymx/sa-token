package xyz.ppmb;

import cn.dev33.satoken.stp.StpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {
    @Test
    void login() {
        // 登录用户
        StpUtil.login(10001);
        // 获取当前会话是否已经登录，返回true=已登录，false=未登录
        StpUtil.isLogin();
        // 检验当前会话是否已经登录, 如果未登录，则抛出异常：`NotLoginException`
        StpUtil.checkLogin();
        // 当前会话注销登录
        StpUtil.logout();
        StpUtil.isLogin();
        StpUtil.checkLogin();
    }

    @Test
    void session() {
        StpUtil.login(1000112545646313L);
        // 获取当前会话账号id, 如果未登录，则抛出异常：`NotLoginException`
        StpUtil.getLoginId();
        // 类似查询API还有：
        // System.out.println(StpUtil.getLoginIdAsInt());       // 获取当前会话账号id, 并转化为`int`类型
        System.out.println(StpUtil.getLoginIdAsString());    // 获取当前会话账号id, 并转化为`String`类型
        System.out.println(StpUtil.getLoginIdAsLong());      // 获取当前会话账号id, 并转化为`long`类型

        // ---------- 指定未登录情形下返回的默认值 ----------
        // 获取当前会话账号id, 如果未登录，则返回 null
        StpUtil.logout();
        System.out.println(StpUtil.getLoginIdDefaultNull());
        // 获取当前会话账号id, 如果未登录，则返回默认值 （`defaultValue`可以为任意类型）
        System.out.println(StpUtil.getLoginId("T defaultValue"));
    }

    @Test
    void token() {
        StpUtil.login(1000112545646313L);
        // 获取当前会话的 token 值
        System.out.println(StpUtil.getTokenValue());
        // 获取当前`StpLogic`的 token 名称
        System.out.println(StpUtil.getTokenName());
        // 获取指定 token 对应的账号id，如果未登录，则返回 null
        System.out.println(StpUtil.getLoginIdByToken(StpUtil.getTokenValue()));
        // 获取当前会话剩余有效期（单位：s，返回-1代表永久有效）
        System.out.println(StpUtil.getTokenTimeout());
        // 获取当前会话的 token 信息参数
        System.out.println(StpUtil.getTokenInfo());
    }
}
