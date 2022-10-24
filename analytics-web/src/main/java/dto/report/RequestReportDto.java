package dto.report;

import enums.report.ERealtyConfigType;

import java.time.LocalDate;

public class RequestReportDto {

    // private LocalDate startDay;
    //
    // private LocalDate endDay;

    private long cityId;

    private ERealtyConfigType configType;

/*    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }*/

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public ERealtyConfigType getConfigType() {
        return configType;
    }

    public void setConfigType(ERealtyConfigType configType) {
        this.configType = configType;
    }
}
