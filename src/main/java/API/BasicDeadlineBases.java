package API;

import API.DTO.DeadlineBasesDto.CreateValueDeadlineBases;
import API.DTO.DeadlineBasesDto.RootDeadlineBases;
import API.DTO.DeadlineBasesDto.RootElementDeadlineBases;

public class BasicDeadlineBases {
    public static String API_DEADLINE_BASES = "http://api.skip.rtech.ru/v1/classifiers/deadline_bases";

    /**
     * Справочник "Основание контрольного срока"
     * **/

    /**
     * Получение значений справочника
     * @param id - id авторизуемого пользователя
     * **/

    public static RootDeadlineBases getDeadlineBasesList(int id){
        RootDeadlineBases actualDeadlineBases= BasicApi.get(API_DEADLINE_BASES, id).body().as(RootDeadlineBases.class);
        return actualDeadlineBases;
    }

    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param short_name - Краткое наименование справочника
     * @param note - Заметка для справочника
     * @param duration_id - id срока поручения
     * @param deadline_only - элемент ...
     * @param excluded - элемент исключения значения из справочника
     */

    public static RootElementDeadlineBases createDeadlineBases(int id, String name, String short_name, String note
            , String duration_id, boolean deadline_only, boolean excluded) {
        CreateValueDeadlineBases value = new CreateValueDeadlineBases(name, short_name, note, duration_id
                , deadline_only, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setNote(note);
        value.setDuration_id(duration_id);
        value.setDeadline_only(deadline_only);
        value.setExcluded(excluded);
        RootElementDeadlineBases createdValue = BasicApi.post(API_DEADLINE_BASES, id, value).body()
                .as(RootElementDeadlineBases.class);
        return createdValue;
    }

    /**
     * Изменение значения справочника
     *
     * @param id - id авторизованного пользователя
     * @param idValue - id справочника, в котором измениться значение
     * @param name - Название справочника
     * @param short_name - Краткое наименование справочника
     * @param note - Заметка для справочника
     * @param duration_id - id срока поручения
     * @param deadline_only - элемент ...
     * @param excluded - элемент исключения значения из справочника
     * **/

    public static RootElementDeadlineBases updateDeadlineBasesValue(int id, int idValue, String name, String short_name
            , String note, String duration_id, boolean deadline_only, boolean excluded) {
        CreateValueDeadlineBases value = new CreateValueDeadlineBases(name, short_name, note, duration_id
                , deadline_only, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setNote(note);
        value.setDuration_id(duration_id);
        value.setDeadline_only(deadline_only);
        value.setExcluded(excluded);
        RootElementDeadlineBases newValue = BasicApi.put(API_DEADLINE_BASES + "/" + idValue, id, value)
                .body().as(RootElementDeadlineBases.class);
        return newValue;
    }

    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    public static void deleteDeadlineBasesValue(int id, int idValue){
        BasicApi.delete(API_DEADLINE_BASES + "/" + idValue, id);
        return;
    }
}
