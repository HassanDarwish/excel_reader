package jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import observer.Iobservable;
import observer.Iobserver;
import observer.Logger;

public class ASingleton implements Iobservable{

	private static volatile ASingleton instance;
 	private static volatile LinkedList<Connection> connection_queue=new LinkedList<Connection>();
	private static Object   mutex = new Object();
	public static boolean isvailed =true;
	
	private ASingleton() { attach(new Logger(),"logger");}
 

	public boolean isIsvailed() {
		return isvailed;
	}


	public static void setIsvailed(boolean isvailed) {
		ASingleton.isvailed = isvailed;
	}


	public static ASingleton getInstance() {
		//ASingleton result = instance;
		if (instance == null || instance.isIsvailed()==false) {
			synchronized (mutex) {
				instance = instance;
				if (instance == null)
					instance = new ASingleton();
			 
				if(connection_queue.size()<=0) {
				int i=0;
				while( i<10) {
					Connection connection;
					connection = getJDBC_Connection();

							if(connection!=null)
							connection_queue.add(connection);
							if(connection==null) {
												setIsvailed(false);
												break;
							}
					
					i++;
			
					}
				
					instance.update("\n databased poll fullfilled by "+i, "logger");
			}
				
				}
		}
		return instance;
	}
	public synchronized Connection poll() {
		Connection	z_connection;
	
		if(connection_queue.size()>0) {
			instance.update("\n databased poll size is "+connection_queue.size(), "logger");
		 	z_connection=connection_queue.poll();
			instance.update("\n databased poll size is "+connection_queue.size(), "logger");
		 try {
			if( z_connection.isValid(1)) {
			return z_connection;
			}else {
				connection_queue.clear();
				int i=0;
				while( i<10) {
					Connection connection=getJDBC_Connection();
					if(connection!=null)
					connection_queue.add(connection);
					if(connection==null)
						break;
					i++;
				 
					}
				 	z_connection=connection_queue.poll();
				instance.update("\n databased poll size is "+connection_queue.size(), "logger");
				return  z_connection;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			instance.update("Exception is" + e.toString(), "logger");
			e.printStackTrace();
		}
		}else {
			instance.update("\n databased poll size is "+connection_queue.size(), "logger");
			connection_queue.clear(); //vip important
			int i=0;
			while( i<10) {
				Connection connection=getJDBC_Connection();
				if(connection!=null)
				connection_queue.add(connection);
				if(connection==null)
					break;
				i++;
			 
				}
			instance.update("\n databased poll size is "+connection_queue.size(), "logger");
			 	z_connection=connection_queue.poll();
			instance.update("\n databased poll size is "+connection_queue.size(), "logger");
	
		 
		}
		return z_connection;
	}
	
	public synchronized void  push(Connection connection) {
		connection_queue.push(connection); 
		instance.update("\n databased poll pushed ", "logger");
		instance.update("\n databased poll size is "+connection_queue.size(), "logger");
	}
	private static Connection getJDBC_Connection() {
		  Connection connectionDB_instance=null;
		  
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");  
					connectionDB_instance =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/excel_to_db","root","");
					instance.update("\n Connection created ", "logger");
					
				} catch (SQLException e) {
						// TODO Auto-generated catch block
					String name=e.getMessage();
					
					if (name.contains("Access Denied For User"))
						  JOptionPane.showMessageDialog(null, "Database user name or password is invaled", "Information ",JOptionPane.INFORMATION_MESSAGE);
					
					if (name.contains("Unknown Database"))
						  JOptionPane.showMessageDialog(null, "Unknown Database ", "Information ",JOptionPane.INFORMATION_MESSAGE);
					
					if (name.contains("Communications Link Failure"))
						  JOptionPane.showMessageDialog(null, "Wrong port or ip_address or server unavalibale ", "Information ",JOptionPane.INFORMATION_MESSAGE);
					
			 		
					instance.update("Exception is" + e.toString(), "logger");
			           
					 return null;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					  JOptionPane.showMessageDialog(null, "DataBase Driver Not found ", "Information ",JOptionPane.INFORMATION_MESSAGE);
				}
	    		
	        
	        
	            instance.update("\n connectionDB_instance  Create Correctlly" , "logger");
		return connectionDB_instance;
		  
	}
 // 109469   109309 			mohamed mohsen     109469
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	 
			 ASingleton instance= ASingleton.getInstance();
			 if(instance.isvailed==true)
			 instance.poll();
		 
	}

	@Override
	public void attach(Iobserver obv, String id) {
		// TODO Auto-generated method stub
		observers.put(id,obv);
	}

	@Override
	public void update(String sms, String id) {
		// TODO Auto-generated method stub
		((Iobserver)observers.get(id)).update(sms);
	}

}
