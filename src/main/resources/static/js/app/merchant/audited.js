$(function () {
    var $userTableForm = $(".audited-table-form");
    var settings = {
        url: ctx + "merchant/list",
        pageSize: 10,
        singleSelect: true,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                mchStatus:$userTableForm.find("select[name='mchStatus']").val(),
                mchName:$userTableForm.find("input[name='mchName']").val()
            };
        },
        columns: [{
            checkbox:true
        },{
            field: 'mchId',
            visible: false
        },{
            field: 'mchName',
            title: '商户名称'
        },{
            field: 'mchType',
            title: '商户类别',
            formatter: function (value, row, index) {
                if (value === 1) return '购物';
                else if (value === 2) return '饮食';
                else if (value === 3) return '娱乐';
                else if (value === 4) return '游览';
                else if (value === 5) return '出行';
                else return '其他';
            }
        },{
            title: '标签',
            formatter: function (value, row, index) {
                var mchLabelVos=row.mchLabelVos;
                var label_length = mchLabelVos.length;
                var labelStrings='';
                for (var i = 0; i < label_length; i++) {
                    labelStrings += mchLabelVos[i].labelName;
                    if (i !== (label_length - 1)) labelStrings += ",";

                }
                if (labelStrings==''){
                    labelStrings='暂无标签'
                }
                return labelStrings;
            }
        }, {
            title: '营业时间',
            formatter: function (value, row, index) {
                return '工作日:'+row.mchWeekdayBegin+'-'+row.mchWeekdayEnd+'周末:'+row.mchWeekendBegin+'-'+row.mchWeekendEnd;
            }
        },{
            field: 'mchTelephone',
            title: '联系电话'
        }, {
            field: 'mchImg',
            title: '商户图片',
            formatter: function (value, row, index) {
                return "<img width='100px' height='100px' src='"+value+"'/>";
            }
        },{
            field: 'mchAddress',
            title: '地址'
        }, {
            field: 'mchStatus',
            title: '状态',
            formatter: function (value, row, index) {
                if (value === 0) return '待审核';
                else if (value === 1) return '已通过';
                else if (value === 3) return '未通过';
                else return '其他';
            }
        },{
            title: '操作',
            formatter: function (value, row, index) {
                return '<a href="#" onclick="merchantView(\''+row.mchId+'\')">查看</a>';
            }
        }
        ]
    };

    $MB.initTable('auditedTable', settings);
});

function search() {
    $MB.refreshTable('auditedTable');
}

function refresh() {
    $(".audited-table-form")[0].reset();
    $MB.refreshTable('auditedTable');
}

//删除
function deleteAudited() {
    var selected = $("#auditedTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的商户！');
        return;
    }
    var mchIds = "";
    for (var i = 0; i < selected_length; i++) {
        mchIds += selected[i].mchId;
        if (i !== (selected_length - 1)) mchIds += ",";

    }
    $MB.confirm({
        text: "确定删除选中商户？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'merchant/deleteMerchant', {"mchId": mchIds}, function (r) {
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
    var selected = $("#auditedTable").bootstrapTable('getSelections');
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
    $.post(ctx + "user/excel", $(".audited-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportUserCsv() {
    $.post(ctx + "user/csv", $(".audited-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function merchantView(mchId){
    $.post(ctx + "merchant/getMerchant", {"merchantId": mchId}, function (ret) {
        if (ret.code === 0) {
            var $merchantView = $("#merchant-view");
            var $form = $('#merchant-view-form');
            $merchantView.modal();
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

            console.log(ret.msg.mchStatus);
            if(ret.msg.mchStatus==1){
                $('.jieguo').show();
                $('.jieguo').html('已审核');
                $('.review').hide();
                $('.reason').hide();
                $("#merchant-view-button").hide();
            }else if(ret.msg.mchStatus==2){
                $('.jieguo').show();    
                $('.jieguo').html('未通过');
                $('.review').hide();
                $('.reason textarea').val(ret.msg.mchComments);
                $('.reason textarea').attr('readonly');
                $("#merchant-view-button").hide();
            }else{
                $("#merchant-view-button").attr("name", "update");
                $('.jieguo').hide();
                $("#merchant-view-button").show();
                $('.review').show();
                $('.reason').show();
                $('.reason textarea').val(ret.msg.mchComments);
                $('.custom-radio').click(function(){
                    $('.reason textarea').attr('readonly');
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