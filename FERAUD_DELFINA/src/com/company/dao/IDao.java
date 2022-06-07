package com.company.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
    public void crearBD();
    public T guardar(T t);
    public T buscarPorId(int id);
    public List<T> listarTodos();
}