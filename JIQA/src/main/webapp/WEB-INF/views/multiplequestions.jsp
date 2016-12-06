<%@page import="com.jiqa.entity.MultipleAnswerBean"%>
<%@page import="com.jiqa.entity.OptionBean"%>
<%@page import="java.util.Map"%>
<%@page import="com.jiqa.entity.QuestionBean"%>
<%@page import="com.jiqa.service.impl.CategoryServiceImpl"%>
<%@page import="com.jiqa.entity.CategoryBean"%>
<%@page import="java.util.List"%>
<%@page import="com.jiqa.service.CategoryService"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Categories</title>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
textarea{ 
  width: 100%; 
  min-width:100%; 
  max-width:100%; 

  height:360px; 
  min-height:360px;  
  max-height:360px;
}
</style>
<script src="//cdn.ckeditor.com/4.5.11/basic/ckeditor.js"></script>
<style type="text/css">
	.modal-dialog {
	  width: 98%;
	  height: 100%;
	  padding: 0;
	}
	
	.modal-content {
	  height: auto;
	  min-height: 100%;
	  border-radius: 5;
	}
	textarea{ 
	  width: 100%; 
	  min-width:100%; 
	  max-width:100%; 
	
	  height:250px; 
	  min-height:250px;  
	  max-height:250px;
	}
</style>
<script type="text/javascript">
$(document).ready(function (){
	$("#chk1").hide();
	$("#chk2").hide();
	$("#chk3").hide();
	$("#chk4").hide();
	
	$("#hasMultipleAnswers").change(function() {
		var chk = $("#hasMultipleAnswers").prop("checked");
		if(chk) {
			$("#chk1").show();
			$("#chk2").show();
			$("#chk3").show();
			$("#chk4").show();
			$("#radio1").hide();
			$("#radio2").hide();
			$("#radio3").hide();
			$("#radio4").hide();
		} else {
			$("#chk1").hide();
			$("#chk2").hide();
			$("#chk3").hide();
			$("#chk4").hide();
			$("#radio1").show();
			$("#radio2").show();
			$("#radio3").show();
			$("#radio4").show();
		}
	}); 
});
</script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#btnSave').click(function(){
			$('#frm1').submit();
		});
		$('#btnSearch').click(function(){
			$("#frmSearch").change(function() {
			  $("#frmSearch").attr("action", "showques");
			});
			$('#frmSearch').submit();
		});
	});
</script>
<script type="text/javascript">
	function checkFlag(field) {
		document.getElementById("addUpdateFlag").value = field;
		if(field == 'update') {
			document.getElementById("frm1").action = "updateQuestion";
			document.getElementById("btnSave").value = "Update";
			$("#modelTitle").html("Edit Question");
		}
		else if(field == 'add') {
			//$("#cke_1_contents").html('');
			CKEDITOR.instances['answer'].setData('');
       		document.getElementById('question').value = "";
       		document.getElementById('status').selectedIndex = 0;
       		document.getElementById('categoryId').selectedIndex = 0;
			document.getElementById("btnSave").value = "Save";
			$("#modelTitle").html("Add New Question");
		} else if (field == 'search') {
			document.getElementById("frm1").method = "GET";
			document.getElementById("frm1").action = "showques";
			document.getElementById("frm1").submit();
		}
	}
</script>
<script type="text/javascript">
        function onClickMethodQuestion(quesId){
        	var cId = 0;
        	if(quesId != 0) {
				$.get("getQues/quesId",{"quesId" : quesId}, function(data) {
		            cId = data.categoryBean.categoryId;
	            	document.getElementById('question').value = data.question;
	            	CKEDITOR.instances['answer'].setData(data.answer);
	            	//$("#cke_1_contents").html(data.answer);
		            document.getElementById("questionid").value = data.questionId;
		            if(data.status == 1) {
		               	document.getElementById('status').selectedIndex = 0;            		
		            }
		            else {
		               	document.getElementById('status').selectedIndex = 1;            		            		
		            }
		            
		            var cats = document.getElementById("categoryId");
		            for(var i = 0;i < cats.length;i++) {
		            	if(cats[i].value == cId) {
		            		document.getElementById("categoryId").selectedIndex = i;
		            		break;
		            	}
		            } 
            	});
        	}
        }
</script>
</head>
<body>
	<%
		List<QuestionBean> lstQuestions = ((List<QuestionBean>) request.getAttribute("LIST_QUES"));
		pageContext.setAttribute("LIST_QUES", lstQuestions);
		List<CategoryBean> lstCategories = ((List<CategoryBean>) request.getAttribute("LIST_CAT"));
		pageContext.setAttribute("LIST_CAT", lstCategories);
	%>
	<jsp:include page="header2.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-8">
					<div class="modal fade" id="myModal" role="dialog">
					    <div class="modal-dialog">
						<form action="saveMultipleQues" method="POST" name="ques" id="frm1">
						<input type="hidden" id = "questionid" name= "quesid" value = "" />					
						<input type="hidden" id = "addUpdateFlag" value = "" />					
	
					      <!-- Modal content-->
					      <div class="modal-content">
					        <div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h4 class="modal-title"><p id ="modelTitle">Add New Question</p></h4>
					        </div>
					        <div class="modal-body">
								<div class = "row">
									<div class="col-sm-12">
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon">
													 <i class="glyphicon glyphicon-inbox"></i>												
												</span>
												<textarea class="form-control" placeHolder="Enter Question..." id="question" name="question"></textarea>
											</div>
										</div>
										<div class="form-group well well-sm">
										 <u>Has Multiple Answers</u>	
											<input type="checkbox" id="hasMultipleAnswers" name="hasMultipleAnswers" value="1" />
										</div>
										<div class="form-group">
											<div class="alert alert-warning" style="display: none;" id = "divMsg">
	  											<strong>Warning!</strong> Answer in Invalid Format.
											</div>
											<div>
											</div>
											<div class="input-group">
												<span class="input-group-addon">
													 <i>
														 <input type="checkbox" id="chk1" name="chk" value="1" />
														 <input type="radio" id="radio1" name="correctOption" value="0" />
													 </i>												
												</span>
												<input type="text" class="form-control" placeHolder="Enter Option1..." id="op1" name="op1" value="" />
											</div>
										</div>
										<div class="form-group">
											<div class="alert alert-warning" style="display: none;" id = "divMsg">
	  											<strong>Warning!</strong> Answer in Invalid Format.
											</div>
											<div>
											</div>
											<div class="input-group">
												<span class="input-group-addon">
													 <i>
													 	<input type="checkbox" id="chk2" name="chk" value="1" />
														 <input type="radio" id="radio2" name="correctOption" value="1" />
													 </i>												
												</span>
												<input type="text" class="form-control" placeHolder="Enter Option2..." id="op2" name="op2" value="" />
											</div>
										</div>
										<div class="form-group">
											<div class="alert alert-warning" style="display: none;" id = "divMsg">
	  											<strong>Warning!</strong> Answer in Invalid Format.
											</div>
											<div>
											</div>
											<div class="input-group">
												<span class="input-group-addon">
													<i>
														<input type="checkbox" id="chk3" name="chk" value="1" />
														<input type="radio" id="radio3" name="correctOption" value="2" />
													 </i>												
												</span>
												<input type="text" class="form-control" placeHolder="Enter Option3..." id="op3" name="op3" value="" />
											</div>
										</div>
										<div class="form-group">
											<div class="alert alert-warning" style="display: none;" id = "divMsg">
	  											<strong>Warning!</strong> Answer in Invalid Format.
											</div>
											<div>
											</div>
											<div class="input-group">
												<span class="input-group-addon">
													<i>
														<input type="checkbox" id="chk4" name="chk" value="1" />
														 <input type="radio" id="radio4" name="correctOption" value="3" />
													 </i>											
												</span>
												<input type="text" class="form-control" placeHolder="Enter Option4..." id="op4" name="op4" value="" />
											</div>
										</div>
										<div class = "row">
											<div class="col-sm-4">
												<div class="form-group">
													<div class="input-group">
														<span class="input-group-addon">
															 <i class="glyphicon glyphicon-list-alt"></i>												
														</span>
														<select class="form-control" name="status" id="status">
															<option value="1">Active</option>
															<option value="0">Inactive</option>
														</select>
													</div>
												</div>
											
											</div>
											<div class="col-sm-4">
												<div class="form-group">
													<div class="input-group">
														<span class="input-group-addon">
															 <i class="glyphicon glyphicon-list"></i>												
														</span>
														<select class="form-control" name="categoryId" id="categoryId">
															<c:forEach items="${LIST_CAT}" var="obj">
																<option value="${obj.categoryId}">${obj.title}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-sm-4" >
												<div class="form-group">
														 <input type="button" class="btn btn-primary" style="width:40%" data-dismiss="modal" value="Save" id="btnSave" />
								  						 <button type="button" class="btn btn-default" style="width:40%" data-dismiss="modal">Close</button>
												</div>
											</div>
										</div>
									</div>
					        	</div>
					        </div>
					      </div>
					      </form>
					    </div>
					  </div>
				</div>
				<div class="col-sm-4">
				</div>
		</div>
		<div class="form-group">
			<div class="row">
				<div class="col-sm-1">
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="checkFlag('add'); onClickMethodQuestion('0');" >Add New</button>
				</div>
				<div class="col-sm-5">
				</div>
				<div class="col-sm-4">
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon">
								 <b class="glyphicon glyphicon-search"> Category</b>												
							</span>
							<select class="form-control" name="categoryId">
								<option value="0">Select</option>
								<c:forEach items="${LIST_CAT}" var="obj">
									<option value="${obj.categoryId}">${obj.title}</option>
								</c:forEach>
						</select>
						</div>
					</div>
				</div>
				<div class="col-sm-2">
					<button type="button" id = "btnSearch" class="btn btn-info"><span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;&nbsp;Search</button>
				</div>
			</div>
		</div>
	</div>
	<c:forEach items="${LIST_QUES}" var="obj" varStatus = "i">
		<div class="container well">
			<div class="form-group">
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label><b>Question</b></label>
							<hr style="border-color: black;" />
							<div class="row">
								<div class="col-sm-12">
									<b style="color: black;">${i.index + 1}.</b>
									<pre class = "well-lg btn-info" style="width: 100%; font-size: large;">${obj.question}</pre>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12">
									<label><b>Options</b></label>
									<hr style="border-color: black;" />
								</div>
							</div>
						</div>
						
						<%
							Map<Object, Object> map = (Map<Object, Object>) request.getAttribute("MAP_OPTIONS");
							List<OptionBean> lstOptions = (List<OptionBean>)map.get(pageContext.getAttribute("obj"));
							pageContext.setAttribute("LIST_OPTIONS" , lstOptions);
							
							Map<Object, Object> answersMap = (Map<Object, Object>) request.getAttribute("MAP_ANSWERS");
							List<MultipleAnswerBean> lstAnswers = (List<MultipleAnswerBean>)answersMap.get(pageContext.getAttribute("obj"));
							pageContext.setAttribute("LIST_ANSWERS" , lstAnswers);
						%>
						<c:forEach var="op" items="${LIST_OPTIONS}" varStatus="i">
							<c:forEach var="ans" items="${LIST_ANSWERS}">
								<div class="form-group">
									<div class="row">
										<div class="col-sm-12">
											<c:set var = "color" value = "btn-warning" />
											<div class = "well-sm ${color}" style="width: 100%">
											<c:if test="${ans.optionBean.optionId == op.optionId && obj.questionId == ans.multipleQuestionBean.questionId}">
												<c:set var = "color" value = "btn-success" />
												<c:set var = "image" value = "red-tick.png" />
												<img src="images/${image}" />
											</c:if>
												${op.optionValue}
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:forEach>
						<!-- <div class="form-group">
							<div class="row">
								<div class="col-sm-12">
									<div class = "well-sm btn-warning" style="width: 100%">
										<b style="color: black;">B.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12">
									<div class = "well-sm btn-success" style="width: 100%">
										<b style="color: black;">C.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12">
									<div class = "well-sm btn-warning" style="width: 100%">
										<b style="color: black;">D.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
									</div>
								</div>
							</div>
						</div> -->
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<!-- <div class="container well">
		<div class="form-group">
			<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
						<label><b>Question</b></label>
						<hr style="border-color: black;" />
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-lg btn-info" style="width: 100%">
									What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<label><b>Options</b></label>
								<hr style="border-color: black;" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-sm btn-warning" style="width: 100%">
									<b style="color: black;">A.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-sm btn-warning" style="width: 100%">
									<b style="color: black;">B.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-sm btn-success" style="width: 100%">
									<b style="color: black;">C.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-sm btn-warning" style="width: 100%">
									<b style="color: black;">D.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container well">
		<div class="form-group">
			<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
						<label><b>Question</b></label>
						<hr style="border-color: black;" />
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-lg btn-info" style="width: 100%">
									What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<label><b>Options</b></label>
								<hr style="border-color: black;" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-sm btn-warning" style="width: 100%">
									<b style="color: black;">A.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-sm btn-warning" style="width: 100%">
									<b style="color: black;">B.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-sm btn-success" style="width: 100%">
									<b style="color: black;">C.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-sm btn-warning" style="width: 100%">
									<b style="color: black;">D.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container well">
		<div class="form-group">
			<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
						<label><b>Question</b></label>
						<hr style="border-color: black;" />
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-lg btn-info" style="width: 100%">
									What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<label><b>Options</b></label>
								<hr style="border-color: black;" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-sm btn-warning" style="width: 100%">
									<b style="color: black;">A.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-sm btn-warning" style="width: 100%">
									<b style="color: black;">B.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-sm btn-success" style="width: 100%">
									<b style="color: black;">C.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12">
								<div class = "well-sm btn-warning" style="width: 100%">
									<b style="color: black;">D.</b> What is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkjWhat is aa? hhhkjhdkjghdfghfdkj
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> -->
</body>
</html>