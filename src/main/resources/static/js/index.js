$(document).ready(function () {
    $("#emp-form").easyform();

    $("table tbody tr").click(function () {
        //var id =$(this).attr("id");
        $(this).removeClass('hover').addClass("backcolor").siblings("tr").removeClass("backcolor");
    });

});

/*function ajax_empno() {
    $("#empNo").trigger("easyform-ajax", false);
}*/
function check(arg) {
    var html = "<div class='form-group'><label for='empNo' class='control-label'>员工编号：</label><input type='TEXT' class='form-control' id='empNo' name='empno' placeholder='编号'/></div><div class='form-group'><label for='empName' class='control-label'>员工姓名：</label><input type='TEXT' class='form-control' id='empName' name='ename' placeholder='姓名'/></div><div class='form-group'><label for='empJob' class='control-label'>员工职位：</label><input type='TEXT' class='form-control' id='empJob' name='job' placeholder='职位'/></div><div class='form-group'><label for='mgrNo' class='control-label'>上级编号：</label><input type='TEXT' class='form-control' id='mgrNo' name='mgr' placeholder='上级编号' data-easyform='length:4;uint:1000 9999;real-time;' data-message='请输入4位上级编号' data-easytip='class:easy-red;position:right;'/></div><div class='form-group'><label for='empSal' class='control-label'>员工薪水：</label><input type='TEXT' class='form-control' id='empSal' name='sal' placeholder='薪水' data-easyform='money;real-time;' data-message='请输入正确的员工薪水' data-easytip='class:easy-red;position:right;'/></div>";
    var empno = $.trim($("#empNo").val());
    var empname = $.trim($("#empName").val());
    var empjob = $.trim($("#empJob").val());
    var mgrno = $.trim($("#mgrNo").val());
    var empsal = $.trim($("#empSal").val());
    if (arg == 'btn-query') {
        $("#emp-form").easyform();
        if (empno != "") {

            //ajax_empno();

            //alert(res);

            //return false;
        }
    } else if (arg == 'btn-add') {
        BootstrapDialog.show({
            title: '添加员工信息',
            message: html,
            closable: true,
            closeByBackdrop: false,
            closeByKeyboard: false,
            buttons: [{
                label: '确定',
                icon: 'glyphicon glyphicon-ok',
                cssClass: 'btn-primary',
                data: {
                    js: 'btn-confirm',
                    'user-id': '3'
                },
                action: function (dialog) {
                    alert('Hi Orange!');
                    dialog.close();
                }
            }, {
                label: '关闭',
                icon: 'glyphicon glyphicon-remove',
                action: function (dialog) {
                    dialog.close();
                }
            }]
        });
    } else if (arg == 'btn-update') {
        BootstrapDialog.show({
            title: '修改员工信息',
            message: html,
            closable: true,
            closeByBackdrop: false,
            closeByKeyboard: false,
            buttons: [{
                label: '确定',
                icon: 'glyphicon glyphicon-ok',
                cssClass: 'btn-primary',
                data: {
                    js: 'btn-confirm',
                    'user-id': '3'
                },
                action: function (dialog) {
                    alert('Hi Orange!');
                    dialog.close();
                }
            }, {
                label: '关闭',
                icon: 'glyphicon glyphicon-remove',
                action: function (dialog) {
                    dialog.close();
                }
            }]
        });
    } else if (arg == 'btn-delete') {
        BootstrapDialog.show({
            title: '确认信息',
            size: BootstrapDialog.SIZE_SMALL,
            message: '确定要删除信息？',
            closable: true,
            closeByBackdrop: false,
            closeByKeyboard: false,
            cssClass: 'login-dialog',
            buttons: [{
                label: '确定',
                icon: 'glyphicon glyphicon-ok',
                data: {
                    js: 'btn-confirm',
                    'user-id': '3'
                },
                action: function (dialog) {
                    alert('已经删除!');
                    dialog.close();
                }
            }, {
                label: '取消',
                icon: 'glyphicon glyphicon-remove',
                cssClass: 'btn-primary',
                action: function (dialog) {
                    dialog.close();
                }
            }]
        });
    }
}


