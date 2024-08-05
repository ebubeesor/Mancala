import java.io.*;

public class UserProfile implements Serializable {
    public UserProfile() {

    }

    public void saveToFile(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static UserProfile loadFromFile(String filename) {
        UserProfile userProfile = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userProfile = (UserProfile) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("UserProfile class not found");
            c.printStackTrace();
        }
        return userProfile;
    }
}