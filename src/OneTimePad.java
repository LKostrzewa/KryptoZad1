import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
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

    public void saveToFile(byte[] cipheredText, String filePath){
        //byte bytes[] = cipheredText.getBytes();
        int lastIndex = filePath.lastIndexOf('.');
        String ext = filePath.substring(lastIndex);
        System.out.println(ext);
        Path path_txt = Paths.get("C:\\Users\\Łukasz\\Desktop\\pliczek.txt");
        Path path_png = Paths.get("C:\\Users\\Łukasz\\Desktop\\pliczek.png");
        try{
            if(ext.equals(".txt")) Files.write(path_txt, cipheredText);
            else Files.write(path_png, cipheredText);
        }
        catch (IOException e) {
            System.out.println("Exception Occurred:");
        }
    }

}
