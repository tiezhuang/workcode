    $(function () {

        var t = $("#table_server").bootstrapTable({
            url: 'http://192.168.254.29:8110/user/getUserByPage',
            method: 'get',
            //toolbar: '#toolbar',    //工具按钮用哪个容器
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
            queryParams: function (params) {
                return {
                    pageSize: params.pageSize,                     // 每页记录条数
                    pageNumber: params.pageNumber,                 // 当前页索引
                    user_id: $('#user_id').val(),                        // 姓名
                    //gender: $('#gender').val()                     // 性别
                };
            },
            //queryParams: queryParams,//前端调用服务时，会默认传递上边提到的参数，如果需要添加自定义参数，可以自定义一个函数返回请求参数
            sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
            search: false,      //搜索栏
            strictSearch: true,
            //showColumns: true,     //是否显示所有的列
            showRefresh: false,     //是否显示刷新按钮
            minimumCountColumns: 1,    //最少允许的列数
            clickToSelect: true,    //是否启用点击选中行
            searchOnEnterKey: true,
            columns: [
                {
                    title: 'ID',//表的列名
                    field: 'id',//json数据中rows数组中的属性名
                    align: 'center'//水平居中
                },
                {
                    title: '账号',
                    field: 'userId',
                    align: 'center'
                },
                {
                    title: '密码',
                    field: 'userPassword',
                    align: 'center'
                },
                {
                    //EMAIL
                    title: '创建时间',
                    field: 'createTime',
                    align: 'center'
                },
                {
                    //EMAIL
                    title: '更新时间',
                    field: 'updateTime',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'usersId',
                    align: 'center',
                    formatter: function (value, row, index) {//自定义显示可以写标签哦~
                        return '<a href="http://192.168.254.29:8110/jump/addUser">添加</a> ';
                    }
                }

            ]
        });

        // 查询按钮
        $('#user_query').click(function () {
            $('#table_server').bootstrapTable('refresh')
        }),

          // 下载按钮
            $('#user_download').click(function () {
                var downloadUser ={
                    user_id: $('#user_id').val(),
                }
                   $.ajax({
                     type:'get',
                     data:downloadUser,
                     contentType :'application/json',
                     dataType:'json',
                     url :'http://192.168.168.29:8110/user/userCsv',
                     /*success :function(data) {
                         alert("OK");
                     },
                     error :function(e) {
                         alert("error");
                     }*/
                 });
             }),
        t.on('load-success.bs.table', function (data) {//table加载成功后的监听函数
            console.log("load success");
            $(".pull-right").css("display", "block");
        });

    });
