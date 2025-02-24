package tete;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** Class that handles file reading/writing operations */
public class Storage {

    private final File dataFile;
    private final ArrayList<String> initContent;

    /**
     * Creates a new Storage for the session.
     * Creates new file reader and writer.
     */
    public Storage() {
        // Use a reliable file path (e.g., inside the user's home directory)
        String userHome = System.getProperty("user.home");
        dataFile = new File(userHome + File.separator + "list.txt");

        // Ensure the file exists and is writable
        try {
            if (!dataFile.exists()) {
                boolean created = dataFile.createNewFile();
                if (!created) {
                    throw new RuntimeException("Failed to create file: " + dataFile.getAbsolutePath());
                }
            }
            if (!dataFile.canWrite()) {
                throw new RuntimeException("File is not writable: " + dataFile.getAbsolutePath());
            }

            // Read initial content
            initContent = backupContent();
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize storage", e);
        }
    }

    /**
     * Backs up the content of the file into an ArrayList.
     *
     * @return ArrayList containing lines from the file.
     */
    private ArrayList<String> backupContent() {
        ArrayList<String> list = new ArrayList<>();
        try (Scanner sc = new Scanner(dataFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().strip();
                if (!line.isEmpty()) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file content", e);
        }
        return list;
    }

    /**
     * Returns contents already stored in the file on opening.
     *
     * @return contents stored in file as ArrayList of String.
     */
    public ArrayList<String> readContents() {
        System.out.println("reading contents");
        System.out.println(initContent.toString());
        return initContent;
    }

    /**
     * Saves entries from parameter and closes file writer.
     *
     * @param items containing String of data representation of all items in list during session.
     * @throws IOException when issues arise while writing to the file.
     */
    public void saveAndClose(ArrayList<String> items) throws IOException {
        try (FileWriter fw = new FileWriter(dataFile)) {
            for (String item : items) {
                fw.write(item + "\n");
            }
        }
    }
}