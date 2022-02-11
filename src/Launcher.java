import java.util.*;

public class Launcher {


    public static void main(String[] args) {
        List<Command> commandes = List.of(new Quit(),new Fibo(),new Freq(),new Predict());
        System.out.println("Bienvenu");
        Scanner scanner= new Scanner(System.in);
        do {
            System.out.println("Tu peux entrer une commande");
            String rep = scanner.nextLine();
            boolean ok = false;
            for (var i : commandes){
                if(i.name().equals(rep)){
                    if(i.run(scanner))
                        return;
                    ok = true;
                }
            }
            if (!ok)
            {
                System.out.println("Unknown command");
            }
        }while(true);

    }
}
