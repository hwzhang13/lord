var validator;
var $couponAddForm = $("#coupon-add-form");

$(function () {
    datetimepickerFun('couponTimeBegin');
    datetimepickerFun('couponTimeEnd');
    $("#coupon-add .btn-save").click(function () {
        var name = $(this).attr("name");
        var validator = $couponAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "merchant/coupon/addMerchantCoupon", $couponAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("coupon-add");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "merchant/coupon/modifyMerchantCoupon", $couponAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("coupon-add");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#coupon-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#coupon-add-button").attr("name", "save");
    $('.couponQrCode').attr('data-th-src','@{img/add.png}');
    validator.resetForm();
    $MB.closeAndRestModal("coupon-add");

}

function datetimepickerFun(className,type){
    if(!className){return}
    className = '.'+className;
    if(type){
        $(className).datetimepicker({
            language:"zh-CN", //设置为控件语言为中文
            format:"hh:ii", //自定义时间格式
            weekStart: 1,
            //todayBtn: 1, //设置table底部(今天)时间按钮
            autoclose: 1,
            todayHighlight: 1,//高亮显示目前的时间
            startView: 1,
            minView: 0,
            maxView: 1,
            forceParse: 0
        });
    }else{
        $(className).datetimepicker({language:"zh-CN",format: 'yyyy-mm-dd hh:ii'});
    }
}
