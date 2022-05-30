import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;
public class ResultSetTableModel extends AbstractTableModel {
	 private Connection c;
	   private Statement statement;
	   private ResultSet resultSet;
	   private ResultSetMetaData metaData;
	   private int numberOfRows;

	  
	   private boolean connectedToDatabase = false;
	   
	public ResultSetTableModel( String query ) 
		      throws SQLException, ClassNotFoundException
		   {         
		      
		     c = global.connection;
		      

		      
		      statement = c.createStatement( 
		         ResultSet.TYPE_SCROLL_INSENSITIVE,
		         ResultSet.CONCUR_READ_ONLY );

		      
		      connectedToDatabase = true;
		      
		      StringBuilder firstLetter = new StringBuilder();
		      firstLetter.append(query.charAt(0));
		      if(firstLetter.toString().equalsIgnoreCase("s"))
		      {
		    	  
		    	  setQuery( query );
		      }
		      else
		    	  setUpdate(query);
				
				
		   } 
		   public Class getColumnClass( int column ) throws IllegalStateException
		   {
		      
		      if ( !connectedToDatabase ) 
		         throw new IllegalStateException( "Not Connected to Database" );

		     
		      try 
		      {
		         String className = metaData.getColumnClassName( column + 1 );
		         
		         
		         return Class.forName( className );
		      } 
		      catch ( Exception exception ) 
		      {
		         exception.printStackTrace();
		      } 
		      
		      return Object.class; 
		   }
		   public int getColumnCount() throws IllegalStateException
		   {   
		      
		      if ( !connectedToDatabase ) 
		         throw new IllegalStateException( "Not Connected to Database" );

		      
		      try 
		      {
		         return metaData.getColumnCount(); 
		      } 
		      catch ( SQLException sqlException ) 
		      {
		         sqlException.printStackTrace();
		      } 
		      
		      return 0; 
		   } 
		   public String getColumnName( int column ) throws IllegalStateException
		   {    
		      
		      if ( !connectedToDatabase ) 
		         throw new IllegalStateException( "Not Connected to Database" );

		      
		      try 
		      {
		         return metaData.getColumnName( column + 1 );  
		      } 
		      catch ( SQLException sqlException ) 
		      {
		         sqlException.printStackTrace();
		      } 
		      
		      return ""; 
		   } 

		   
		   public int getRowCount() throws IllegalStateException
		   {      
		      
		      if ( !connectedToDatabase ) 
		         throw new IllegalStateException( "Not Connected to Database" );
		 
		      return numberOfRows;
		   } 

		   
		   public Object getValueAt( int row, int column ) 
		      throws IllegalStateException
		   {
		      
		      if ( !connectedToDatabase ) 
		         throw new IllegalStateException( "Not Connected to Database" );

		      
		      try 
		      {
				   resultSet.next();  
		         resultSet.absolute( row + 1 );
		         return resultSet.getObject( column + 1 );
		      } 
		      catch ( SQLException sqlException ) 
		      {
		         sqlException.printStackTrace();
		      } 
		      
		      return ""; 
		   } 
		   
		  
		   public void setQuery( String query ) 
		      throws SQLException, IllegalStateException 
		   {
		      
		      if ( !connectedToDatabase ) 
		         throw new IllegalStateException( "Not Connected to Database" );

		      
		      resultSet = statement.executeQuery( query );

		      
		      metaData = resultSet.getMetaData();

		     
		      resultSet.last();                  
		      numberOfRows = resultSet.getRow();        
		      
		      
		      fireTableStructureChanged();
		   } 


		
		   public void setUpdate( String query ) 
		      throws SQLException, IllegalStateException 
		   {
			    int res;
		     
		      if ( !connectedToDatabase ) 
		         throw new IllegalStateException( "Not Connected to Database" );

		      
		      res = statement.executeUpdate( query );
	   
		      
		      fireTableStructureChanged();
		   } 




		                 
		   public void disconnectFromDatabase()            
		   {              
		      if ( !connectedToDatabase )                  
		         return;

		                  
		      try                                          
		      {                                            
		         statement.close();                        
		         c.close();                       
		      }                                
		      catch ( SQLException sqlException )          
		      {                                            
		         sqlException.printStackTrace();           
		      }                              
		      finally  
		      {                                            
		         connectedToDatabase = false;              
		      }                              
		   }          
		}  





