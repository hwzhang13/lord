function updateLabel() {
    var selected = $("#labelTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要编辑的标签！');
        return;
    }
    var labelIds = "";
    for (var i = 0; i < selected_length; i++) {
        labelIds += selected[i].labelId;
        if (i !== (selected_length - 1)) labelIds += ",";

    }
    $.post(ctx + "merchant/label/getLabel", {"labelId": labelIds}, function (r) {
        if (r.code === 0) {
            var $form = $('#label-add');
            $form.modal();
            var label = r.msg;
            $("#label-add-modal-title").html('修改标签');
            $form.find("input[name='labelName']").val(label.labelName);
            $form.find("input[name='labelId']").val(label.labelId);
            $form.find("select[name='labelType']").val(label.labelType);

            $("#label-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}