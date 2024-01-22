package API.DTO.ErrorsDTO.CalcDeadlinesErrors;

public class DatumPeriodicityIdPeriodInDaysError {
    public String[] periodicity_id;
    public String[] period_in_days;

    public String[] getPeriodicity_id() {
        return periodicity_id;
    }

    public DatumPeriodicityIdPeriodInDaysError(String[] periodicity_id, String[] period_in_days) {
        this.periodicity_id = periodicity_id;
        this.period_in_days = period_in_days;
    }

    public void setPeriodicity_id(String[] periodicity_id) {
        this.periodicity_id = periodicity_id;
    }

    public String[] getPeriod_in_days() {
        return period_in_days;
    }

    public void setPeriod_in_days(String[] period_in_days) {
        this.period_in_days = period_in_days;
    }
}
