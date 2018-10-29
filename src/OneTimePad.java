

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
public class OneTimePad {

    private String input;
    private String key;
    //String password;
    //String output;

    public OneTimePad(String input) {
        this.input = input;
    }

    public OneTimePad() {

    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    } //setery i getery to chyba wiecie co jest 5

    public String getKey() {

        key="";
        Random rand = new Random();
        for(int i=0; i<input.length(); i++){
            char ch=(char)(rand.nextInt(78) + '0');     //losujemy z tego zakresu dowolny znaczek
            key+=ch;                                            //i go dodajemy do klucza
        }
        return key;
    }

    public String cipher(String plain){

        String pass="";
        int xor;
        Random rand = new Random();
        for(int i=0; i<plain.length(); i++){
            xor=plain.charAt(i)^key.charAt(i);      //xorujemy tekst z kluczem
            if(xor>=32 && xor!=127)                 //sprawdzamy czy nie wypierdala zakresu
                pass+=(char)xor;                    //jak nie wypierdala to zajebiscie i dodajemy do zaszyfrowanego
            else {                                  //jak wypierdala to
                char[] newkey = key.toCharArray();  // generujemy tablice char zeby mozna bylo zmienic dowolny znak
                newkey[i] = (char) (rand.nextInt(78) + '0');    //losujemy
                key = String.valueOf(newkey);       //wpierdalamy nowy lancuch do klucza
                i--;                                   //zmniejszamy i zeby mozna bylo ponownie zaszyfrowac ten sam znak
            }
            /*do {
                xor=plain.charAt(i)^key.charAt(i);
            }*/
        }
        return pass;
    }

    public  String setFileInput(String filePath){                       // funkcjia pobiera ścieżke do pliku a zwraca wczytany tekst.
        System.out.println(filePath);
        File file = new File(String.format(filePath));                      // tworzymy se zmienną która przechowa nam abstrakcyjną reprezentację pliku z podanaj ścieżki
        FileInputStream fin = null;                                         // tworzymy FileInputStream (przyłe połączenie z plikiem) o nazwie fin
        String s = "";                                                      // tworzymy łańcuch w którym zapiszemy nasz tekst znajdujący się wpliku
        try {
            fin = new FileInputStream(file);                                // nawiązujemy połączenie pomiędzy utworzonym fin a naszym plikiem
            byte fileContent[] = new byte [(int)file.length()];              // tworzymy tablice bajtów, do przechowywania
            fin.read(fileContent);                                          // następuje czytanie bajtów z pliku
            s = new String(fileContent);                                    // tworzymy stringa z otrzymanej tablicy bajtów
            //System.out.println("File content: " + s);                       // pokazujemy na ekranie zawartość pliku, którą udało nam się odczytać
        }
        catch (FileNotFoundException e) {                                         // to sie odpali jak nie znajdzie pliku (czyli wyskoczy błąd FileNotFoundException)
            System.out.println("File not found: " + e);
        }
        catch (IOException ioe) {                                                  // gdy jakiś problem się pojawi w czasie odczytu
            System.out.println("Exception while reading file: " + ioe);
        }
        finally {                                                                   //  po wczytaniu pliku następuje to
            try {
                if (fin != null) {                                                  // zamyka strumień wejściowy i zwalnia zasoby sytemowe związane z tym połączeniem
                    fin.close();
                }
            }
            catch (IOException ioe) {                                               // a jak sie nie uda to komunikat
                System.out.println("Error while closing stream: " + ioe);
            }
        }
        return s;                                                                   // zwracamy odczytaną zawartość pliku
    }

    public void saveToFile(String cipheredText, String filePath){
        byte bytes[] = cipheredText.getBytes();
        int lastIndex = filePath.lastIndexOf('.');
        String ext = filePath.substring(lastIndex);
        System.out.println(ext);
        Path path_txt = Paths.get("C:\\Users\\Łukasz\\Desktop\\pliczek.txt");
        Path path_png = Paths.get("C:\\Users\\Łukasz\\Desktop\\pliczek.jpg");
        try{
            if(ext.equals(".txt")) Files.write(path_txt, bytes);
            else Files.write(path_png, bytes);
        }
        catch (IOException e) {
            System.out.println("Exception Occurred:");
        }
    }
}
