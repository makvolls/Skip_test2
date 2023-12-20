package API;

import API.DTO.EventStatesDto.CreateValueClassEventStates;
import API.DTO.EventStatesDto.RootElementEventStates;
import API.DTO.EventStatesDto.RootEventStates;

public class BasicEventStates {
    public static String API_EVENT_STATES = "http://api.skip.rtech.ru/v1/classifiers/event_states";

    /**
     * Справочник "Состояние события"
     * **/

    /**
     * Получение значений справочника
     *
     * @param id - id авторизуемого пользователя
     **/

    public static RootEventStates getEventStatesList(int id) {
        RootEventStates actualEventStates = BasicApi.get(API_EVENT_STATES, id).body().as(RootEventStates.class);
        return actualEventStates;
    }

    /**
     * Создание значение справочника
     *
     * @param id       - id авторизованного пользователя
     * @param name     - Название справочника
     * @param excluded - элемент исключения значения из справочника
     */

    public static RootElementEventStates createEventStates(int id, String name, boolean excluded) {
        CreateValueClassEventStates value = new CreateValueClassEventStates(name, excluded);
        value.setName(name);
        value.setExcluded(excluded);
        RootElementEventStates createdValue = BasicApi.post(API_EVENT_STATES, id, value).body().as(RootElementEventStates.class);
        return createdValue;
    }

    /**
     * Изменение значения справочника
     *
     * @param id       - id авторизованного пользователя
     * @param idValue  - id значения , в котором изменяется значение
     * @param name     - Название справочника
     * @param excluded - элемент исключения значения из справочника
     **/

    public static RootElementEventStates updateEventStates(int id, int idValue, String name, boolean excluded) {
        CreateValueClassEventStates value = new CreateValueClassEventStates(name, excluded);
        value.setName(name);
        value.setExcluded(excluded);
        RootElementEventStates newValue = BasicApi.put(API_EVENT_STATES + "/" + idValue, id, value).body().as(RootElementEventStates.class);
        return newValue;

    }
    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    public static void deleteEventStateValue(int id, int idValue){
        BasicApi.delete(API_EVENT_STATES + "/" + idValue, id);
        return;
    }
}
