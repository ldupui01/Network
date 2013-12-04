import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar; 
import java.util.GregorianCalendar;
import java.util.Date;

public class EchoServer extends UnicastRemoteObject implements EchoService{
	// to be compile with: javac EchoServer.java
	// then use the RMI compiler on the created class file to compile the stub: rmic EchoServer
	
	public EchoServer() throws RemoteException{
		//nothing to initialise for this server
	}
	
	@Override
	public String echo(String s){
		//this println is not necessary, but helps verifying whether
		//the server has received the call or not on the remote machine
		System.out.println("Replied to some client saying '" + s + "'");
		DateTime dt = new DateTime();
		if (s.equals("time")){
			s = dt.getTime();	
		}else{
			s = s + " was received and sent back";
		}
		return s;
	}
}

class DateTime{
	private String date;
	private String time;
	
	public DateTime(){
		this.date = this.setDate();
		this.time= this.setTime();
	}
	
	public String setDate(){
		Calendar c = new GregorianCalendar();
		String result = c.toString();
		return result;
	}
	
	public String setTime(){
		Date d = new Date();
		String result = d.toString();
		return result;
	}
	
	public String getTime(){
		return this.time;
	}
	
	public String getDate(){
		return this.date;
	}

}