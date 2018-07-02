package com.publibrary.librarymanager.service;

import java.util.List;

import com.publibrary.librarymanager.model.ListItem;
import com.publibrary.librarymanager.model.User;

public interface UserService {
	
	User authenticateUser(String username);
	
	List<ListItem> getListItems(int userId);

}
