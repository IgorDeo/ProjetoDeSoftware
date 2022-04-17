package user;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryImp
{	private static EntityManagerFactoryImp factory = null;
	private EntityManagerFactory emf = null;
			
	private EntityManagerFactoryImp()
	{	
		try
		{	
			emf = Persistence.createEntityManagerFactory("exercicio");
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			System.out.println(">>>>>>>>>> Error message: " + e.getMessage());
		}
	}

	public static EntityManager initializeSession()
	{	if (factory == null)
		{	factory = new EntityManagerFactoryImp();
		}	

		return factory.emf.createEntityManager();
	}

	public static void closeEntityManagerFactory()
	{	if (factory != null)
			if (factory.emf != null)
				factory.emf.close();
	}
}