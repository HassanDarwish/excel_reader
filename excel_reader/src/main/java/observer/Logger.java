package observer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Logger implements Iobserver {

	@Override
	public synchronized  void update(String sms) {
		// TODO Auto-generated method stub
 
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		String strDate = dateFormat.format(date);  
		
		String fileContent = "\n "+ strDate+" :"+sms;
	    try {
      	 //InputStream input = getClass().getResourceAsStream("ListStopWords.txt");
	   
	 	 File file = new File("./logger_AccTrucks.txt");
	    // File file = new File("d:\\","logger_AccBordingReporting.txt");
	    file.createNewFile();
	 
	 
	    try(FileWriter fw = new FileWriter( file, true);
	    	    BufferedWriter bw = new BufferedWriter(fw);
	    	    PrintWriter out = new PrintWriter(bw))
	    	{
	    	    out.println (fileContent);
	    	    //more code
	    	 
	    	} catch (IOException e) {
	    	    //exception handling left as an exercise for the reader
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	 
	
	}

}
