/*
 * webdatagrid 1.0.0
 *
 * Author : Vincent
 * Created by Vincent on 2017.11.08
 * Version 1.0.0
 */
jQuery.extend({

    select: function (selectall, items) {

        $("#" + selectall).click(function () {
            $("input[name=" + items + "]").prop('checked', $(this).prop("checked"));
        });
        $("[name=" + items + "]:checkbox").click(function () {
            var isAllCheck = true;
            $("[name="+items+"]:checkbox").each(function () {
                if ($(this).is(':checked') == false) {
                    isAllCheck = false;
                }
            });
            $("#" + selectall).prop('checked', isAllCheck);
        });
    },

    getSelectIds: function (items) {
        var selectedIds = [];
        $("[name="+items+"]:checkbox").each(function () {
            if ($(this).is(':checked') == true) {
                selectedIds.push($(this).val());
            }
        });
        return selectedIds;
    }

});