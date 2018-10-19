$(function () {
    var $merchantViewForm = $("#merchant-view-form");

    $("#merchant-view .btn-close").click(function () {
        $('.checkbox-content').html(' ');
        closeMerchantViewModal();
    });
    $("#merchant-view .btn-save").click(function () {
        var validator = $merchantViewForm.validate();
        var flag = validator.form();
        if (flag) {
            var arr = $('#merchant-view .custom-control-input');
            var val = null;
            for(var i=0;i<arr.length;i++){
                if(arr[i].checked){
                    val = $(arr[i]).val();
                };
            }
            if (val == 1) {
                $.post(ctx + "merchant/auditedMerchant", {'mchId':$('#merchant-view .mchId').val()}, function (r) {
                    if (r.code === 0) {
                        closeMerchantViewModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("merchantTable");
                    } else $MB.n_danger(r.msg);
                });
            }else{
                if(!$('.reason textarea').val()){
                    $MB.n_danger('请填写未通过原因');
                    return;
                }
                $.post(ctx + "merchant/notThroughMerchant", {'mchId':$('#merchant-view .mchId').val(),'mchComments':$('#merchant-view .mchComments').val()}, function (r) {
                    if (r.code === 0) {
                        closeMerchantViewModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("merchantTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

});

function closeMerchantViewModal() {
    $("#merchant-view-button").attr("name", "save");
    $MB.closeAndRestModal("merchant-view");

}
