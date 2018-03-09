package com.practice.rest.webservice.restfulwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	private static int userCounter =3;
	static
	{
		users.add(new User(1,"Laxman",new Date()));
		users.add(new User(2,"Shraddha",new Date()));
		users.add(new User(3,"Shravu",new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user){
		if(user.getId()==null){
			user.setId(++userCounter);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(Integer id){
		for(User user:users){
			if(user.getId()== id){
				return user;
			}
		}
		return null;
	}
	
	public User deleteUserById(Integer id){
		Iterator<User> iterator = users.listIterator();
		while(iterator.hasNext()){
			User user = iterator.next();
			if(user.getId() == id){
				iterator.remove();
				return user;
			}
		}
		return null;
	}

}
