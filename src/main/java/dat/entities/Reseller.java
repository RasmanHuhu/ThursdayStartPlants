package dat.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

//TASK 4.3 Implement a Reseller entity class with the following properties: id, name, address, phone, and a
//ManyToMany relationship to Plant. This means that a reseller (Plant Shop) can have many plants in stock,
//and each plant can be sold by many resellers.

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "reseller")
public class Reseller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name", length = 25)
    String name;

    @Column(name = "address", length = 25)
    String address;

    //String phone er smart ifht constructor og build i testclass senere. Den vil fejle hvis int provided.
    @Column(name = "phoneNr")
    String phone;


    //Den som er many er tit en liste eller set. Fordi der er flere.
    //Plants er slave-side
    @OneToMany(mappedBy = "reseller", orphanRemoval = true)
    private List<Plant> plants = new ArrayList<>();

    public Reseller(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
