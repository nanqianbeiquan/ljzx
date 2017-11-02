package com.srd.ljzd.controller.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.exception.BusinessException;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.service.index.NavMenuService;
import com.srd.ljzd.util.ErrorCode;
@Controller
@RequestMapping("/navMenu")
public class NavMenuController {
    @Autowired
	NavMenuService navMenuService;

	
	@RequestMapping("updateNavPath.do")
	public String updateNavPath(Integer deep,String name,String baseURL,String baseParamURL,String paramURL,Model model
			,HttpServletRequest request,HttpServletResponse response){
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		if(account==null||account.getAccountId()==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		if(deep==null){
			throw new BusinessException(ErrorCode.ER_UNKNOWN_EXCEPTION, "updateNavPath exception,deep is null,param[name="+name+",baseURL="+baseURL+"]", null, NavMenuController.class);
		}
		List<Map<String, Object>> navData = navMenuService.getNavPathData(account.getAccountId());
		Map<String, Object> menu = null;
        if(navData!=null){
			if(deep>navData.size()-1){
				menu = new HashMap<String, Object>();
				menu.put("deep", deep);
				menu.put("name", name);
				menu.put("baseURL", baseURL);
				menu.put("paramURL", paramURL);
				menu.put("baseParamURL",baseParamURL);
				navData.add(menu);
			}else{
				menu = navData.get(deep);
				menu.put("deep", deep);
				menu.put("name", name);
				menu.put("baseURL", baseURL);
				menu.put("paramURL", paramURL);
				menu.put("baseParamURL",baseParamURL);
				for(int i=deep+1;i<navData.size();i++){
					navData.remove(i--);
				}
			}
		}else if(deep==0){//第一次进首页
			navData = new ArrayList<Map<String,Object>>();
			menu = new HashMap<String, Object>();
			menu.put("deep", deep);
			menu.put("name", name);
			menu.put("baseURL", baseURL);
			menu.put("paramURL", paramURL);
			menu.put("baseParamURL",baseParamURL);
			navData.add(menu);
		}
        navData = navMenuService.updateNavPathData(account.getAccountId(), navData);
        Map<String, Object> preMenu = null;
        if(navData!=null&&navData.size()>=2&&deep>0&&navData.size()>deep-1){
        	preMenu = navData.get(deep-1);
        }
        model.addAttribute("preMenu", preMenu);
        model.addAttribute("navData", navData);
        
		return "/modules/monitor/navMenu";
	}
	
}
