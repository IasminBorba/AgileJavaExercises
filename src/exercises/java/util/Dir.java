package util;

import java.io.*;
import java.util.ArrayList;

public class Dir {
    File directory;
    String dirName;
    String path;
    ArrayList<MyFile> myFiles = new ArrayList<>();

    public Dir(String filename) throws IOException {
        File fileExist = new File(System.getProperty("user.dir") + File.separator + filename);
        if(!fileExist.isDirectory())
            throw new IOException("Directory not already exists");
        else {
            if(fileExist.isFile())
                throw new IOException("The directory name is the same as a file name");
            else {
                this.directory = fileExist;
                this.dirName = directory.getName();
                this.path = directory.getPath();
            }
        }
    }

    public static Dir ensureExists(String filename) throws IOException {
        File DirNotExist = new File(System.getProperty("user.dir") + File.separator + filename);
        try{
            DirNotExist.mkdir();
        } finally {
            return new Dir(filename);
        }
    }

    public MyFile addMyFile(String filename){
        File fileAux = new File(directory, filename);
        return new MyFile(fileAux.getPath());
    }

    public void listMyFiles() throws IOException {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new IOException("Failed to list files in directory: " + directory.getPath());
        }

        for (File file : files) {
            if (file.isFile()) {
                myFiles.add(new MyFile(file.getPath()));
                file.delete();
            }
        }
    }

    public int getMyFiles() throws IOException {
        listMyFiles();
        return myFiles.size();
    }
}
