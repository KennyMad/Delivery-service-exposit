package com.company.service;

import com.company.exception.LoadDataException;
import com.company.exception.SaveDataException;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;

public interface FileService {

    void save(String fileName, Object objectToSave) throws SaveDataException;

    Object load(String fileName, Type type) throws FileNotFoundException, LoadDataException;

}
