package API.DTO;

import com.google.gson.JsonParser;

public class Rights {
    public static String rights = "[{block=Разграничение прав доступа, rights=[{id=index_users," +
            " name=Пользователи. Просмотр списка}, {id=update_users, name=Пользователи. Редактирование}, " +
            "{id=create_users, name=Пользователи. Создание}, {id=destroy_users, name=Пользователи. Удаление}," +
            " {id=show_roles, name=Роли. Просмотр}, {id=index_roles, name=Роли. Просмотр списка}," +
            " {id=update_roles, name=Роли. Редактирование}, {id=create_roles, name=Роли. Создание}," +
            " {id=destroy_roles, name=Роли. Удаление}, {id=index_providers, name=Список провайдеров. Просмотр списка}," +
            " {id=update_providers, name=Список провайдеров. Редактирование}, {id=update_providers_control_subjects, " +
            "name=Список провайдеров. Редактирование субъектов контроля}, {id=destroy_providers_control_subjects," +
            " name=Список провайдеров. Удаление субъектов контроля}]}, {block=Работа с формой «Главное окно СКИП», " +
            "rights=[{id=simple_search, name=Простой поиск (сортировка/фильтрация)}, {id=advanced_search, " +
            "name=Расширенный поиск}, {id=create_search_templates, " +
            "name=Сохранение параметров поиска в качестве шаблона для дальнейшего использования}, " +
            "{id=context_search, name=Контекстный поиск}, {id=print_tables, name=Печать таблицы}, " +
            "{id=export_tables, name=Экспорт таблицы}, {id=export_images, name=Экспорт электронных образов}, " +
            "{id=show_protocol_of_works, name=Просмотр протокола работы с формой}, " +
            "{id=create_control_cards_on_main_window, name=Формирование КК}]}, " +
            "{block=Документы, rights=[{id=index_documents, name=Просмотр всех документов подразделения}, " +
            "{id=show_documents, name=Просмотр РК документа}, {id=update_documents, name=Редактирование РК документа}, " +
            "{id=update_tabular_documents, name=Редактирование в табличной форме}, " +
            "{id=update_sed_fields, name=Редактирование полей, заполненных данными из СЭД}, " +
            "{id=update_skip_reg_number, name=Редактирование поля «Рег. № СКИП»}, " +
            "{id=create_documents, name=Создание РК документа}, {id=export_documents, name=Экспорт РК документа}, " +
            "{id=print_documents, name=Печать РК документа}, " +
            "{id=create_tabular_documents, name=Создание в табличной форме}, " +
            "{id=link_documents_to_skip_documents, name=Создание ссылок на документ СКИП}, " +
            "{id=link_documents_to_assignments, name=Создание ссылок на поручение (мероприятие) СКИП}, " +
            "{id=link_documents_to_sed_documents, name=Создание ссылок на документ СЭД}, " +
            "{id=index_documents_links, name=Просмотр списка связанных документов и поручений (мероприятий)}, " +
            "{id=show_documents_changes, name=Протокол изменений}, " +
            "{id=update_documents_states, name=Редактирование состояния документа}, " +
            "{id=destroy_documents, name=Удаление РК документа}, {id=destroy_tabular_documents, " +
            "name=Удаление в табличной форме}, {id=print_documents_images, name=Электронные образы. Печать}, " +
            "{id=show_documents_images, name=Электронные образы. Просмотр}, " +
            "{id=index_documents_images, name=Электронные образы. Просмотр списка}," +
            " {id=update_title_documents_images, name=Электронные образы. Редактирование названия}, " +
            "{id=create_documents_images, name=Электронные образы. Создание}, " +
            "{id=destroy_documents_images, name=Электронные образы. Удаление}, {id=destroy_sed_documents_images, " +
            "name=Электронные образы. Удаление загруженных из СЭД ЭО}, {id=recognize_documents_images, " +
            "name=Электронные образы. Распознавание образа}]}, {block=Поручения, rights=[{id=show_assignments, " +
            "name=Просмотр РК поручения}, {id=index_tabular_assignments, " +
            "name=Просмотр в табличной форме всех поручений подразделения}, {id=update_assignments, " +
            "name=Редактирование РК поручения}, {id=update_tabular_assignments, " +
            "name=Редактирование в табличной форме}, {id=calc_assignments_deadlines, " +
            "name=Расчет сроков исполнения поручения и дат контроля}, " +
            "{id=create_control_cards, name=Формирование КК}, {id=update_control_cards, " +
            "name=Редактирование КК}, {id=show_control_cards, name=Просмотр КК}, " +
            "{id=sign_control_cards, name=Визирование заполненной КК}, {id=export_control_cards, name=Экспорт КК}," +
            " {id=update_assignments_deadlines, name=Редактирование сроков исполнения, дат контроля}, " +
            "{id=update_assignments_performers, name=Редактирование исполнителей}, {id=index_documents_assignments, " +
            "name=Просмотр всех поручений, прикрепленных к документу}, {id=create_assignments, " +
            "name=Создание РК поручения}, {id=print_assignments, name=Печать РК поручения}, " +
            "{id=export_assignments, name=Экспорт РК поручения}, {id=create_tabular_assignments, " +
            "name=Создание в табличной форме}, {id=link_assignments_to_skip_documents, " +
            "name=Создание ссылок на документы СКИП}, {id=link_assignments_to_assignments, " +
            "name=Создание ссылок на поручения СКИП}, {id=link_assignments_to_sed_documents, " +
            "name=Создание ссылок на документы СЭД}, {id=index_assignments_links, " +
            "name=Просмотр списка связанных документов и поручений}, {id=show_assignments_changes, " +
            "name=Протокол изменений}, {id=update_assignments_states, name=Редактирование состояния поручения}, " +
            "{id=destroy_assignments, name=Удаление поручения}, " +
            "{id=print_assignments_images, name=Электронные образы. Печать}, " +
            "{id=show_assignments_images, name=Электронные образы. Просмотр}, " +
            "{id=index_assignments_images, name=Электронные образы. Просмотр списка}, " +
            "{id=update_title_assignments_images, name=Электронные образы. Редактирование названия}, " +
            "{id=create_assignments_images, name=Электронные образы. Создание}, " +
            "{id=destroy_assignments_images, name=Электронные образы. Удаление}, " +
            "{id=recognize_assignments_images, name=Электронные образы. Распознавание образа}, " +
            "{id=print_deadlines_images, name=Электронные образы контрольного срока. Печать}, " +
            "{id=show_deadlines_images, name=Электронные образы контрольного срока. Просмотр}, " +
            "{id=index_deadlines_images, name=Электронные образы контрольного срока. Просмотр списка}, " +
            "{id=update_title_deadlines_images, name=Электронные образы контрольного срока. Редактирование названия}, " +
            "{id=create_deadlines_images, name=Электронные образы контрольного срока. Создание}, " +
            "{id=destroy_deadlines_images, name=Электронные образы контрольного срока. Удаление}, " +
            "{id=recognize_deadlines_images, name=Электронные образы контрольного срока. Распознавание образа}]}, " +
            "{block=Таблица контроля исполнения поручений (мероприятий)," +
            " rights=[{id=create_control_tables, name=Формирование таблицы контроля}, " +
            "{id=show_control_tables, name=Предварительный просмотр таблицы контроля}, " +
            "{id=export_control_tables, name=Экспорт таблицы контроля}, " +
            "{id=update_control_tables, name=Редактирование сформированной таблицы контроля}]}, " +
            "{block=Отчеты и справки, rights=[{id=create_reports, name=Отчеты по подразделению}, " +
            "{id=export_reports, name=Экспорт сформированного отчета в файл формата docx, xlsx, pdf, odf, ods}, " +
            "{id=setup_reports, name=Установка параметров формирования отчетов}]}, " +
            "{block=Справочники, rights=[{id=show_classifiers, name=Просмотр}," +
            " {id=update_classifiers, name=Редактирование}, " +
            "{id=create_classifiers_items, name=Добавление элементов справочника}, " +
            "{id=destroy_classifiers_items, name=Удаление элементов справочника}, " +
            "{id=sort_classifiers_items, name=Упорядочивание (сортировка) элементов справочника}, " +
            "{id=switch_classifiers_items, name=Управление исключением элементов справочника из функций выбора}, " +
            "{id=search_classifiers_items, name=Поиск элементов справочника}, " +
            "{id=print_classifiers, name=Печать данных справочника}, {id=manage_nsi, name=Переход в НСИ}]}, " +
            "{block=Журналирование, rights=[{id=index_logs, name=Просмотр журналирования}, " +
            "{id=destroy_logs, name=Удаление протоколов аудита}]}, {block=Персональные настройки, " +
            "rights=[{id=setup_personal_colors, name=Настройка цветовой индикации состояния исполнения поручения}, " +
            "{id=setup_personal_notifications, name=Настройка оповещения о приближающихся сроках исполнения}]}, " +
            "{block=Конструктор, rights=[{id=documents_and_assignments_constructor, " +
            "name=Конструктор главного окна документов и поручений (мероприятий)}," +
            " {id=control_tables_constructor, name=Конструктор главного окна таблицы контроля}, " +
            "{id=provider_tables_constructor, " +
            "name=Конструктор главного окна. Создание, редактирование, удаление таблиц на провайдер}]}, " +
            "{block=Администрирование, rights=[{id=setup_banners, " +
            "name=Настройка отображения баннеров и уведомлений}, {id=setup_banners_text, " +
            "name=Настройка текста в баннерах и уведомлениях}, {id=setup_reports_storage, " +
            "name=Настройка времени хранения сформированного отчета}, {id=setup_archiving_and_recovery_logs, " +
            "name=Настройка архивирования и восстановления протоколов аудита}, " +
            "{id=setup_assignments_default_fields, name=Настройка значений полей по умолчанию РК поручений}, " +
            "{id=show_system_settings, name=Системные настройки. Просмотр}, " +
            "{id=show_flk, name=Настройка ФЛК. Просмотр}, {id=create_flk, " +
            "name=Настройка ФЛК. Добавление новых полей для настройки}, " +
            "{id=update_flk, name=Настройка ФЛК. Редактирование предустановленных полей}, " +
            "{id=update_print_headers, name=Редактирование шапок печатных форм}, " +
            "{id=create_print_headers, name=Добавление шапки печатной формы}, " +
            "{id=setup_default_tables, name=Настройка выбора таблицы ГО по умолчанию}, " +
            "{id=setup_untypical_user_actions_notifications, " +
            "name=Настройка уведомлений о нетипичных действиях пользователя}, {id=setup_untypical_user_actions, " +
            "name=Настройка нетипичных действий пользователя}," +
            " {id=manage_training_contour, name=Администрирование учебного контура}, " +
            "{id=migrate_akidpp_data, name=Миграция данных из АКИДПП}, " +
            "{id=setup_import_and_export_data, name=Настройка параметров экспорта и импорта данных}, " +
            "{id=setup_provider_colors," +
            " name=Настройка цветовой индикации состояния исполнения поручения на провайдер}, " +
            "{id=setup_documents_and_assignments_fields, " +
            "name=Настройка отображения полей РК документа и РК поручения}, " +
            "{id=setup_control_cards_fields, " +
            "name=Настройка полей печатной формы контрольной карточки поручения (мероприятия)}]}]";
}
