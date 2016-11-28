package jargon.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class SQLManager {

	protected Connection dbConnection;
	protected String host;
	protected String database;
	protected String user;
	protected String password;
	
	/**
	 * Initiates SQLManager using config.properties resource file for login information.
	 * @param bUseProperties
	 */
	public SQLManager(boolean bUseProperties) /*throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException*/ {
		if (bUseProperties) {
			//Get server info from properties file
			try {
				Properties configFile = new Properties();
				configFile.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
				
				this.init(
					configFile.getProperty("server"),
					configFile.getProperty("database"),
					configFile.getProperty("user"),
					configFile.getProperty("password")
				);
			} catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Initiates SQLManager using the parameters provided.
	 * @param sHost
	 * @param sDatabase
	 * @param sUser
	 * @param sPassword
	 */
	public SQLManager(String sHost, String sDatabase, String sUser, String sPassword) /*throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException*/ {
		try {
			this.init(sHost, sDatabase, sUser, sPassword);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Initiates SQLManager with provided parameters, sets up connection.
	 * Specifies UTF-8 character encoding for correct communication, and dontTrackOpenResources to
	 * ensure objects are disposed of after queries are executed.
	 * @param sHost
	 * @param sDatabase
	 * @param sUser
	 * @param sPassword
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void init(String sHost, String sDatabase, String sUser, String sPassword) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.host = sHost;
		this.database = sDatabase;
		this.user = sUser;
		this.password = sPassword;
		
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		
		dbConnection = DriverManager.getConnection(
			"jdbc:mysql://".concat(sHost.concat("/".concat(sDatabase.concat("?useEncoding=true&characterEncoding=UTF-8&dontTrackOpenResources=true")))),
			sUser,
			sPassword
		);
	}
	
	/**
	 * Reinitiates connection after timeout.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void reInit() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this.close();
		dbConnection = null;
		
		this.init(
			this.host,
			this.database,
			this.user,
			this.password
		);
	}
	
	/**
	 * Creates prepared statement for querying. Safely inserts values into query.
	 * @param sQuery	Query to be executed, using question marks for variable values. E.g.:
	 * 					SELECT * FROM ? WHERE id = ?
	 * @param oValues	Array containing variables to be inserted into the query. Length has to match
	 * 					number of question marks in sQuery. E.g.: {"testTable","testId"}
	 * @return			PreparedStatement.
	 * @throws SQLException
	 */
	private PreparedStatement prepare(String sQuery, Object[] oValues) throws SQLException {
		PreparedStatement preparedStatement = dbConnection.prepareStatement(
			sQuery
		);
		
		for(int i=0; i<oValues.length; i++) {
			preparedStatement.setObject(i+1, oValues[i]);
		}
		
		System.out.println(preparedStatement.toString());
		
		preparedStatement.closeOnCompletion();
		return preparedStatement;
	}
	
	/**
	 * Executes prepared statement and returns query results.
	 * @param sQuery	Query to be executed, using question marks for variable values. E.g.:
	 * 					SELECT * FROM ? WHERE id = ?
	 * @param oValues	Array containing variables to be inserted into the query. Length has to match
	 * 					number of question marks in sQuery. E.g.: {"testTable","testId"}
	 * @return			ResultSet containing matching rows.
	 */
	public ResultSet queryPrepared(String sQuery, Object[] oValues) /*throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException*/ {
		try {
			if (! dbConnection.isValid(0))
				this.reInit();
			
			return this.prepare(sQuery, oValues).executeQuery();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public HashMap<String, Object> queryPrepared(String sQuery, Object[] oValues, boolean singleRowToHashMap) {
		ResultSet resultSet = this.queryPrepared(
			(sQuery.endsWith("LIMIT 1") ? sQuery : sQuery.trim().concat(" LIMIT 1")),
			oValues
		);
		
		HashMap<String, Object> objects = new HashMap<String, Object>();
		
		try {
			while (resultSet.next()) {
				for (int i=1; i<=resultSet.getMetaData().getColumnCount(); i++) {
					objects.put(
						resultSet.getMetaData().getColumnLabel(i),
						resultSet.getObject(resultSet.getMetaData().getColumnLabel(i))
					);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return objects;
	}
	
	public Object[] queryPreparedAsPrimitives(String sQuery, Object[] oValues, String sPrimitive) {
		ResultSet resultSet = this.queryPrepared(sQuery, oValues);
		ArrayList<Object> objects = new ArrayList<Object>();
		
		try {
			while (resultSet.next()) {
				for (int i=1; i<=resultSet.getMetaData().getColumnCount(); i++) {
					objects.add(resultSet.getClass().getMethod(
						"get".concat(sPrimitive.substring(sPrimitive.lastIndexOf(".")+1)),
						String.class
					).invoke(resultSet, resultSet.getMetaData().getColumnLabel(i)));
				}
			}
		
			return objects.toArray(
				(Object[]) java.lang.reflect.Array.newInstance(
					Class.forName(sPrimitive),
					(objects.size() > 0 ? objects.size() - 1 : 0)
				)
			);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** Executes prepared statement, maps the results to a POJO, and returns them as an array.
	 * @param sQuery	Query to be executed, using question marks for variable values. E.g.:
	 * 					SELECT * FROM ? WHERE id = ?
	 * @param oValues	Array containing variables to be inserted into the query. Length has to match
	 * 					number of question marks in sQuery. E.g.: {"testTable",123}
	 * @param sObject	@param sObject	POJO to which matching rows should be mapped (e.g. "strip.model.Medication")
	 * @return			Array containing elements of class sObject populated by matching rows.
	 */
	public Object[] queryPrepared(String sQuery, Object[] oValues, String sObject) {
		ResultSet resultSet = this.queryPrepared(sQuery, oValues);
		ArrayList<Object> objects = new ArrayList<Object>();
		
		try {
			while (resultSet.next()) {
				Object object = Class.forName(sObject).newInstance();
				
				for (int i=1; i<=resultSet.getMetaData().getColumnCount(); i++) {
					if (resultSet.getObject(resultSet.getMetaData().getColumnLabel(i)) != null) {
						try {
							//map query result to fields
							switch(resultSet.getMetaData().getColumnType(i)) {
								case java.sql.Types.DECIMAL:
									object.getClass().getField(
										resultSet.getMetaData().getColumnLabel(i)
									).set(object, new BigDecimal(
										resultSet.getString(resultSet.getMetaData().getColumnLabel(i))
									));
								default:
									object.getClass().getField(
										resultSet.getMetaData().getColumnLabel(i)
									).set(object, resultSet.getObject(resultSet.getMetaData().getColumnLabel(i)));
							}
						} catch(NoSuchFieldException e) {
							//map query result via methods. Only String supported at the moment.
							try {
								object.getClass().getMethod(
									"set".concat(resultSet.getMetaData().getColumnLabel(i).substring(0,1).toUpperCase()).concat(resultSet.getMetaData().getColumnLabel(i).substring(1)),
									String.class
								).invoke(object, resultSet.getObject(resultSet.getMetaData().getColumnLabel(i)));
							} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException f) {
								// TODO Auto-generated catch block
								f.printStackTrace();
							}
						}
					}
				}
				
				objects.add(object);
			}
			
			return objects.toArray(
				(Object[]) java.lang.reflect.Array.newInstance(
					Class.forName(sObject),
					(objects.size() > 0 ? objects.size() - 1 : 0)
				)
			);
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Executes prepared update statement.
	 * @param sQuery	Query to be executed, using question marks for variable values. E.g.:
	 * 					SELECT * FROM ? WHERE id = ?
	 * @param oValues	Array containing variables to be inserted into the query. Length has to match
	 * 					number of question marks in sQuery. E.g.: {"testTable","testId"}
	 */
	public synchronized void updatePrepared(String sQuery, Object[] oValues) /*throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException*/ {
		try {
			if (! dbConnection.isValid(0))
				this.reInit();
			
			this.prepare(sQuery, oValues).executeUpdate();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Executes statement and returns query results. Does not use prepared statements and thus does not
	 * check correctness and safety of queries. Use is discouraged, use queryPrepared instead.
	 * @param sQuery	Query to be executed.
	 * @return			ResultSet containing matching rows.
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public ResultSet query(String sQuery) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (! dbConnection.isValid(0))
			this.reInit();
		
		return dbConnection.createStatement().executeQuery(sQuery);
	}
	
	/**
	 * Closes connection.
	 */
	public void close() {
		try {
			dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}