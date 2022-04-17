package user;

import java.util.List;


public interface UserDAO
{	
	long create(User umProduto); 

	void update(User umProduto)
		throws UserNotFoundException; 
	
	void delete(long id) 
		throws UserNotFoundException; 
	
	User findOne(long numero) 
		throws UserNotFoundException; 
	
	List<User> findAll();
}