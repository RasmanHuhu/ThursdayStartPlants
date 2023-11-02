package dat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//Task #1.3

@Data
@Builder
@AllArgsConstructor // @AllArgsConstructor - laver en constructor med alle fields
@NoArgsConstructor //Påkrævet hvis POST i dev.http skal virke
public class PlantDTO {
    
    int plantID;   //Vigtigt med @AllArgsConstructor, for ellers får jeg ikke en constructor på ID

     String plantType;

    String name;

    int maxHeight;

    double price;

    }
