package API;

import API.DTO.ImageMarksDto.CreateValueImageMarks;
import API.DTO.ImageMarksDto.RootElementImageMarks;
import API.DTO.ImageMarksDto.RootImageMarks;

public class BasicImageMarks {
    public static String API_IMAGE_MARKS = "http://api.skip.rtech.ru/v1/classifiers/image_marks";

    /**
     * Справочник "Отметки на электронных образах"
     * **/

    /**
     * Получение значений справочника
     * @param id - id авторизуемого пользователя
     * **/

    public static RootImageMarks getImageMarksList(int id){
        RootImageMarks actualImageMarks= BasicApi.get(API_IMAGE_MARKS, id).body().as(RootImageMarks.class);
        return actualImageMarks;
    }

    /**
     * Создание значение справочника
     *
     * @param id - id авторизованного пользователя
     * @param name - Название справочника
     * @param short_name - Краткое наименование справочника
     * @param excluded - элемент исключения значения из справочника
     */

    public static RootElementImageMarks createImageMarks(int id, String name, String short_name, boolean excluded) {
        CreateValueImageMarks value = new CreateValueImageMarks(name, short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootElementImageMarks createdValue = BasicApi.post(API_IMAGE_MARKS, id, value).body()
                .as(RootElementImageMarks.class);
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

    public static RootElementImageMarks updateImageMarksValue(int id, int idValue, String name, String short_name
            , boolean excluded) {
        CreateValueImageMarks value = new CreateValueImageMarks(name, short_name, excluded);
        value.setName(name);
        value.setShort_name(short_name);
        value.setExcluded(excluded);
        RootElementImageMarks newValue = BasicApi.put(API_IMAGE_MARKS + "/" + idValue, id, value).body()
                .as(RootElementImageMarks.class);
        return newValue;
    }

    /**
     * Удаление значения справочника
     *
     * @param id - авторизованного пользователя
     * @param idValue - id значения , которое будет удалено
     * **/

    public static void deleteImageMarksValue(int id, int idValue){
        BasicApi.delete(API_IMAGE_MARKS + "/" + idValue, id);
        return;
    }
}
