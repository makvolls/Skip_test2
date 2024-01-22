package API.DTO.CalcDeadlinesDto;

public class FullRequestCdDto {
    public String type;
    public String start_date;
    public String deadline_date;
    public int periodicity_id;

    public String getType() {
        return type;
    }

    public FullRequestCdDto(String type, String start_date, String deadline_date, int periodicity_id, int period_in_days) {
        this.type = type;
        this.start_date = start_date;
        this.deadline_date = deadline_date;
        this.periodicity_id = periodicity_id;
        this.period_in_days = period_in_days;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getDeadline_date() {
        return deadline_date;
    }

    public void setDeadline_date(String deadline_date) {
        this.deadline_date = deadline_date;
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
