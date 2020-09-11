$(function () {
    var t = $("#table_server").bootstrapTable({
        //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
        url: 'http://192.168.168.29:8110/file-test/getFileByPage',   //请求方法
        method: 'get',
        toolbar: '#toolbar',    //工具按钮用哪个容器
        striped: true,      //是否显示行间隔色
        cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,     //是否显示分页（*）
        sortable: false,      //是否启用排序
        sortOrder: "asc",     //排序方式
        pageNumber:1,      //初始化加载第一页，默认第一页
        pageSize: 10,      //每页的记录行数（*）
        pageList: [10, 25, 50, 100],  //可供选择的每页的行数（*）
        queryParamsType:'', //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort
                            // 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber

        //queryParams: queryParams,//前端调用服务时，会默认传递上边提到的参数，如果需要添加自定义参数，可以自定义一个函数返回请求参数
        sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
        search: true,      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        //showColumns: true,     //是否显示所有的列
        showRefresh: true,     //是否显示刷新按钮
        minimumCountColumns: 1,    //最少允许的列数
        clickToSelect: true,    //是否启用点击选中行
        searchOnEnterKey: true,
        columns: [
            {
                title: '#',//表的列名
                field: 'id',//json数据中rows数组中的属性名
                align: 'center'//水平居中
            },
            {
                title: '操作记录',
                field: 'fileAction',
                align: 'left'
            },
            {
                title: 'mac',
                field: 'mac',
                align: 'center'
            },
            {
                title: '工号',
                field: 'userId',
                align: 'center'
            },
            {
                title: '操作时间',
                field: 'createTime',
                align: 'center'
            },
             {
                 title: '操作',
                 field: 'usersId',
                 align: 'center',
                 formatter:function(value,row,index){
                     //通过formatter可以自定义列显示的内容
                     //value：当前field的值，即id
                     //row：当前行的数据
                     var a = '<a href="" >测试</a>';
                 }
             }

        ]
    });


    t.on('load-success.bs.table', function (data) {//table加载成功后的监听函数
        console.log("load success");
        $(".pull-right").css("display", "block");
    });

});
