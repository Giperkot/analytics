package converter.realty;

import db.entity.realty.excelimport.ImportRealtyObjectEntity;
import dto.realty.excelimport.ImportExcelRealtyDto;
import org.mapstruct.Mapper;

@Mapper
public interface ImportMapper {

    ImportRealtyObjectEntity toImportRealtyObjectEntity(ImportExcelRealtyDto importExcelRealtyDto);

    ImportExcelRealtyDto toImportExcelRealtyDto(ImportRealtyObjectEntity entity);

}
