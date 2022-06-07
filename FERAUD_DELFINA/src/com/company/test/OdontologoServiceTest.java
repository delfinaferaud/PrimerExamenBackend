package com.company.test;

import com.company.dao.OdontologoDAOH2;
import com.company.model.Odontologo;
import com.company.service.OdontologoService;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class OdontologoServiceTest {
    private OdontologoService odontologoService = new OdontologoService(new OdontologoDAOH2());

    @Test
    public void guardarTest() throws SQLException{
        Odontologo odontologo1 = new Odontologo("365", "Martin", "Gonzalez");
        odontologoService.guardar(odontologo1);
        Assert.assertNotNull(odontologoService.buscarPorId(1) != null);
    }

    @Test
    public void listarTodosTest() throws SQLException{
        Odontologo odontologo2 = new Odontologo("123", "Delfina", "Feraud");
        Odontologo odontologo3 = new Odontologo("234", "Tomas", "Arce");
        Odontologo odontologo4 = new Odontologo("345", "Camila", "Prado");
        odontologoService.guardar(odontologo2);
        odontologoService.guardar(odontologo3);
        odontologoService.guardar(odontologo4);

        odontologoService.listarTodos().size();
        Assert.assertEquals(4, odontologoService.listarTodos().size());
    }

}
