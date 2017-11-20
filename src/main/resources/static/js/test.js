$(document).ready(function () {
    $("#btn_hello").click(function () {

        var html = "<div class='form-group'><label for='empNo' class='control-label'>员工编号：</label><input type='TEXT' class='form-control' id='empNo' name='empno' placeholder='编号'/></div><div class='form-group'><label for='empName' class='control-label'>员工姓名：</label><input type='TEXT' class='form-control' id='empName' name='ename' placeholder='姓名'/></div><div class='form-group'><label for='empJob' class='control-label'>员工职位：</label><input type='TEXT' class='form-control' id='empJob' name='job' placeholder='职位'/></div><div class='form-group'><label for='mgrNo' class='control-label'>上级编号：</label><input type='TEXT' class='form-control' id='mgrNo' name='mgr' placeholder='上级编号' data-easyform='length:4;uint:1000 9999;real-time;' data-message='请输入4位上级编号' data-easytip='class:easy-red;position:right;'/></div><div class='form-group'><label for='empSal' class='control-label'>员工薪水：</label><input type='TEXT' class='form-control' id='empSal' name='sal' placeholder='薪水' data-easyform='money;real-time;' data-message='请输入正确的员工薪水' data-easytip='class:easy-red;position:right;'/></div>";

        BootstrapDialog.show({
            title: '修改员工信息',
            message: html,
            closable: true,
            closeByBackdrop: false,
            closeByKeyboard: false,
            buttons: [{
                label: '确定',
                icon: 'glyphicon glyphicon-edit',
                cssClass: 'btn-primary',
                data: {
                    js: 'btn-confirm',
                    'user-id': '3'
                },
                action: function () {
                    alert('Hi Orange!');
                }
            }, {
                label: '关闭',
                icon: 'glyphicon glyphicon-remove',
                action: function (dialogItself) {
                    dialogItself.close();
                }
            }]
        });


    });
});
