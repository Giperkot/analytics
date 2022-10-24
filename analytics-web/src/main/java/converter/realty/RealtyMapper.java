package converter.realty;

import db.entity.realty.DistrictEntity;
import db.entity.realty.view.VNoticeInfoWithAvgPriceEntity;
import dto.realty.FullDistrictDto;
import dto.realty.VNoticeInfoWithAvgPriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RealtyMapper {

    VNoticeInfoWithAvgPriceDto toNoticeInfoWithAvgPriceDto(VNoticeInfoWithAvgPriceEntity noticeInfoWithAvgPriceEntity);

    @Mapping(source = "parentDistrictId", target = "parentId")
    @Mapping(source = "coords", target = "coords")
    FullDistrictDto toFullDistrictDto(DistrictEntity districtEntity);

    @Mapping(source = "parentId", target = "parentDistrictId")
    DistrictEntity toDistrictEntity(FullDistrictDto districtDto);
}
