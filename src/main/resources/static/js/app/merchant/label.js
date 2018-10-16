$(function () {
    var $userTableForm = $(".label-table-form");
    var settings = {
        url: ctx + "merchant/label/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                labelType:$userTableForm.find("select[name='labelType']").val()
            };
        },
        columns: [{
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
        }, {
            field: 'labelDisable',
            title: '操作',
            formatter: function (value, row, index) {
                console.log(row.labelId)
                var $labelId=row.labelId;
                if (value === 1) return "<a href='#'  onclick='updateLabel(\"" + $labelId + "\")'>编辑</a><a href='#' shiro:hasPermission='label:disable' onclick='disableLabel(\""+$labelId+"\",0)'>启用</a><a href='#' shiro:hasPermission='label:delete' onclick='deleteLabel(\""+$labelId+"\")'>删除</a>";
                if (value === 0) return "<a href='#'  onclick='updateLabel(\"" + $labelId + "\")'>编辑</a><a href='#' shiro:hasPermission='label:disable' onclick='disableLabel(\""+$labelId+"\",1)'>禁用</a><a href='#' shiro:hasPermission='label:delete' onclick='deleteLabel(\""+$labelId+"\")'>删除</a>";
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
function deleteLabel(labelId) {

    $MB.confirm({
        text: "确定删除选中标签？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'merchant/label/deleteLabel', {"labelId": labelId}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function disableLabel(labelId,labelDisable) {
    $MB.confirm({
        text: "确定操作选中标签？",
        confirmButtonText: "确定"
    }, function () {
        $.post(ctx + 'merchant/label/disableLabel', {"labelId": labelId,"labelDisable":labelDisable}, function (r) {
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