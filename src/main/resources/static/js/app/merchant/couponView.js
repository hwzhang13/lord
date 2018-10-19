$(function () {

    $("#coupon-view .btn-close").click(function () {
        $('.checkbox-content').html(' ');
        closeCouponViewModal();
    });

    $("#coupon-view .btn-save").click(function () {
        var val = $('#coupon-view .custom-control-input').val();

        if (val == 1) {
            $.post(ctx + "merchant/coupon/auditedMerchantCoupon", {'couponId':$('.couponId').val()}, function (r) {
                if (r.code === 0) {
                    closeModal();
                    $MB.n_success(r.msg);
                    $MB.refreshTable("coupon-view");
                } else $MB.n_danger(r.msg);
            });
        }else{
        	$.post(ctx + "merchant/coupon/notThroughMerchantCoupon", {'couponId':$('#coupon-view .couponId').val(),'couponComments':$('#coupon-view .couponComments').val()}, function (r) {
                if (r.code === 0) {
                    closeModal();
                    $MB.n_success(r.msg);
                    $MB.refreshTable("coupon-view");
                } else $MB.n_danger(r.msg);
            });
        }
    });

});

function closeCouponViewModal() {
    $("#coupon-view-button").attr("name", "save");
    $MB.closeAndRestModal("coupon-view");

}
