import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EchoServer extends UnicastRemoteObject implements EchoService{
	// to be compile with: javac EchoServer.java
	// then use the RMI compiler on the created class file to compile the stub: rmic EchoServer
	
	public EchoServer() throws RemoteException{
		//nothing to initialise for this server
	}
	
	@Override
	public String echo(String s){
		//this println is not necessary, but helps verifying wheter
		//the server has received the call or not on the remote machine
		System.out.println("Replied to some client saying '" + s + "'");
		return s;
	}
}