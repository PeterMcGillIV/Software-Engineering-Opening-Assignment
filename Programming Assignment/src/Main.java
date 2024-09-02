import java.io.*;
import java.util.*;

public class Main {

    //A method to get the name of a file without the extension.
    public static String getBaseName(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return fileName;
        }
        return fileName.substring(0, lastDotIndex);
    }

    public static void main(String[] args) throws IOException{

        //Since everyone's computer is different, this allows the end user to put their own respective paths for the input and output folders.
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the path of the input folder: ");
        String inputString = sc.nextLine();
        System.out.print("Enter the path of the output folder: ");
        String outputString = sc.nextLine();
        sc.close();
        File input = new File(inputString);
        File output = new File(outputString);

        //Setting up some variables to manipulate the files later on.
        File[] fileListing = input.listFiles();
        File xmlFile = null;
        String xmlName = null;
        File pngFile = null;
        String pngName = null;

        if (fileListing != null) { //If the input folder is empty, there is no point in continuing, so this if statement takes care of that.
            
            for (int i = 0; i < fileListing.length; i++) { //As we iterate through the input folder, we determine whether we are currently on a png or xml, and get their base names.
                File currentFile = fileListing[i];
                if (currentFile.getName().endsWith("xml")) {
                    xmlFile = currentFile;
                    xmlName = getBaseName(xmlFile.getName());
                }
                if (currentFile.getName().endsWith("png")) {
                    pngFile = currentFile;
                    pngName = getBaseName(pngFile.getName());
                }


                if (xmlName != null && pngName != null) { //This ensures that we don't break the program after the first iteration since one of the filetypes won't have a file to check.
                    
                    if (xmlName.equals(pngName) && (currentFile == xmlFile || currentFile == pngFile)) { //The png and xml files come in pairs, so this if statement ensures the loop proceeds only after we have matching base names and haven't moved away from them with a storage file.
                        try {
                            XMLParsing parse = new XMLParsing();
                            List<Leaf> leaves = parse.parser(xmlFile);
                            ImageUpdater artist = new ImageUpdater();
                            File outputFile = new File(output, pngName+"_annotated.png"); //Set up the file to be converted in the output folder
                            outputFile.createNewFile();
                            artist.drawBoxes(pngFile, leaves, outputFile);

                        //Allowing the loop to continue if something is wrong with one of the files, so we can still complete the process with the others.
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        // /Users/petermcgill/Desktop/Programming-Assignment-Data
        // /Users/petermcgill/Desktop/Programming-Assignment-Output
    }
}
