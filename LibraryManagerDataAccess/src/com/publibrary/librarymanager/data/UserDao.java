package com.publibrary.librarymanager.data;

import com.publibrary.librarymanager.model.User;

public interface UserDao {
	User getUser(String username);
}
