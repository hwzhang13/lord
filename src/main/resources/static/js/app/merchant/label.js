$(function () {
    var $userTableForm = $(".label-table-form");
    var settings = {
        url: ctx + "merchant/label/list",
        pageSize: 10,
        singleSelect: true,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                labelType:$userTableForm.find("select[name='labelType']").val()
            };
        },
        columns: [{
            checkbox:true
        },{
            field: 'labelId',
            visible: false
        }, {
            field: 'labelType',
            title: '标签分类',
            formatter: function (value, row, index) {
                if (value === 1) return '购物';
                else if (value === 2) return '饮食';
                else if (value === 3) return '娱乐';
                else if (value === 4) return '游览';
                else if (value === 5) return '出行';
                else return '其他';
            }
        }, {
            field: 'labelName',
            title: '标签名称'
        }, {
            field: 'labelDisable',
            title: '状态',
            formatter: function (value, row, index) {
                if (value === 1) return '禁用';
                else if (value === 0) return '启用';
                else return '其他';
            }
        }
        ]
    };

    $MB.initTable('labelTable', settings);
});

function search() {
    $MB.refreshTable('labelTable');
}

function refresh() {
    $(".label-table-form")[0].reset();
    $MB.refreshTable('labelTable');
}

//删除标签
function deleteLabel() {
    var selected = $("#labelTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的标签！');
        return;
    }
    var labelIds = "";
    for (var i = 0; i < selected_length; i++) {
        labelIds += selected[i].labelId;
        if (i !== (selected_length - 1)) labelIds += ",";

    }
    $MB.confirm({
        text: "确定删除选中标签？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'merchant/label/deleteLabel', {"labelId": labelIds}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function disableLabel(labelDisable) {
    if (labelDisable==0){

    }
    var selected = $("#labelTable").bootstrapTable('getSelections');
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
    $.post(ctx + "user/excel", $(".label-table-form").serialize(), function (r) {
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