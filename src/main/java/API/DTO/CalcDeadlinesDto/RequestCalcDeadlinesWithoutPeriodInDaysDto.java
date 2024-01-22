package API.DTO.CalcDeadlinesDto;

public class RequestCalcDeadlinesWithoutPeriodInDaysDto {
    public String start_date;

    public String getStart_date() {
        return start_date;
    }

    public RequestCalcDeadlinesWithoutPeriodInDaysDto(String start_date, int periodicity_id) {
        this.start_date = start_date;
        this.periodicity_id = periodicity_id;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public int getPeriodicity_id() {
        return periodicity_id;
    }

    public void setPeriodicity_id(int periodicity_id) {
        this.periodicity_id = periodicity_id;
    }

    public int periodicity_id;
}
