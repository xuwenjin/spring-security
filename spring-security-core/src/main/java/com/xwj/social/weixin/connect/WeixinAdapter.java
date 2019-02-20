package com.xwj.social.weixin.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.xwj.social.weixin.api.Weixin;
import com.xwj.social.weixin.api.WeixinUserInfo;

/**
 * 微 api适配器，将微信api的数据模型转为spring social的标准模型
 */
public class WeixinAdapter implements ApiAdapter<Weixin> {

	private String openId;

	public WeixinAdapter() {
		super();
	}

	public WeixinAdapter(String openId) {
		this.openId = openId;
	}

	/**
	 * 测试Weixin服务是否可用
	 */
	@Override
	public boolean test(Weixin api) {
		return true;
	}

	/**
	 * Connection和Api之间做适配
	 */
	@Override
	public void setConnectionValues(Weixin api, ConnectionValues values) {
		WeixinUserInfo userInfo = api.getUserInfo(openId);
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getHeadimgurl());
		values.setProviderUserId(userInfo.getOpenid());// 服务提供商返回的该user的openId
	}

	@Override
	public UserProfile fetchUserProfile(Weixin api) {
		return null;
	}

	@Override
	public void updateStatus(Weixin api, String message) {
	}

}
