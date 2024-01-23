package API;

import API.DTO.DocumentExecutionStatesDto.CreateValueDocumentExecutionStates;
import API.DTO.DocumentExecutionStatesDto.RequestNameShortNameDESDto;
import API.DTO.DocumentExecutionStatesDto.RootDocumentExecutionStates;
import API.DTO.DocumentExecutionStatesDto.RootElementDocumentExecutionStates;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;
import API.DTO.ErrorsDTO.RootShortNameError;

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
    public static RootElementDocumentExecutionStates updateDocumentExecutionStatesNameShortName(int id, int idValue, String name,
                                                                                        String short_name){
        RequestNameShortNameDESDto value = new RequestNameShortNameDESDto(name, short_name);
        value.setName(name);
        value.setShort_name(short_name);
        RootElementDocumentExecutionStates newValue = BasicApi.put(API_DOCUMENT_EXECUTION_STATES + "/" + idValue,
                id, value).body().as(RootElementDocumentExecutionStates.class);
        return newValue;
    }

    public static RootNameError updateDocumentExecutionStatesValueErrorName(int id, int idValue, String name,
                                                                            String short_name, boolean excluded){
        CreateValueDocumentExecutionStates value = new CreateValueDocumentExecutionStates(name, short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootNameError newValue = BasicApi.putErrors(API_DOCUMENT_EXECUTION_STATES + "/" + idValue,
                id, value).body().as(RootNameError.class);
        return newValue;
    }

    public static RootShortNameError updateDocumentExecutionStatesValueErrorShortName(int id, int idValue, String name,
                                                                                 String short_name, boolean excluded){
        CreateValueDocumentExecutionStates value = new CreateValueDocumentExecutionStates(name, short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootShortNameError newValue = BasicApi.putErrors(API_DOCUMENT_EXECUTION_STATES + "/" + idValue,
                id, value).body().as(RootShortNameError.class);
        return newValue;
    }

    public static RootErrorNoRights updateDocumentExecutionStatesValueErrorNoRights(int id, int idValue, String name,
                                                                                    String short_name, boolean excluded){
        CreateValueDocumentExecutionStates value = new CreateValueDocumentExecutionStates(name, short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootErrorNoRights newValue = BasicApi.putErrorsNoRights(API_DOCUMENT_EXECUTION_STATES + "/" + idValue,
                id, value).body().as(RootErrorNoRights.class);
        return newValue;
    }

    public static RootErrorNoRights updateDocumentExecutionStatesValueErrorNoRightsWithOutExcluded(int id, int idValue, String name,
                                                                                    String short_name){
        RequestNameShortNameDESDto value = new RequestNameShortNameDESDto(name, short_name);
        value.setName(name);
        value.setShort_name(short_name);
        RootErrorNoRights newValue = BasicApi.putErrorsNoRights(API_DOCUMENT_EXECUTION_STATES + "/" + idValue,
                id, value).body().as(RootErrorNoRights.class);
        return newValue;
    }

    public static RootElementDocumentExecutionStates updateDocumentExecutionStatesWithoutBody(int id,int idValue){
        RootElementDocumentExecutionStates actualDocumentExecutionStates= BasicApi.putNoBody(API_DOCUMENT_EXECUTION_STATES+ "/" + idValue,
                        id)
                .body().as(RootElementDocumentExecutionStates.class);
        return actualDocumentExecutionStates;
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

