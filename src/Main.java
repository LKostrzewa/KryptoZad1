
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window=primaryStage;
        window.setTitle("Hello World");     //cale te trzy linijki co jest window to tworzymy jakby nasze okno

        GridPane grid = new GridPane();     //tworzymy nową siatkę żeby ładnie mozna było porozkładać wszystkie elementy
        grid.setPadding(new Insets(15, 15, 15, 15));        //pokazujemy na ile elementow dzielimy siatke
        grid.setVgap(5);
        grid.setHgap(5); //ustalamy poziome i pionowe odleglosci

        Label fileLabel = new Label("Podaj ścieżkę do pliku z którego należy odczytać dane");
        GridPane.setConstraints(fileLabel, 0, 0);       //tworzymy nowa etykiete i mowimy gdzie ma sie znajdowac

        TextField fileInput = new TextField();
        fileInput.setPrefWidth(300);
        GridPane.setConstraints(fileInput, 0, 1);       //tworzymy nowe pole tekstowe i mowimy gdzie ma sie znajdowac

        Button buttonFile = new Button("Szyfruj");
        GridPane.setConstraints(buttonFile, 1,5);        //tworzymy nowy guzik i mowimy gdzie ma sie znajdowac

        Label fileoutLabelC = new Label("Podaj ścieżkę do pliku do którego należy wczytać zaszyfrowany tekst");
        GridPane.setConstraints(fileoutLabelC, 0,2);

        TextField fileoutInputC = new TextField();
        GridPane.setConstraints(fileoutInputC, 0, 3);

        Label fileoutLabelD = new Label("Podaj ścieżkę do pliku do którego należy wczytać odszyfrowany tekst");
        GridPane.setConstraints(fileoutLabelD, 0,4);

        TextField fileoutInputD = new TextField();
        GridPane.setConstraints(fileoutInputD, 0, 5);

        Label inputLabel = new Label("Podaj tekst do zaszyfrowania");
        GridPane.setConstraints(inputLabel, 0, 6);      //tworzymy nowa etykiete i mowimy gdzie ma sie znajdowac

        TextField userInput = new TextField();
        GridPane.setConstraints(userInput, 0, 7);        //tworzymy nowe pole tekstowe i mowimy gdzie ma sie znajdowac

        Button buttonInput = new Button("Szyfruj");
        GridPane.setConstraints(buttonInput, 1,7);       //tworzymy nowy guzik i mowimy gdzie ma sie znajdowac

        Label cipherLabel = new Label("Zaszyfrowany tekst");
        GridPane.setConstraints(cipherLabel, 0, 8);     //tworzymy nowa etykiete i mowimy gdzie ma sie znajdowac

        TextField cipherOutput = new TextField();
        GridPane.setConstraints(cipherOutput, 0, 9);    //tworzymy nowe pole tekstowe i mowimy gdzie ma sie znajdowac

        Label decipherLabel = new Label("Ponownie odszyfrowany tekst");
        GridPane.setConstraints(decipherLabel, 0, 10);       //tworzymy nowa etykiete i mowimy gdzie ma sie znajdowac

        TextField decipherOutput = new TextField();
        GridPane.setConstraints(decipherOutput, 0, 11);      //tworzymy nowe pole tekstowe i mowimy gdzie ma sie znajdowac

        OneTimePad cipherInput = new OneTimePad();

        buttonInput.setOnAction(e -> {                                              //tutaj beda rzeczy co sie beda dzialy po wcisnieciu guzika ktory zwie sie button imput
            String input = userInput.getText();
            byte[] plain = input.getBytes();
            byte[] randKey = cipherInput.getKey(plain);
            byte[] cipheredB = cipherInput.cipher(plain, randKey);
            String cipheredS = new String(cipheredB);
            cipherOutput.setText(cipheredS);
            byte[] decipheredB = cipherInput.cipher(cipheredB, randKey);
            String decipheredS = new String(decipheredB);
            decipherOutput.setText(decipheredS);
        });

        buttonFile.setOnAction(e -> {
            byte[] fileContent = cipherInput.readFromFile(fileInput.getText());
            byte[] randKey = cipherInput.getKey(fileContent);
            byte[] zaszyfrowany = cipherInput.cipher(fileContent, randKey);
            String ss = new String(zaszyfrowany);
            cipherOutput.setText(ss);
            cipherInput.saveToFile(zaszyfrowany, fileInput.getText(), fileoutInputC.getText());
            byte[] odszyfrowany = cipherInput.cipher(zaszyfrowany, randKey);
            String ss2 = new String(odszyfrowany);
            decipherOutput.setText(ss2);
            cipherInput.saveToFile(odszyfrowany, fileInput.getText(), fileoutInputD.getText());
        });


        grid.getChildren().addAll(fileLabel, fileInput, buttonFile, inputLabel, userInput, buttonInput, cipherLabel, cipherOutput, decipherLabel, decipherOutput, fileoutInputC, fileoutLabelC, fileoutInputD, fileoutLabelD); //dodajemy wszystko do siaty

        Scene scene = new Scene(grid, 500, 475);        //ustawiamy "scene" i podajemy wymiary
        window.setScene(scene);     //przekazujemy ja do naszego okna
        window.show();      //i wyswietlamy
    }


    public static void main(String[] args) {
        launch(args);
    }
}