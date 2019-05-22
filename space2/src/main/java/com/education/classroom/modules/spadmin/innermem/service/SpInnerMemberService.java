package com.education.classroom.modules.spadmin.innermem.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.education.classroom.core.common.util.PageUtil;
import com.education.classroom.core.exception.ServiceException;
import com.education.classroom.core.member.type.MemberState;
import com.education.classroom.core.member.type.PublishState;
import com.education.classroom.core.modules.spadmin.innermem.dao.SpInnerMemberDao;
import com.education.classroom.core.modules.spadmin.innermem.entity.SpInnerMember;
import com.education.classroom.core.modules.spadmin.space.dao.SpSpaceDao;
import com.education.classroom.core.modules.spadmin.spclass.dao.SpClassDao;
import com.education.classroom.core.modules.spadmin.specialty.dao.SpSpecialtyDao;
import com.education.classroom.core.page.helper.PageHelper;
import com.education.classroom.core.page.helper.PageInfo;
import com.education.classroom.core.persistence.Page;
import com.education.classroom.core.service.CrudService;
import com.education.classroom.core.users.util.UserUtils;

import com.education.classroom.utils.ArrayHelper;
import com.education.classroom.utils.IdGen;
import com.education.classroom.utils.classroom.SpaceUtils;
import com.google.common.collect.Maps;

/**
 * 会员预留信息管理Service
 * @author 尚军伟
 * @version 2016/08/06
 */
@Service
public class SpInnerMemberService extends CrudService<SpInnerMemberDao, SpInnerMember> {

    private Logger logger = LoggerFactory.getLogger(SpInnerMemberService.class);
	@Autowired
	private SpInnerMemberDao innerMemberDao;
	@Autowired
	private SpSpecialtyDao specialtyDao;
	@Autowired
	private SpClassDao classDao;
	@Autowired
	private SpSpaceDao spaceDao;
	
	
	public SpInnerMember get(String id) {
		return super.get(id);
	}
	
	public List<SpInnerMember> findList(SpInnerMember spInnerMember) {
		return super.findList(spInnerMember);
	}
	
	public Page<SpInnerMember> findPage(Page<SpInnerMember> page, Map<String,Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpInnerMember> schems = innerMemberDao.findList(filters);
		PageInfo<SpInnerMember> resultPage = new PageInfo<SpInnerMember>(schems);
		PageUtil.convertPage(resultPage, page);
		return page;
	}
	
	/*public Page<SpInnerMemberView> findPage(Page<SpInnerMemberView> page, Map<String,Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpInnerMember> members = innerMemberDao.findList(filters);
		long total = page.getCount();
		List<SpInnerMemberView> views = Lists.newArrayList();
		for(SpInnerMember member : members){
			SpInnerMemberView viewItem = new SpInnerMemberView();
			BeanUtils.copyProperties(member, viewItem);
			views.add(viewItem);
		}
		PageInfo<SpInnerMemberView> resultPage = new PageInfo<SpInnerMemberView>(views);
		//resultPage.setTotal(page.getCount());
		PageUtil.convertPage(resultPage, page);
		page.setCount(total);
		return page;
	}*/
	
	/*public Page<SpInnerMemberView> findPage(Page<SpInnerMember> page, Map<String,Object> filters) {
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		List<SpInnerMember> members = innerMemberDao.findList(filters);
		List<SpInnerMemberView> views = Lists.newArrayList();
		for(SpInnerMember member : members){
			SpInnerMemberView viewItem = new SpInnerMemberView();
			BeanUtils.copyProperties(member, viewItem);
			views.add(viewItem);
		}
		Page<SpInnerMemberView> viewPage = new Page<SpInnerMemberView>();
		viewPage.setList(views);
		viewPage.setPageNo(page.getPageNo());
		viewPage.setPageSize(page.getPageSize());
		viewPage.setCount(page.getTotalPage());
		return viewPage;
	}*/

	
	@Transactional(readOnly = false)
	public void save(SpInnerMember innerMember) {
		if(StringUtils.isEmpty(innerMember.getId())){
			innerMember.setId(IdGen.uuid());
			innerMember.setPublishState(PublishState.WAIT_AUDIT);
			innerMember.setState(MemberState.UN_ACTIVED);
			innerMember.setIsNewRecord(true);
			String userId = UserUtils.getUserId();
			if (StringUtils.isNotEmpty(userId)) {
				innerMember.setCreateBy(userId);
				innerMember.setUpdateBy(userId);
			}
			innerMember.setCreateDate(new Date());
			innerMember.setUpdateDate(new Date());
			innerMember.setSpecialtyName(specialtyDao.get(innerMember.getSpecialtyId()).getName());
			innerMember.setClassName(classDao.get(innerMember.getClassId()).getName());
			innerMemberDao.insert(innerMember);
		} else {
			innerMember.setIsNewRecord(false);
			String userId = UserUtils.getUserId();
			if (StringUtils.isNotEmpty(userId)) {
				innerMember.setCreateBy(userId);
				innerMember.setUpdateBy(userId);
			}
			innerMember.setCreateDate(new Date());
			innerMember.setUpdateDate(new Date());
			innerMember.setSpecialtyName(specialtyDao.get(innerMember.getSpecialtyId()).getName());
			innerMember.setClassName(classDao.get(innerMember.getClassId()).getName());
			innerMemberDao.update(innerMember);
		}
		
		
	}
	
	@Transactional(readOnly = false)
	public void checkApproveState(String id) {
		
	}
	
	@Transactional(readOnly = false)
	public void deleteInnerMember(String id) {
		innerMemberDao.deleteInnerMember(id);
		
	}
	
	@Transactional(readOnly = false)
	public void delete(SpInnerMember spInnerMember) {
		super.delete(spInnerMember);
	}
	
	@Transactional(readOnly = false)
	public void deleteById(String id) {
		innerMemberDao.deleteInnerMember(id);
	}
	
	/**
	 * 根据会员手机号检验手机号是否已经注册
	 * 先检查本地是否已经注册，如果本地没有注册，再去平台检查
	 * 2016年8月15日
	 * By zhangyongsheng
	 * @param phoneNo
	 * @return
	 */
	public boolean checkPhoneNo(String phoneNo) {
		String spaceId = SpaceUtils.get("SpaceId");
		Map<String, Object> params = Maps.newHashMap();
		params.put("spaceId", spaceId);
		params.put("phoneNo", phoneNo);
		List<SpInnerMember> members = innerMemberDao.getByParams(params);
		if(ArrayHelper.isNotEmpty(members)){
			return false;
		} else {
			List<SpInnerMember> platMembers = null;
			if(ArrayHelper.isNotEmpty(platMembers)){
				return false;
			} else {
				return true;
			}
		}
	}
	
}