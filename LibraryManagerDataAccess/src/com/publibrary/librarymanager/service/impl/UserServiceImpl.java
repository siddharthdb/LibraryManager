package com.publibrary.librarymanager.service.impl;

import java.util.List;

import com.publibrary.librarymanager.data.ListItemDao;
import com.publibrary.librarymanager.data.UserDao;
import com.publibrary.librarymanager.data.impl.ListItemDaoImpl;
import com.publibrary.librarymanager.data.impl.UserDaoImpl;
import com.publibrary.librarymanager.model.ListItem;
import com.publibrary.librarymanager.model.User;
import com.publibrary.librarymanager.service.UserService;

public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	private final ListItemDao listItemDao;
	
	public UserServiceImpl() {
		this.userDao = new UserDaoImpl();
		
		this.listItemDao = new ListItemDaoImpl();
	}

	@Override
	public User authenticateUser(String username) {
		User user = null;
		
		if (username != null && !"".equals(username.trim())) {
			user = userDao.getUser(username);
		}
		
		return user;
	}

	@Override
	public List<ListItem> getListItems(int userId) {
		return listItemDao.getListItemByUserId(userId);
	}

}
