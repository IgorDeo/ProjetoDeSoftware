package user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;

public class JPAUserDAO implements UserDAO {
	public long create(User oneUser) {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = EntityManagerFactoryImp.initializeSession();
			tx = em.getTransaction();
			tx.begin();

			em.persist(oneUser);

			tx.commit();
			return oneUser.getId();
		} catch (RuntimeException e) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (RuntimeException he) {
				}
			}
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(User oneUser) throws UserNotFoundException {
		EntityManager em = null;
		EntityTransaction tx = null;
		User user = null;
		try {
			em = EntityManagerFactoryImp.initializeSession();
			tx = em.getTransaction();
			tx.begin();

			user = em.find(User.class, oneUser.getId(), LockModeType.PESSIMISTIC_WRITE);

			if (user == null) {
				tx.rollback();
				throw new UserNotFoundException("User not found");
			}
			em.merge(oneUser);
			tx.commit();
		} catch (OptimisticLockException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ObsoleteObjectStateException();
		} catch (RuntimeException e) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (RuntimeException he) {
				}
			}
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(long userId) throws UserNotFoundException {
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = EntityManagerFactoryImp.initializeSession();
			tx = em.getTransaction();
			tx.begin();

			User user = em.find(User.class, userId, LockModeType.PESSIMISTIC_WRITE);

			if (user == null) {
				tx.rollback();
				throw new UserNotFoundException("User not found");
			}

			em.remove(user);

			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				try {
					tx.rollback();
				} catch (RuntimeException he) {
				}
			}
			throw e;
		} finally {
			em.close();
		}
	}

	public User findOne(long userId) throws UserNotFoundException {
		EntityManager em = null;

		try {
			em = EntityManagerFactoryImp.initializeSession();

			User oneUser = em.find(User.class, userId);

			if (oneUser == null) {
				throw new UserNotFoundException("User not found");
			}
			return oneUser;
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		EntityManager em = null;

		try {
			em = EntityManagerFactoryImp.initializeSession();

			List<User> users = em
					.createQuery("select u from User u order by u.id")
					.getResultList();

			return users;
		} finally {
			em.close();
		}
	}
}