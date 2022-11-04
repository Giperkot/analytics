package converter.realty;

import db.entity.realty.excelimport.ImportRealtyObjectEntity;
import dto.realty.excelimport.ImportExcelRealtyDto;
import enums.report.ERoomsCount;
import org.mapstruct.Mapper;

@Mapper
public interface ImportMapper {

    default ERoomsCount strToRoomsCount(String roomsCountStr) {
        return ERoomsCount.getByRoomsCount(roomsCountStr);
    }

    abstract ImportRealtyObjectEntity toImportRealtyObjectEntity(ImportExcelRealtyDto importExcelRealtyDto);

    abstract ImportExcelRealtyDto toImportExcelRealtyDto(ImportRealtyObjectEntity entity);

}
