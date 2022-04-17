package user;

public class UserNotFoundException extends Exception
{	
	private final static long serialVersionUID = 1;
	
	private int codigo;
	
	public UserNotFoundException(String msg)
	{	super(msg);
	}

	public UserNotFoundException(int codigo, String msg)
	{	super(msg);
		this.codigo = codigo;
	}
	
	public int getCodigoDeErro()
	{	return codigo;
	}
}	