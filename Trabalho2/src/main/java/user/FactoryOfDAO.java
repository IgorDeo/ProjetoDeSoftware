package user;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class FactoryOfDAO
{	
	private static ResourceBundle prop;

	static
	{	try
		{	prop = ResourceBundle.getBundle("dao");
		}
		catch(MissingResourceException e)
		{	System.out.println("File dao.properties not found.");
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static <T> T getDAO(Class<T> type)
	{			
		T dao = null;
		String className = null; 
	
		try
		{	className = prop.getString(type.getSimpleName());
			dao = (T) Class.forName(className).newInstance();
		} 
		catch (InstantiationException e)
		{	System.out.println("Impossible to instatiate an object of " + className);
			throw new RuntimeException(e);
		} 
		catch (IllegalAccessException e)
		{	System.out.println("Impossible to instatiate an object of " + className);
			throw new RuntimeException(e);
		} 
		catch (ClassNotFoundException e)
		{	System.out.println("Class " + className + " not found");
			throw new RuntimeException(e);
		}
		catch(MissingResourceException e)
		{	System.out.println("Key " + type + " not found in dao.properties");
			throw new RuntimeException(e);
		}
		
		return dao;
	}
}
