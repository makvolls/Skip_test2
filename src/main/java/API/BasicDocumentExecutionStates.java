package API;

import API.DTO.DocumentExecutionStatesDto.CreateValueDocumentExecutionStates;
import API.DTO.DocumentExecutionStatesDto.RootDocumentExecutionStates;
import API.DTO.DocumentExecutionStatesDto.RootElementDocumentExecutionStates;

public class BasicDocumentExecutionStates {
    public static String API_DOCUMENT_EXECUTION_STATES = "http://api.skip.rtech.ru/v1/classifiers/document_execution_states";

    /**
     * Справочник "Состояние исполнения документа"
     * **/

    /**
     * Получение значений справочника
     * @param id - id авторизуемого пользователя
     * **/

    public static RootDocumentExecutionStates getDocumentExecutionStatesList(int id){
        RootDocumentExecutionStates actualDocumentExecutionStates= BasicApi.get(API_DOCUMENT_EXECUTION_STATES, id)
                .body().as(RootDocumentExecutionStates.class);
        return actualDocumentExecutionStates;
    }

    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param short_name - Краткое наименование справочника
     * @param excluded - элемент исключения значения из справочника
     */

    public static RootElementDocumentExecutionStates createDocumentExecutionStates(int id, String name, String short_name,
                                                                                 boolean excluded) {
        CreateValueDocumentExecutionStates value = new CreateValueDocumentExecutionStates(name, short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootElementDocumentExecutionStates createdValue = BasicApi.post(API_DOCUMENT_EXECUTION_STATES, id, value)
                .body().as(RootElementDocumentExecutionStates.class);
        return createdValue;
    }

    /**
     * Изменение значения справочника
     *
     * @param id - id авторизованного пользователя
     * @param idValue - id значения , в котором изменяется значение
     * @param name - Название справочника
     * @param short_name - Краткое название справочника
     * @param excluded - элемент исключения значения из справочника
     * **/

    public static RootElementDocumentExecutionStates updateDocumentExecutionStatesValue(int id, int idValue, String name,
                                                                                 String short_name, boolean excluded){
        CreateValueDocumentExecutionStates value = new CreateValueDocumentExecutionStates(name, short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootElementDocumentExecutionStates newValue = BasicApi.put(API_DOCUMENT_EXECUTION_STATES + "/" + idValue,
                id, value).body().as(RootElementDocumentExecutionStates.class);
        return newValue;
    }
    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    public static void deleteDocumentExecutionStatesValue(int id, int idValue){
        BasicApi.delete(API_DOCUMENT_EXECUTION_STATES + "/" + idValue, id);
        return;
    }


}

