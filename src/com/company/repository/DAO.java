package com.company.repository;

import com.company.exception.SaveDataException;

import java.util.Collection;

public interface DAO <T> {

    void initialize();

    void save() throws SaveDataException;

    Collection<T> readAll();

    T getById(int id);

    T remove(int id);

    void add (T t);
}
