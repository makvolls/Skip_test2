package DTO.EventStatesDto;

import DTO.ThemesDto.DataElementThemes;

public class RootElementEventStates {
    public DataElementThemes getData() {
        return data;
    }

    public void setData(DataElementThemes data) {
        this.data = data;
    }

    public RootElementEventStates(DataElementThemes data) {
        this.data = data;
    }

    public DataElementThemes data;
}
