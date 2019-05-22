package com.education.classroom.modules.sys.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.education.classroom.core.beanvalidator.BeanValidators;
import com.education.classroom.core.common.util.CommonUtils;
import com.education.classroom.core.common.util.UserTypeConstants;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.excel.util.ExportExcel;
import com.education.classroom.core.excel.util.ImportExcel;
import com.education.classroom.core.modules.spadmin.user.entity.SpUser;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.security.service.SystemService;
import com.education.classroom.core.users.entity.Role;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.core.web.BaseController;
import com.education.classroom.modules.spadmin.user.service.SpUserService;

import com.education.classroom.utils.PasswordUtil;
import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.OssUtils.OSSClientUtil;
import com.education.classroom.utils.classroom.SpaceUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 用户Controller
 * @author ThinkGem
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SystemService systemService;
	
	@Autowired
	private SpUserService spUserService;
	
	@ModelAttribute
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return systemService.getUser(id);
		}else{
			return new User();
		}
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"index"})
	public String index(User user, Model model) {
		return "modules/sys/userIndex";
	}
	
	
	@RequestMapping(value = {"toinfo"})
	public String toinfo(User user, Model model) {
		User currentUser = UserUtils.getUser();
		model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
			return "modules/sys/userInfo";
		
	}
	

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"list", ""})
	public String list(User user, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			
			Map<?,?> filters = request.getParameterMap();
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
			//queryMap.put("schoolId", schoolId);
			queryMap.put("delFlag", "0");
			Page<User> page = systemService.findUser(new Page<User>(request, response), queryMap);
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("查询用户列表失败！", e);
		}
		return "modules/sys/userList";
	}
	
	@ResponseBody
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"listData"})
	public Page<User> listData(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
	    Page<User> page =null;
	    try{
	    	Map<?, ?> filters = request.getParameterMap();
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap = CommonUtils.copyQueryMap(filters, queryMap);
	        page = systemService.findUser(new Page<User>(request, response), queryMap);
	    }catch(Exception e){
            logger.error("查询列表数据失败！",e);
        }
		return page;
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "form")
	public String form(User user, Model model) {
	    try{
	   // String schoolId =	UserUtils.getUser().getSchoolId();
		//CrTeam c = new CrTeam();
		//c.setDelFlag(stateConstants.NODEL);
		//List<CrTeam> ls = null;
		//	ls = crTeamService.findList(c);
		//	model.addAttribute("schoolId", schoolId);
		//model.addAttribute("teamList", ls);
	    	if(user != null && StringUtils.isNotEmpty(user.getId())){
	    		User use = systemService.getUser(user.getId());
	    		System.out.println("user="+use.getId()+" , "+use.getName()+" , "+use.getLoginName()+" , "+use.getMobile());
	    		model.addAttribute("user", use);
	    	}else{
	    		model.addAttribute("user", user);
	    	}
	    	
		model.addAttribute("allRoles", systemService.findAllRole());
	 }catch(Exception e){
         logger.error("用户信息查询失败！",e);
     }
		return "modules/sys/userForm";
	}

	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "save")
	public String save(@RequestParam("file") CommonsMultipartFile file,User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
	    try{
	/*	if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}*/
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		/*user.setCompany(new Office(request.getParameter("company.id")));
		user.setOffice(new Office(request.getParameter("office.id")));*/
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(PasswordUtil.encodePassword(user.getNewPassword()));
		}
		if (!beanValidator(model, user)){
			return form(user, model);
		}
		if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
	         logger.warn( "保存用户'" + user.getLoginName() + "'失败，登录名已存在");

			return form(user, model);
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		for (Role r : systemService.findAllRole()){
			if (roleIdList.contains(r.getId())){
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		// 保存用户信息
		
		if(!file.isEmpty()){
			OSSClientUtil o = new OSSClientUtil();
			Map<String,String> map = o.uploadImg2Oss(file);
			if("1".equals(map.get("SUCCESS"))){
				String name = map.get("NAMEKEY");
				String url = o.getImgOrFileUrl(name);
				user.setPhoto(url);
				o.destory();
				
			}else{
				logger.error( "上传图片失败");
			}
			
		}
		user.setSchoolId(SpaceUtils.getSpaceId());//设置第二空间id
			if(UserTypeConstants.TEACHER_USER.equals(user.getUserType())
					||UserTypeConstants.CREATOR_USER.equals(user.getUserType())
					||UserTypeConstants.INVEST_USER.equals(user.getUserType())
					||UserTypeConstants.MIANASSISTANT_USER.equals(user.getUserType())
					||UserTypeConstants.TUTOR_USER.equals(user.getUserType())
					||UserTypeConstants.VICEASSISTANT_USER.equals(user.getUserType())){
			 String userId=null;
			try {
				//userId = dataTransferService.syncTeacherUser(user);
			} catch (Exception e) {
				System.out.println("同步数据失败，"+e.getMessage());
				logger.error("同步数据失败，"+e.getMessage());
				addMessage(redirectAttributes, "同步数据失败，平台用户已存在");
				return "redirect:" + adminPath + "/sys/user/list?repage";
			}
			int hasUser=systemService.hasUser(user);//【根据mobile查询前端用户表sp_user】
			System.out.println("学校端前端用户表，该mobile个数："+hasUser);
			if(hasUser!=0){
				if(StringUtils.isNotBlank(user.getId())){
					SpUser existUser = spUserService.get(user.getId());
					if(existUser.getMobile().equals(user.getMobile())){
						//说明手机号仍旧是当前用户自己的占用的手机号，所以不应该报“用户已存在”
					}else{
						addMessage(redirectAttributes, "前端用户已存在");
						return "redirect:" + adminPath + "/sys/user/list?repage";
					}
				}else{
					addMessage(redirectAttributes, "前端用户已存在");
					return "redirect:" + adminPath + "/sys/user/list?repage";
				}
			}
			 if(StringUtils.isNotEmpty(userId)){
				 user.setId(userId);
				  systemService.saveUser(user);
			 }else{
				 addMessage(redirectAttributes, "保存/修改用户失败，平台用户操作异常！");
			 }
			}else{
				systemService.saveUser(user);
			}
	
		    
		// 清除当前用户缓存
		if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
			UserUtils.clearCache();
			//UserUtils.getCacheMap().clear();
		}
		addMessage(redirectAttributes, "保存用户'" + user.getLoginName() + "'成功");
	    }catch(Exception e){
             logger.error( "保存用户'" + user.getLoginName() + "'失败");
	    }
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}
	
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "delete")
	public String delete(User user, RedirectAttributes redirectAttributes) {
	    try{
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		if (UserUtils.getUser().getId().equals(user.getId())){
			addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
		}else if (User.isAdmin(user.getId())){
			addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
		}else{
			
				systemService.deleteUser(user);
				addMessage(redirectAttributes, "删除用户成功");
			
			
		}
	 }catch(Exception e){
         logger.error( "保存用户'" + user.getLoginName() + "'失败");
    }
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}

	/**
	 * 导入用户数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/user/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<User> list = ei.getDataList(User.class);
			for (User user : list){
				try{     // SystemService.entryptPassword(plainPassword);
					if ("true".equals(checkLoginName("", user.getLoginName()))){
						user.setPassword(PasswordUtil.encodePassword("123456"));
						user.setUserType(UserTypeConstants.STUDENT_USER);
						BeanValidators.validateWithException(validator, user);
						systemService.saveUser(user);
						successNum++;
					}else{
						failureMsg.append("<br/>登录名 "+user.getLoginName()+" 已存在; ");
						failureNum++;
					}
				}catch(ConstraintViolationException ex){
					failureMsg.append("<br/>登录名 "+user.getLoginName()+" 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList){
						failureMsg.append(message+"; ");
						failureNum++;
					}
				}catch (Exception ex) {
					failureMsg.append("<br/>登录名 "+user.getLoginName()+" 导入失败："+ex.getMessage());
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }
	
	/**
	 * 下载导入用户数据模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户数据导入模板.xlsx";
    		List<User> list = Lists.newArrayList(); list.add(UserUtils.getUser());
    		new ExportExcel("用户数据", User.class, 2).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
    }

	/**
	 * 验证登录名是否有效
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String oldLoginName, String loginName) {
		System.out.println("??????????????????oldLoginName="+oldLoginName+" , loginName="+loginName);
		if (oldLoginName ==null || loginName.equals(oldLoginName)) {
			return "true";//返回“true”不提示错误信息。
		} else if (loginName !=null && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}
 
	/**
	 * 校验手机号
	 * 2016年8月18日
	 * By 路志友
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkMobile")
	public String checkMobile(String mobile,String oldMobile) {
		System.out.println("??????????????????oldMobile="+oldMobile+" , mobile="+mobile);
		if (oldMobile ==null || mobile.equals(oldMobile)) {
			return "true";
		}else{
			//Boolean flag = dataTransferService.syncCheckMobile(mobile);
			Boolean flag = systemService.checkMobile(mobile);//存在记录，返回true
					if(flag){
						return "false";
					}
			
		}
		return "true";
	}
	
	/**
	 * 用户信息显示及保存
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "info")
	public String info(@RequestParam("file") CommonsMultipartFile file,User user, HttpServletResponse response, Model model) {
		User currentUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getName())){
			/*if(Global.isDemoMode()){
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userInfo";
			}*/
			
			if(!file.isEmpty()){
				OSSClientUtil o = new OSSClientUtil();
				Map<String,String> map = o.uploadImg2Oss(file);
				if("1".equals(map.get("SUCCESS"))){
					String name = map.get("NAMEKEY");
					String url = o.getImgOrFileUrl(name);
					currentUser.setPhoto(url);
					o.destory();
				}else{
					logger.error( "上传图片失败");
				}
				
			}
			currentUser.setEmail(user.getEmail());
			currentUser.setPhone(user.getPhone());
			currentUser.setMobile(user.getMobile());
			currentUser.setRemarks(user.getRemarks());
			
			if(UserTypeConstants.TEACHER_USER.equals(user.getUserType())){
				 //dataTransferService.syncTeacherUser(user);
				}
		
			systemService.updateUserInfo(currentUser);
			model.addAttribute("message", "保存用户信息成功");
		}
		model.addAttribute("user", currentUser);
		model.addAttribute("Global", new Global());
		return "modules/sys/userInfo";
	}

	/**
	 * 返回用户信息
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "infoData")
	public User infoData() {
		return UserUtils.getUser();
	}
	
	/**
	 * 修改个人用户密码
	 * @param oldPassword
	 * @param newPassword
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)){
			if(Global.isDemoMode()){
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userModifyPwd";
			}
			if (SystemService.validatePassword(oldPassword, user.getPassword())){
				systemService.updatePasswordById(user.getId(), user.getLoginName(), newPassword);
				model.addAttribute("message", "修改密码成功");
			}else{
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		model.addAttribute("user", user);
		return "modules/sys/userModifyPwd";
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<User> list = systemService.findUserByOfficeId(officeId);
		for (int i=0; i<list.size(); i++){
			User e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", "u_"+e.getId());
			map.put("pId", officeId);
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}
    
//	@InitBinder
//	public void initBinder(WebDataBinder b) {
//		b.registerCustomEditor(List.class, "roleList", new PropertyEditorSupport(){
//			@Autowired
//			private SystemService systemService;
//			@Override
//			public void setAsText(String text) throws IllegalArgumentException {
//				String[] ids = StringUtils.split(text, ",");
//				List<Role> roles = new ArrayList<Role>();
//				for (String id : ids) {
//					Role role = systemService.getRole(Long.valueOf(id));
//					roles.add(role);
//				}
//				setValue(roles);
//			}
//			@Override
//			public String getAsText() {
//				return Collections3.extractToString((List) getValue(), "id", ",");
//			}
//		});
//	}
}
