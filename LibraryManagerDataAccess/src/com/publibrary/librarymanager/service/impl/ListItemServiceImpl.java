package com.publibrary.librarymanager.service.impl;

import com.publibrary.librarymanager.data.ListItemDao;
import com.publibrary.librarymanager.data.impl.ListItemDaoImpl;
import com.publibrary.librarymanager.model.ListItem;
import com.publibrary.librarymanager.service.ListItemService;

public class ListItemServiceImpl implements ListItemService {
	private final ListItemDao listItemDao;
	
	public ListItemServiceImpl() {
		this.listItemDao = new ListItemDaoImpl();
	}
	
	@Override
	public ListItem addListItem(int userId, String value) {
		ListItem listItem = null;
		
		if (value != null && !"".equals(value)) {
			listItem = new ListItem();
			listItem.setUserId(userId);
			listItem.setValue(value);
			listItem = listItemDao.saveListItem(listItem);
		}
		
		return listItem;
	}

	@Override
	public ListItem updateListItem(int userId, int listItemId, String newValue) {
		ListItem listItem = listItemDao.getListItemById(listItemId);
		
		if (listItem == null || userId != listItem.getUserId()) {
			listItem = null;
		} else {
			listItem.setValue(newValue);
			listItem = listItemDao.saveListItem(listItem);
		}
		
		return listItem;
	}

	@Override
	public void deleteListItem(int userId, int listItemId) {
		ListItem listItem = listItemDao.getListItemById(listItemId);
		
		if (listItem != null || userId != listItem.getUserId()) {
			listItemDao.deleteListItem(listItem.getId());
		}
	}

}
