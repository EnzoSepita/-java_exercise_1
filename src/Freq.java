import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Freq implements Command{
    @Override
    public String name(){
        return "freq";
    }
    @Override
    public boolean run(Scanner scanner){
        System.out.println("Quel est le chemin du fichier ?");
        String chemin = scanner.nextLine();
        Path file = Paths.get(chemin);
        String content;
        try {
            content = Files.readString(file);
        }
        catch (Exception e) {
            System.err.println("Unreadable file: " + e.getMessage());
            return false;
        }
        List<String> mots = word_freq(content);
        System.out.println(String.join(" ",mots));
        return false;

    }
    public static List<String> word_freq(String str){
        str = str.toLowerCase().replaceAll("[.!?\\-'\"\n]", " ");

        Map<String, Integer> wofreq = new HashMap<>();

        for (var word : str.split(" ")){
            if (word.isBlank()) continue;

            wofreq.putIfAbsent(word,0);
            wofreq.put(word, wofreq.get(word) + 1);
        }

        List<String> mots = new ArrayList<>();

        while (mots.size() < 3 && wofreq.keySet().size() > 0){
            int max = Collections.max(wofreq.values());
            var keys = wofreq.keySet().stream().filter(k -> wofreq.get(k) == max).collect(Collectors.toList());
            var last = keys.get(keys.size() - 1);
            mots.add(last);
            wofreq.remove(last);
        }
        return mots;

    }
}
