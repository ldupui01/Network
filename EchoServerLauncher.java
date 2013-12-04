import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.RMISecurityManager;
import java.net.MalformedURLException;
import java.rmi.Naming;


public class EchoServerLauncher{
	// to launch the server:  java -Djava.security.policy=server.policy EchoServerLauncher
	private void launch(){
		//1. If there is no security manager. start one
		if (System.getSecurityManager()== null){
			System.setSecurityManager(new RMISecurityManager());
		}
		try{
			//2. Create the registry if there is not one
			LocateRegistry.createRegistry(1099);
			//3. Create the server object
			EchoServer server = new EchoServer();
			//4. Register (bind) the server object on the registry.
			//   The registry may be on different machine
			String registryHost = "//localhost/";
			String serviceName = "echo";
			Naming.rebind(registryHost + serviceName, server);
		}catch (MalformedURLException ex){
			ex.printStackTrace();
		}catch (RemoteException ex){
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		try{
			EchoServerLauncher esl = new EchoServerLauncher();
			esl.launch();
		}
		catch(Exception e)
		{}
	}
}