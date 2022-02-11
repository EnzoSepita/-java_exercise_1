import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Predict implements Command{

    private static class Mot {
        private final String mot;
        private final Map<String, Integer> occurences = new HashMap<>();

        public Mot(String mot) {
            this.mot = mot;
        }

        public void putSuivant(String w) {
            this.occurences.put(w, this.occurences.getOrDefault(w, 0) + 1);
        }

        public String predict() {
            if (occurences.isEmpty())
                return null;

            var i = Collections.max(occurences.values());

            var list = this.occurences.keySet().stream().filter(k -> occurences.get(k).equals(i)).toList();

            return list.get(0);
        }
    }
    @Override
    public String name(){
        return "predict";
    }
    @Override
    public boolean run(Scanner scanner){
        System.out.println("Quel est le chemin du fichier ?");
        String chemin =  scanner.nextLine();
        Path file = Paths.get(chemin);
        String content;
        try {
            content = Files.readString(file);
        }
        catch (Exception e) {
            System.err.println("Unreadable file: " + e.getMessage());
            return false;
        }

        content = content.toLowerCase().replaceAll("[.!?\\-'\"\t\n]", " ").replaceAll(" {2}", " ");

        if (content.isBlank())
            return false;

        Map<String, Mot> table= new HashMap<>();

        var dernier = Arrays.stream(content.split(" ")).filter(s -> !s.isBlank()).reduce("", (prev, next) -> {
                    if (!prev.isBlank()) {
                        table.putIfAbsent(prev, new Mot(prev));
                        table.get(prev).putSuivant(next);
                    }
                    return next;
                });

        table.putIfAbsent(dernier, new Mot(dernier));

        System.out.println("Entrer un mot :");
        var premier = scanner.nextLine().toLowerCase();

        if (!table.containsKey(premier))
            System.err.println("Erreur, le mot est introuvable dans le texte");
        else{
            var phrase = new ArrayList<>(List.of(premier));
            while (phrase.size() < 20) {
                var suivant = table.get(phrase.get(phrase.size() - 1)).predict();
                if (suivant == null)
                    break;
                phrase.add(suivant);
            }
            System.out.println(String.join(" ",phrase));
        }
        return false;
    }
}
