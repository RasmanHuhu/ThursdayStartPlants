package dat.dao;

import dat.ErrorHandling.ApiException;
import dat.dto.ResellerDTO;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Task #3.7
public class ResellerDAOGeneric implements iDAO<ResellerDTO, String> {

    private Map<Integer, ResellerDTO> resellerData = new HashMap<>(Map.of(
            1, new ResellerDTO(1, "Lyngby Plantecenter", "Firskovvej 18", 33212334),
            2, new ResellerDTO(2, "Glostrup Planter", "Tværvej 35", 32233232),
            3, new ResellerDTO(3, "Holbæk Planteskole", "Stenhusvej 49", 59430945)

            //HVORDAN POPULATER VI I JPA?
            //Gøres feks gennem en main metode et sted, hvor vi persister lortet op - klassisk JPA stil du ved


    ));

    private static ResellerDAOGeneric instance;

    public static ResellerDAOGeneric getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            instance = new ResellerDAOGeneric();
        }
        return instance;
    }


    @Override
    public List<ResellerDTO> getAll() {
        ;
        List<ResellerDTO> resellerList = new ArrayList<>(resellerData.values());
        if (!resellerList.isEmpty()) {
            return resellerList;
        } else {
            throw new ApiException(404, "Resellers not found");
        }
    }

    @Override
    public ResellerDTO getById(int id) {
        List<ResellerDTO> resellerList = new ArrayList<>(resellerData.values());
        if (id < resellerList.size()) {
            return resellerList.get(id - 1);
        } else {
            throw new ApiException(404, "Not found");
        }
    }

    @Override
    public List<ResellerDTO> getByType(String type) {
        return null;
    }

    @Override
    public ResellerDTO add(ResellerDTO reseller) {
        ResellerDTO newReseller = new ResellerDTO(reseller.getId(), reseller.getName(), reseller.getAddress(), reseller.getPhone());
        if (newReseller == null) {
            resellerData.put(newReseller.getId(), newReseller);
            return newReseller;
        } else {
            throw new ApiException(404, "Not found");
        }
    }
}

