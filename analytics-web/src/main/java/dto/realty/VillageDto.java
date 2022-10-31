package dto.realty;

import enums.realty.EVillageType;

public class VillageDto {

    private String name;

    private EVillageType villageType;

    public boolean exists() {
        return name != null && villageType != null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EVillageType getVillageType() {
        return villageType;
    }

    public void setVillageType(EVillageType villageType) {
        this.villageType = villageType;
    }


}
