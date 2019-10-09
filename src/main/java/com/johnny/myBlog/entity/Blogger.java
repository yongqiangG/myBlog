package com.johnny.myBlog.entity;
/**
 * 博主
 * @author johnny
 *
 */
public class Blogger {
	/**主键*/
	private Integer id;
	/**用户名*/
	private String userName;
	/**密码主键*/
	private String password;
	/**个人信息*/
	private String profile;
	/**昵称*/
	private String nickName;
	/**头像地址*/
	private String sign;
	/**主键*/
	private String imageName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	@Override
	public String toString() {
		return "Blogger [id=" + id + ", userName=" + userName + ", password=" + password + ", profile=" + profile
				+ ", nickName=" + nickName + ", sign=" + sign + ", imageName=" + imageName + "]";
	}
	

}
