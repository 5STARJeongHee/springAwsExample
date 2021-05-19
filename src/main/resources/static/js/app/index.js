var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function (){
            _this.save();
        });

        $('#btn-update').on('click',function (){
            _this.update();
        });

        $('#btn-delete').on('click',function(){
            _this.delete();
        })

    },
    save : function (){
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        })
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };
        var id = $('#id').val();
        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.')
            window.location.href = '/';
        }).fail(function (error){
            alert(JSON.stringfy(error));
        });

    },
    delete : function (){
        var id = $('#id').val();

        $.ajax({
            type: "DELETE",
            url : "/api/v1/posts/" + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.')
            window.location.href='/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};
main.init();
// 이런식으로 사용할 함수를 바로 만들지 않고 내부 속성으로 준 이유는
// 공동 작업을 할 때 새로운 js 파일이 있고 중복된 이름을 가지고 있다면
// 최근에 읽은 js 파일로 내용이 덮어버리는 문제가 있으므로 이를 피하기 위해
// 별도의 유효 범위를 제한 한 것임