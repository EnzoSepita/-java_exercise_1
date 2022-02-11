import java.util.Scanner;
public class Launcher {
    public static void main(String[] args) {
        System.out.println("Bienvenu");
        Scanner scanner= new Scanner(System.in);
        do {
            System.out.println("Ecrit quelque chose");
            String rep = scanner.nextLine();
            if (rep.equals("quit")) {
                break;
            }
            else{
                System.out.println("Unknown command");
            }
        }while(true);

    }
}
