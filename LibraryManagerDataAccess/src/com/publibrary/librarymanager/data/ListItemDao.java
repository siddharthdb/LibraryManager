package com.publibrary.librarymanager.data;

import java.util.List;
import com.publibrary.librarymanager.model.ListItem;

public interface ListItemDao {
	
	ListItem getListItemById(int listItemId);
	
	List<ListItem> getListItemByUserId(int userId);
	
	ListItem saveListItem(ListItem listItem);
	
	void deleteListItem(int listItemId);
}
