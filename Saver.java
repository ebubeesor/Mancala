package mancala;
import java.io.*;

public class Saver {
    public static void saveObject(Serializable toSave, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("assets/" + filename))) {
            oos.writeObject(toSave);
        } catch (IOException e) {
            System.err.println("Error saving object: " + e.getMessage());
            throw e;
        }
    }

    public static Serializable loadObject(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("assets/" + filename))) {
            return (Serializable) ois.readObject();
        } catch (IOException e) {
            System.err.println("Error loading object: " + e.getMessage());
            throw e;
        }

    }   

   
    
}
