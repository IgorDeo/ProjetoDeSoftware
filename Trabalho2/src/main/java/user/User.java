package user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="user")

public class User
{	
	private Long id;
	private String username;
	private double balance;

	private int version;

	public User()
	{
	}

	public User(String username, 
	               double balance)
	{	this.username = username;
		this.balance = balance;
			
	}

	// ********* Getters *********

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Long getId()
	{	return id;
	}
	@Column(name="username")
	public String getUsername()
	{	return username;
	}
	
	@Column(name="balance")
	public double getBalance()
	{	return balance;
	}
	
	@Version
	public int getVersao() {
		return version;
	}
	
	// ********* Setters *********

	public void setVersion(int version) {
		this.version = version;
	}

	@SuppressWarnings("unused")
	private void setId(Long id)
	{	this.id = id;
	}

	public void setUsername(String username)
	{	this.username = username;
	}
	
	public void setBalance(double balance)
	{	this.balance = balance;
	}
}

