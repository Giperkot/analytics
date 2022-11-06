package converter.realty;

import db.entity.realty.HouseEntity;
import db.entity.realty.NoticeCategoryEntity;
import db.entity.realty.excelimport.ImportRealtyObjectEntity;
import dto.geocode.yandex.BoundedByDto;
import dto.geocode.yandex.GeoObject;
import dto.geocode.yandex.PointDto;
import dto.realty.BoundsDto;
import dto.realty.CoordPoint;
import dto.realty.HouseCoords;
import dto.realty.HouseDto;
import dto.realty.excelimport.ImportExcelRealtyDto;
import dto.report.NoticeWrapper;
import enums.report.EFloor;
import enums.report.EHouseFloor;
import enums.report.EKitchenArea;
import enums.report.EMetroDistance;
import enums.report.ETotalArea;
import helper.report.param.*;

public class RealtyConverter {
    private static final RealtyConverter instance = new RealtyConverter();

    public static RealtyConverter getInstance() {
        return instance;
    }

    private RealtyConverter() {
    }

    private CoordPoint toCoordPoint(String position) {
        int spacePos = position.indexOf(" ");

        if (spacePos < 0) {
            throw new RuntimeException("Непредвиденный формат координат");
        }

        double longitude = Double.parseDouble(position.substring(0, spacePos));
        double latitude = Double.parseDouble(position.substring(spacePos + 1));

        return new CoordPoint(longitude, latitude);
    }

    private BoundsDto toBoundsDto(BoundedByDto boundedBy) {
        BoundsDto bound = new BoundsDto();
        String lowerCorner = boundedBy.getEnvelope().getLowerCorner();
        String upperCorner = boundedBy.getEnvelope().getUpperCorner();

        bound.setLower(toCoordPoint(lowerCorner));
        bound.setUpper(toCoordPoint(upperCorner));

        return bound;
    }

    private CoordPoint toCoordPoint(PointDto point) {
        String position = point.getPos();

        return toCoordPoint(position);
    }

    public HouseCoords toHouseCoords(GeoObject geoObject) {
        HouseCoords result = new HouseCoords();

        PointDto point = geoObject.getPoint();
        BoundedByDto boundedBy = geoObject.getBoundedBy();

        result.setCenter(toCoordPoint(point));
        result.setBound(toBoundsDto(boundedBy));

        return result;
    }

    public HouseEntity createHouseEntity(HouseDto houseDto, long cityId, long districtId, HouseCoords houseCoords) {
        HouseEntity houseEntity = new HouseEntity();
        houseEntity.setHouseNum(houseDto.getHouseNum());
        houseEntity.setCityId(cityId);
        houseEntity.setStreet(houseDto.printStreetAddr());
        houseEntity.setDistrictId(districtId);
        houseEntity.setCoords(houseCoords);

        return houseEntity;
    }

    public NoticeCategoryEntity toNoticeCategoryEntity(ImportRealtyObjectEntity importRealtyObjectEntity) {

        NoticeCategoryEntity noticeCategoryEntity = new NoticeCategoryEntity();

        noticeCategoryEntity.setRoomsCount(importRealtyObjectEntity.getRoomsCount());
        noticeCategoryEntity.setFloor(
                EFloor.getByFloorAndHouseFloor(importRealtyObjectEntity.getFloor(), importRealtyObjectEntity.getHouseFloorsCount()));
        noticeCategoryEntity.setHouseFloor(EHouseFloor.getByHouseFloor(importRealtyObjectEntity.getHouseFloorsCount()));
        // noticeCategoryEntity.setHouseType();
        noticeCategoryEntity.setBalcon(importRealtyObjectEntity.getBalcon());
        //
        noticeCategoryEntity.setRealtySegment(importRealtyObjectEntity.getRealtySegment());
        noticeCategoryEntity.setRepairType(importRealtyObjectEntity.getRepairType());
        noticeCategoryEntity.setSimpleHouseType(importRealtyObjectEntity.getWallMaterial());
        noticeCategoryEntity.setTotalArea(ETotalArea.getByArea(importRealtyObjectEntity.getTotalArea()));
        noticeCategoryEntity.setKitchenArea(EKitchenArea.getByArea(importRealtyObjectEntity.getKitchenArea()));
        noticeCategoryEntity.setMetroDistance(EMetroDistance.fromDistance(importRealtyObjectEntity.getMetroDistance()));

        return noticeCategoryEntity;

    }

    public ImportExcelRealtyDto toAdjustmentImportRealtyDto(NoticeWrapper noticeWrapper) {
        ImportExcelRealtyDto result = new ImportExcelRealtyDto();

        result.setId(noticeWrapper.getNoticeEntity().getId());
        result.setAddress(noticeWrapper.getNoticeEntity().getAddress());
        result.setRoomsCount(RoomsCountClassParam.getInstance().getRoomsCount(noticeWrapper).getName());
        result.setRealtySegment(RealtySegmentParam.getInstance().getRealtySegment(noticeWrapper));
        result.setHouseFloorsCount(HouseFloorClassParam.getInstance().getFloorValue(noticeWrapper));
        result.setWallMaterial(SimpleHouseTypeParam.getInstance().getSimpleHouseType(noticeWrapper));
        result.setFloor(FloorClassParam.getInstance().getFloor(noticeWrapper));
        result.setTotalArea(TotalAreaClassParam.getInstance().getTotalArea(noticeWrapper));
        result.setKitchenArea(KitchenAreaClassParam.getInstance().getKitchenArea(noticeWrapper));
        result.setBalcon(BalconClassParam.getInstance().getBalcon(noticeWrapper));
        result.setMetroDistance(MetroDistanceClassParam.getInstance().getMetroDistance(noticeWrapper).getDistanceExample());
        result.setRepairType(RepairTypeClassParam.getInstance().getRepairType(noticeWrapper));
        result.setSum(noticeWrapper.getNoticeEntity().getSum());

        return result;
    }

}
