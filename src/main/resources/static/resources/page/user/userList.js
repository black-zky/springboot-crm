layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url : '/users',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'telephone', title: '电话', minWidth:100, align:"center"},
            {field: 'realname', title: '姓名', minWidth:100, align:"center"},
            {field: 'birthday', title: '生日', minWidth:100, align:"center"},
            {field: 'available', title: '状态', align:'center', templet:function (row) {
                    return row.available==1?"启用":"禁用";
                }},
            {field: 'dept', title: '部门',  align:'center', templet:function (row) {
                    return row.dept.dname;
                }},
            {title: '操作', minWidth:175, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        // table.reload("newsListTable",{
        //     page: {
        //         curr: 1 //重新从第 1 页开始
        //     },
        //     where: {
        //         key: $(".searchVal").val()  //搜索的关键字
        //     }
        // })
        var name = $(".searchVal").val();
        table.reload('userListTable', {url: "users?realname=" + name});
    });

    // $('#search_input').bind('keyup', function(event) {
    //     if (event.keyCode == "13") {
    //         var name=$(".searchVal").val();
    //         table.reload('userListTable',{url:"users?realname="+name});
    //     }
    // });

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加用户",
            type : 2,
            content : "toUserAddPage.do",
            success : function(layero, index){
                // var body = layui.layer.getChildFrame('body', index);
                // if(edit){
                //     body.find(".userName").val(edit.userName);  //登录名
                //     body.find(".userEmail").val(edit.userEmail);  //邮箱
                //     body.find(".userSex input[value="+edit.userSex+"]").prop("checked","checked");  //性别
                //     body.find(".userGrade").val(edit.userGrade);  //会员等级
                //     body.find(".userStatus").val(edit.userStatus);    //用户状态
                //     body.find(".userDesc").text(edit.userDesc);    //用户简介
                //     form.render();
                // }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".addNews_btn").click(function(){
        addUser();
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            userIds = [];
        if(data.length > 0) {
            for (var i in data) {
                userIds.push(data[i].id);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                $.delete("users",{
                    userIds : userIds  //将需要删除的newsId作为参数传入
                },function(res){
                    if(res.code==200){
                        layer.msg("删除成功");
                        tableIns.reload();
                        layer.close(index);
                    } else{
                        layer.msg("删除成功");
                    }

                })
            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此用户？",
                btnText = "已禁用";
            if(_this.text()=="已禁用"){
                usableText = "是否确定启用此用户？",
                btnText = "已启用";
            }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
                _this.text(btnText);
                layer.close(index);
            },function(index){
                layer.close(index);
            });
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
                // $.get("删除文章接口",{
                //     newsId : data.newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                    tableIns.reload();
                    layer.close(index);
                // })
            });
        }
    });

})
