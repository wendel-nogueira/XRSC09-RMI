import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

// Servidor
public class Server extends UnicastRemoteObject {
    private static final long serialVersionUID = 1L;

    protected Server() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        try {
            System.out.println("Iniciando servidor...\n");
            ImplCalc server = new ImplCalc();

            // Registrando o servidor
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost:1099/Calc", server);

            System.out.println("Servidor iniciado com sucesso!\n");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Pressione Enter para encerrar o servidor...");

        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.exit(0);
    }
}