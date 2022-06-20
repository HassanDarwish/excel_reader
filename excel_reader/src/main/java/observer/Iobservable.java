package observer;
 
import java.util.Map;
import java.util.TreeMap;

public interface Iobservable {

	public Map<String,Iobserver> observers=new TreeMap<String,Iobserver>();
	public void attach(Iobserver obv,String id) ;
	public void update (String sms , String id);
}
