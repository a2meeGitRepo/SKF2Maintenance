package com.SKF2Maintenance.repository;

import java.util.List;

import com.SKF2Maintenance.model.User;

public interface UserCustomRepo {
	
	List<User> getUserList(int page_no,int item_per_page);
	
	List<User> getlistUserByLimitAndSearch(String searchText, int pageNo, int perPage);

}
