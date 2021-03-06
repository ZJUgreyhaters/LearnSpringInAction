package com.quantchi.authority.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class ShiroService {

	@Autowired
	ShiroFilterFactoryBean shiroFilterFactoryBean;

	@Autowired
	SysPermissionInitService sysPermissionInitService;

	/**
	 * 初始化权限
	 */
	public Map<String, String> loadFilterChainDefinitions() {
		// 权限控制map.从数据库获取
		Map<String, String> filterChainDefinitionMap;
		filterChainDefinitionMap = sysPermissionInitService.selectAll();

		return filterChainDefinitionMap;
	}

	/**
	 * 重新加载权限
	 */
	public void updatePermission() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		ServletContext servletContext = request.getSession().getServletContext();
		AbstractShiroFilter shiroFilter = (AbstractShiroFilter) WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).getBean("shiroFilter");

		synchronized (shiroFilter) {

			PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
							.getFilterChainResolver();
			DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
							.getFilterChainManager();

			// 清空老的权限控制
			manager.getFilterChains().clear();

			shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
			shiroFilterFactoryBean
							.setFilterChainDefinitionMap(loadFilterChainDefinitions());
			// 重新构建生成
			Map<String, String> chains = shiroFilterFactoryBean
							.getFilterChainDefinitionMap();
			for (Map.Entry<String, String> entry : chains.entrySet()) {
				String url = entry.getKey();
				String chainDefinition = entry.getValue().trim()
								.replace(" ", "");
				manager.createChain(url, chainDefinition);
			}

			System.out.println("更新权限成功！！");
		}
	}

}
