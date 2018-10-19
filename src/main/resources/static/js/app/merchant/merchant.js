$(function () {
    var $userTableForm = $(".merchant-table-form");
    var settings = {
        url: ctx + "merchant/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                mchName: $userTableForm.find("input[name='mchName']").val().trim(),
                mchType: $userTableForm.find("select[name='mchType']").val()
            };
        },
        columns: [{
            field: 'mchId',
            visible: false
        }, {
            field: 'mchName',
            title: '商户名称',
            formatter:function(value, row, index){
                return '<span onclick="stretch(event,\''+row.mchId+'\',\''+row.mchName+'\')" style="font-weight: 600;" class="treegrid-expander zmdi zmdi-chevron-right zmdi-hc-fw"></span>'+value;
            }
        }, {
            field: 'mchType',
            title: '商户类别',
            formatter:function(value, row, index){
                return ['','购物','饮食','娱乐','游览','出行'][value];
            }
        }, {
            field: 'mchLabelVos',
            title: '标签',
            formatter: function (value, row, index) {
                var vosHtml='';
                for(var i=0;i<value.length;i++){

                    vosHtml += '<p>'+value[i].labelName+'</p>';
                }
                return vosHtml;
            }
        }, {
            field: 'businessHours',
            title: '营业时间',
            formatter: function (value, row, index) {
                return '<p>工作日：'+row.mchWeekdayBegin+'</p><p>周&emsp;日：'+row.mchWeekendBegin+'</p>';
            }
        }, {
            field: 'mchTelephone',
            title: '联系电话'
        }, {
            field: 'mchImg',
            title: '商户图片',
            formatter: function (value, row, index) {
                return '<img style="width:60px;" src="'+row.mchMap+'">';
            }
        }, {
            field: 'mchAddress',
            title: '地址'
        }, {
            field: 'mchStatus',
            title: '状态',
            formatter: function (value, row, index) {
                if (value === 0) return '<span class="badge badge-info">待审核</span>';
                if (value === 1) return '<span class="badge badge-success">已审核</span>';
                if (value === 2) return '<span class="badge badge-danger">未通过</span>';
            }
        }, {
            field: 'operating',
            title: '操作',
            formatter: function (value, row, index) {
                return '<button onclick="merchantView(\''+row.mchId+'\')" type="button" class="btn btn-info" style="margin-right:10px;">查看</button>'+
                '<button onclick="merchantEdit(\''+row.mchId+'\')" type="button" class="btn btn-primary" style="margin-right:10px;">编辑</button>'+
                '<button onclick="deleteMerchant(\''+row.mchId+'\',\''+row.mchName+'\')" type="button" class="btn btn-danger">删除</button>';
            }
        }]
        //<button type="button" class="btn btn-primary">编辑</button>
        //<button type="button" class="btn btn-info">查看</button>
        //<button type="button" class="btn btn-success">通过</button>
        //<button type="button" class="btn btn-danger">删除</button>
    };
    $MB.initTable('merchantTable', settings);
});

function search() {
    $MB.refreshTable('merchantTable');
}

function refresh() {
    $(".merchant-table-form")[0].reset();
    $MB.refreshTable('merchantTable');
}

//删除商户
function deleteMerchant(mchId,mchName) {

    $MB.confirm({
        text: "确定删除商户:"+mchName+"？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'merchant/deleteMerchant', {"merchantId": mchId}, function (ret) {
            if (ret.code === 0) {
                $MB.n_success(ret.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}
function deleteCoupon(mchId,mchName) {

    $MB.confirm({
        text: "确定删除商户:"+mchName+"？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'merchant/deleteMerchant', {"merchantId": mchId}, function (ret) {
            if (ret.code === 0) {
                $MB.n_success(ret.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function stretch(event,mchId,mchName){
    event.stopPropagation();
    if($(event.target).hasClass('zmdi-chevron-right')){
        $(event.target).removeClass('zmdi-chevron-right');
        $(event.target).addClass('zmdi-chevron-down');
        if(!$(event.target).attr('data-stretch')){
            $(event.target).attr('data-stretch',true);
            $.get(ctx + 'merchant/coupon/mchCouponList?mchId='+mchId,function(ret){   
                console.log(ret.msg);         
                var couponHtml = '';
                for(var i=0;i<ret.msg.length;i++){
                    couponHtml+='<tr data-stretch-'+mchId+'="true">'+
                                    '<td>商户优惠'+ (i+1) +'</td>'+
                                    '<td colspan="6">'+
                                        '优惠类型：'+ (ret.msg[i].couponType==1?'满减':'') +'&emsp;'+
                                        '优惠金额：'+ (ret.msg[i].couponAmount) +'&emsp;'+
                                        '优惠条件：'+ (ret.msg[i].couponUsageAmount) +'&emsp;'+
                                        '支付方式：'+ (ret.msg[i].couponPaymentTypeName) +'&emsp;'+
                                        '使用商户：'+ (ret.msg[i].mchName) +'&emsp;'+
                                        '有效时间：'+ (ret.msg[i].couponTimeEnd) +
                                    '</td>'+
                                    '<td>'+ (ret.msg[i].couponExpire==1?'<span class="badge badge-danger">已过期</span>':'<span class="badge badge-success">使用中</span>') +'</td>'+
                                    '<td><button onclick="couponView(\''+ret.msg[i].couponId+'\')" type="button" class="btn btn-info" style="margin-right:10px;">查看</button>'+
                                    '<button onclick="couponEdit(\''+ret.msg[i].couponId+'\')" type="button" class="btn btn-primary" style="margin-right:10px;">编辑</button>'+
                                    '<button onclick="deleteMerchant(\''+ret.msg[i].couponId+'\')" type="button" class="btn btn-danger">删除</button></td>'+
                                '</tr>';couponEdit
                }
                couponHtml += '<tr data-stretch-'+mchId+'="true">'+
                                    '<td colspan="9" style="text-align:center;">'+
                                        '<button onclick="couponAdd(\''+mchName+'\',\''+mchId+'\')" type="button" class="btn btn-primary">+&emsp;新增优惠</button>'+
                                    '</td>'+
                                '</tr>'
                $(event.target).closest('tr').after(couponHtml);
            });
        }else{
            $('tr[data-stretch-'+mchId+']').show();
        }
    }else{
        $(event.target).removeClass('zmdi-chevron-down');
        $(event.target).addClass('zmdi-chevron-right');
        $('tr[data-stretch-'+mchId+']').hide();
    }
}

function exportUserExcel() {
    $.post(ctx + "user/excel", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportUserCsv() {
    $.post(ctx + "user/csv", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function merchantEdit(mchId){
    $.post(ctx + "merchant/getMerchant", {"merchantId": mchId}, function (ret) {
        if (ret.code === 0) {
            var $merchantEdit = $("#merchant-add");
            var $form = $('#merchant-add-form');
            $merchantEdit.modal();
            $form.find(".mchId").val(ret.msg.mchId);
            $form.find(".mchName").val(ret.msg.mchName);
            $form.find(".mchContacts").val(ret.msg.mchContacts);
            $form.find(".mchContactsTelephone").val(ret.msg.mchContactsTelephone);
            $form.find("img.mchBusinesslicence").attr('src',ret.msg.mchBusinesslicence);
            $form.find("input.mchBusinesslicence").val(ret.msg.mchBusinesslicence);
            $form.find(".mchBusinesslicenceNumbe").val(ret.msg.mchBusinesslicenceNumbe);
            $form.find("img.mchImg").attr('src',ret.msg.mchImg);
            $form.find("input.mchImg").val(ret.msg.mchImg);
            $form.find("img.mchMap").attr('src',ret.msg.mchMap);
            $form.find("input.mchMap").val(ret.msg.mchMap);
            $form.find(".mchAddress").val(ret.msg.mchAddress);
            $form.find(".mchType").val(ret.msg.mchType);
            $.post(ctx + "merchant/label/list", {"labelType": ret.msg.mchType}, function (res) {
                var str = '';
                for(var i=0;i<res.rows.length;i++){
                    var lmm = true;
                    ret.msg.mchLabelVos.forEach(function(item){
                        if(item.labelId==res.rows[i].labelId){
                            str += '<label class="checkbox-inline">'+
                                     '<input type="checkbox" checked  class="labelId" value="'+res.rows[i].labelId+'">'+res.rows[i].labelName+
                                   '</label>';
                            lmm = false;
                        }
                    });
                    if(lmm){
                        str += '<label class="checkbox-inline">'+
                                 '<input type="checkbox"   class="labelId" value="'+res.rows[i].labelId+'">'+res.rows[i].labelName+
                               '</label>';
                    }
                }
                $('.checkbox-content').html(str);
            });

            $form.find(".mchPerCapitaConsume").val(ret.msg.mchPerCapitaConsume);
            $form.find(".mchTelephone").val(ret.msg.mchTelephone);
            $form.find(".mchWeekdayBegin").val(ret.msg.mchWeekdayBegin);
            $form.find(".mchWeekdayEnd").val(ret.msg.mchWeekdayEnd);
            $form.find(".mchWeekendBegin").val(ret.msg.mchWeekendBegin);
            $form.find(".mchWeekendEnd").val(ret.msg.mchWeekendEnd);
            $("#merchant-add-button").attr("name", "update");
        } else {
            $MB.n_danger(ret.msg);
        }
    });
}
function couponEdit(couponId){
    $.post(ctx + "merchant/coupon/getMerchantCoupon", {"couponId": couponId}, function (ret) {
        if (ret.code === 0) {
            var $couponAdd = $("#coupon-add");
            var $form = $('#coupon-add-form');
            $couponAdd.modal();
            $("#coupon-add-form").find(".mchId").val(ret.msg.mchId);
            $("#coupon-add-form").find(".couponId").val(ret.msg.couponId);
            $("#coupon-add-form").find(".mchName").val(ret.msg.mchName);
            $("#coupon-add-form").find("input[name='couponType']").val(1);
            $("#coupon-add-form").find(".couponType").val("满减");
            $("#coupon-add-form").find(".couponPaymentTypeName").val("建行信用卡");
            $("#coupon-add-form").find(".couponAmount").val(ret.msg.couponAmount);
            $("#coupon-add-form").find(".couponTimeBegin").val(ret.msg.couponTimeBegin);
            $("#coupon-add-form").find(".couponTimeEnd").val(ret.msg.couponTimeEnd);
            $("#coupon-add-form").find(".couponUsageAmount").val(ret.msg.couponUsageAmount);
            $("#coupon-add-form").find("input[name='couponQrCode']").val(ret.msg.couponQrCode);
            $("#coupon-add-form").find(".couponQrCode").attr('src',ret.msg.couponQrCode);
            $("#coupon-add-button").attr("name", "update");
        } else {
            $MB.n_danger(ret.msg);
        }
    });
}

function merchantView(mchId){
    $.post(ctx + "merchant/getMerchant", {"merchantId": mchId}, function (ret) {
        if (ret.code === 0) {
            var $merchantView = $("#merchant-view");
            var $form = $('#merchant-view-form');
            $merchantView.modal();
            $("#merchant-view").find(".mchId").val(ret.msg.mchId);
            $form.find(".mchName").val(ret.msg.mchName);
            $form.find(".mchContacts").val(ret.msg.mchContacts);
            $form.find(".mchContactsTelephone").val(ret.msg.mchContactsTelephone);
            $form.find(".mchBusinesslicence").attr('src',ret.msg.mchBusinesslicence);
            $form.find(".mchBusinesslicenceNumbe").val(ret.msg.mchBusinesslicenceNumbe);
            $form.find(".mchImg").attr('src',ret.msg.mchImg);
            $form.find(".mchMap").attr('src',ret.msg.mchMap);
            $form.find(".mchAddress").val(ret.msg.mchAddress);
            $form.find(".mchType").val(['','购物','饮食','娱乐','游览','出行'][ret.msg.mchType]);

            var str = '';
            ret.msg.mchLabelVos.forEach(function(item){
                str += '<span class="badge badge-info" style="margin-right:4px;">'+item.labelName+'</span>';
            });
            $('.checkbox-content').html(str);

            $form.find(".mchPerCapitaConsume").val(ret.msg.mchPerCapitaConsume);
            $form.find(".mchTelephone").val(ret.msg.mchTelephone);
            $form.find(".mchWeekdayBegin-v").val(ret.msg.mchWeekdayBegin);
            $form.find(".mchWeekdayEnd-v").val(ret.msg.mchWeekdayEnd);
            $form.find(".mchWeekendBegin-v").val(ret.msg.mchWeekendBegin);
            $form.find(".mchWeekendEnd-v").val(ret.msg.mchWeekendEnd);
            if(ret.msg.mchStatus==1){
                $('.jieguo').show();
                $('.jieguo').html('已审核');
                $('.review').hide();
                $('.reason').hide();
                $("#merchant-view-button").hide();
            }else if(ret.msg.mchStatus==2){
                $('.jieguo').show();    
                $('.reason').show();
                $('.jieguo').html('未通过');
                $('.review').hide();
                $('.reason textarea').val(ret.msg.mchComments);
                $('.reason textarea').attr('readonly',true);
                $("#merchant-view-button").hide();
            }else{
                $("#merchant-view-button").attr("name", "update");
                $('.jieguo').hide();
                $("#merchant-view-button").show();
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

function couponAdd(name,mchId){
    var $couponAdd = $("#coupon-add");
    var $form = $('#coupon-add-form');
    $("#coupon-add-form").find("input[name='mchId']").val(mchId);
    $("#coupon-add-form").find("input[name='couponType']").val(1);
    $("#coupon-add-form").find("input[name='mchName']").val(name);
    $("#coupon-add-form").find(".couponType").val("满减");
    $("#coupon-add-form").find(".couponPaymentTypeName").val("建行信用卡");
    $couponAdd.modal();
    $("#coupon-add-button").attr("name", "save");
}

function addImgClick(e){
    $(e.target).next().click();
}
function addImgShow(e){
    var formData=new FormData();
    formData.append('file', $(e.target)[0].files[0]);
    
    $.ajax({
        type: "post",
        url: ctx+"uploadImg",
        data:formData,
        contentType:false,
        processData:false,
        success:function(ret){

            $(e.target).prev().attr('src',ret.msg.img);
            $(e.target).next().val(ret.msg.img);
        }
    });
}