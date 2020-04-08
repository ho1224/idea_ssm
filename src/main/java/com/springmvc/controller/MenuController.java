/**  
 * @Title:  MenuController.java   
 * @Package cn.lastwhisper.controller   
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: 最后的轻语_dd43     
 * @date:   2019年4月6日 下午5:05:32   
 * @version V1.0 
 */
package com.springmvc.controller;

import java.util.List;

import com.core.annotation.LogAnno;
import com.core.util.UserUtils;
import com.springmvc.entity.Menu;
import com.springmvc.entity.User;
import com.springmvc.service.MenuService;
import com.springmvc.util.GlobalResult;
import com.springmvc.util.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**   
 * @ClassName:  MenuController   
 * @Description:菜单管理
 * @author:     最后的轻语_dd43
 * @date:       2019年4月6日
 */
@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	/**
	 * 查找所有
	 * @author Zoe
	 * @date 2019年2月15日下午3:46:48
	 * @return
	 */
	@RequestMapping(value="/menu/menulist")
	@ResponseBody
	public List<Tree> findAll() {
		return menuService.findMenuList(); 
	}
	
	/**
	 * 根据菜单id查找菜单，显示菜单详情
	 * @author Zoe
	 * @date 2019年2月15日下午8:15:02
	 * @param menuid 主键
	 * @return
	 */
	@RequestMapping("/menu/menufindById")
	@ResponseBody
	public List<Menu> findById(String menuid) {
		return menuService.findMenuById(menuid);
	}
	/**
	 * 添加数据
	 * @author Zoe
	 * @date 2019年2月15日下午9:47:56
	 * @param menu 菜单对象
	 * @return
	 */
	@LogAnno(operateType = "添加权限菜单")
	@RequestMapping(value="/menu/menuadd")
	@ResponseBody
	public GlobalResult insert(Menu menu) {
		return menuService.addMenu(menu);
	}
	
	/**
	 * 根据id删除数据[修改状态]
	 * @author Zoe
	 * @date 2019年2月15日下午9:47:41
	 * @param menuid 主键
	 * @return
	 */
	@LogAnno(operateType = "修改权限菜单")
	@RequestMapping(value="/menu/menudelete")
	@ResponseBody
	public GlobalResult deleteById(String menuid) {
		return menuService.deleteMenuById(menuid);
	}
	
	/**
	 * 根据id修改数据
	 * @author Zoe
	 * @date 2019年2月15日下午9:48:22
	 * @param menu 菜单对象
	 * @return
	 */
	@LogAnno(operateType = "更新权限菜单")
	@RequestMapping(value="/menu/menuupdate")
	@ResponseBody
	public GlobalResult updateById(Menu menu) {
		return menuService.updateMenuById(menu);
	}
	
	
	/**
	 * 
	* @Title: loadMenu 
	* @Description: 根据session中的user_id加载菜单
	* @return Menu
	* @author 最后的轻语_dd43
	* @date 2019年2月16日下午9:18:20
	 */
	@RequestMapping(value="/menu/loadMenus")
	@ResponseBody
	public Menu loadMenus() {
		User user = UserUtils.getSubjectUser();
		Menu menus = null;
		if(user!=null) {
			menus = menuService.findMenuByUserid(user.getUser_id());
		}
		return menus;
	}
}
