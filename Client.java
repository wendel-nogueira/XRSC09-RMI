import java.util.Scanner;
import java.rmi.Naming;

// Cliente
public class Client {
    public static void main(String[] args) {
        float table[][] = new float[3][4];
        int method = -1;

        System.out.println("Iniciando cliente...\n");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira as informacoes dos objetos (3 objetos, 4 caracteristicas cada):");

        for (int index = 0; index < 3; index++) {
            try {
                System.out.println("Objeto " + (index == 0 ? "A" : index == 1 ? "B" : "C") + ":");
                System.out.println("Modelo de input: 5.1 3.5 1.4 0.2");

                String[] input = scanner.nextLine().split(" ");

                for (int index2 = 0; index2 < 4; index2++) {
                    table[index][index2] = Float.parseFloat(input[index2]);
                }

                System.out.println();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("\nInput invalido, tente novamente.\n");
                index--;
            }
        }

        System.out.println("Escolha o método de calculo de distancia:");

        while (method != 0 && method != 1) {
            System.out.println("0 - Euclidiana\n1 - City Block");

            try {
                method = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("\nInput invalido, tente novamente.\n");
            }
        }

        scanner.close();

        try {
            System.out.println("Calculando a distancia entre os objetos...");
            Calc calc = (Calc) Naming.lookup("rmi://localhost:1099/Calc");
            float[][] distance = calc.calculate(table, method);

            System.out.println("Distancia entre os objetos:\n");

            for (int index = 0; index < 3; index++) {
                System.out.println("Objeto " + (index == 0 ? "A" : index == 1 ? "B" : "C") + ":\n");

                for (int index2 = 0; index2 < 3; index2++) {
                    System.out.println("Distancia para o objeto " + (index2 == 0 ? "A" : index2 == 1 ? "B" : "C") + ": "
                            + distance[index][index2]);
                }

                System.out.println();
            }

            System.out.println("Fim do calculo\n");
            System.out.println("Par de objetos mais proximos:");

            float min = distance[0][1];

            for (int index = 0; index < 3; index++) {
                for (int index2 = 0; index2 < 3; index2++) {
                    if (index != index2 && distance[index][index2] < min) {
                        min = distance[index][index2];
                        System.out.println("Objeto " + (index == 0 ? "A" : index == 1 ? "B" : "C") + " e "
                                + (index2 == 0 ? "A" : index2 == 1 ? "B" : "C"));
                    }
                }
            }

            System.out.println("Distancia: " + min);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}