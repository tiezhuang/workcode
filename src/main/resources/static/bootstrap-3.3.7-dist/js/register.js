    $(function () {



        // 查询按钮
        $('#user_query').click(function () {

        }),

          // 下载按钮
            $('#excel_download').click(function () {
                   $.ajax({
                     type:'get',
                     data:null,
                     contentType :'application/json',
                     dataType:'json',
                     url :'http://192.168.168.29:8110/user/downloadExce',
                     /*success :function(data) {
                         alert("OK");
                     },
                     error :function(e) {
                         alert("error");
                     }*/
                 });
             })


    });
