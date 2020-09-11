var productList=[];　　//订单数据
//生成订单数据
$.ajax({
    url:'http://localhost:8110/file-test/getFileByPage',
    type:'get',
    async:false,
    success:function(res){
        var response=JSON.parse(decodeURIComponent(res));
        productList=response;
        $('#table_server').bootstrapTable({
            data:response,
            height:base.countTbodyHeight("#tb_data") + 100, //高度调整
            striped: true,                          //是否显示行间隔色
            pagination: true,                       //是否显示分页（*）
            sortable: true,                         //是否启用排序
            pageSize:10,                            //单页记录数
            pageList:[5,10,20,30],                  //分页步进值
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
    }
});