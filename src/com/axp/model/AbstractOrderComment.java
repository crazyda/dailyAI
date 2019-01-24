package com.axp.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * AbstractOrders entity provides the base persistence definition of the Orders
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrderComment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Double score;
	private Integer snapshotId;
	private String comment;
	private String imgurl;
	private Timestamp commentDate;
	private Boolean isValid;
	private ReGoodsorderItem reGoodsorderItem;

	public ReGoodsorderItem getReGoodsorderItem() {
		return reGoodsorderItem;
	}

	public void setReGoodsorderItem(ReGoodsorderItem reGoodsorderItem) {
		this.reGoodsorderItem = reGoodsorderItem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public Timestamp getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	
	
	
	public Integer getSnapshotId() {
		return snapshotId;
	}

	public void setSnapshotId(Integer snapshotId) {
		this.snapshotId = snapshotId;
	}

	public AbstractOrderComment() {

	}
	
	
	public List<String> getImgList(){
		if(this.imgurl!=null){
			List<JSONObject> list = JSONArray.parseArray(this.imgurl,JSONObject.class);
			List<String> imgList = new ArrayList<>();
			for(JSONObject jsonObject :list ){
				imgList.add(jsonObject.getString("image").replace("/nomal", "/160"));
			}
			return imgList;
		}
		return null;
		
	}
	
	public void setImgList(String imgJson){
		JSONArray array = JSONArray.parseArray(imgJson);
		JSONArray array2 = new JSONArray();
		if(imgJson!=null){
			for(int i=0;i<array.size();i++){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("image", array.get(i));
				array2.add(jsonObject);
			}
			setImgurl(array2.toJSONString());
		}
	}
	
	public String getUserComment(){
		List<JSONObject> list = JSONArray.parseArray(this.comment,JSONObject.class);
		if(list.size()>0){
			JSONObject jsonObject = list.get(0);
			return jsonObject.getString("comment");
		}
		return "";
	}
	
	public void setUserComment(String comment){
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("comment", comment);
		jsonArray.add(jsonObject);
		setComment(jsonArray.toJSONString());
	}
	
	public String getSellerReply(){
		List<JSONObject> list = JSONArray.parseArray(this.comment,JSONObject.class);
		if(list.size()>0){
			JSONObject jsonObject = list.get(0);
			return jsonObject.getString("reply")==null?"":jsonObject.getString("reply");
		}
		return "";
	}
	

	
	public class InnerOrderComment{
		private String commentGoal;
		private Timestamp date;
		private String commentContent;
		private List<String> commentImages;
		
			
		public InnerOrderComment() {
			
		}
		public InnerOrderComment(String commentGoal, Timestamp date,
				String commentContent, List<String> commentImages) {
			this.commentGoal = commentGoal;
			this.date = date;
			this.commentContent = commentContent;
			this.commentImages = commentImages;
		}
		public String getCommentGoal() {
			return commentGoal = score.toString();
		}
		public void setCommentGoal(String commentGoal) {
			this.commentGoal = commentGoal;
		}
		@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
		@JsonProperty("commentDate")
		public Timestamp getDate() {
			return date =  commentDate;
		}
		public void setDate(Timestamp date) {
			this.date = date;
		}
		public String getCommentContent() {
			return commentContent = getUserComment();
		}
		public void setCommentContent(String commentContent) {
			this.commentContent = commentContent;
		}
		public List<String> getCommentImages() {
			return commentImages;
		}
		public void setCommentImages(String basePath) {
			commentImages = new ArrayList<>();
			for(String uri : getImgList()){
				commentImages.add(basePath+uri);
			}
		}
		
	}
}