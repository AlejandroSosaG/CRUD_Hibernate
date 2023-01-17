import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.*;
import java.util.logging.*;

public class Ejercicio5_1 {
    private static SessionFactory sessionFactory = null;
    private static Session session;
    private static Transaction transaction;
    public static void main(String[] args) {
        try {
            /**
             * Codigo para deshabilitar los warnings de hibernate
             */
            LogManager.getLogManager().reset();
            Logger globalLogger = Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
            globalLogger.setLevel(java.util.logging.Level.OFF);
            Direccion direccion1=new Direccion(3, "Calle de la sarten", 23, "Manises", "Valencia");
            Alumnado alumnado1=new Alumnado(3, "Sergio", "Mateo", "Ramis", direccion1);
            alumnado1.setDireccion(direccion1);
            direccion1.setAlumnado(alumnado1);
            Direccion direccion2=new Direccion(4, "Calle Luis lamarca", 45, "Torrente", "Valencia");
            Alumnado alumnado2=new Alumnado(4, "Paco", "Moreno", "Díaz", direccion2);
            alumnado2.setDireccion(direccion2);
            direccion2.setAlumnado(alumnado2);
            guardar(alumnado1);
            guardar(alumnado2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
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
