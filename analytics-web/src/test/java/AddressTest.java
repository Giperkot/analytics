import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import core.AnalyticsContext;
import dto.geocode.yandex.CoordinatesDto;
import dto.realty.HouseDto;
import org.junit.Test;
import service.realty.RealtyService;

import javax.servlet.ServletContextEvent;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AddressTest {

    /*protected boolean assert() {

    }*/

    @Test
    public void addressTest() {
        TestServletContext context = new TestServletContext();
        ServletContextEvent event = new ServletContextEvent(context);
        AnalyticsContext analyticsContext = new AnalyticsContext();
        analyticsContext.contextInitialized(event);

        RealtyService realtyService = RealtyService.getInstance();

        HouseDto houseDto = realtyService.getHouseAddr("Пермский край, Пермь, Петропавловская ул., 101, этаж 2", -1);
        assertEquals("ул. Петропавловская, 101", (houseDto.printFullAddr()));
        assertEquals("ул. Петропавловская, 101", realtyService.getHouseAddr("ул. Петропавловская, 101", -1).printFullAddr());
        // System.out.println(houseDto.printFullAddr());

        houseDto = realtyService.getHouseAddr("Пермский край, Пермь, Чердынская ул., 38А, этаж 7", -1);
        assertEquals("ул. Чердынская, 38а", houseDto.printFullAddr());
        assertEquals("ул. Чердынская, 38а", realtyService.getHouseAddr("ул. Чердынская, 38а", -1).printFullAddr());

        houseDto = realtyService.getHouseAddr("Пермский край, Пермь, Гатчинская ул., 20", -1);
        assertEquals("ул. Гатчинская, 20", houseDto.printFullAddr());
        assertEquals("ул. Гатчинская, 20", realtyService.getHouseAddr("ул. Гатчинская, 20", -1).printFullAddr());

        houseDto = realtyService.getHouseAddr("Пермский край, Пермь, Автозаводская ул., 25", -1);
        assertEquals("ул. Автозаводская, 25", houseDto.printFullAddr());
        assertEquals("ул. Автозаводская, 25", realtyService.getHouseAddr("ул. Автозаводская, 25", -1).printFullAddr());

        houseDto = realtyService.getHouseAddr("Пермский край, Пермь, Комсомольский пр-т, 71", -1);
        assertEquals("пр-т Комсомольский, 71", houseDto.printFullAddr());
        assertEquals("пр-т Комсомольский, 71", realtyService.getHouseAddr("пр-т Комсомольский, 71", -1).printFullAddr());

        houseDto = realtyService.getHouseAddr("Пермский край, Пермь, ул. Карпинского, 112/1", -1);
        assertEquals("ул. Карпинского, 112/1", houseDto.printFullAddr());
        assertEquals("ул. Карпинского, 112/1", realtyService.getHouseAddr("ул. Карпинского, 112/1", -1).printFullAddr());

        houseDto = realtyService.getHouseAddr("Пермский край, Пермский р-н, Савинское сельское поселение, д. Песьянка, Молодёжная ул., 9", -1);
        // assertEquals("д. Песьянка, ул. Молодежная, 9", houseDto.printFullAddr());
        assertEquals("ул. Петропавловская, 101", realtyService.getHouseAddr("ул. Петропавловская, 101", -1).printFullAddr());

        houseDto = realtyService.getHouseAddr("Пермский край, Пермский муниципальный округ, пос. Сокол, 2", -1);
        assertEquals("пос. Сокол, 2", houseDto.printFullAddr());
        assertEquals("пос. Сокол, 2", realtyService.getHouseAddr("пос. Сокол, 2", -1).printFullAddr());

        houseDto = realtyService.getHouseAddr("ул. Ветлужская, д. 30", -1);
        assertEquals("ул. Ветлужская, 30", (houseDto.printFullAddr()));
        assertEquals("ул. Ветлужская, 30", realtyService.getHouseAddr("ул. Ветлужская, 30", -1).printFullAddr());

        houseDto = realtyService.getHouseAddr("Москва, Ярославское ш., 116к2", -1);
        assertEquals("ш. Ярославское, 116к2", (houseDto.printFullAddr()));
        assertEquals("ш. Ярославское, 116к2", realtyService.getHouseAddr("ш. Ярославское, 116к2", -1).printFullAddr());

        houseDto = realtyService.getHouseAddr("ул. Красного Маяка, вл. 26", -1);
        assertEquals("ул. Красного Маяка, 26", (houseDto.printFullAddr()));
        assertEquals("ул. Красного Маяка, 26", realtyService.getHouseAddr("ул. Красного Маяка, 26", -1).printFullAddr());

        houseDto = realtyService.getHouseAddr("Москва, Новомосковский административный округ, поселение Сосенское, квартал № 195, 16к1вл1", -1);
        assertEquals("пос. Сосенское, кв-л 195, 16к1вл1", houseDto.printFullAddr());
        assertEquals("пос. Сосенское, кв-л 195, 16к1вл1", realtyService.getHouseAddr("пос. Сосенское, кв-л 195, 16к1вл1", -1).printFullAddr());

        houseDto = realtyService.getHouseAddr("Ветлужская, д. 30", -1);
        assertEquals("ул. Ветлужская, 30", houseDto.printFullAddr());
        assertEquals("ул. Ветлужская, 30", realtyService.getHouseAddr("ул. Ветлужская, 30", -1).printFullAddr());

        houseDto = realtyService.getHouseAddr("Москва, Нижегородская ул., 32с3", -1);
        assertEquals("ул. Нижегородская, 32с3", houseDto.printFullAddr());
        assertEquals("ул. Нижегородская, 32с3", realtyService.getHouseAddr("ул. Нижегородская, 32с3", -1).printFullAddr());

        houseDto = realtyService.getHouseAddr("район Покровское-Стрешнево, жилой комплекс Левел Стрешнево", -1);
        assertEquals("р-н. Покровское-Стрешнево, жк. Левел Стрешнево, null", houseDto.printFullAddr());

        houseDto = realtyService.getHouseAddr("Москва, Новомосковский административный округ, поселение Мосрентген, д. Дудкино, садоводческое некоммерческое товарищество Дары природы, 272", -1);
        assertEquals("д. Дудкино, снт. Дары природы, 272", houseDto.printFullAddr());

        houseDto = realtyService.getHouseAddr("Москва, Юго-Восточный административный округ, район Выхино-Жулебино", -1);
        assertEquals("р-н. Выхино-Жулебино, null", houseDto.printFullAddr());

        houseDto = realtyService.getHouseAddr("Москва, Новомосковский административный округ, поселение Московский, жилой комплекс Филатов Луг, ул. Картмазовские Пруды, 2к2", -1);
        // Москва, Новомосковский административный округ, поселение Московский, жилой комплекс Филатов Луг, ул. Картмазовские Пруды, 2к2
        System.out.println(houseDto.printFullAddr());

        // Москва, Зеленоград, к2032, этаж 2


    }

    public static void main(String[] args) throws IOException {

        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<ymaps xmlns=\"http://maps.yandex.ru/ymaps/1.x\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.opengis.net/gml http://schemas.opengis.net/gml/3.1.1/base/gml.xsd http://maps.yandex.ru/ymaps/1.x https://maps.yandex.ru/schemas/ymaps/1.x/ymaps.xsd http://maps.yandex.ru/geocoder/1.x http://maps.yandex.ru/schemas/geocoder/1.x/geocoder.xsd http://maps.yandex.ru/address/1.x http://maps.yandex.ru/schemas/search/1.x/address.xsd urn:oasis:names:tc:ciq:xsdschema:xAL:2.0 http://docs.oasis-open.org/election/external/xAL.xsd\">\n"
                + "\t<GeoObjectCollection>\n"
                + "\t\t<metaDataProperty xmlns=\"http://www.opengis.net/gml\">\n"
                + "\t\t\t<GeocoderResponseMetaData xmlns=\"http://maps.yandex.ru/geocoder/1.x\">\n"
                + "\t\t\t\t<request>Москва Нижеская ул. 32с3</request>\n"
                + "\t\t\t\t<suggest>Москва Нская ул. 32с3</suggest>\n"
                + "\t\t\t\t<found>1</found>\n"
                + "\t\t\t\t<results>10</results>\n"
                + "\t\t\t</GeocoderResponseMetaData>\n"
                + "\t\t</metaDataProperty>\n"
                + "\t\t<featureMember xmlns=\"http://www.opengis.net/gml\">\n"
                + "\t\t\t<GeoObject xmlns=\"http://maps.yandex.ru/ymaps/1.x\" xmlns:gml=\"http://www.opengis.net/gml\" gml:id=\"1\">\n"
                + "\t\t\t\t<description xmlns=\"http://www.opengis.net/gml\">Москва, Россия</description>\n"
                + "\t\t\t\t<name xmlns=\"http://www.opengis.net/gml\">Нежинская улица, 25</name>\n"
                + "\t\t\t\t<boundedBy xmlns=\"http://www.opengis.net/gml\">\n"
                + "\t\t\t\t\t<Envelope>\n"
                + "\t\t\t\t\t\t<lowerCorner>37.457942 55.706365</lowerCorner>\n"
                + "\t\t\t\t\t\t<upperCorner>37.466152 55.711001</upperCorner>\n"
                + "\t\t\t\t\t</Envelope>\n"
                + "\t\t\t\t</boundedBy>\n"
                + "\t\t\t\t<Point xmlns=\"http://www.opengis.net/gml\">\n"
                + "\t\t\t\t\t<pos>37.462047 55.708683</pos>\n"
                + "\t\t\t\t</Point>\n"
                + "\t\t\t</GeoObject>\n"
                + "\t\t</featureMember>\n"
                + "\t</GeoObjectCollection>\n"
                + "</ymaps>";

        XmlMapper mapper = new XmlMapper();

        CoordinatesDto coordinatesDto = mapper.readValue(str, CoordinatesDto.class);


        // System.out.println(EStreetType.createStringRegexp());
    }
}
