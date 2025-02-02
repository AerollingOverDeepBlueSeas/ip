import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final FileWriter fw;
    private final Scanner sc;
    private final ArrayList<String> initContent;

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

    public ArrayList<String> backupContent() {
        ArrayList<String> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine().strip();
            if (!line.isEmpty()) {
                list.add(line);
            }
        }
        return list;
    }

    public ArrayList<String> readContents() {
        return initContent;
    }

    public void saveAndClose(ArrayList<String> items) throws IOException {
        for (String item : items) {
            fw.write(item + "\n");
        }
        fw.close();
    }

}
