var no;
var name;
var job;
var mgr;
var hiredate;
var sal;
var comm;
var deptno;
$(document).ready(function () {
    $("#emp-form").easyform();

    $("table tbody tr").click(function () {
        //var id =$(this).attr("id");
        $(this).removeClass('hover').addClass("backcolor").siblings("tr").removeClass("backcolor");
        no = $('.backcolor').children().eq(0).text();
        name = $('.backcolor').children().eq(1).text();
        job = $('.backcolor').children().eq(2).text();
        mgr = $('.backcolor').children().eq(3).text();
        hiredate = $('.backcolor').children().eq(4).text();
        sal = $('.backcolor').children().eq(5).text();
        comm = $('.backcolor').children().eq(6).text();
        deptno = $('.backcolor').children().eq(7).text();

    });

});

/*function ajax_empno() {
    $("#empNo").trigger("easyform-ajax", false);
}*/
function initInputData() {
    var json = {
        "empno": $.trim($('#eno').val()),
        "ename": $.trim($('#ename').val()),
        "job": $.trim($('#ejob').val()),
        "mgr": $.trim($('#mno').val()),
        "hiredate": $.trim($('#hiredate').val()),
        "sal": $.trim($('#esal').val()),
        "comm": $.trim($('#comm').val()),
        "deptno": $.trim($('#dno').val())
    };
    return json;
}

function check(arg) {
    var add_html = "<div class='form-group'><label for='eno' class='control-label'>员工编号：</label><input type='TEXT' class='form-control' id='eno' name='empno' placeholder='编号'/></div><div class='form-group'><label for='ename' class='control-label'>员工姓名：</label><input type='TEXT' class='form-control' id='ename' name='ename' placeholder='姓名'/></div><div class='form-group'><label for='ejob' class='control-label'>员工职位：</label><input type='TEXT' class='form-control' id='ejob' name='job' placeholder='职位'/></div><div class='form-group'><label for='mno' class='control-label'>上级编号：</label><input type='TEXT' class='form-control' id='mno' name='mgr' placeholder='上级编号' data-easyform='length:4;uint:1000 9999;real-time;' data-message='请输入4位上级编号' data-easytip='class:easy-red;position:right;'/></div><div class='form-group'><label for='hiredate' class='control-label'>出生日期：</label><input type='TEXT' class='form-control' id='hiredate' name='hiredate' placeholder='出生日期' data-easyform='money;real-time;' data-message='请输入正确的出生日期' data-easytip='class:easy-red;position:right;'/></div><div class='form-group'><label for='esal' class='control-label'>员工薪水：</label><input type='TEXT' class='form-control' id='esal' name='sal' placeholder='薪水' data-easyform='money;real-time;' data-message='请输入正确的员工薪水' data-easytip='class:easy-red;position:right;'/></div><div class='form-group'><label for='comm' class='control-label'>员工佣金：</label><input type='TEXT' class='form-control' id='comm' name='comm' placeholder='佣金' data-easyform='money;real-time;' data-message='请输入正确的佣金' data-easytip='class:easy-red;position:right;'/></div><div class='form-group'><label for='dno' class='control-label'>部门编号：</label><input type='TEXT' class='form-control' id='dno' name='deptno' placeholder='部门编号'/></div>";
    var update_html = "<div class='form-group'><label for='eno' class='control-label'>员工编号：</label><input type='TEXT' class='form-control' id='eno' name='empno' placeholder='编号' value='" + no + "' disabled/></div><div class='form-group'><label for='ename' class='control-label'>员工姓名：</label><input type='TEXT' class='form-control' id='ename' name='ename' placeholder='姓名' value='" + name + "'/></div><div class='form-group'><label for='ejob' class='control-label'>员工职位：</label><input type='TEXT' class='form-control' id='ejob' name='job' placeholder='职位' value='" + job + "'/></div><div class='form-group'><label for='mno' class='control-label'>上级编号：</label><input type='TEXT' class='form-control' id='mno' name='mgr' placeholder='上级编号' value='" + mgr + "' data-easyform='length:4;uint:1000 9999;real-time;' data-message='请输入4位上级编号' data-easytip='class:easy-red;position:right;'/></div><div class='form-group'><label for='hiredate' class='control-label'>出生日期：</label><input type='TEXT' class='form-control' id='hiredate' name='hiredate' placeholder='出生日期' value='" + hiredate + "' data-easyform='money;real-time;' data-message='请输入正确的出生日期' data-easytip='class:easy-red;position:right;'/></div><div class='form-group'><label for='esal' class='control-label'>员工薪水：</label><input type='TEXT' class='form-control' id='esal' name='sal' placeholder='薪水' value='" + sal + "' data-easyform='money;real-time;' data-message='请输入正确的员工薪水' data-easytip='class:easy-red;position:right;'/></div><div class='form-group'><label for='comm' class='control-label'>员工佣金：</label><input type='TEXT' class='form-control' id='comm' name='comm' placeholder='佣金' value='" + comm + "' data-easyform='money;real-time;' data-message='请输入正确的佣金' data-easytip='class:easy-red;position:right;'/></div><div class='form-group'><label for='dno' class='control-label'>部门编号：</label><input type='TEXT' class='form-control' id='dno' name='deptno' placeholder='部门编号' value='" + deptno + "'/></div>";
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
            message: add_html,
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
                    $.ajax({
                        async: true, // (默认: true) 默认设置下，所有请求均为异步请求。如果需要发送同步请求，请将此选项设置为 false
                        type: "POST",
                        url: "/add",
                        dataType: "json", // 预期服务器返回的数据类型
                        contentType: "application/json; charset=utf-8",
                        data: JSON.stringify(initInputData()),
                        success: function (data) {
                            dialog.close();
                            if (data.retCode == 0) {
                                layer.msg(data.msg, {
                                    icon: 1,
                                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                                }, function () {
                                    window.location.href = '/refresh';
                                });
                            }
                        },
                        error: function (data) {
                            layer.msg(' 服务器出现异常 ！', {icon: 5});
                        }
                    });
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
        if (typeof (no) == 'undefined' || no == '') {
            layer.msg(' 请选择所要修改的 数据 ！', {icon: 0, time: 2455});
        } else {
            BootstrapDialog.show({
                title: '修改员工信息',
                message: update_html,
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
                        $.ajax({
                            async: true,
                            type: "POST",
                            url: "/update",
                            dataType: "json",
                            contentType: "application/json; charset=utf-8",
                            data: JSON.stringify(initInputData()),
                            success: function (data) {
                                dialog.close();
                                if (data.retCode == 0) {
                                    layer.msg(data.msg, {
                                        icon: 1,
                                        time: 2000
                                    }, function () {
                                        window.location.href = '/refresh';
                                    });
                                }
                            },
                            error: function (data) {
                                layer.msg(' 服务器出现异常 ！', {icon: 5});
                            }
                        });
                    }
                }, {
                    label: '关闭',
                    icon: 'glyphicon glyphicon-remove',
                    action: function (dialog) {
                        dialog.close();
                    }
                }]
            });
        }
    } else if (arg == 'btn-delete') {
        if (typeof (no) == 'undefined' || no == '') {
            layer.msg(' 请选择所要删除的 数据 ！', {icon: 0, time: 2455});
        } else {
            layer.confirm('确定要删除信息 ？', {
                title: '系统提示',
                icon: 3,
                skin: 'layui-layer-lan',
                btn: ['确定', '取消']
                , yes: function () {
                    $.ajax({
                        async: false,
                        type: "POST",
                        url: "/delete/" + no,
                        dataType: "json",
                        success: function (data) {
                            layer.close();
                            if (data.retCode == 0) {
                                layer.msg(data.msg, {
                                    icon: 1,
                                    time: 2000
                                }, function () {
                                    window.location.href = '/refresh';
                                });
                            }
                        },
                        error: function (data) {
                            layer.msg(data.msg, {icon: 5});
                        }
                    });
                    layer.close();
                }
                , cancel: function () {
                    layer.close();
                }
            });
        }
    }
}