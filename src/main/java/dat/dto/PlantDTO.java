package dat.dto;

import lombok.*;


//Task #4.2

@Data
@Builder
@AllArgsConstructor // @AllArgsConstructor - laver en constructor med alle fields
@NoArgsConstructor //Påkrævet hvis POST i dev.http skal virke
@Getter
public class PlantDTO {
    
    int plantID;   //Vigtigt med @AllArgsConstructor, for ellers får jeg ikke en constructor på ID

    String plantType;

    String name;

    int maxHeight;

    double price;

    //Constructor brugt til tests
    public PlantDTO(String plantType, String name, int maxHeight, double price) {
        this.plantType = plantType;
        this.name = name;
        this.maxHeight = maxHeight;
        this.price = price;
    }
}
