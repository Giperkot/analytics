package enums.report;

import com.fasterxml.jackson.annotation.JsonValue;
import db.annotations.EnumMethodDataBase;
import dto.report.RealtyConfigurationDto;

@EnumMethodDataBase(fromDbName = "fromString")
public enum ERealtyConfigType {

    /**
     * Средняя цена по городу (без классификации)
     */
    SIMPLE("SIMPLE"),
    /**
     * Средняя цена по району
     */
    ONLY_DISTRICT("ONLY_DISTRICT"),
    /**
     * Средняя цена по микрорайону
     */
    ONLY_MICRODISTRICT("ONLY_MICRODISTRICT"),
    /**
     * Микрорайон, Этаж, Этажность, кол. комнат
     */
    COMPLEX("COMPLEX"),
    /**
     * Квартира, кол. комнат, Сегмент жилья, Этажность, Материал стен
     */
    HAKATON_CONFIG("HAKATON_CONFIG"),
    /**
     * Квартира, кол. комнат, Сегмент жилья, Этажность, Материал стен
     */
    HAKATON_FULL_CONFIG("HAKATON_FULL_CONFIG"),
    /**
     * Микрорайон, Этаж, Этажность, кол. комнат, Год постройки
     */
    MORE_COMPLEX("MORE_COMPLEX"),
    /**
     * Микрорайон, Этаж, Этажность, кол. комнат, Год постройки, тип дома., балкон
     */
    MORE_COMPLEX_WITH_HOUSE_TYPE("MORE_COMPLEX_WITH_HOUSE_TYPE");

    private static ERealtyConfigType[] values = values();

    @JsonValue
    private final String configName;

    ERealtyConfigType(String configName) {
        this.configName = configName;
    }

    public RealtyConfigurationDto buildConfiguration() {
        RealtyConfigurationDto result = new RealtyConfigurationDto();
        switch (this) {
            case SIMPLE:
                result.setPlacement(EPlacement.NONE);
                return result;
            case ONLY_DISTRICT:
                result.setPlacement(EPlacement.DISTRICT);

                return result;
            case ONLY_MICRODISTRICT:
                result.setPlacement(EPlacement.MICRODISTRICT);

                return result;
            case COMPLEX:
                result.setPlacement(EPlacement.MICRODISTRICT);
                result.setFloor(true);
                result.setHouseFloor(true);
                result.setRoomsCount(true);

                return result;
            case HAKATON_CONFIG:
                result.setRoomsCount(true);
                result.setRealtySegment(true);
                result.setHouseFloor(true);
                result.setSimpleHouseType(true);

                return result;
            case HAKATON_FULL_CONFIG:
                result.setRoomsCount(true);
                result.setRealtySegment(true);
                result.setHouseFloor(true);
                result.setSimpleHouseType(true);
                result.setFloor(true);
                result.setTotalSquare(true);
                result.setKitchenSquare(true);
                result.setBalcon(true);
                result.setMetroDistance(true);
                result.setRepairType(true);

                return result;
            case MORE_COMPLEX:
                result.setPlacement(EPlacement.MICRODISTRICT);
                result.setFloor(true);
                result.setHouseFloor(true);
                result.setRoomsCount(true);
                result.setHouseBuildYear(true);

                return result;
            case MORE_COMPLEX_WITH_HOUSE_TYPE:
                result.setPlacement(EPlacement.MICRODISTRICT);
                result.setFloor(true);
                result.setHouseFloor(true);
                result.setRoomsCount(true);
                result.setHouseBuildYear(true);
                result.setHouseType(true);
                result.setBalcon(true);

                return result;
        }

        throw new IllegalArgumentException("Данной конфигурации на предусмотрено " + this.configName);
    }

    public static ERealtyConfigType getConfigTypeByConfiguration(RealtyConfigurationDto config) {

        if (config.getPlacement() == EPlacement.MICRODISTRICT && config.isFloor() && config.isHouseFloor()
                && config.isRoomsCount() && config.isHouseBuildYear() && config.isHouseType() && config.isBalcon()
                && !config.isKitchenSquare()) {
            return ERealtyConfigType.MORE_COMPLEX_WITH_HOUSE_TYPE;
        }

        if (config.getPlacement() == EPlacement.MICRODISTRICT && config.isFloor() && config.isHouseFloor()
                && config.isRoomsCount() && config.isHouseBuildYear() && !config.isHouseType() && !config.isBalcon()
                && !config.isKitchenSquare()) {
            return ERealtyConfigType.MORE_COMPLEX;
        }

        if (config.getPlacement() == EPlacement.MICRODISTRICT && config.isFloor() && config.isHouseFloor()
                && config.isRoomsCount() && !config.isHouseBuildYear() && !config.isHouseType() && !config.isBalcon()
                && !config.isKitchenSquare()) {
            return ERealtyConfigType.COMPLEX;
        }

        if (config.getPlacement() == EPlacement.MICRODISTRICT && !config.isFloor() && !config.isHouseFloor()
                && !config.isRoomsCount() && !config.isHouseBuildYear() && !config.isHouseType() && !config.isBalcon()
                && !config.isKitchenSquare()) {
            return ERealtyConfigType.ONLY_MICRODISTRICT;
        }

        if (config.getPlacement() == EPlacement.DISTRICT && !config.isFloor() && !config.isHouseFloor()
                && !config.isRoomsCount() && !config.isHouseBuildYear() && !config.isHouseType() && !config.isBalcon()
                && !config.isKitchenSquare()) {
            return ERealtyConfigType.ONLY_DISTRICT;
        }

        if (config.getPlacement() == EPlacement.NONE && !config.isFloor() && !config.isHouseFloor()
                && !config.isRoomsCount() && !config.isHouseBuildYear() && !config.isHouseType() && !config.isBalcon()
                && !config.isKitchenSquare()) {
            return ERealtyConfigType.SIMPLE;
        }

        throw new IllegalArgumentException("Данной конфигурации на предусмотрено");
    }

    public ERealtyConfigType fromString(String name) {
        for (ERealtyConfigType val : values) {
            if (val.configName.equals(name)) {
                return val;
            }
        }

        throw new IllegalArgumentException("Значение не найдено " + name);
    }

    public String getConfigName() {
        return configName;
    }
}
