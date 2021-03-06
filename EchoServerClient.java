import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;
import java.rmi.registry.*;
import java.rmi.Naming;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public class EchoServerClient {
	// to be call as follow : java -Djava.security.policy=SecurityPolicy.txt EchoServerClient <sometext>
	// need to set the classpath:  set CLASSPATH=%CLASSPATH%;H:\JAVA\Java\Network -- the folder where the server echo is
	public void launch(String myString) {
			// 1. if no security manager running, launch one
			if (System.getSecurityManager() == null) {
					System.setSecurityManager(new RMISecurityManager());
			}
			try {
					// 2. find a reference to remote server object
					Remote myService = Naming.lookup("//127.0.0.1:1099/echo");
					EchoService echoService = (EchoService) myService; // in order to use methods, need to downcast them to the right type
					//3. Now call the remote method
					String receivedEcho = echoService.echo(myString);
					System.out.println("Message Received was: " + receivedEcho);
			} catch (MalformedURLException ex) {
					ex.printStackTrace();
			} catch (RemoteException ex) {
					ex.printStackTrace();
			} catch (NotBoundException ex) {
					ex.printStackTrace();
			}
	}
	
	public static void main(String[] args) {
			String myString="";
			if (args.length>0) {
					for (int i=0;i<args.length;i++) {
							myString = myString + args[i];
					}
			} else {
					myString = "Dummy Text";
			}
			
			EchoServerClient script = new EchoServerClient();
			script.launch(myString);
	}
}