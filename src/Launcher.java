import java.util.Scanner;
public class Launcher {
    public static int fibonacci(int n){
        int u1 = 0;
        int u2 = 1;
        int som = 0;
        for (int i = 0; i < n; i++)
        {
            som = u1 + u2;
            u1 = u2;
            u2 = som;
        }
        return som;
    }
    public static void main(String[] args) {
        System.out.println("Bienvenu");
        Scanner scanner= new Scanner(System.in);
        do {
            System.out.println("Ecrit quelque chose");
            String rep = scanner.nextLine();
            if (rep.equals("quit")) {
                break;
            }
            else if (rep.equals("fibo")){
                System.out.println("Entrer un entier n");
                int n = scanner.nextInt();
                scanner.nextLine();
                System.out.println(fibonacci(n));
            }
            else
            {
                System.out.println("Unknown command");
            }
        }while(true);

    }
}
