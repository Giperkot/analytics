package converter.realty;

import db.entity.parser.NoticeEntity;
import db.entity.parser.view.VParserNoticeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ParserMapper {

    NoticeEntity toNoticeEntity(VParserNoticeEntity parserNoticeEntity);

}
