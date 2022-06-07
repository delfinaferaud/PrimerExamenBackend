package com.company.service;

import com.company.dao.IDao;
import com.company.model.Odontologo;

import java.sql.SQLException;
import java.util.EmptyStackException;
import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoDao;

    public OdontologoService(IDao<Odontologo> odontologoDao){
        this.odontologoDao = odontologoDao;
    }

    public IDao<Odontologo> getOdontologoDao() {
        return odontologoDao;
    }

    public void setOdontologoDao(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }

    public Odontologo guardar (Odontologo odontologo){
        return odontologoDao.guardar(odontologo);
    }

    public Odontologo buscarPorId(int id){
        return odontologoDao.buscarPorId(id);
    }

    public List<Odontologo> listarTodos(){
        return odontologoDao.listarTodos();
    }
}
