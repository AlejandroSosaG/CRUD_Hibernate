package Tarea5_2;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Player")
public class Player {
    @Id
    @Column(name = "idPlayer")
    private int idPlayer;

    @Column(name = "nick")
    private String nick;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public Player(int idPlayer, String nick, String password, String email) {
        this.idPlayer = idPlayer;
        this.nick = nick;
        this.password = password;
        this.email = email;
    }
    public Player(){
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @OneToMany(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "idPlayer")
    private List<Compras> listaCompras;

    public List<Compras> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<Compras> listaCompras) {
        this.listaCompras = listaCompras;
    }
}
