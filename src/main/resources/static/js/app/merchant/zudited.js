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
            checkbox: true
        }, {
            field: 'mchId',
            visible: false
        }, {
            field: 'mchName',
            title: '商户名称'
        }, {
            field: 'mchType',
            title: '商户类别'
        }, {
            field: 'mchLabelVos',
            title: '标签'
        }, {
            field: 'mchLabelVos',
            title: '营业时间',
            formatter: function (value, row, index) {
                return '<p>工作日：'+row.mchWeekdayBegin+'</p><p>周日：'+row.mchWeekendBegin+'</p>'
            }
        }, {
            field: 'mchTelephone',
            title: '联系电话'
        }, {
            field: 'mchImg',
            title: '商户图片'
        }, {
            field: 'mchAddress',
            title: '地址'
        }, {
            field: 'mchStatus',
            title: '状态',
            formatter: function (value, row, index) {
                if (value === '0') return '<span class="badge badge-info">待审核</span>';
                if (value === '1') return '<span class="badge badge-success">已审核</span>';
                if (value === '2') return '<span class="badge badge-danger">未通过</span>';
            }
        }, {
            field: 'operating',
            title: '操作',
            formatter: function (value, row, index) {
                return '<button type="button" class="btn btn-info">查看</button>'+
                '<button type="button" class="btn btn-primary">编辑</button>'+
                '<button type="button" class="btn btn-danger">删除</button>'
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
function deleteMerchant(merchantId) {

    $MB.confirm({
        text: "确定删除选中用户？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'merchant/deleteMerchant', {"merchantId": merchantId}, function (r) {
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