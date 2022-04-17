package user;

public class ObsoleteObjectStateException extends RuntimeException
{	
	private final static long serialVersionUID = 1;
	
	public ObsoleteObjectStateException()
	{	super();
	}

	public ObsoleteObjectStateException(String msg)
	{	super(msg);
	}
}	