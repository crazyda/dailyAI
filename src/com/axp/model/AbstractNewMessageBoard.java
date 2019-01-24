package com.axp.model;

import java.sql.Timestamp;

public abstract class AbstractNewMessageBoard implements java.io.Serializable{
	// Fields

		private Integer id;
		private Users users;
		private Timestamp sumbitTime;
		private String title;
		private String detail;
		private String name;
		private String phone;
		private String email;
		private Integer state;
		private Boolean isValid;

		// Constructors

		/** default constructor */
		public AbstractNewMessageBoard() {
		}

		/** full constructor */
		public AbstractNewMessageBoard(Users users, Timestamp sumbitTime, String title,
				String detail, String name, String phone, String email,
				Integer state, Boolean isValid) {
			this.users = users;
			this.sumbitTime = sumbitTime;
			this.title = title;
			this.detail = detail;
			this.name = name;
			this.phone = phone;
			this.email = email;
			this.state = state;
			this.isValid = isValid;
		}

		// Property accessors

		public Integer getId() {
			return this.id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Users getUsers() {
			return users;
		}

		public void setUsers(Users users) {
			this.users = users;
		}

		public Timestamp getSumbitTime() {
			return this.sumbitTime;
		}

		public void setSumbitTime(Timestamp sumbitTime) {
			this.sumbitTime = sumbitTime;
		}

		public String getTitle() {
			return this.title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDetail() {
			return this.detail;
		}

		public void setDetail(String detail) {
			this.detail = detail;
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhone() {
			return this.phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getEmail() {
			return this.email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Integer getState() {
			return this.state;
		}

		public void setState(Integer state) {
			this.state = state;
		}

		public Boolean getIsValid() {
			return this.isValid;
		}

		public void setIsValid(Boolean isValid) {
			this.isValid = isValid;
		}
}
