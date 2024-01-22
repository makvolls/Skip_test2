package API.DTO.CalcDeadlinesDto;

public class RequestCalcDeadlinesDto {
    public String start_date;
    public int periodicity_id;

    public String getStart_date() {
        return start_date;
    }

    public RequestCalcDeadlinesDto(String start_date, int periodicity_id, int period_in_days) {
        this.start_date = start_date;
        this.periodicity_id = periodicity_id;
        this.period_in_days = period_in_days;
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

    public int getPeriod_in_days() {
        return period_in_days;
    }

    public void setPeriod_in_days(int period_in_days) {
        this.period_in_days = period_in_days;
    }

    public int period_in_days;
}
