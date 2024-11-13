<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DANDI</title>
<style>
   .row{
      border-bottom: 1px solid #ddd;
   }
   #comments, #replyList textarea{
      width:90%;
      height:100px;
      border-radius:5px;
   }
   
   #replyList{
      padding: 20px 0;
   }
    #imgBox1 {
      width: 100%;
      height: 500px;
      overflox: hidden;
      margin: 0 auto;
   }
   
   #imgContent1 {
      width: 100%;
      height: 100%;
      object-fit: cover;
   }
</style>
<script>
   function courseDelCheck(){
      if(confirm("현재글을 삭제하시겠습니까?")){
         location.href="/mini/course/courseDel?news_no=${vo.news_no}";
      }
   }
   /*댓글*/
   $(function(){
      //글목록
      function replyList(){
         
         $.ajax({
            url:'/mini/courseReply/list',
            type: 'get',
            data:{
               news_no:${vo.news_no}
            },success:function(result){
               console.log(result);

               var replyTag = "";
               $(result).each(function(idx, rVO){
                  replyTag += `<div><div><b>`+rVO.userid+`</b>(`+rVO.writedate+`)`;
   
                  if(rVO.userid == '${logId}'){
                     replyTag += `<input type='button' value='Edit' class="btn btn-outline-secondary" style="margin: 10px;"/>
                               <input type='button' value='Del' class="btn btn-outline-danger" alt='`+rVO.reply_no+`'/>`;
                     replyTag += `</div><p>`+ rVO.comments+ `</p></div>
                               <div style='display:none'>
                                  <form method='post'>
                                     <textarea name='comments' id='comments`+rVO.reply_no+`'>`+rVO.comments+`</textarea>
                                     <input type='hidden' name='reply_no' value='`+rVO.reply_no+`'/>
                                     <input type='submit' value='댓글수정하기'/>
                                  </form>
                               </div>
                               <hr/>`;               
                  }else{
                     replyTag += `</div><p>`+ rVO.comments+ `</p></div><hr/>`;   
                  }
               });
               $("#replyList").html(replyTag);
            },error:function(e){
               console.log(e.responseText);
            }
         });
         
      }
      //글등록
      $("input[value=SAVE]").click(function(){
         if($("#comments").val()==''){//댓글란이 공백일떄
            alert("댓글을 입력 후 저장하세요..");
            return false;
         }
         //댓글이 있을때
         var params = $("#replyForm").serialize(); //데이터준비
         var url = "/mini/courseReply/writeOk";
         
         $.ajax({
            type:'post',
            url: url,
            data: params,
            success:function(result){
               if(result=='1'){//댓글등록시
                  $("#comments").val('');
                  //댓글목록 업데이트
                  replyList();
               }else{//댓글등록실패시
                  alert("댓글..등록실패..");
               }
               console.log(result);
            },error:function(e){
               console.log(e.responseText);
            }
         })
      })
      
      //댓글수정폼 보여주기
      $(document).on('click','input[value=Edit]',function(){
         $(this).parent().parent().css('display','none');
         $(this).parent().parent().next().css('display','block');
      });
      
      //댓글수정 DB업데이트
      $(document).on('submit','#replyList form', function(){
         event.preventDefault();//기본이벤트제거
         
         //댓글수정폼의 댓글입력유무 확인
         console.log($(this).children('textarea').val())
         
         if($(this).children('textarea').val()==''){
            alert('댓글입력 후 수정..')
         }else{
            var params = $(this).serialize();
            $.ajax({
               type:'post',
               data:params,
               url:'/mini/courseReply/edit',
               success: function(result){
                  console.log(result)
                  
                  if(result=='0'){
                     alert("수정실패..");
                  }else
                     replyList();
                  
               },
               error: function(e){
                  console.log(e.responseText);
               }
            });
            
         } 
      });
      
      //댓글삭제
      $(document).on('click','#replyList input[value=Del]',function(){
         if(confirm("댓글 정말 삭제할거에용?")){
            var reply_no = $(this).attr('alt');
            console.log('reply_no',reply_no);
            
            $.ajax({
               url:'/mini/courseReply/delete',
               data:{
                  reply_no: reply_no
               },
               success:function(result){
                  console.log(result)
                  
                  if(result==0)
                     alert("삭제실패..")
                  replyList();
               },
               error:function(e){
                  console.log(e)
               }
            })
         }
      });
      
      replyList();
   })
   
</script>
</head>
<body>
   <div id="imgBox1">
      <img src="/mini/images/main/카페1.jpg" id="imgContent1" />
   </div>
<div class="container" style="width: 60%;">
   <h2 style="text-align: center; padding: 20px 0px; margin: 50px;">코스내용보기📌</h2>
   <div class="row">
      <div class="col-sm-1 p-2"><b>📅일정</b></div>
      <div class="col-sm-2 p-2">약  ${vo.schedule}일소요</div>
      <div class="col-sm-1 p-2"><b>👋작성자</b></div>
      <div class="col-sm-2 p-2">${vo.userid}</div>
      <div class="col-sm-1 p-2"><b>👀조회수</b></div>
      <div class="col-sm-1 p-2">${vo.hit}</div>
      <div class="col-sm-1 p-2"><b>⏱️등록일</b></div>
      <div class="col-sm-3 p-2">${vo.writedate}</div>
   </div>
   <div class="row">
      <div class="col-sm-1 p-2"><b>📒제목</b></div>
      <div class="col-sm-11 p-2">${vo.subject}</div>
   </div>   
   <div class="row">
      <div class="col-sm-12 p-2" style="text-align: center;">${vo.content}</div>
   </div>   
   <c:if test="${logStatus=='Y' && logId==vo.userid}">
   <div class="row">
      <div class="col-sm-12 p-2" style="text-align:center;">
         <button type="button" class="btn btn-outline-secondary" onclick="location.href='/mini/course/courseEdit?news_no=${vo.news_no}'">수정</button>
         <button type="button" class="btn btn-danger" onclick="courseDelCheck()">삭제</button>
      </div>
   </div>
   </c:if>
   <!-- 댓글기능 -->
   <div id="replyArea" style="width:100%;">
      <!-- 로그인-> 댓글 폼 -->
      <c:if test="${logStatus=='Y'}">
         <form method="post" id="replyForm" style="text-align:center;">
            <br/><textarea name="comments" id="comments" placeholder="댓글을 입력해주세요.."></textarea><br/>
            <input type="button" class="btn btn-outline-secondary" id="save" value="SAVE"/>
            <!-- 원글 글번호 -->
            <input type="hidden" name="news_no" value="${vo.news_no}"/>
         </form>
      </c:if>
      
      <!-- 댓글목록 -->
         <br/><div style="font-size:14pt;"><b>📌댓글목록</b>    💬${vo.reply_count}</div>
         <div id="replyList"></div>
      </div>
</div>
</body>
</html>

