var validator;
var $labelAddForm = $("#label-add-form");

$(function () {

    $("#label-add .btn-save").click(function () {
        var name = $(this).attr("name");
        var validator = $labelAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "merchant/label/addLabel", $labelAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("labelTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "merchant/label/modifyLabel", $labelAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("labelTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#label-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#label-add-button").attr("name", "save");
    /*$labelAddForm.find("input[name='labelType']").select();*/
    $labelAddForm.find("input[name='labelName']").val("");
    $labelAddForm.find("input[name='labelId']").val("");
    $labelAddForm.find("select[name='labelType']").val("");
    $("#label-add-modal-title").html('添加标签');
    $MB.closeAndRestModal("label-add");

}


