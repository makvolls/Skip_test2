package DTO.EventStatesDto;

public class RootEventStates {
    public DatumEventStates[] getData() {
        return data;
    }

    public void setData(DatumEventStates[] data) {
        this.data = data;
    }

    public RootEventStates(DatumEventStates[] data) {
        this.data = data;
    }

    public DatumEventStates[] data;
}
