package co.edu.poli.WordShake.view;
import java.util.List;
import static co.edu.poli.WordShake.model.Wordshake.generarLetras;


public class Main {
    public static void main(String[] args) {

        List<Character> letras = generarLetras(25, 3, 3);

        // Imprimir el resultado
        System.out.println(letras);
    }
}
