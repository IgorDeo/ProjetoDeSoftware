package user;

import java.util.List;

import corejava.Console;

public class Principal {
	public static void main(String[] args) {
		String username;
		double balance;
		User oneUser;

		UserDAO userDAO = FactoryOfDAO.getDAO(UserDAO.class);

		boolean aux = true;
		while (aux) {
			System.out.println('\n' + "1. Create an user");
			System.out.println("2. Update an user");
			System.out.println("3. Delete an user");
			System.out.println("4. List all users");
			System.out.println("5. Exit");

			int opcao = Console.readInt('\n' +
					"Choose an option between 1 and 5:");

			switch (opcao) {
				case 1: {
					username = Console.readLine('\n' +
							"Username: ");
					balance = Console.readDouble(
							"Balance: ");
					oneUser = new User(username, balance);

					long userId = userDAO.create(oneUser);

					System.out.println('\n' + "User with " +
							userId + " id created with success!");

					break;
				}

				case 2: {
					int userId = Console.readInt('\n' +
							"Type the userId you want to update: ");

					try {
						oneUser = userDAO.findOne(userId);
					} catch (UserNotFoundException e) {
						System.out.println('\n' + e.getMessage());
						break;
					}

					System.out.println('\n' +
							"UserId = " + oneUser.getId() +
							"    Username = " + oneUser.getUsername() +
							"    Balance = " + oneUser.getBalance() +
							"    Version = " + oneUser.getVersao());

					System.out.println('\n' + "What do you want to update?");
					System.out.println('\n' + "1. Username");
					System.out.println("2. Balance");

					int updateOption = Console.readInt('\n' +
							"Choose between 1 and 2:");

					switch (updateOption) {
						case 1:
							String newUsername = Console.readLine("New username: ");

							oneUser.setUsername(newUsername);

							try {
								userDAO.update(oneUser);

								System.out.println('\n' +
										"Update done with success!");
							} catch (UserNotFoundException e) {
								System.out.println('\n' + e.getMessage());
							} catch (ObsoleteObjectStateException e) {
								System.out.println('\n' + "Operation not successful " +
										"the data you tried to save was altered by another user ");
							}

							break;

						case 2:
							double newBalance = Console.readDouble("New balance: ");

							oneUser.setBalance(newBalance);

							try {
								userDAO.update(oneUser);

								System.out.println('\n' +
										"Update done with success!");
							} catch (UserNotFoundException e) {
								System.out.println('\n' + e.getMessage());
							} catch (ObsoleteObjectStateException e) {
								System.out.println('\n' + "Operation not successful " +
										"the data you tried to save was altered by another user ");
							}

							break;

						default:
							System.out.println('\n' + "Invalid option");
					}

					break;
				}

				case 3: {
					int userId = Console.readInt('\n' +
							"Type the userId you want to delete: ");

					try {
						oneUser = userDAO.findOne(userId);
					} catch (UserNotFoundException e) {
						System.out.println('\n' + e.getMessage());
						break;
					}

					System.out.println('\n' +
							"userId = " + oneUser.getId() +
							"    Username = " + oneUser.getUsername() +
							"    Version = " + oneUser.getVersao());

					String resp = Console.readLine('\n' +
							"Are you sure tou want to delete this user? (y/n)");

					if (resp.equals("y")) {
						try {
							userDAO.delete(oneUser.getId());
							System.out.println('\n' +
									"User deleted with success!");
						} catch (UserNotFoundException e) {
							System.out.println('\n' + e.getMessage());
						}
					} else {
						System.out.println('\n' +
								"User not deleted.");
					}

					break;
				}

				case 4: {
					List<User> users = userDAO.findAll();

					for (User user : users) {
						System.out.println('\n' +
								"Id = " + user.getId() +
								"  Username = " + user.getUsername() +
								"  Balance = " + user.getBalance() +
								"  Version = " + user.getVersao());
					}

					break;
				}

				case 5: {
					aux = false;
					break;
				}

				default:
					System.out.println('\n' + "Invalid Option!");
			}
		}
	}
}
