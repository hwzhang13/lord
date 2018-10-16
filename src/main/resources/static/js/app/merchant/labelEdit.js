function updateLabel(labelId) {

    $.post(ctx + "merchant/label/getLabel", {"labelId": labelId}, function (r) {
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