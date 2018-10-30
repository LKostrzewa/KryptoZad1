import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class OneTimePad {

    public byte[] getKey(byte[] fileContent){
        byte[] key= new byte[fileContent.length];
        Random rand = new Random();
        rand.nextBytes(key);
        return key;
    }


    public byte[] cipher(byte[] plain, byte[] key){
        byte[] ciphered = new byte[plain.length];
        for(int i=0 ;i<plain.length ;i++)
            ciphered[i]=(byte) (plain[i]^key[i]);
        return ciphered;
    }

    public void saveToFile(byte[] cipheredText, String filePath, String filetoPath){
        //int lastIndex = filePath.lastIndexOf('.');
        //String ext = filePath.substring(lastIndex);       to na razie zostawiam zakomentowane bo nie uzywam i to tylko taki bajer jakbyco i nie działa :((((
        Path path = Paths.get(filetoPath);
        //String extout = filetoPath.substring(lastIndex);
        //if(!ext.equals(extout)) System.out.println("PODANO ZLE ROZSZERZENIE PLIKU");
        try{
            Files.write(path, cipheredText);
        }
        catch (IOException e) {
            System.out.println("Exception Occurred:");
        }
    }

    public byte[] readFromFile(String filePath){
        File plik = new File(filePath);
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
        return fileContent;
    }

}
