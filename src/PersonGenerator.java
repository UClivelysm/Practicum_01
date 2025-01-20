
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args)
    {
        boolean doneInput = false;
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        String rec = "";

        int YOB = 0;

        ArrayList <String> people = new ArrayList<>();

        Scanner in = new Scanner(System.in);


        //create a loop to input person data
        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter Your ID [000001]");
            firstName = SafeInput.getNonZeroLenString(in, "Enter your first name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter your last name");
            title = SafeInput.getNonZeroLenString(in, "Enter your title");
            YOB = SafeInput.getRangedInt(in, "Enter your year of birth", 1000, 9999);

            rec = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
            people.add(rec);
            System.out.println(rec);

            doneInput = SafeInput.getYNConfirm(in, "Are you done?");
        }while (!doneInput);




    File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "//src//PersonTestData.txt"); //Unix/Linux file system version
        //Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt"); //Windows file system version

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String person : people)
            {
                writer.write(person, 0, person.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}