package com.company.repository;

import com.company.exception.WrongIdException;
import com.company.models.Store;

import java.util.Collection;

public interface DAO <T> {

    void initialize();

    void save();

    Collection<T> readAll();

    T read(int id) throws WrongIdException;

    void remove(int id) throws WrongIdException;

    void add (T t);
}
