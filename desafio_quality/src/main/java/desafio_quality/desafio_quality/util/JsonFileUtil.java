package desafio_quality.desafio_quality.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class JsonFileUtil<T> {
    private String fileName;


    public void createFile () {
        FileWriter file = null;
        try {
            file = new FileWriter(this.fileName);
            file.write("[]");
            file.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> read (Class<T> elementClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<T> tList = new ArrayList<>();
        try {
            File file = new File(fileName);
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
            return objectMapper.readValue(file, listType);
        } catch(FileNotFoundException e){
            createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tList;
    }

    public T append (T t, Class<T> elementClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<T> tList = this.read(elementClass);
        tList.add(t);
        try {
            objectMapper.writeValue(new File(fileName), tList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    public List<T> update(List<T> newList) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(fileName), newList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newList;
    }

}
