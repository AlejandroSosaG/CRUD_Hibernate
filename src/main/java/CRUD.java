import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUD {
    private static SessionFactory sessionFactory = null;
    private static Session session;

    public static void main(String[] args) {
        try {
            String[] Personas= {"idPersona INT AUTO_INCREMENT", "nombre VARCHAR(100)", "tipo CHAR"};
            String[] Libros = {"idLibro INT AUTO_INCREMENT", "titulo VARCHAR(45)", "idAutor INT"};
            String[] Lectoras = {"idLibro INT", "idLector INT", "puntos INT"};
            setUp();

            sessionFactory.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void CrearTablas(Statement st, Connection con, String tabla, String[] campos) throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS " + tabla +"(";
        for(int i = 0; i < campos.length; i++){

            if (i == campos.length - 1){
                sql += campos[i];
            } else {
                sql += campos[i] + ",";
            }
        }
        sql += ")";
        System.out.println(sql);
        System.out.println();
        st.execute();
    }
    private static void guardar() {
        PersonasEntity persona = new PersonasEntity("", "");
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(persona);
        transaction.commit();
        System.out.println(id);

    }
    private static void leer(int id, String tabla) throws Exception {
        Object instancia = null;
        session = sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.createQuery("SELECT * FROM " + tabla);
        PersonasEntity persona = session.load(PersonasEntity.class, id);//   PersonasEntity persona = session.get(PersonasEntity.class, id); // Esta línea también funcionaría como la anterior
        System.out.println(persona.getNombre());
        transaction.commit();
    }
    private static void actualizar(int id,String nombre, String tipo) throws Exception {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        PersonasEntity persona = session.get(PersonasEntity.class,id);
        persona.setNombre(nombre);
        persona.setTipo(tipo);
        // session.saveOrUpdate(persona);       // session.merge(persona);
        session.update(persona);
        transaction.commit();

    }
    private static void borrar(int id){
        PersonasEntity persona = session.load(PersonasEntity.class, id);
        session.delete(persona);
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

