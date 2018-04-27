package com.eighth.web.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.AttributeOverride;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eighth.entity.Modules;
import com.eighth.service.ModulesService;

@Controller
public class ModulesCtroller {
	
	@Autowired
	private ModulesService modulesService;
	
	/**
	 * 显示所有菜单
	 * */
	@RequestMapping("/showAllModules")
	@ResponseBody
	public Object getAll(){
		List<Modules> list =modulesService.getMenuList(null);
		return list;
	}
	
	@RequestMapping("/addModules")
	@ResponseBody
	public Object addModule(@Valid @ModelAttribute("module") Modules module){
		int n=modulesService.selectCountByModuleName(module.getModuleName());
		int row=0;
		Map map=new HashMap();
		if(n==0){
			row=modulesService.addModules(module);
			if(row==0){
				map.put("errorMsg", false);
				map.put("message", "添加失败！");
			}else{
			map.put("errorMsg", true);
			map.put("message", "添加成功！");
			}
		}else{
			map.put("errorMsg", false);
			map.put("message", "该节点名称已存在！添加失败！");
		}
		return map;
	}
	
	@RequestMapping("/updModules")
	@ResponseBody
	public  Object updModule(@ModelAttribute("menu")Modules module ){
		int n=modulesService.selectCountByModuleName(module.getModuleName());
		int row=0;
		Map map=new HashMap();
		if(n==0||modulesService.selectModuleIdByModuleName(module.getModuleName())==module.getModuleId()){
			row=modulesService.updModules(module);
			if(row==0){
				map.put("errorMsg", false);
				map.put("message", "修改失败！");
			}else{
			map.put("errorMsg", true);
			map.put("message", "修改成功！");
			}
		}else{
			map.put("errorMsg", false);
			map.put("message", "该节点名称已存在！修改失败！");
		}
		return map;
	}
	
	@RequestMapping("/delModule")
	@ResponseBody
	public Object delModule(Integer mId){
		int r=modulesService.deleteByPrimaryKey(mId);
		if(r>0){
			return "success";
		}else{
			return "error";
		}
	}
	
	@RequestMapping("/getAllModuleByRoleId")
	@ResponseBody
	public Object getAllModuleByRoleId(String roleId){
		List<Modules> list =modulesService.getMenuList(roleId);
		return list;
	}
	
	/**
	 * 动态查询用户权限模块
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/queryModByUid")
	@ResponseBody
	public Object queryModByUid(@RequestParam("userId") String userId){
		return modulesService.selectByUserid(userId);
	}
}
