package com.company.dao;

import com.company.model.Odontologo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class OdontologoDAOH2 implements IDao<Odontologo> {
    private static final Logger log = Logger.getLogger(OdontologoDAOH2.class);

    private static final String DB_JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/db_odontologos";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "sa";

    private static final String SQL_CREATE = "DROP TABLE IF EXISTS odontologos; CREATE TABLE odontologos (id INT auto_increment PRIMARY KEY, matricula VARCHAR(100), nombre VARCHAR(100), apellido VARCHAR(100));";
    private static final String SQL_INSERT = "INSERT INTO odontologos (matricula, nombre, apellido) VALUES (?,?,?);";
    private static final String SQL_SELECT = "SELECT * FROM odontologos WHERE id = ?;";
    private static final String SQL_SELECT_ALL = "SELECT * FROM odontologos;";


    @Override
    public void crearBD() {
        Connection connection = null;

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            log.info("Conexión exitosa con la base de datos");

            //2 crear una BD con Statement
            Statement statement = connection.createStatement();
            statement.execute(SQL_CREATE);
            log.info("Tabla creada");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Odontologo guardar(Odontologo odontologo){
        log.info("Guardando el odontológo: "+ odontologo);

        Connection connection = null;
        PreparedStatement psInsert = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            log.info("Conexión establecida con la base de datos");

            psInsert = connection.prepareStatement(SQL_INSERT);

            //psInsert.setInt(1, odontologo.getId());
            psInsert.setString(1, odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());

            psInsert.execute();
            log.info("Nuevo registro insertado en la tabla 'odontologos'");

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            log.error("Error relacionado con la base de datos", e);
        }

        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(int id){
        log.info("Buscando el odontólogo por id " + id);
        Odontologo odontologo = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            log.info("Conexión establecida con la base de datos");

            preparedStatement = connection.prepareStatement(SQL_SELECT);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            log.info("Se ejecutó la sentencia SELECT");

            while (rs.next()) {
                int idOdontologo = rs.getInt("id");
                String matriculaOdontologo = rs.getString("matricula");
                String nombreOdontologo = rs.getString("nombre");
                String apellidoOdontologo = rs.getString("apellido");

                odontologo = new Odontologo(idOdontologo, matriculaOdontologo, nombreOdontologo, apellidoOdontologo);
                log.info("Obtenido el odontologo: " + odontologo);
            }
            preparedStatement.close();
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            log.error("Error relacionado con la base de datos", e);
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos(){
        log.info("Buscando todos los odontólogos de la tabla");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologoList = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            log.info("Conexión establecida con la base de datos");

            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);

            ResultSet rs = preparedStatement.executeQuery();
            log.info("Se ejecutó la sentencia SELECT");

            while (rs.next()){
                int idOdontologo = rs.getInt("id");
                String matriculaOdontologo = rs.getString("matricula");
                String nombreOdontologo = rs.getString("nombre");
                String apellidoOdontologo = rs.getString("apellido");

                Odontologo odontologo = new Odontologo(idOdontologo, matriculaOdontologo, nombreOdontologo, apellidoOdontologo);
                odontologoList.add(odontologo);
                log.info("Lista de odontologos obtenida: "+ odontologoList);

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            log.error("Error relacionado con la base de datos", e);
        }

        return odontologoList;
    }
}
