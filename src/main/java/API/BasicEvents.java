package API;

import API.DTO.ErrorsDTO.EventsErrorsDto.RootErrorESI;
import API.DTO.ErrorsDTO.EventsErrorsDto.RootEventStateError;
import API.DTO.ErrorsDTO.EventsErrorsDto.RootNoEventsStatesErrorDto;
import API.DTO.ErrorsDTO.RootErrorNameShortName;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import API.DTO.EventStatesDto.CreateValueClassEventStates;
import API.DTO.EventStatesDto.EventStateDto;
import API.DTO.EventStatesDto.RootElementEventStates;
import API.DTO.EventsDto.RequestEventsDto;
import API.DTO.EventsDto.RootEventsDto;
import API.DTO.EventsDto.RootListDto;

import static io.restassured.RestAssured.given;

public class BasicEvents {
    public static String API_EVENT = "http://api.skip.rtech.ru/v1/classifiers/events";


    /**
     * Получение списка событий
     *
     * @param id - id авторизованного пользователя
     */
    public static RootListDto getListEvents(int id){
        RootListDto actualList = BasicApi.get(API_EVENT, id).as(RootListDto.class);
        return actualList;
    }

    /**
     * Создание события
     *
     */

    public static RootEventsDto createEvents(int id, String name, String short_name, boolean excluded,
                                             Object... event_state_ids) {
        RequestEventsDto value = new RequestEventsDto(name,short_name, excluded, event_state_ids);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        value.setEvent_state_ids(event_state_ids);
        RootEventsDto createdValue = BasicApi.post(API_EVENT, id, value).body().as(RootEventsDto.class);
        return createdValue;
    }
    public static RootNameError createErrNameEvents(int id, String name, String short_name, boolean excluded,
                                             Object... event_state_ids) {
        RequestEventsDto value = new RequestEventsDto(name,short_name, excluded, event_state_ids);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        value.setEvent_state_ids(event_state_ids);
        RootNameError createdErValue = BasicApi.postError(API_EVENT, id, value).body().as(RootNameError.class);
        return createdErValue;
    }

    public static RootErrorESI createErrESI(int id, String name, String short_name, boolean excluded,
                                                   Object... event_state_ids) {
        RequestEventsDto value = new RequestEventsDto(name,short_name, excluded, event_state_ids);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        value.setEvent_state_ids(event_state_ids);
        RootErrorESI createdErValue = BasicApi.postError(API_EVENT, id, value).body().as(RootErrorESI.class);
        return createdErValue;
    }



    public static RootErrorNameShortName createErrNameShortNameEvents(int id, String name, String short_name, boolean excluded,
                                                                   Object... event_state_ids) {
        RequestEventsDto value = new RequestEventsDto(name,short_name, excluded, event_state_ids);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        value.setEvent_state_ids(event_state_ids);
        RootErrorNameShortName createdErrValue = BasicApi.postError(API_EVENT, id, value).body().as(RootErrorNameShortName.class);
        return createdErrValue;
    }
    public static RootEventStateError createErrEventsNoId(int id, String name, String short_name, boolean excluded,
                                                         Object... event_state_ids) {
        RequestEventsDto value = new RequestEventsDto(name,short_name, excluded, event_state_ids);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        value.setEvent_state_ids(event_state_ids);
        RootEventStateError createdErrValue = BasicApi.postError(API_EVENT, id, value).body().as(RootEventStateError.class);
        return createdErrValue;
    }

    public static RootErrorNoRights createErrNoRightsEvents(int id, String name, String short_name, boolean excluded,
                                                       Object... event_state_ids) {
        RequestEventsDto value = new RequestEventsDto(name,short_name, excluded, event_state_ids);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        value.setEvent_state_ids(event_state_ids);
        RootErrorNoRights createdErrValue = BasicApi.postErrorNoRights(API_EVENT, id, value).body().as(RootErrorNoRights.class);
        return createdErrValue;
    }
    public static void deleteEvents(int id, int idEvents) {
        BasicApi.delete(API_EVENT + "/" + idEvents, id);
        return;
    }

    public static RootEventsDto updateEvents(int id,int idEvents, String name, String short_name, boolean excluded,
                                             Object... event_state_ids){
        RequestEventsDto value = new RequestEventsDto(name,short_name, excluded, event_state_ids);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        value.setEvent_state_ids(event_state_ids);
        RootEventsDto updateEvents = BasicApi.put(API_EVENT+"/"+idEvents,id,value).body().as(RootEventsDto.class);
        return updateEvents;
    }

    public static RootNameError updateErrNameEvents(int id,int idEvents, String name, String short_name, boolean excluded,
                                                    Object... event_state_ids) {
        RequestEventsDto value = new RequestEventsDto(name,short_name, excluded, event_state_ids);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        value.setEvent_state_ids(event_state_ids);
        RootNameError updateErValue = BasicApi.putErrors(API_EVENT+"/"+idEvents,id,value).body().as(RootNameError.class);
        return updateErValue;
    }
    public static RootErrorNoRights updateErrNoRightsEvents(int id,int idEvents, String name, String short_name, boolean excluded,
                                                            Object... event_state_ids) {
        RequestEventsDto value = new RequestEventsDto(name,short_name, excluded, event_state_ids);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        value.setEvent_state_ids(event_state_ids);
        RootErrorNoRights createdErrValue = BasicApi.putErrorsNoRights(API_EVENT+"/"+idEvents,id,value).body().as(RootErrorNoRights.class);
        return createdErrValue;
    }

    public static RootEventStateError updateErrEventsNoId(int id,int idEvents, String name, String short_name, boolean excluded,
                                                          Object... event_state_ids) {
        RequestEventsDto value = new RequestEventsDto(name,short_name, excluded, event_state_ids);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        value.setEvent_state_ids(event_state_ids);
        RootEventStateError createdErrValue = BasicApi.putErrors(API_EVENT+"/"+idEvents,id,value).body().as(RootEventStateError.class);
        return createdErrValue;
    }

    public static RootNoEventsStatesErrorDto noEventsStatesError(int id, int idEvents, String name, String short_name, boolean excluded,
                                                                 Object... event_state_ids) {
        RequestEventsDto value = new RequestEventsDto(name,short_name, excluded, event_state_ids);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        value.setEvent_state_ids(event_state_ids);
        RootNoEventsStatesErrorDto createdNoEvents = BasicApi.putErrorsNoEventStatesId(API_EVENT+"/"+idEvents,id,value)
                .as(RootNoEventsStatesErrorDto.class);
        return createdNoEvents;
    }

}
