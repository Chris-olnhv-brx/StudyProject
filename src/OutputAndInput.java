import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputAndInput {

    /*
    Streams
    a ordered sequence of data
    --> Provides a common I/O model
    --> Abstracts details of underlying source or destination
    --> Stream types are unidirectional (Read from or Write to)

    Categories
    - Byte streams -> Interact as binary data
    - Text streams -> Interact as Unicode characters

     */

    public static void main(String[] args) {
        //ByteStreams();
        //CharacterStreams();
        doTryWithResources();
    }

    /*
    Byte streams use:

    InputStream
        --> int read()
        --> int read(byte[] buff)

    OutputStream
        --> void write(int b)
        --> void write(byte[] buff)
     */
    public static void ByteStreams() {
        try {
            InputStream input = new ByteArrayInputStream("test".getBytes());
            int intVal;

            while((intVal = input.read()) >= 0) {
                byte byteVal = (byte) intVal;
                System.out.println(byteVal);
            }
        } catch (IOException ie) {
            System.out.println("Exception");
        }

        try {
            InputStream input = new ByteArrayInputStream("test".getBytes());
            int length;
            byte[] byteArray = new byte[10];

            while((length = input.read(byteArray)) >= 0) {
                for(int i=0; i < length; i++) {
                    byte byteVal = byteArray[i];
                    System.out.println(byteVal);
                }
            }
        } catch (IOException ie) {
            System.out.println("Exception");
        }
    }

    /*
    Character streams use:

    Reader
        --> int read()
        --> int read(char[] buff)

    Writer
        --> void write(int ch)
        --> void write(char[] buff)
        --> void write(String str)
     */

    public static void CharacterStreams() {
        try {
            Reader reader = new InputStreamReader(new ByteArrayInputStream("test".getBytes()));
            int intVal;

            while((intVal = reader.read()) >= 0) {
                char charVal = (char) intVal;
                System.out.println(charVal);
            }
        } catch (IOException ie) {
            System.out.println("Exception");
        }

        try {
            Reader reader = new InputStreamReader(new ByteArrayInputStream("test".getBytes()));
            int length;
            char[] charArray = new char[10];

            while((length = reader.read(charArray)) >= 0) {
                for(int i=0; i < length; i++) {
                    char charVal = charArray[i];
                    System.out.println(charVal);
                }
            }
        } catch (IOException ie) {
            System.out.println("Exception");
        }
    }

    /*
    Input / OutputStream Derived Classes:

    Input : ByteArrayInputStream / PipedInputStream / FileInputStream

    Output : ByteArrayOutputStream / PipedOutputStream / FileOutputStream

    Reader / Writer Derived Classes

    Reader : CharArrayReader / StringReader / PipedReader / InputStreamReader -> FileReader

    Writer : CharArrayWriter / StringWriter / PipedWriter / OutputStreamWriter -> FileWriter
     */

    /*
    Try-with-resources -> automates cleanup of 1 or more resources
    --> takes care of closing resources and handling any exceptions
    --> takes multiple resources divided by a semicolon
     */

    public static void doTryWithResources() {
        try (Reader reader = new InputStreamReader(new ByteArrayInputStream("test".getBytes()))){
            int length;
            char[] charArray = new char[10];

            while((length = reader.read(charArray)) >= 0) {
                for(int i=0; i < length; i++) {
                    char charVal = charArray[i];
                    System.out.println(charVal);
                }
            }
        } catch (IOException ie) {
            System.out.println("Exception");
        }
    }

    /*
    Accessing Files -> java.io package with FileReader / FileWriter / FileInputStream / FileOutputStream
    ---> is deprecated but still widely used in code !! Only the FileXXX streams

    New Accessing Files with the java.nio.file package

    Path
        -> Used to locate a file system item
        -> Can be a file or a directory

    Paths
        -> Static Path factory methods
        -> From string-based hierarchical path
        -> From URI

    Files Type
        -> Static methods for interacting with files
        -> create, copy, delete, etc
        -> Open file streams (newBufferedReader, etc)
        -> Read/write file contents (readAllLines, write)
     */

    public static void pathExample() {
        Path p1 = Paths.get("\\documents\\data\\foo.txt");
    }

    public static void filesExample() throws IOException{
        try(BufferedReader br =
                    Files.newBufferedReader(Paths.get("data.txt"))) {

            String inValue;
            while ((inValue = br.readLine()) != null) {
                System.out.println(inValue);
            }
        }
    }

    /*
    File System Types:

    FileSystem
        -> Represents an individual file system
        -> Factory for Path instances
    FileSystems
        -> Static FileSystem Factory methods
        -> Open or create a file system => newFileSystem
     */

    
}
