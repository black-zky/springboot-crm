layui.use(['form', 'layer', 'laydate', 'upload'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload;

    //执行一个laydate实例
    laydate.render({
        elem: '.birthday' //指定元素
    });

    upload.render({
        elem: '#test1' //绑定元素
        , url: '/uploadImage.do/' //上传接口,
        , field: "headImage" //指定文件域名
        , done: function (res) {
            console.log(res);
            layer.msg(res.msg);
            $("#himg").val(res.data);
        }
        , error: function () {
            //请求异常回调
        }
        , before: function (res) {
            //上传之前预读本地文件示例
            res.preview(function (index, file, result) {
                $("#demo1").attr("src", result);
            })
        }
    });

    //请求获取数据
    //动态绑定下拉菜单的数据
    var html;
    $.ajax({
        url: '/depts',
        type: 'GET',
        success: function (res) {
            var data = res.data;
            html += "<option value=''>------------请选择------------</option>"
            for (i = 0; i < data.length; i++) {
                html += ("<option value='" + data[i].id + "'>" + data[i].dname + "</option>")
            }
            $("#userDept").append(html);
            form.render('select');
        }
    });

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        var params=$('#dataform').serialize();
        console.log(params);
        $.post('/users',params,function (res) {
            if(res.code==1003){
                top.layer.close(index);
                top.layer.msg(res.msg);
                var index=parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
                parent.location.reload();
            }
        },'json');

        return false;
    })
})