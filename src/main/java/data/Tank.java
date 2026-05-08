package data;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "tanks")
@NamedQuery(name = "Tank.findAll", query = "SELECT t FROM Tank t")
public class Tank extends DataBaseSave implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    private String id;

    @Column
    private String name;
    @Column
    private String type;
    @Column
    private String username1;

    @Column
    private String tankRestConnectionState;

    @Column
    private String tankAddress;


  




    
    public Tank() {
    }

    public Tank(String name, String type, String username1, String tankRestConnectionState, String tankAddress) {
        this.name = name;
        this.type = type;
        this.username1 = username1;
        this.tankRestConnectionState = tankRestConnectionState;
        this.tankAddress = tankAddress;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getTankRestConnectionState() {
        return tankRestConnectionState;
    }

    public void setTankRestConnectionState(String tankRestConnectionState) {
        this.tankRestConnectionState = tankRestConnectionState;
    }


    public String getTankAddress() {
        return tankAddress;
    }

    public void setTankAddress(String tankAddress) {
        this.tankAddress = tankAddress;
    }










}
