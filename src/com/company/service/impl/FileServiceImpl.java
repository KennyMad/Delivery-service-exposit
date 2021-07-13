package com.company.service.impl;

import com.company.exception.LoadDataException;
import com.company.exception.SaveDataException;
import com.company.service.FileService;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;

public class FileServiceImpl implements FileService {
    @Override
    public void save(String fileName, Object objectToSave) throws SaveDataException {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter(fileName)){
            writer.write(gson.toJson(objectToSave));
        }
        catch (IOException exception){
            throw new SaveDataException(exception.getMessage());
        }
    }

    @Override
    public Object load(String fileName, Type type) throws FileNotFoundException, LoadDataException {
        Gson gson = new Gson();

        if (!new File(fileName).exists())
            throw new FileNotFoundException(fileName);

        String jsonString = "";
        String s = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            while ((s = reader.readLine()) != null)
                jsonString += s;

            return gson.fromJson(jsonString,type);
        }
        catch (Exception exception) {
            throw new LoadDataException(fileName, exception.getMessage());
        }
    }
}
