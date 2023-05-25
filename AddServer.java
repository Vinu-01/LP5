import java.net.*;
import java.rmi.*;
import java.rmi.registry.Registry;

public class AddServer {
    public static void main(String args[]) {
        try {
            AddServerImpl addServerImpl = new AddServerImpl();
            Naming.rebind("AddServerImpl", addServerImpl);
            System.out.println("Registry started successfully at 127.0.0.1");
        }
        catch(Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}