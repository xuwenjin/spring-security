package com.xwj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.xwj.entity.AuthUserInfo;
import com.xwj.repository.UserRepository;
import com.xwj.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate template; // 具名参数

	@PostConstruct
	public void init() {
		template = new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	@Override
	public AuthUserInfo findById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public AuthUserInfo save(AuthUserInfo info) {
		return userRepository.save(info);
	}

	@Override
	public AuthUserInfo findByUsername(String username) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT id, username, password, role ");
		sql.append(" FROM `auth_user_info` ");
		sql.append(" WHERE username = :username  ");

		Map<String, Object> param = new HashMap<>(1);
		param.put("username", username);
		List<AuthUserInfo> list = template.query(sql.toString(), param,
				new BeanPropertyRowMapper<>(AuthUserInfo.class));
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
