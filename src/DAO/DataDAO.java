package DAO;

import model.Data;
import utils.Constant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataDAO {

    private List<Data> dataList;

    public List<Data> getDataList(){
        return this.dataList;
    }

    public void write(List<Data> dataList) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(Constant.DATA));
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
            File file = new File(Constant.DATA);
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

    private void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeStream(OutputStream os) {
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

    public void update(String name, int level){
        List<Data> dataList =read();
        for (Data data : dataList) {
            if (data.getName().equals(name)){
                data.setLevel(level);
                write(dataList);
            }
        }
    }

}
