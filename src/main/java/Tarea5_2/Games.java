package Tarea5_2;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "Games")
public class Games {

    @Id
    @Column(name = "idGames")
    private int idGames;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tiempoJugado")
    private Time tiempoJugado;


    public Games(int idGames, String nombre, Time tiempoJugado) {
        this.idGames = idGames;
        this.nombre = nombre;
        this.tiempoJugado = tiempoJugado;
    }
    public Games(){
    }

    public int getIdGames() {
        return idGames;
    }

    public void setIdGames(int idGames) {
        this.idGames = idGames;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Time getTiempoJugado() {
        return tiempoJugado;
    }

    public void setTiempoJugado(Time tiempoJugado) {
        this.tiempoJugado = tiempoJugado;
    }
    @OneToMany(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "idGames")
    private List<Compras> listaCompras;

    public List<Compras> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<Compras> listaCompras) {
        this.listaCompras = listaCompras;
    }
}
