package smart.plantpot.cot.entities;


import jakarta.json.bind.annotation.JsonbVisibility;
import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
public class Pot implements Serializable {

    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String plantType;
    @Column
    private Long plantState;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Column
    private String user_id;


    public Pot() {

    }

    public Pot(String id, String name, String plantType, Long plantState, String user_id) {
        this.id = id;
        this.name = name;
        this.plantType = plantType;
        this.plantState = plantState;
        this.user_id = user_id;
    }






    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public Long getPlantState() {
        return plantState;
    }

    public void setPlantState(Long plantState) {
        this.plantState = plantState;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pot)) return false;
        Pot pot = (Pot) o;
        return Objects.equals(getId(), pot.getId()) && Objects.equals(getName(), pot.getName()) && Objects.equals(getPlantType(), pot.getPlantType()) && getPlantState() == pot.getPlantState() && Objects.equals(getUser_id(), pot.getUser_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPlantType(), getPlantState(), getUser_id());
    }
}
