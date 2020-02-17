/**
 * 提交回复
 */
function commentToTarget(targetId,type,content) {
    if(!content){
        alert("输入框不能为空");
        return;
    }

    $.ajax({
        type:"POST",
        url:"/comment",
        contentType:'application/json',
        data:JSON.stringify({
            "parentId":targetId,
            "content":content,
            "type":type
        }),
        success:function (response) {
            if(response.code==200){
                //请求成功
                //$("#comment_section").hide();
                window.location.reload();
            }else {
                if(response.code==2003){
                    //说明未登录
                    var isAccepted = confirm(response.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=6d02359770fa1e33a768&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        window.localStorage.setItem("closable",true);
                    }
                }else{
                    alert(response.message);
                }
            }
            console.log(response);
        },
        dataType:"json"
    });
}

/**
 * 向问题提交回复
 */
function post(){
    var questionId=$("#question_id").val();
    var content=$("#comment_content").val();
    commentToTarget(questionId,1,content);
}

/**
 * 向评论提交回复
 */
function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content=$("#input-"+commentId).val();
    commentToTarget(commentId,2,content);
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-"+id);

    if(comments.hasClass("in")){
        //折叠掉二级评论
        comments.removeClass("in");
        e.classList.remove("active");
    }else{
        //展开二级评论
        var subCommentContainer=$("#comment-"+id);
        if(subCommentContainer.children().length>1){
            //若加载过一次了，就不用再发送请求了
            comments.addClass("in");
            e.classList.add("active");
        }else{
            $.getJSON("/comment/"+id,function (data) {
                console.log(data);
                $.each(data.data.reverse(),function (index,comment) {

                    var mediaLeftElement=$("<div/>",{
                        "class":"media-left"
                    }).append($("<img/>",{
                        "class":"media-object img-rounded",
                        "src":comment.user.avatarUrl
                    }));

                    var mediaBodyElement=$("<div/>",{
                        "class":"media-body comment-ava s-p-5"
                    }).append($("<h6/>",{
                        "class":"media-heading",
                        "html":comment.user.name
                    })).append($("<div/>",{
                        "html":comment.content
                    })).append($("<div/>",{
                        "class":"s-menu s-m-t-10 s-p-b-5"
                    }).append($("<span/>",{
                        "class":"pull-right",
                        "html":moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    var mediaElement=$("<div/>",{
                        "class":"media s-m-b-15 s-b-b"
                    }).append(mediaLeftElement)
                        .append(mediaBodyElement);

                    var commentElement=$("<div/>",{
                        "class":"col-lg-12 col-md-12 col-sm-12 col-xs-12 s-p-r-0"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });

                comments.addClass("in");
                e.classList.add("active");
            });
        }
    }
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var pre = $("#tag").val();
    if(pre){
        if(pre.indexOf(value)==-1){
            $("#tag").val(pre+','+value);
        }
    }else{
        $("#tag").val(value);
    }
}

function showSelectTag() {
    $("#dis-none").show();
}