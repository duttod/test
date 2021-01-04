package com.karrus.demo.server.database;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Class used to query the database.
 */
public class DatabaseDriver {
	
	public static DateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static Logger logger = Logger.getLogger(DatabaseDriver.class);

	/**
	 * Timestamp format used by the database.
	 * @return Timestamp format used by the database.
	 */
	public static DateFormat getTimestampFormat() {
		return timestampFormat;
	}
	
	/**
	 * Generic SQL query.
	 * @param query SQL query to be executed.
	 * @throws Exception Exception thrown if something goes wrong while executing the query.
	 */
	public static void executeQuery(String query) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
		//	logger.info(query);
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.executeUpdate();
			transaction.commit();
		} catch (ConstraintViolationException e) {
			transaction.rollback();
			logger.error(e.getMessage()+"\n"+e.getCause());
			throw new Exception(e.getMessage());
		} catch (HibernateException e) {
			transaction.rollback();
			logger.error(e.getMessage()+"\n"+e.getCause());
			throw new Exception(e.getMessage()+"\n"+e.getCause());
		}
		finally {
			session.close();
		}
	}
	
	public static String countQuery(String query) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String count = "0" ;
		try {
			transaction = session.beginTransaction();
		//	logger.info(query);
			SQLQuery sqlQuery = session.createSQLQuery(query);
			List<Object> rows = sqlQuery.list();
			count = rows.get(0).toString();
//			for(Object[]  row :rows) {
//				count = row[0].toString(); 
//			}
			
			transaction.commit();
		} catch (ConstraintViolationException e) {
			transaction.rollback();
			logger.error(e.getMessage()+"\n"+e.getCause());
			throw new Exception(e.getMessage());
		} catch (HibernateException e) {
			transaction.rollback();
			logger.error(e.getMessage()+"\n"+e.getCause());
			throw new Exception(e.getMessage()+"\n"+e.getCause());
		}
		finally {
			session.close();
		}
		return count;
	}
	
	/**
	 * Generic object saver.
	 * @param object Object to be saved in the database.
	 * @throws Exception Exception thrown if something goes wrong while saving the object in the database.
	 */
	static public void save(Object object) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			// logger.info("Save to database: " + object.toString());
			session.save(object);
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			throw hibernateException;
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Generic object deleter.
	 * @param object Object to be deleted in the database.
	 * @throws Exception Exception thrown if something goes wrong while deleting the object in the database.
	 */
	static public void delete(Object object) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
		//	logger.info("Delete from database: " + object.toString());
			session.delete(object);
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			throw hibernateException;
		}
		finally {
			session.close();
		}
	}

	/**
	 * Generic object getter.
	 * @param query SQL query to be executed.
	 * @param ojectClass Class of the objects that will be returned.
	 * @return List of object constructed from the database rows.
	 * @throws Exception Exception thrown if something goes wrong while executing the SQL query. 
	 */
	static public List<?> get(String query, Class<?> ojectClass) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<?> result = null;
		try {
			transaction = session.beginTransaction();
			// logger.info(query);
			result = session.createSQLQuery(query).addEntity(ojectClass).list();
			transaction.commit();
		} catch (HibernateException hibernateException) {
			transaction.rollback();
			throw hibernateException;
		}
		finally {
			session.close();
		}
		return result;
	}
	
	/**
	 * Generic object updater.
	 * @param object Object to be updated.
	 * @throws Exception Exception thrown if something goes wrong while executing the update. 
	 */
	static public void update(Object object) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			// logger.info("update from database: " + object.toString());
			session.update(object);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw new Exception(e.getMessage()+"\n"+e.getCause());
		}
		finally {
			session.close();
		}
	}
	
	/**
	 * Table clearer.
	 * @param table Table to be cleared.
	 * @throws Exception Exception thrown if something goes wrong while clearing the table. 
	 */
	static public void clearTable(String table) throws Exception {
		String query = "DELETE FROM \""+table+"\"";
		// logger.info(query);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.executeUpdate();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw new Exception(e.getMessage()+"\n"+e.getCause());
		}
		finally {
			session.close();
		}
	}
	
}
