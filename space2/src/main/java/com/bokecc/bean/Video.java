package com.bokecc.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能：视频包装类，用来对视频数据进行封装 
 * 版本：1.0 
 * 日期：2010-12-21 
 * 作者：chu 
 **/
public class Video {

	private int id;
	private String spark_videoid;
	private String title;//标题
	private String description;//描述
	private String tag;//标签
	private String status;//状态
	private String duration;//时长
	private String image;//预览图
	
	private String category;//分类
	private List<String> imageList=new ArrayList<String>();//所有截图
	private Date createTime;//上传时间
	private double size;//文件大小
	private int width;//播放器宽度
	private int height;//播放器高度
	private boolean isAuto;//是否自动播放
	private String playcode;//播放代码

	@Override
	public String toString() {
		return "Video [id=" + id + ", spark_videoid=" + spark_videoid
				+ ", title=" + title + ", description=" + description
				+ ", tag=" + tag + ", status=" + status 
				+ ", duration="+ duration + ", image=" + image 
				+ ", category=" + category + ", createTime=" + createTime
				+ ", size=" + size + ", isAuto=" + isAuto
				+ ", width=" + width + ", height=" + height
				+ ", playcode=" + playcode 
				+ "]\r\n";
	}

	public Video() {
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isAuto() {
		return isAuto;
	}
	public void setAuto(boolean isAuto) {
		this.isAuto = isAuto;
	}
	public String getPlaycode() {
		return playcode;
	}
	public void setPlaycode(String playcode) {
		this.playcode = playcode;
	}
	public List<String> getImageList() {
		return imageList;
	}
	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSpark_videoid() {
		return spark_videoid;
	}
	public void setSpark_videoid(String spark_videoid) {
		this.spark_videoid = spark_videoid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

}
