package Tarea5_2;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Compras")
public class Compras {

    @Id
    @Column(name = "idCompra")
    private int idCompra;

    @Column(name = "cosa")
    private String cosa;

    @Column(name = "precio")
    private double precio;

    @Column(name = "fechaCompra")
    private Date fechaCompra;

    public Compras(int idCompra, String cosa, double precio, Date fechaCompra) {
        this.idCompra = idCompra;
        this.cosa = cosa;
        this.precio = precio;
        this.fechaCompra = fechaCompra;
    }
    public Compras(){}

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getCosa() {
        return cosa;
    }

    public void setCosa(String cosa) {
        this.cosa = cosa;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idPlayer")
    private Player player;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idGames")
    private Games games;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Games getGames() {
        return games;
    }

    public void setGames(Games games) {
        this.games = games;
    }
}
