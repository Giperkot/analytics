package service.realty.catalog;

import db.entity.realty.HouseAddInfoEntity;
import db.entity.realty.HouseEntity;
import dto.realty.giszkh.HouseInfoResponseDto;
import dto.realty.giszkh.HouseResponseDtoDto;
import dto.realty.giszkh.ItemDto;
import dto.realty.giszkh.StreetResponseDtoDto;
import dto.realty.giszkh.finalres.HouseFinalInfoDto;
import dto.report.NoticeWrapper;
import enums.report.EHouseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.giszkh.GisZkhRepository;
import service.report.ReportService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class GisZkhService extends AbstractCatalogService implements IRealtyCatalogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GisZkhService.class);
    private static final GisZkhService instance = new GisZkhService();

    public static GisZkhService getInstance() {
        return instance;
    }

    private final GisZkhRepository gisZkhRepository = GisZkhRepository.getInstance();

    private final ReportService reportService = ReportService.getInstance();

    private GisZkhService() {
    }

    private int getFloorsCount(String maxFloorsCount) {
        int minusIdx = maxFloorsCount.indexOf("-");

        if (minusIdx > -1) {
            maxFloorsCount = maxFloorsCount.substring(minusIdx + 1);
        }

        try {
            return Integer.parseInt(maxFloorsCount);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private String prepareStreet(String street) {
        if (street.contains("ё")) {
            street = street.replace('ё', 'е');
        }

        return street;
    }

    private void fillHouseInfoByNotices(Connection connection, HouseAddInfoEntity houseAddInfoEntity) {
        List<NoticeWrapper> noticeWrapperList = reportService.getQllNoticeByHouse(connection, houseAddInfoEntity.getHouseId());

        if (houseAddInfoEntity.getHouseType() == null) {
            houseAddInfoEntity.setHouseType(EHouseType.UNKNOWN);
        }

        EHouseType finalHouseType = null;
        int maxTypeInDb = 0;
        int maxFloorsCount = 0;
        int[] houseTypePop = new int[EHouseType.getValues().length];
        houseTypePop[houseAddInfoEntity.getHouseType().getId()] += 2;

        for (NoticeWrapper noticeWrapper : noticeWrapperList) {
            EHouseType houseType = noticeWrapper.getHouseType();
            int floorsCouknt = noticeWrapper.getFloorsCount();

            /*if (finalHouseType == null) {
                finalHouseType = houseType;
            }*/

            houseTypePop[houseType.getId()] += 1;

            /*if (finalHouseType != houseType) {
                throw new RuntimeException("Тип дома не сходится по объявлениям: " + houseType + " " + finalHouseType);
            }*/

            if (maxFloorsCount < floorsCouknt) {
                maxFloorsCount = floorsCouknt;
            }
        }

        for (int i = 0; i < houseTypePop.length; i++) {
            if (maxTypeInDb < houseTypePop[i]) {
                maxTypeInDb = houseTypePop[i];
                finalHouseType = EHouseType.getByOrdinal(i);
            }
        }

        if (finalHouseType != EHouseType.UNKNOWN) {
            houseAddInfoEntity.setHouseType(finalHouseType);
        }

        houseAddInfoEntity.setFloorsCount(maxFloorsCount);
    }

    private StreetResponseDtoDto chooseStreet(StreetResponseDtoDto[] streetResponseDtoArr, String streetName) {
        String lowerStreetname = streetName.toLowerCase();

        Arrays.sort(streetResponseDtoArr, (a, b) -> {
            return a.getFormalName().length() - b.getFormalName().length();
        });

        for (int i = 0; i < streetResponseDtoArr.length; i++) {
            StreetResponseDtoDto streetResponseDtoDto = streetResponseDtoArr[i];

            if (!"ул".equals(streetResponseDtoDto.getShortName()) &&
                    !"пр-кт".equals(streetResponseDtoDto.getShortName()) &&
                    !"б-р".equals(streetResponseDtoDto.getShortName())) {
                continue;
            }

            if (!streetResponseDtoDto.getFormalName().toLowerCase().contains(lowerStreetname)) {
                continue;
            }

            return streetResponseDtoDto;
        }

        return streetResponseDtoArr[0];
    }

    @Override
    public void init() {

    }

    public HouseAddInfoEntity fillHouseYearInfo(HouseEntity houseEntity, Connection connection) throws InterruptedException, IOException, URISyntaxException {

        String preparedStreet = prepareStreet(houseEntity.getStreet());
        // Получить улицу и дом из ГисЖкх
        StreetResponseDtoDto[] streetResponseDtoArr = gisZkhRepository.getStreetInfo(preparedStreet);

        if (streetResponseDtoArr.length < 1) {
            throw new RuntimeException("Отсутствует улица в БД гис жкх " + houseEntity.getStreet());
        }

        StreetResponseDtoDto streetResponseDto = chooseStreet(streetResponseDtoArr, preparedStreet);

        // Сделать запрос на информацию о доме.
        HouseResponseDtoDto[] houseResponseDtoDtos = gisZkhRepository.getHouseUUID(streetResponseDto.getAoGuid(), houseEntity.getHouseNum());

        if (houseResponseDtoDtos.length < 1) {
            throw new RuntimeException("Отсутствует дом в БД гис жкх " + houseEntity.getStreet() + " " + houseEntity.getHouseNum());
        }

        HouseResponseDtoDto houseResponseDtoDto = houseResponseDtoDtos[0];


        HouseInfoResponseDto houseInfoResponseDto = gisZkhRepository.getFinalHouseInfo(houseResponseDtoDto.getActualHouseGuid());

        List<ItemDto> itemDtoList = houseInfoResponseDto.getItems();

        if (itemDtoList.isEmpty()) {
            throw new RuntimeException("Отсутствует подробная информация по дому " + houseEntity.getId() + " " + houseEntity.getStreet() + " " + houseEntity.getHouseNum());
        }

        ItemDto houseItemDto = itemDtoList.get(0);

        // Теперь финальная информация.

        HouseFinalInfoDto houseFinalInfoDto = gisZkhRepository.houseDetailsByUUID(houseItemDto.getGuid());

        // int buildingYear = Integer.parseInt(houseFinalInfoDto.getBuildingYear());
        int buildingYear = houseFinalInfoDto.getOperationYear() != null ? houseFinalInfoDto.getOperationYear()
                : Integer.parseInt(houseFinalInfoDto.getBuildingYear());
        Integer deterioration = (houseFinalInfoDto.getDeterioration() == null) ? null :
                (int)Double.parseDouble(houseFinalInfoDto.getDeterioration());
        String wallMaterial = houseFinalInfoDto.getIntWallMaterialList();
        int floorsCount = getFloorsCount(houseItemDto.getMaxFloorCount());

        HouseAddInfoEntity houseAddInfoEntity = createHouseAddinfoEntity(houseEntity.getId(), buildingYear, deterioration,
                wallMaterial, floorsCount);

        // Тип дома и этажность возьмём с Авито. Ибо на ГисЖКХ чёт не так.
        fillHouseInfoByNotices(connection, houseAddInfoEntity);

        return houseAddInfoEntity;
    }

}
