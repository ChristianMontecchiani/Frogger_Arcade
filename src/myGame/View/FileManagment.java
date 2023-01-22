package myGame.View;

import java.io.*;
import java.util.LinkedList;

public class FileManagment {

    //LinkedList<String[]> lstSA
    public static void write(String fileName, String charset,LinkedList<String[]> lstSA) throws IOException {
        PrintWriter printWriter = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(fileName), charset)), true);

        for (String[] sArr : lstSA)
            for (int i = 0; i < sArr.length; i++)
                if (i < (sArr.length - 1))
                    printWriter.print(sArr[i] + ";");
                else
                    printWriter.print(sArr[i] + "\r\n");



        printWriter.close();
    }

    public static LinkedList<String[]> read(String fileName, String charset) throws  IOException {
        LinkedList<String[]> lstRows = null;

        BufferedReader buffRead = null;
        try {
            buffRead = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileName), charset));

            lstRows = new LinkedList<>();
            String s=null;

            while ((s = buffRead.readLine()) != null)
                if (!s.isEmpty() && s.contains(";"))
                    lstRows.add(s.trim().split(";"));
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            if (buffRead != null)
                buffRead.close();
        }

        return lstRows;
    }
}