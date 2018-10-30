
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileInputStream;


public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window=primaryStage;
        window.setTitle("Hello World");     //cale te trzy linijki co jest window to tworzymy jakby nasze okno
        //primaryStage.setScene(new Scene(root, 300, 275));

        GridPane grid = new GridPane();     //tworzymy nową siatkę żeby ładnie mozna było porozkładać wszystkie elementy
        grid.setPadding(new Insets(10, 10, 10, 10));        //pokazujemy na ile elementow dzielimy siatke
        grid.setVgap(5);
        grid.setHgap(5); //ustalamy poziome i pionowe odleglosci

        Label fileLabel = new Label("Podaj ścieżkę do pliku");
        GridPane.setConstraints(fileLabel, 0, 0);       //tworzymy nowa etykiete i mowimy gdzie ma sie znajdowac

        TextField fileInput = new TextField();
        fileInput.setPrefWidth(300);
        GridPane.setConstraints(fileInput, 0, 1);       //tworzymy nowe pole tekstowe i mowimy gdzie ma sie znajdowac

        Button buttonFile = new Button("Szyfruj");
        GridPane.setConstraints(buttonFile, 1,1);        //tworzymy nowy guzik i mowimy gdzie ma sie znajdowac

        Label inputLabel = new Label("Podaj tekst do zaszyfrowania");
        GridPane.setConstraints(inputLabel, 0, 2);      //tworzymy nowa etykiete i mowimy gdzie ma sie znajdowac

        TextField userInput = new TextField();
        GridPane.setConstraints(userInput, 0, 3);        //tworzymy nowe pole tekstowe i mowimy gdzie ma sie znajdowac

        Button buttonInput = new Button("Szyfruj");
        GridPane.setConstraints(buttonInput, 1,3);       //tworzymy nowy guzik i mowimy gdzie ma sie znajdowac

        Label cipherLabel = new Label("Zaszyfrowany tekst");
        GridPane.setConstraints(cipherLabel, 0, 4);     //tworzymy nowa etykiete i mowimy gdzie ma sie znajdowac

        TextField cipherOutput = new TextField();
        GridPane.setConstraints(cipherOutput, 0, 5);    //tworzymy nowe pole tekstowe i mowimy gdzie ma sie znajdowac

        Label decipherLabel = new Label("Ponownie odszyfrowany tekst");
        GridPane.setConstraints(decipherLabel, 0, 6);       //tworzymy nowa etykiete i mowimy gdzie ma sie znajdowac

        TextField decipherOutput = new TextField();
        GridPane.setConstraints(decipherOutput, 0, 7);      //tworzymy nowe pole tekstowe i mowimy gdzie ma sie znajdowac

        OneTimePad cipherInput = new OneTimePad();      //tworzymy nowy obiekt klasy onetimepad czyli naszego algorytmu
        OneTimePad2 test = new OneTimePad2();

        buttonInput.setOnAction(e -> {                                              //tutaj beda rzeczy co sie beda dzialy po wcisnieciu guzika ktory zwie sie button imput
            cipherInput.setInput(userInput.getText());//ustawaimy w naszym obiekcie typu onetimepad tekst jawny jako zawartosc pola tekstowego co sie zwie userinput
            String inputinput = cipherInput.getInput();
            //System.out.println(cipherInput.getInput());
            String keykey = cipherInput.getKey();                                                   //losujemy se klucza
            //System.out.println(cipherInput.key);
            //String zaszyfrowany = cipherInput.cipher(cipherInput.getInput());
            //System.out.println(zaszyfrowany);
           // System.out.println(cipherInput.cipher(zaszyfrowany));
            byte[] plainplain = inputinput.getBytes();
            byte[] klucz = keykey.getBytes();
            byte[] zaszyfrowany = test.cioher(plainplain, klucz);
            String ss = new String(zaszyfrowany);
            cipherOutput.setText(ss);
            byte[] odszyfrowany = test.cioher(zaszyfrowany, klucz);
            String ss2 = new String(odszyfrowany);
            decipherOutput.setText(ss2);
            //cipherOutput.setText(cipherInput.cipher(cipherInput.getInput()));       //szyfrujemy i wyswietlamy
            //cipherInput.saveToFile();
            //System.out.println(cipherInput.cipher(cipherInput.getInput()));
            //decipherOutput.setText(cipherInput.cipher(cipherOutput.getText()));     //odszyfrujemy i wyswietlamy
           // System.out.println(cipherInput.cipher(cipherOutput.getText()));
        });

        buttonFile.setOnAction(e -> {
            File plik = new File(fileInput.getText());
            byte[] fileContent = new byte[(int) plik.length()];
            FileInputStream fin = null;
            try{
                fin = new FileInputStream(plik);
                fin.read(fileContent);
            }
            catch (Exception ae){
                System.out.println("Blad " + ae);
            }
            try {
                fin.close();
            }
            catch (Exception ea){
                System.out.println("Blad " + ea);
            }
            byte[] keykey = test.getKey(fileContent);
            //byte[] klucz = keykey.getBytes();
            //cipherInput.setInput(cipherInput.setFileInput(fileInput.getText()));   // To samo zo wyżej, tylko czytam z okienka fileInput
            byte[] zaszyfrowany = test.cioher(fileContent, keykey);
            String ss = new String(zaszyfrowany);
            cipherOutput.setText(ss);
            byte[] odszyfrowany = test.cioher(zaszyfrowany, keykey);
            String ss2 = new String(odszyfrowany);
            decipherOutput.setText(ss2);
            test.saveToFile(odszyfrowany, fileInput.getText());


            /*cipherInput.getKey();
            cipherOutput.setText(cipherInput.cipher(cipherInput.getInput()));
            decipherOutput.setText(cipherInput.cipher(cipherOutput.getText()));
            cipherInput.saveToFile(decipherOutput.getText(),fileInput.getText());*/

        });


        grid.getChildren().addAll(fileLabel, fileInput, buttonFile, inputLabel, userInput, buttonInput, cipherLabel, cipherOutput, decipherLabel, decipherOutput); //dodajemy wszystko do siaty

        Scene scene = new Scene(grid, 400, 375);        //ustawiamy "scene" i podajemy wymiary
        window.setScene(scene);     //przekazujemy ja do naszego okna
        window.show();      //i wyswietlamy
    }


    public static void main(String[] args) {
        launch(args);
    }
}