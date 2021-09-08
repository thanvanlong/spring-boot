var stompClient = null;
function connect() {
    var socket = new SockJS('/websocket');
        stompClient = Stomp.over(socket);
        //stompClient.debug = null;
        stompClient.connect({}, function (frame) {
            //console.log('Connected: ' + frame);
            stompClient.subscribe("/topic/comment", function (cmt) {
                if(stompClient.subscribe()){
                    console.log(JSON.parse(cmt.body).user.avatar);
                    showMessage(JSON.parse(cmt.body).post.id,
                                JSON.parse(cmt.body).text,
                                JSON.parse(cmt.body).user.avatar,
                                JSON.parse(cmt.body).time,
                                JSON.parse(cmt.body).user.lastName + " "
                        + JSON.parse(cmt.body).user.firstName)

                }

            });

        });


}



function sendCmt(event,dc) {
    var id_post = dc.previousElementSibling.value;
    var id_user = dc.nextElementSibling.value;
    var text = dc.value;
    if(event.keyCode === 13){
        stompClient.send("/app/comment/" + id_post + '-' + id_user ,{},JSON.stringify({'text' : text}));
    }



}
//
function showMessage(id_post,text,img,time,name){
    var allInput = document.getElementsByTagName('input');
    var data ="\t<li>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"comet-avatar\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<img src=\""+img+"\" alt=\"\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"we-comment\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"coment-head\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<h5><a href=\"time-line.html\" title=\"\">"+name+"</a></h5>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<span>"+time+"</span>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a class=\"we-reply\" href=\"#\" title=\"Reply\"><i class=\"fa fa-reply\"></i></a>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<p>"+text+"\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<i class=\"em em-smiley\"></i>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</p>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t</li>"
    for (let i = 0; i < allInput.length ; i++) {
        if(allInput[i].value == id_post && allInput[i].type == 'hidden'){
            //console.log();
            allInput[i].parentElement.parentElement.parentElement.insertAdjacentHTML('afterbegin',data);
        }
    }
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();


});