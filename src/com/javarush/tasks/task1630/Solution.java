package com.javarush.tasks.task1630;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.util.Scanner;

/*
Последовательный вывод файлов
*/

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    static {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            firstFileName=reader.readLine();
            secondFileName=reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface{
        String fileName="";
        String fileContent="";
        public void run(){
            try {
                InputStream inputStream=new FileInputStream(fileName);
                Scanner content = new Scanner(inputStream);
                fileContent=content.next();
                while (content.hasNext()) {
                    fileContent=fileContent+" "+content.next();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        public void setFileName(String filename) {
            fileName=filename;
        }
        public String getFileContent(){
            return fileContent;
        }
    }
}
