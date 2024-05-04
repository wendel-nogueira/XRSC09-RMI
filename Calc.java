import java.rmi.Remote;
import java.rmi.RemoteException;

// Interface remota que define o método de cálculo de distância
public interface Calc extends Remote {
   public float[][] calculate(float table[][], int method) throws RemoteException;
}