package API;

import API.BasicApi;
import API.DTO.AssignmentExecutionStatesDto.CreateValueAssignmentExecutionStates;
import API.DTO.AssignmentExecutionStatesDto.RootAssignmentExecutionStates;
import API.DTO.AssignmentExecutionStatesDto.RootElementAssignmentExecutionStates;

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


}
