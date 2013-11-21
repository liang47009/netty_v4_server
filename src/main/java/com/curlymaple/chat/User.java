package com.curlymaple.chat;

/**
 * {
 * 	"method.name":"yyuserInfo",
 * 	"method.param":
 * 		{
		 * "uid":20, // Number类型 用户的uid,唯一标识id。
		 * "role":255,
				role: Number类型 用户的马甲 对应的信息如下：
				无效角色 0 
				未知用户 灰马 20 
				游客(U) 白马 25 
				临时嘉宾(G) 浅绿色马甲 66 
				嘉宾(VIP) 绿马 88 
				会员(R) 蓝马 100 
				二级子频道管理员(CA2) 粉马 150 
				子频道管理员(CA) 红马 175 
				全频道管理员(MA) 黄马 200 
				频道总管理(VP) 橙马 230 
				频道所有者(OW) 紫马 255 
				客服 300 
				歪歪官方人员 黑马 1000 。

		 * "sex":1,		 
		 * "imId":3, //
		 * "level":2,//
		 * "name":"king",//昵称
		 * "points":10,
		 * "vip":true, // 
		 * "viplevel":10,
		 * "contribution":10,
		 * "sign":"吃葡萄不吐葡萄皮"
 * 		}
 * }
 * @author CurlyMaple
 *
 */
public class User {
	private int uid;// yy唯一id
	private int role;// 角色类型
	private int ch_top;//顶级频道
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getCh_top() {
		return ch_top;
	}
	public void setCh_top(int ch_top) {
		this.ch_top = ch_top;
	}
}
