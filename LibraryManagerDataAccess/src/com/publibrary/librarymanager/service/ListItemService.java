package com.publibrary.librarymanager.service;

import com.publibrary.librarymanager.model.ListItem;

public interface ListItemService {
	ListItem addListItem (int userId, String value);
	
	ListItem updateListItem(int userId, int listItemId, String newValue);
	
	void deleteListItem(int userId, int listItemId);
}
