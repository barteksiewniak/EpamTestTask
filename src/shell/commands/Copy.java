package shell.commands;

import shell.Command;
import shell.Shell;

import java.io.*;

/**
 * Created by BSiewni on 7/4/2017.
 */
public class Copy implements Command {

    private Shell shell;

    public Copy(Shell shell) {
        this.shell = shell;
    }

    @Override
    public void executeWithTwoParameters(Object param, Object param2) {
        String strParam = (String) param;
        String strParam2 = (String) param2;
        System.out.println(strParam);
        System.out.println(strParam2);
        try {
            copyFileUsingStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFileUsingStream() throws IOException {
        InputStream inStream = null;
        OutputStream outStream = null;

        try{

            File afile =new File("C:/INSTALL.LOG");
            File bfile =new File("C:/Bfile.txt");

            inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0){

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            System.out.println("File is copied successful!");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
