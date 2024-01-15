package API;

import API.BasicApi;
import API.DTO.AssignmentExecutionStatesDto.CreateValueAssignmentExecutionStates;
import API.DTO.AssignmentExecutionStatesDto.CreateValueAssignmentExecutionStatesName;
import API.DTO.AssignmentExecutionStatesDto.RootAssignmentExecutionStates;
import API.DTO.AssignmentExecutionStatesDto.RootElementAssignmentExecutionStates;
import API.DTO.ErrorsDTO.RootErrorNameShortName;
import API.DTO.ErrorsDTO.RootErrorNoRights;
import API.DTO.ErrorsDTO.RootNameError;

public class BasicAssignmentExecutionStates {
    public static String API_ASSIGNMENT_EXECUTION_STATES = "http://api.skip.rtech.ru/v1/classifiers/assignment_execution_states";


    /**
     * Справочник "Состояние исполнения поручения"
     * "**/

    /**
     * Получение значений справочника
     * @param id - id авторизуемого пользователя
     * **/

    public static RootAssignmentExecutionStates getAssignmentExecutionStatesList(int id){
        RootAssignmentExecutionStates actualAssignmentExecutionStates= BasicApi.get(API_ASSIGNMENT_EXECUTION_STATES,
                id).body().as(RootAssignmentExecutionStates.class);
        return actualAssignmentExecutionStates;
    }

    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param short_name - Краткое наименование справочника
     * @param excluded - элемент исключения значения из справочника
     */

    public static RootElementAssignmentExecutionStates createValueAssignmentExecutionStates(int id, String name,
                                                                                     String short_name,
                                                                                     boolean excluded) {
        CreateValueAssignmentExecutionStates value = new CreateValueAssignmentExecutionStates(name, short_name,
                excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootElementAssignmentExecutionStates createdValue = BasicApi.post(API_ASSIGNMENT_EXECUTION_STATES, id, value).
                body().as(RootElementAssignmentExecutionStates.class);
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

    public static RootElementAssignmentExecutionStates updateAssignmentExecutionStatesValue(int id, int idValue, String name,
                                                                                     String short_name,
                                                                                     boolean excluded){
        CreateValueAssignmentExecutionStates value = new CreateValueAssignmentExecutionStates(name, short_name,
                excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootElementAssignmentExecutionStates newValue = BasicApi.put(API_ASSIGNMENT_EXECUTION_STATES + "/"
                + idValue, id, value).body().as(RootElementAssignmentExecutionStates.class);
        return newValue;
    }

    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    public static void deleteAssignmentExecutionStatesValue(int id, int idValue){
        BasicApi.delete(API_ASSIGNMENT_EXECUTION_STATES + "/" + idValue, id);
        return;
    }

    public static RootNameError createValueErrorNameAssignmentExecutionStates(int id, String name,
                                                                     String short_name,
                                                                     boolean excluded) {
        CreateValueAssignmentExecutionStates value = new CreateValueAssignmentExecutionStates(name, short_name,
                excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootNameError createdValue = BasicApi.postError(API_ASSIGNMENT_EXECUTION_STATES, id, value).
                body().as(RootNameError.class);
        return createdValue;

    }
    public static RootErrorNameShortName createValueErrorNameShortNameAssignmentExecutionStates(int id, String name,
                                                                                       String short_name,
                                                                                       boolean excluded) {
        CreateValueAssignmentExecutionStates value = new CreateValueAssignmentExecutionStates(name, short_name,
                excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootErrorNameShortName createdValue = BasicApi.postError(API_ASSIGNMENT_EXECUTION_STATES, id, value).
                body().as(RootErrorNameShortName.class);
        return createdValue;

    }

    public static RootErrorNoRights createValueErrorNoRightsAssignmentExecutionStates(int id, String name,
                                                                                           String short_name,
                                                                                           boolean excluded) {
        CreateValueAssignmentExecutionStates value = new CreateValueAssignmentExecutionStates(name, short_name,
                excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootErrorNoRights createdValue = BasicApi.postErrorNoRights(API_ASSIGNMENT_EXECUTION_STATES, id, value).
                body().as(RootErrorNoRights.class);
        return createdValue;

    }

    public static RootElementAssignmentExecutionStates createValueAssignmentExecutionStatesName
            (int id, String name) {
        CreateValueAssignmentExecutionStatesName value = new CreateValueAssignmentExecutionStatesName(name);
        value.setName(name);
        RootElementAssignmentExecutionStates createdValue = BasicApi.post(API_ASSIGNMENT_EXECUTION_STATES, id, value).
                body().as(RootElementAssignmentExecutionStates.class);
        return createdValue;

    }

}
