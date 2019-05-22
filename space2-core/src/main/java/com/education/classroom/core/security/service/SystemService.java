/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.education.classroom.core.security.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.education.classroom.core.cache.util.CacheUtils;
import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.common.util.UserTypeConstants;
import com.education.classroom.core.config.Global;
import com.education.classroom.core.exception.ServiceException;
import com.education.classroom.core.log.util.LogUtils;
import com.education.classroom.core.modules.spadmin.user.dao.SpUserDao;
import com.education.classroom.core.modules.spadmin.user.entity.SpUser;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.security.Digests;
import com.education.classroom.core.security.shiro.session.SessionDAO;
import com.education.classroom.core.service.BaseService;
import com.education.classroom.core.servlet.Servlets;
import com.education.classroom.core.users.dao.MenuDao;
import com.education.classroom.core.users.dao.RoleDao;
import com.education.classroom.core.users.dao.UserDao;
import com.education.classroom.core.users.entity.Menu;
import com.education.classroom.core.users.entity.Role;
import com.education.classroom.core.users.entity.User;
import com.education.classroom.core.users.util.UserUtils;
import com.education.classroom.utils.Encodes;
import com.education.classroom.utils.IdGen;
import com.education.classroom.utils.PasswordUtil;
import com.education.classroom.utils.StringUtils;
import com.education.classroom.utils.classroom.SpaceUtils;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * @author ThinkGem
 * @version 2013-12-05
 */
@Service
@Transactional(readOnly = true)
public class SystemService extends BaseService {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private SessionDAO sessionDao;
	@Autowired
	private SpUserDao spUserDao;
	
	
	public SessionDAO getSessionDao() {
		return sessionDao;
	}

	/**
	 * 获取用户
	 * @param id
	 * @return
	 */
	public User getUser(String id) {
		return UserUtils.get(id);
	}

	/**
	 * 根据登录名获取用户
	 * @param loginName
	 * @return
	 */
	public User getUserByLoginName(String loginName) {
		return UserUtils.getByLoginName(loginName);
	}
	
	public Page<User> findUser(Page<User> page, Map<String, Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<User> users = userDao.findList(filters);
		PageInfo<User> userPage = new PageInfo<User>(users);
		PageUtil.convertPage(userPage, page);
		return page;
	}
	
	/**
	 * 无分页查询人员列表
	 * @param user
	 * @return
	 */
	public List<User> findUser(Map<String, Object> filters){
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		List<User> list = userDao.findListByMap(filters);
		return list;
	}

	/**
	 * 通过部门ID获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUserByOfficeId(String officeId) {
		List<User> list = (List<User>)CacheUtils.get(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + officeId);
		if (list == null){
			User user = new User();
			list = userDao.findUserByOfficeId(user);
			CacheUtils.put(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + officeId, list);
		}
		return list;
	}
	private void showUser(User user){
		String str="";
		str+="\n id="+user.getId();
		str+="\n loginName="+user.getLoginName();
		str+="\n password="+user.getPassword();
		str+="\n no="+user.getNo();
		str+="\n name="+user.getName();
		str+="\n email="+user.getEmail();
		str+="\n phone="+user.getPhone();
		str+="\n mobile="+user.getMobile();
		str+="\n userType="+user.getUserType();
		str+="\n photo="+user.getPhoto();
		str+="\n schoolId="+user.getSchoolId();
		str+="\n loginIp="+user.getLoginIp();
		str+="\n loginDate="+user.getLoginDate();
		str+="\n loginFlag="+user.getLoginFlag();
		str+="\n updateBy="+user.getUpdateBy();
		str+="\n updateDate="+user.getUpdateDate();
		str+="\n remarks="+user.getRemarks();
		str+="\n delFlag="+user.getDelFlag();
		str+="\n roleName="+user.getRoleNames();
		System.out.println(str);
	}
	@Transactional(readOnly = false)
	public void saveUser(User user) {
		showUser(user);
		System.out.println("---------------------userId-pre : "+user.getId());
		if (StringUtils.isBlank(user.getId())){
			user.setId(IdGen.uuid());
		}
		System.out.println("---------------------userId-post: "+user.getId());
		User localUser=userDao.get(user.getId());//user是平台刚同步添加的用户，我要先用那个id查一下本地是否有这个用户，单纯的用有没有id判读是新增还是修改已经不行了。
		if (localUser==null){
			user.setCreateBy(user.getCurrentUser().getId());
			user.setUpdateBy(user.getCurrentUser().getId());
			user.setCreateDate(new Date());
			user.setUpdateDate(user.getCreateDate());
			user.setSchoolId(SpaceUtils.getSpaceId());
			userDao.insert(user);
		}else{
			user.setUpdateBy(user.getCurrentUser().getId());
			user.setUpdateDate(new Date());
			user.setSchoolId(SpaceUtils.getSpaceId());
			userDao.update(user);
		}
		if(UserTypeConstants.SYS_USER.equals(user.getUserType())){
		}else{
			SpUser spus = spUserDao.get(user.getId());
			String oper="update";
			if(spus == null){
				spus = new SpUser();
				oper="insert";
			}
			System.out.println("--------777----------id="+user.getId()+" , name="+user.getName()+" , loginName="+user.getLoginName()+" , password="+user.getPassword());
			spus.setName(user.getName());
			spus.setLoginName(user.getLoginName());
			spus.setUserPwd(user.getPassword());
			spus.setUserType(user.getUserType());
			spus.setMobile(user.getMobile());
			spus.setEmail(user.getEmail());
			spus.setThumbImg(user.getPhoto());
			spus.setSpaceId(SpaceUtils.getSpaceId());
			spus.setRemarks(user.getRemarks());
			spus.setUpdateBy(user.getCurrentUser().getId());
			spus.setUpdateDate(new Date());
			if("insert".equals(oper)){
				spus.setId(user.getId());
				spus.setCreateBy(user.getCurrentUser().getId());
				spus.setCreateDate(new Date());
				spus.setDelFlag("0");
				spUserDao.insert(spus);
			}else{
				spus.preUpdate();
				spUserDao.update(spus);
			}
			
		}
		
		if (StringUtils.isNotBlank(user.getId())){
			// 更新用户与角色关联
			userDao.deleteUserRole(user);
			if (user.getRoleList() != null && user.getRoleList().size() > 0){
				userDao.insertUserRole(user);
			}else{
				throw new ServiceException(user.getLoginName() + "没有设置角色！");
			}
			// 将当前用户同步到Activiti
	//		saveActivitiUser(user);
			// 清除用户缓存
			UserUtils.clearCache(user);
//			// 清除权限缓存
//			systemRealm.clearAllCachedAuthorizationInfo();
		}
	}
	
	@Transactional(readOnly = false)
	public void updateUserInfo(User user) {
		user.preUpdate();
		userDao.updateUserInfo(user);
		// 清除用户缓存
		UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	
	@Transactional(readOnly = false)
	public String deleteUser(User user) {
		 String spUserId ="";
		User oldUser = userDao.get(user.getId());
		if(UserTypeConstants.TEACHER_USER.equals(oldUser.getUserType())){
				spUserDao.deleteSpUser(user.getId());
		}
		userDao.delete(user);
		// 同步到Activiti
	//	deleteActivitiUser(user);
		// 清除用户缓存
		UserUtils.clearCache(user); 
		
		return spUserId;
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	
	@Transactional(readOnly = false)
	public void updatePasswordById(String id, String loginName, String newPassword) {
		User user = new User(id);//entryptPassword
		user.setPassword(PasswordUtil.encodePassword(newPassword));
		userDao.updatePasswordById(user);
		// 清除用户缓存
		user.setLoginName(loginName);
		UserUtils.clearCache(user);
		User oldUser = userDao.get(id);
		if(UserTypeConstants.TEACHER_USER.equals(user.getUserType())){
			SpUser us = new SpUser();
			us.setLoginName(oldUser.getLoginName());
			SpUser u = spUserDao.get(us);
			u.preUpdate();
			u.setUserPwd(user.getNewPassword());
			spUserDao.insert(u);
		}
		
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	
	@Transactional(readOnly = false)
	public void updateUserLoginInfo(User user) {
		// 保存上次登录信息
		user.setOldLoginIp(user.getLoginIp());
		user.setOldLoginDate(user.getLoginDate());
		// 更新本次登录信息
		user.setLoginIp(StringUtils.getRemoteAddr(Servlets.getRequest()));
		user.setLoginDate(new Date());
		userDao.updateLoginInfo(user);
	}
	
	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		String plain = Encodes.unescapeHtml(plainPassword);
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
	}
	
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param password 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		String plain = Encodes.unescapeHtml(plainPassword);
		byte[] salt = Encodes.decodeHex(password.substring(0,16));
		byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 获得活动会话
	 * @return
	 */
	public Collection<Session> getActiveSessions(){
		return sessionDao.getActiveSessions(false);
	}
	
	//-- Role Service --//
	
	public Role getRole(String id) {
		return roleDao.get(id);
	}

	public Role getRoleByName(String name) {
		Role r = new Role();
		r.setName(name);
		return roleDao.getByName(r);
	}
	
	public Role getRoleByEnname(String enname) {
		Role r = new Role();
		r.setEnname(enname);
		return roleDao.getByEnname(r);
	}
	
	public List<Role> findRole(Role role){
		return roleDao.findList(role);
	}
	
	public List<Role> findAllRole(){
		return UserUtils.getRoleList();
	}
	
	@Transactional(readOnly = false)
	public void saveRole(Role role) {
		if (StringUtils.isBlank(role.getId())){
			role.preInsert();
			roleDao.insert(role);
			// 同步到Activiti
		//	saveActivitiGroup(role);
		}else{
			role.preUpdate();
			roleDao.update(role);
		}
		// 更新角色与菜单关联
		roleDao.deleteRoleMenu(role);
		if (role.getMenuList().size() > 0){
			roleDao.insertRoleMenu(role);
		}
		// 更新角色与部门关联
		roleDao.deleteRoleOffice(role);
		// 清除用户角色缓存
		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}

	@Transactional(readOnly = false)
	public void deleteRole(Role role) {
		roleDao.delete(role);
		// 同步到Activiti
	//	deleteActivitiGroup(role);
		// 清除用户角色缓存
		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
	}
	
	@Transactional(readOnly = false)
	public Boolean outUserInRole(Role role, User user) {
		List<Role> roles = user.getRoleList();
		for (Role e : roles){
			if (e.getId().equals(role.getId())){
				roles.remove(e);
				saveUser(user);
				return true;
			}
		}
		return false;
	}
	
	@Transactional(readOnly = false)
	public User assignUserToRole(Role role, User user) {
		if (user == null){
			return null;
		}
		List<String> roleIds = user.getRoleIdList();
		if (roleIds.contains(role.getId())) {
			return null;
		}
		user.getRoleList().add(role);
		saveUser(user);
		return user;
	}

	//-- Menu Service --//
	
	public Menu getMenu(String id) {
		return menuDao.get(id);
	}

	public List<Menu> findAllMenu(){
		return UserUtils.getMenuList();
	}
	
	@Transactional(readOnly = false)
	public void saveMenu(Menu menu) {
		
		// 获取父节点实体
		menu.setParent(this.getMenu(menu.getParent().getId()));
		
		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = menu.getParentIds(); 
		
		// 设置新的父节点串
		menu.setParentIds(menu.getParent().getParentIds()+menu.getParent().getId()+",");

		// 保存或更新实体
		if (StringUtils.isBlank(menu.getId())){
			menu.preInsert();
			menuDao.insert(menu);
		}else{
			menu.preUpdate();
			menuDao.update(menu);
		}
		
		// 更新子节点 parentIds
		Menu m = new Menu();
		m.setParentIds("%,"+menu.getId()+",%");
		List<Menu> list = menuDao.findByParentIdsLike(m);
		for (Menu e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, menu.getParentIds()));
			menuDao.updateParentIds(e);
		}
		// 清除用户菜单缓存
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
		// 清除日志相关缓存
		CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}

	@Transactional(readOnly = false)
	public void updateMenuSort(Menu menu) {
		menuDao.updateSort(menu);
		// 清除用户菜单缓存
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
		// 清除日志相关缓存
		CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}

	@Transactional(readOnly = false)
	public void deleteMenu(Menu menu) {
		menuDao.delete(menu);
		// 清除用户菜单缓存
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
		// 清除日志相关缓存
		CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
	}
	
	/**
	 * 获取Key加载信息
	 */
	public static boolean printKeyLoadMessage(){
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n======================================================================\r\n");
		sb.append("\r\n    欢迎使用 "+Global.getConfig("productName")+"  - Powered By zkyd");
		sb.append("\r\n======================================================================\r\n");
		System.out.println(sb.toString());
		return true;
	}


	/**
	 * 插入创业者数据到cr_user_team
	 * @param loginName
	 * @return
	 */
	/*@Transactional(readOnly = false)
	public int insertUserTeam(UserTeam userTeam){
		return userDao.insertUserTeam(userTeam);
	};*/
	
	/**
	 * 插入创业者数据到cr_user
	 * @param loginName
	 * @return
	 */
	/*@Transactional(readOnly = false)
	public int insertCreatorUser(User user){
		UserTeam userTeam = new UserTeam();
		if (StringUtils.isBlank(user.getId())){
			user.preInsert();
		 userDao.insertCreatorUser(user);
		userTeam.setUserId(user.getId());
		userTeam.setTeamId(user.getTeamId());
		userTeam.preInsert();
		return userDao.insertUserTeam(userTeam);
		} else {
			// 更新用户数据
			user.preUpdate();
			userDao.updateCreator(user);
			userTeam.setUserId(user.getId());
			userTeam.setTeamId(user.getTeamId());
			userTeam.preUpdate();
			return userDao.updateCreatorTeam(userTeam);
		}
	}
	*/
	/**
	 * 插入投资者数据到cr_user
	 * @param loginName
	 * @return
	 */
	@Transactional(readOnly = false)
	public int insertInvestorUser(User user){
		if (StringUtils.isBlank(user.getId())){
			user.preInsert();
		 return userDao.insertCreatorUser(user);
		} else {
			// 更新用户数据
			user.preUpdate();
			return userDao.updateCreator(user);
		}
	}
	
	
	/**
	 * 删除创业者cr_user
	 * @param loginName
	 * @return
	 */
	@Transactional(readOnly = false)
	 public void deleteCreatorUser(User user){
		userDao.deleteCreator(user.getId());
		userDao.deleteCreatorUser(user.getId());
	}
	
	/**
	 * 删除投资者cr_user
	 * @param loginName
	 * @return
	 */
	@Transactional(readOnly = false)
	 public void deleteInvestorUser(User user){
		userDao.deleteCreatorUser(user.getId());
	}
    
	/**
	 * 检查手机号是否重复
	 * 2016年8月18日
	 * By 路志友
	 * @param mobile
	 * @return
	 */
	public Boolean checkMobile(String mobile) {
		User user = userDao.checkMobile(mobile);
		if(user != null){
			return true;
		}
		return false;
	}

	//后台添加用户，需要同步到前端用户表，需要检查前端用户表是否存在该用户mobile
	public int hasUser(User user) {
		return spUserDao.hasUser(user);
	}
	
}
