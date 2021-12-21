package DAO;

import model.Data;
import utils.Constant;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataDAO {
    private File file;
    public List<Data> dataList;

    public DataDAO(){
        file = new File(Constant.DATA);
        dataList = read();
    }

    public void write(List<Data> dataList) {
        this.dataList = dataList;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(dataList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(fileOutputStream);
            closeStream(objectOutputStream);
        }
    }

    public List<Data> read() {
        List<Data> dataList = null;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            if(file.length() == 0){
                dataList = new ArrayList<>();
            }
            else {
                fileInputStream = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(fileInputStream);
                dataList = (List<Data>) objectInputStream.readObject();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeStream(fileInputStream);
            closeStream(objectInputStream);
        }
        return dataList;
    }

    public void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public Boolean checkNameExist(String name){
        List<Data> dataList = read();
        for (Data data : dataList) {
            if (data.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void update(Data data){
        List<Data> dataList = read();
        for (Data data1 : dataList) {
            if (data1.getName().equals(data.getName())){
                data1.setLevel(data.getLevel());
                write(dataList);
            }
        }
    }

}
