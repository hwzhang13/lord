var validator;
var $userAddForm = $("#merchant-add-form");
var $rolesSelect = $userAddForm.find("select[name='rolesSelect']");
var $roles = $userAddForm.find("input[name='roles']");

$(function () {
    validator = $userAddForm.validate();
    datetimepickerFun('mchWeekdayBegin',true);
    datetimepickerFun('mchWeekdayEnd',true);
    datetimepickerFun('mchWeekendBegin',true);
    datetimepickerFun('mchWeekendEnd',true);

    $("#merchant-add .btn-save").click(function () {
        var name = $(this).attr("name");
        var validator = $userAddForm.validate();
        var flag = validator.form();
        if (flag) {
            var data = $userAddForm.serialize();
            var inputArr =  $('.labelId');
            var labelStr='';
            for(var i=0;i<inputArr.length;i++){
                if(inputArr[i].checked){
                    if (labelStr.length>1) {
                        labelStr+=',';
                    }
                    labelStr+=$(inputArr[i]).val();
                }
            };
            data += '&'+$.param({'labelVos': labelStr});
            if (name === "save") {
                $.post(ctx + "merchant/addMerchant", data, function (r) {
                    if (r.code === 0) {
                        closeMerchantAddModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("merchantTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "merchant/modifyMerchant", data, function (r) {
                    if (r.code === 0) {
                        closeMerchantAddModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("merchantTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#merchant-add .btn-close").click(function () {
        closeMerchantAddModal();
    });

});

function closeMerchantAddModal() {
    $("#merchant-add-button").attr("name", "save");
    validator.resetForm();
    $('.checkbox-content').html(' ');
    $userAddForm.find("input.mchId").val('');
    $userAddForm.find("img.mchBusinesslicence").attr('data-th-src','@{img/add.png}');
    $userAddForm.find("img.mchImg").attr('data-th-src','@{img/add.png}');
    $userAddForm.find("img.mchMap").attr('data-th-src','@{img/add.png}');
    $MB.closeAndRestModal("merchant-add");

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
        $(className).datetimepicker({format: 'yyyy-mm-dd hh:ii'});
    }
}


function getLabelList(){
    var mchType = $('.mchType').val();
    $.post(ctx + "merchant/label/list", {"labelType": mchType}, function (ret) {
        var str = '';
        for(var i=0;i<ret.rows.length;i++){
            str += '<label class="checkbox-inline">'+
                     '<input type="checkbox" class="labelId" name="labelId" value="'+ret.rows[i].labelId+'" data-name="'+ret.rows[i].labelName+'">'+ret.rows[i].labelName+
                   '</label>'
        }
        $('.checkbox-content').html(str);
    });
}