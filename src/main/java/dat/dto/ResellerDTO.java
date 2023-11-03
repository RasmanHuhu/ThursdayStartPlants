package dat.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor // @AllArgsConstructor - laver en constructor med alle fields
@NoArgsConstructor //Påkrævet hvis POST i dev.http skal virke
@Getter
public class ResellerDTO {

    int id;

    String name;

    String address;

    int phone;
    
}
