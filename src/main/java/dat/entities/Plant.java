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
    private int id;

    @Column
    private String type;

    @Getter
    @Column
    private String name;

    @Column
    private int maxHeight;

    private int price;

    //Det skal være et set, fordi sets kan have flere planter og derved flere resellers

    //Den som har ManyToOne, er blot et objekt - i det her tilfælde er reseller ONE
    //Reseller er owning side - da reselleren ejer flere planter
    @ManyToOne
    private Reseller reseller;


}
