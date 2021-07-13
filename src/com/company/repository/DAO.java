package com.company.repository;

import com.company.exception.SaveDataException;
import com.company.exception.WrongIdException;

import java.util.Collection;

public interface DAO <T> {

    void initialize();

    void save() throws SaveDataException;

    Collection<T> readAll();

    T read(int id) throws WrongIdException;

    void remove(int id) throws WrongIdException;

    void add (T t);
}
