package Tarea5_2;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Tarea5_2 {
    private static SessionFactory sessionFactory = null;
    private static Session session;
    private static Transaction transaction;
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws SQLException, IOException {
        try {
            /**
             * Codigo para deshabilitar los warnings de hibernate
             */
            LogManager.getLogManager().reset();
            Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
            globalLogger.setLevel(java.util.logging.Level.OFF);
            Player jugador = new Player();
            Games juego = new Games();
            List<Compras> listaCompras = new ArrayList<>();
            listaCompras.add(new Compras(1, "cosa", 65.21, new Date()));
            listaCompras.add(new Compras(2, "cosa2", 43.43, new Date()));
            listaCompras.add(new Compras(3, "cosa3", 21.43, new Date()));
            jugador.setListaCompras(listaCompras);
            juego.setListaCompras(listaCompras);
            guardar(jugador);
            guardar(juego);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
     * Este método se conecta con la base de datos.
     */
    public static void abrir() throws Exception {
        setUp();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }
    public static void cerrar(){
        try {
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
    private static void guardar(Object cosa) throws Exception {
        abrir();
        int id = (int) session.save(cosa);
        System.out.println(id);
        cerrar();
    }
    protected static void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }
}
