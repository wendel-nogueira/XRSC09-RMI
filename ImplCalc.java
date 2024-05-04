import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Implementação da interface Calc
public class ImplCalc extends UnicastRemoteObject implements Calc {
    private static final long serialVersionUID = 1L;

    protected ImplCalc() throws RemoteException {
        super();
    }

    // Método de cálculo de distância euclidiana
    public float euclidean(float[] a, float[] b) throws RemoteException {
        float sum = 0;

        for (int index = 0; index < a.length; index++) {
            sum += Math.pow(a[index] - b[index], 2);
        }

        return (float) Math.sqrt(sum);
    }

    // Método de cálculo de distância city block
    public float cityBlock(float[] a, float[] b) throws RemoteException {
        float sum = 0;

        for (int index = 0; index < a.length; index++) {
            sum += Math.abs(a[index] - b[index]);
        }

        return sum;
    }

    // Método de cálculo de distância
    public float[][] calculate(float[][] table, int method) throws RemoteException {
        float[][] result = new float[3][3];

        System.out.println("Calculando distancia entre os objetos...");

        for (int index = 0; index < 3; index++) {
            for (int index2 = 0; index2 < 3; index2++) {
                if (index == index2) {
                    result[index][index2] = 0;
                } else {
                    result[index][index2] = method == 0 ? euclidean(table[index], table[index2])
                            : cityBlock(table[index], table[index2]);
                }
            }
        }

        System.out.println("Distancia calculada com sucesso!");
        System.out.println("Retornando resultado...\n");
        System.out.println();

        return result;
    }
}