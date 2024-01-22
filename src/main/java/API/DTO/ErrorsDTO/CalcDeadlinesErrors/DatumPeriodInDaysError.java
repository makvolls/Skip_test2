package API.DTO.ErrorsDTO.CalcDeadlinesErrors;

public class DatumPeriodInDaysError {
    public DatumPeriodInDaysError(String[] period_in_days) {
        this.period_in_days = period_in_days;
    }

    public String[] getPeriod_in_days() {
        return period_in_days;
    }

    public void setPeriod_in_days(String[] period_in_days) {
        this.period_in_days = period_in_days;
    }

    public String[] period_in_days;
}
