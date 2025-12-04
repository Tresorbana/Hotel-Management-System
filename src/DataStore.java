import java.io.*;
import java.util.ArrayList;

public class DataStore {
    public synchronized static <T> ArrayList<T> load(String file) {
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
            return (ArrayList<T>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public synchronized static void save(String file, Object data) {
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
            out.writeObject(data);
        } catch (Exception ignored) {}
    }

    public synchronized static void writeTemplate(String file, String text) {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(text);
        } catch (Exception ignored) {}
    }
}
