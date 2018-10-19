$(function () {
    var $userTableForm = $(".coupon-table-form");
    var settings = {
        url: ctx + "merchant/coupon/list",
        pageSize: 10,
        singleSelect: true,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                couponStatus:$userTableForm.find("select[name='couponStatus']").val(),
                mchName:$userTableForm.find("input[name='mchName']").val()
            };
        },
        columns: [{
            checkbox:true
        },{
            field: 'couponId',
            visible: false
        },{
            field: 'mchName',
            title: '商户名称'
        },{
            field: 'couponType',
            title: '优惠类型',
            formatter: function (value, row, index) {
                if (value === 1) return '满减';
                else return '其他';
            }
        },{
            field: 'couponAmount',
            title: '优惠金额'
        }, {
            field: 'couponUsageAmount',
            title: '使用条件',
            formatter: function (value, row, index) {
                return value+'元以上';
            }
        },{
            field: 'couponPaymentTypeName',
            title: '支付方式'
        }, {
            title: '有效期',
            formatter: function (value, row, index) {
                return row.couponTimeBegin+"-"+row.couponTimeEnd;
            }
        }, {
            field: 'couponStatus',
            title: '状态',
            formatter: function (value, row, index) {
                if (value === 0) return '待审核';
                else if (value === 1) return '已通过';
                else if (value === 2) return '未通过';
                else return '其他';
            }
        },{
            title: '操作',
            formatter: function (value, row, index) {
                return '<button onclick="couponView(\''+row.couponId+'\')" type="button" class="btn btn-info" style="margin-right:10px;">查看</button>';
            }
        }
        ]
    };

    $MB.initTable('couponTable', settings);
});

function search() {
    $MB.refreshTable('couponTable');
}

function refresh() {
    $(".coupon-table-form")[0].reset();
    $MB.refreshTable('couponTable');
}

//删除标签
function deleteCoupon() {
    var selected = $("#couponTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的优惠！');
        return;
    }
    var couponIds = "";
    for (var i = 0; i < selected_length; i++) {
        couponIds += selected[i].couponId;
        if (i !== (selected_length - 1)) couponIds += ",";

    }
    $MB.confirm({
        text: "确定删除选中优惠？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'merchant/coupon/deleteMerchantCoupon', {"couponId": couponIds}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function disableCoupon(labelDisable) {
    if (labelDisable==0){

    }
    var selected = $("#couponTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要操作的标签！');
        return;
    }
    var labelIds = "";
    for (var i = 0; i < selected_length; i++) {
        labelIds += selected[i].labelId;
        if (i !== (selected_length - 1)) labelIds += ",";

    }
    $MB.confirm({
        text: "确定操作选中标签？",
        confirmButtonText: "确定"
    }, function () {
        $.post(ctx + 'merchant/label/disableLabel', {"labelId": labelIds,"labelDisable":labelDisable}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });

}

function exportUserExcel() {
    $.post(ctx + "user/excel", $(".coupon-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportUserCsv() {
    $.post(ctx + "user/csv", $(".coupon-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function couponView(couponId){
    $.post(ctx + "merchant/coupon/getMerchantCoupon", {"couponId": couponId}, function (ret) {
        if (ret.code === 0) {
            var $couponView = $("#coupon-view");
            var $form = $('#coupon-view-form');
            $couponView.modal();
            $("#coupon-view-form").find(".mchId").val(ret.msg.mchId);
            $("#coupon-view-form").find(".couponId").val(ret.msg.couponId);
            $("#coupon-view-form").find(".mchName").val(ret.msg.mchName);
            $("#coupon-view-form").find("input[name='couponType']").val(1);
            $("#coupon-view-form").find(".couponType").val("满减");
            $("#coupon-view-form").find(".couponPaymentTypeName").val("建行信用卡");
            $("#coupon-view-form").find(".couponAmount").val(ret.msg.couponAmount);
            $("#coupon-view-form").find("input[name='couponTimeBegin']").val(ret.msg.couponTimeBegin);
            $("#coupon-view-form").find("input[name='couponTimeEnd']").val(ret.msg.couponTimeEnd);
            $("#coupon-view-form").find(".couponUsageAmount").val(ret.msg.couponUsageAmount);
            $("#coupon-view-form").find("input[name='couponQrCode']").val(ret.msg.couponQrCode);
            $("#coupon-view-form").find(".couponQrCode").attr('src',ret.msg.couponQrCode);
            $("#coupon-view-button").attr("name", "update");

            if(ret.msg.couponStatus==1){
                $('.jieguo').show();
                $('.jieguo').html('已审核');
                $('.review').hide();
                $('.reason').hide();
                $("#coupon-view-button").hide();
            }else if(ret.msg.couponStatus==2){
                $('.jieguo').show();    
                $('.reason').show();
                $('.jieguo').html('未通过');
                $('.review').hide();
                $('.reason textarea').val(ret.msg.couponComments);
                $('.reason textarea').attr('readonly',true);
                $("#coupon-view-button").hide();
            }else{
                $("#coupon-view-button").attr("name", "update");
                $('.jieguo').hide();
                $("#coupon-view-button").show();
                $('.review').show();
                $('.reason').show();
                $('.reason textarea').removeAttr('readonly');
                $('.custom-radio').click(function(){
                    if($(this).find('input').val()==1){
                        $('.reason').hide();
                    }else{
                        $('.reason').show();
                    }
                });
            } 
        } else {
            $MB.n_danger(ret.msg);
        }
    });
}