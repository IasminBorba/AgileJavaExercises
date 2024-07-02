package util;

import java.io.*;

public class SerializableObj implements Serializable {
    private final String nameClass;
    private final double valorClass;
    private final int seqClass;

    public SerializableObj(String nameClass, double valorClass, int seqClass){
        this.nameClass = nameClass;
        this.valorClass = valorClass;
        this.seqClass = seqClass;
    }

    public String getName(){
        return nameClass;
    }

    public double getValor(){
        return valorClass;
    }

    public int getSeq(){
        return seqClass;
    }

    public void serializableObj(String filename) throws IOException{
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))) {
            output.writeObject(this);
        }
    }

    public SerializableObj copyObj(String filename){
        SerializableObj obj = null;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename))) {
            obj = (SerializableObj)input.readObject();
        } finally {
            return obj;
        }
    }
}
