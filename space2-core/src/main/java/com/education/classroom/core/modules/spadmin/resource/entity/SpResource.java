/**
 * Copyright &copy; 2016-2018 luzhiyou All rights reserved.
 */
package com.education.classroom.core.modules.spadmin.resource.entity;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.validator.constraints.Length;

import com.education.classroom.core.modules.spadmin.category.entity.SpCategory;
import com.education.classroom.core.modules.spadmin.space.entity.SpSpace;
import com.education.classroom.core.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 资料库管理Entity
 * @author 杨立明
 * @version 2016-08-05
 */
public class SpResource extends DataEntity<SpResource> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 资料名称
	private String type;		// 1视频2音频3电子书
	private String categoryId;		// 分类
	private String spaceId;		// 第二空间Id
	private String spaceIdNot;		// 第二空间Id
	private String publishState;		// 发布状态 0未发布 1等待审核 2审核未通过 3审核通过
	private String domain;		// 0本站 1cc
	private String baseUrl;		// 资料路径
	private String thumbImg;		// 预览图
	private Date beginUpdateDate;		// 开始 修改时间
	private Date endUpdateDate;		// 结束 修改时间
	
	private String orderColumn;//排序依据列
	private String orderSort;//升序或降序
	private String scoreAvg;//平均分
	private String clickNum;//点击量
	private String downloadNum;//下载量
	private String collectNum;//收藏数
	private String readNum;//阅读量
	private String isCollect;//我是否收藏
	private String isDownload;//我是否下载
	private Double scoreLevel;//我的评分
	private String scoreContent;//我的评价
	private Date scoreTime;//我的评分时间
	private String userId;//我的ID
	private String operType;//1下载 2收藏
	
	private SpCategory spCategory;//分类
	private SpSpace spSpace;//所属空间
	
	private Integer pageNo;
	private Integer pageSize;
	private String categoryName;//所属分类名称
	private Integer scoreNum;
	
	private String sourceType;//项目所属平台：1本校 2平台
	private List<String> idList;//根据ids从平台获取资料
	
	//获取url里面的key
	public static String getKeyFromUrl(String url) {
		if(url==null){
			return "";
		}
		String key="";
		String regEx="/([a-zA-Z_0-9\\.]*)\\?";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(url);
		if(mat.find()){
			key=mat.group(1);
		}
		return key;
	}
	//获取视频播放script中的src
	public static String getScrFromScript(String scriptjs) {
		if(scriptjs==null){
			return "";
		}
		String key="";
		String regEx="script src=\"(.*)\" ";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(scriptjs);
		if(mat.find()){
			key=mat.group(1);
		}
		return key;
	}
	public SpResource() {
		super();
	}

	public SpResource(String id){
		super(id);
	}

	public Integer getScoreNum() {
		return scoreNum;
	}
	public void setScoreNum(Integer scoreNum) {
		this.scoreNum = scoreNum;
	}
	public List<String> getIdList() {
		return idList;
	}
	public void setIdList(List<String> idList) {
		this.idList = idList;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getSpaceIdNot() {
		return spaceIdNot;
	}
	public void setSpaceIdNot(String spaceIdNot) {
		this.spaceIdNot = spaceIdNot;
	}
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getOrderSort() {
		return orderSort;
	}

	public void setOrderSort(String orderSort) {
		this.orderSort = orderSort;
	}

	public String getScoreAvg() {
		return scoreAvg;
	}

	public void setScoreAvg(String scoreAvg) {
		this.scoreAvg = scoreAvg;
	}

	public String getClickNum() {
		return clickNum;
	}

	public void setClickNum(String clickNum) {
		this.clickNum = clickNum;
	}

	public String getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(String downloadNum) {
		this.downloadNum = downloadNum;
	}

	public String getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(String collectNum) {
		this.collectNum = collectNum;
	}

	public String getReadNum() {
		return readNum;
	}

	public void setReadNum(String readNum) {
		this.readNum = readNum;
	}

	public String getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}

	public String getIsDownload() {
		return isDownload;
	}

	public void setIsDownload(String isDownload) {
		this.isDownload = isDownload;
	}

	public Double getScoreLevel() {
		return scoreLevel;
	}

	public void setScoreLevel(Double scoreLevel) {
		this.scoreLevel = scoreLevel;
	}

	public String getScoreContent() {
		return scoreContent;
	}

	public void setScoreContent(String scoreContent) {
		this.scoreContent = scoreContent;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScoreTime() {
		return scoreTime;
	}

	public void setScoreTime(Date scoreTime) {
		this.scoreTime = scoreTime;
	}

	public SpSpace getSpSpace() {
		return spSpace;
	}

	public void setSpSpace(SpSpace spSpace) {
		this.spSpace = spSpace;
	}

	@JsonBackReference
	public SpCategory getSpCategory() {
		return spCategory;
	}

	public void setSpCategory(SpCategory spCategory) {
		this.spCategory = spCategory;
	}

	@Length(min=0, max=128, message="资料名称长度必须介于 0 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=1, message="1视频2音频3电子书长度必须介于 1 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=32, message="分类长度必须介于 0 和 32 之间")
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	@Length(min=0, max=32, message="第二空间Id长度必须介于 0 和 32 之间")
	public String getSpaceId() {
		return spaceId;
	}

	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	
	@Length(min=1, max=1, message="发布状态 0未发布 1等待审核 2审核未通过 3审核通过长度必须介于 1 和 1 之间")
	public String getPublishState() {
		return publishState;
	}

	public void setPublishState(String publishState) {
		this.publishState = publishState;
	}
	
	@Length(min=1, max=1, message="0本站 1cc长度必须介于 1 和 1 之间")
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@Length(min=1, max=256, message="资料路径长度必须介于 1 和 256 之间")
	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	@Length(min=0, max=256, message="预览图长度必须介于 0 和 256 之间")
	public String getThumbImg() {
		return thumbImg;
	}

	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}
	
	public Date getBeginUpdateDate() {
		return beginUpdateDate;
	}

	public void setBeginUpdateDate(Date beginUpdateDate) {
		this.beginUpdateDate = beginUpdateDate;
	}
	
	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}
	
	@Override
	public String toString() {
		return "SpResource ["  +"\r\n"
				+ "name=" + name  +"\r\n"
				+ ", type=" + type  +"\r\n"
				+ ", categoryId=" + categoryId  +"\r\n"
				+ ", spaceId=" + spaceId  +"\r\n"
				+ ", spaceIdNot=" + spaceIdNot  +"\r\n"
				+ ", publishState=" + publishState  +"\r\n"
				+ ", domain=" + domain  +"\r\n"
				+ ", baseUrl=" + baseUrl  +"\r\n"
				+ ", thumbImg=" + thumbImg  +"\r\n"
				+ ", beginUpdateDate=" + beginUpdateDate  +"\r\n"
				+ ", endUpdateDate=" + endUpdateDate  +"\r\n"
				
				+ ", orderColumn=" + orderColumn  +"\r\n"
				+ ", orderSort=" + orderSort  +"\r\n"
				+ ", scoreAvg=" + scoreAvg  +"\r\n"
				+ ", clickNum=" + clickNum  +"\r\n"
				+ ", downloadNum=" + downloadNum  +"\r\n"
				+ ", collectNum=" + collectNum  +"\r\n"
				+ ", readNum=" + readNum  +"\r\n"
				+ ", isCollect=" + isCollect  +"\r\n"
				+ ", isDownload=" + isDownload  +"\r\n"
				+ ", scoreLevel=" + scoreLevel  +"\r\n"
				+ ", scoreContent=" + scoreContent  +"\r\n"
				+ ", scoreTime=" + scoreTime  +"\r\n"
				+ ", userId=" + userId  +"\r\n"
				+ ", operType=" + operType  +"\r\n"
				
				+ ", spCategory=" + spCategory  +"\r\n"
				+ ", spSpace=" + spSpace  +"\r\n"
				
				+ ", pageNo=" + pageNo  +"\r\n"
				+ ", pageSize=" + pageSize  +"\r\n"
				+ ", categoryName=" + categoryName  +"\r\n"
				+ ", scoreNum=" + scoreNum  +"\r\n"
				
				+ ", sourceType=" + sourceType  +"\r\n"
				
				+ ", id=" + id  +"\r\n"
				+ ", createBy=" + createBy  +"\r\n"
				+ ", createDate=" + createDate  +"\r\n"
				+ ", updateBy=" + updateBy  +"\r\n"
				+ ", updateDate=" + updateDate  +"\r\n"
				+ ", delFlag=" + delFlag  +"\r\n"
				+ "]";
	}
	public static void main(String[] args) {
		SpResource t=new SpResource();
		
		System.out.println(t.getId());
	}	
}