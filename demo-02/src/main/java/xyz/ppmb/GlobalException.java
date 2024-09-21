package xyz.ppmb;

import cn.dev33.satoken.exception.*;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	// 全局异常拦截（拦截项目中的NotLoginException异常）
	@ExceptionHandler(NotLoginException.class)
	public SaResult handlerNotLoginException(NotLoginException nle)
			throws Exception {
		// 打印堆栈，以供调试
		nle.printStackTrace();
		// 判断场景值，定制化异常信息
		String message = "";
		if(nle.getType().equals(NotLoginException.NOT_TOKEN)) {
			message = "未能读取到有效 token";
		}
		else if(nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
			message = "token 无效";
		}
		else if(nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
			message = "token 已过期";
		}
		else if(nle.getType().equals(NotLoginException.BE_REPLACED)) {
			message = "token 已被顶下线";
		}
		else if(nle.getType().equals(NotLoginException.KICK_OUT)) {
			message = "token 已被踢下线";
		}
		else if(nle.getType().equals(NotLoginException.TOKEN_FREEZE)) {
			message = "token 已被冻结";
		}
		else if(nle.getType().equals(NotLoginException.NO_PREFIX)) {
			message = "未按照指定前缀提交 token";
		}
		else {
			message = "当前会话未登录";
		}

		// 返回给前端
		return SaResult.error(message);
	}

	// 拦截：缺少权限异常
	@ExceptionHandler(NotPermissionException.class)
	public SaResult handlerException(NotPermissionException e) {
		e.printStackTrace(); 
		return SaResult.error("缺少权限：" + e.getPermission());
	}

	// 拦截：缺少角色异常
	@ExceptionHandler(NotRoleException.class)
	public SaResult handlerException(NotRoleException e) {
		e.printStackTrace(); 
		return SaResult.error("缺少角色：" + e.getRole());
	}

	// 拦截：二级认证校验失败异常
	@ExceptionHandler(NotSafeException.class)
	public SaResult handlerException(NotSafeException e) {
		e.printStackTrace(); 
		return SaResult.error("二级认证校验失败：" + e.getService());
	}

	// 拦截：服务封禁异常 
	@ExceptionHandler(DisableServiceException.class)
	public SaResult handlerException(DisableServiceException e) {
		e.printStackTrace(); 
		return SaResult.error("当前账号 " + e.getService() + " 服务已被封禁 (level=" + e.getLevel() + ")：" + e.getDisableTime() + "秒后解封");
	}

	// 拦截：Http Basic 校验失败异常 
	@ExceptionHandler(NotHttpBasicAuthException.class)
	public SaResult handlerException(NotHttpBasicAuthException e) {
		e.printStackTrace(); 
		return SaResult.error(e.getMessage());
	}

	// 拦截：其它所有异常
	@ExceptionHandler(Exception.class)
	public SaResult handlerException(Exception e) {
		e.printStackTrace(); 
		return SaResult.error(e.getMessage());
	}
	
}