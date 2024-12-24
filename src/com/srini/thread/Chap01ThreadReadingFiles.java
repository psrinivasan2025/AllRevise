package com.srini.thread;

import java.io.*;
import java.util.Arrays;

public class Chap01ThreadReadingFiles {


    public static void main(String[] args){

        new Thread(new Watcher()).start();
    }
}

class Watcher implements Runnable{

    @Override
    public void run() {

// File fileDirectory = new File("C:\\Users\\z579651\\workspace\\First\\resources");
        File fileDirectory = new File(".\\resources");
        while(true){

            if(fileDirectory.listFiles().length != 0){

                Arrays.stream(fileDirectory.listFiles()).forEach(file -> new Thread(new FileProcessor(file)).start());
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class FileProcessor implements Runnable{

    private final File file;
    private final static String OUTPUT_PATH = ".//output";
    private final static String INPUT_PATH = ".//resources";
    public FileProcessor(File file){
        this.file = file;
    }
    @Override
    public void run() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OUTPUT_PATH+"//"+"output"+file.getName()));

            BufferedReader bufferedReader = new BufferedReader(new FileReader(INPUT_PATH+"//"+file.getName()));
            System.out.println(INPUT_PATH+file.getName());
            String s = bufferedReader.readLine();
            while(s != null){
// System.out.println();
                bufferedWriter.write(s+"\n"+s+"\n");
                s = bufferedReader.readLine();

            }
            bufferedWriter.close();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
