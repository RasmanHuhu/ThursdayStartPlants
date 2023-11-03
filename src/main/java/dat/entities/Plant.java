package dat.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//TASK 4.2 -  Implement a Plant entity class with the following properties: id, type, name, maxheight, price.

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "plant")
public class Plant {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "plantType")
    String type;

    @Column(name = "plantTame")
    String name;

    @Column(name = "maxHeight")
    int maxHeight;

    @Column(name = "price")
    double price;

    //Det skal være et set, fordi sets kan have flere planter og derved flere resellers

    //Den som har ManyToOne, er blot et objekt - i det her tilfælde er reseller ONE
    //Reseller er owning side - da reselleren ejer flere planter
    @ManyToOne
    @JoinColumn(name = "reseller_id")
    private Reseller reseller;

    public Plant(String type, String name, int maxHeight, double price) {
        this.type = type;
        this.name = name;
        this.maxHeight = maxHeight;
        this.price = price;
    }
}
