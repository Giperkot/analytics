import core.AnalyticsContext;
import dto.realty.HouseDto;
import enums.realty.EStreetType;
import service.realty.RealtyService;

import javax.servlet.ServletContextEvent;

public class AddressTest {

    public static void main(String[] args) {
        TestServletContext context = new TestServletContext();
        ServletContextEvent event = new ServletContextEvent(context);
        AnalyticsContext analyticsContext = new AnalyticsContext();
        analyticsContext.contextInitialized(event);

        RealtyService realtyService = RealtyService.getInstance();

        HouseDto houseDto = realtyService.getHouseAddr("Пермский край, Пермь, Петропавловская ул., 101, этаж 2", -1);
        System.out.println(houseDto.getStreet() + " | " + houseDto.getHouseNum());

        houseDto = realtyService.getHouseAddr("Пермский край, Пермь, Чердынская ул., 38А, этаж 7", -1);
        System.out.println(houseDto.getStreet() + " | " + houseDto.getHouseNum());

        houseDto = realtyService.getHouseAddr("Пермский край, Пермь, Гатчинская ул., 20", -1);
        System.out.println(houseDto.getStreet() + " | " + houseDto.getHouseNum());

        houseDto = realtyService.getHouseAddr("Пермский край, Пермь, Автозаводская ул., 25", -1);
        System.out.println(houseDto.getStreet() + " | " + houseDto.getHouseNum());

        houseDto = realtyService.getHouseAddr("Пермский край, Пермь, Комсомольский пр-т, 71", -1);
        System.out.println(houseDto.getStreet() + " | " + houseDto.getHouseNum());

        houseDto = realtyService.getHouseAddr("Пермский край, Пермь, ул. Карпинского, 112/1", -1);
        System.out.println(houseDto.getStreet() + " | " + houseDto.getHouseNum());

        houseDto = realtyService.getHouseAddr("Пермский край, Пермский р-н, Савинское сельское поселение, д. Песьянка, Молодёжная ул., 9", -1);
        System.out.println(houseDto.getStreet() + " | " + houseDto.getHouseNum());

        houseDto = realtyService.getHouseAddr("Пермский край, Пермский муниципальный округ, пос. Сокол, 2", -1);
        System.out.println(houseDto.getStreet() + " | " + houseDto.getHouseNum());

        houseDto = realtyService.getHouseAddr("ул. Ветлужская, д. 30", -1);
        System.out.println(houseDto.getStreet() + " | " + houseDto.getHouseNum());
        houseDto = realtyService.getHouseAddr("Москва, Ярославское ш., 116к2", -1);
        System.out.println(houseDto.getStreet() + " | " + houseDto.getHouseNum());
        houseDto = realtyService.getHouseAddr("ул. Красного Маяка, вл. 26", -1);
        System.out.println(houseDto.getStreet() + " | " + houseDto.getHouseNum());

        System.out.println(EStreetType.createStringRegexp());
    }
}
