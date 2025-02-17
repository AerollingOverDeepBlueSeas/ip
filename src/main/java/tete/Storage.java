package tete;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/** Class that handles file reading/writing operations  */
public class Storage {

    private final FileWriter fw;
    private final Scanner sc;
    private final ArrayList<String> initContent;

    /**
     * Creates a new Storage for the session.
     * Creates new file reader and writer.
     */
    public Storage() {
        try {
            File data = new File("./src/main/data/list.txt");
            sc = new Scanner(data);
            initContent = backupContent();
            fw = new FileWriter(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<String> backupContent() {
        ArrayList<String> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().strip();
            if (!line.isEmpty()) {
                list.add(line);
            }
        }
        return list;
    }

    /**
     * Return contents already stored in the file on opening.
     *
     * @return contents stored in file as ArrayList of String.
     */
    public ArrayList<String> readContents() {
        return initContent;
    }

    /**
     * Saves entries from parameter and closes file reader and writer.
     *
     * @param items containing String of data representation of all items in list during session.
     * @throws IOException when issues arise while writing to the file.
     */
    public void saveAndClose(ArrayList<String> items) throws IOException {
        for (String item : items) {
            fw.write(item + "\n");
        }
        fw.close();
    }

}
