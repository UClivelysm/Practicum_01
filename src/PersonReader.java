import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class PersonReader
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();

        final int FIELDS_LENGTH = 5;
        String id, firstName, lastName, title;
        int yob;

        try {
            // uses a fixed known path:
            //  Path file = Paths.get("c:\\My Documents\\data.txt");

            // use the toolkit to get the current working directory of the IDE
            // Not sure if the toolkit is thread safe...
            File workingDirectory = new File(System.getProperty("user.dir"));

            // Typically, we want the user to pick the file so we use a file chooser
            // kind of ugly code to make the chooser work with NIO.
            // Because the chooser is part of Swing it should be thread safe.
            chooser.setCurrentDirectory(workingDirectory);
            // Using the chooser adds some complexity to the code.
            // we have to code the complete program within the conditional return of
            // the filechooser because the user can close it without picking a file

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                // Typical java pattern of inherited classes
                // we wrap a BufferedReader around a lower level BufferedInputStream
                try (InputStream in = new BufferedInputStream(Files.newInputStream(file));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(in)))
                {

                    // Finally we can read the file LOL!
                    int line = 0;
                    while (reader.ready()) {
                        rec = reader.readLine();
                        lines.add(rec);
                        line++;
                        // echo to screen
                        System.out.printf("\nLine %4d %-60s ", line, rec);
                    }
                    System.out.println("\n\nData file read!");

                    System.out.println();
                    System.out.println("ID#     Firstname                LastName                 Title   YOB\n" +
                                       "=====================================================================");

                    String[] fields;
                    for(String l:lines)
                    {
                        fields = l.split(",");

                        if(fields.length == FIELDS_LENGTH)
                        {
                            id        = fields[0].trim();
                            firstName = fields[1].trim();
                            lastName  = fields[2].trim();
                            title     = fields[3].trim();
                            yob       = Integer.parseInt(fields[4].trim());

                            System.out.printf("\n%-8s%-25s%-25s%-6s%6d", id, firstName, lastName, title, yob);
                        }
                        else {
                            System.out.println("Found a record that may be corrupt: ");
                            System.out.println(l);
                        }
                    }
                }
            } else  // User closed the chooser without selecting a file
            {
                System.out.println("No file selected.");
                System.out.println("Please run the program again.");
                System.out.println("Please pick a file next time");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
