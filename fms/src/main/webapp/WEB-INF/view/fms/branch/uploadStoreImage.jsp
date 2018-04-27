<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/common/commonutil.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/fms/branch/uploadStoreImageInit.js"></script>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script> --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fms/branch/uploadStoreImage.css" />
<input type="hidden" name="storeId" id="uploadImage_storeId" value="${storeId}">
<input type="hidden" name="operate" id="uploadImage_operate" value="${operate}">

<div class="demo">
	<div id="main_image">
		<img id="main_image_img" src="<%=request.getContextPath()%>/img/unUploadStoreImage.jpg">
	</div>
	<ul class="gallery_demo_unstyled gallery_demo" id="allImagelist">
	    <%-- <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/01.jpg" alt="Flowing Rock" title="Flowing Rock Caption"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/02.jpg" alt="Stones" title="Stones - from Apple images"></li>
	    <li onclick="liOnClick(this)" class="active"><img src="<%=request.getContextPath()%>/img/03.jpg" alt="Grass Blades" title="Apple nature desktop images"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/04.jpg" alt="Ladybug" title="Ut rutrum, lectus eu pulvinar elementum, lacus urna vestibulum ipsum"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/05.jpg" alt="Lightning" title="Black &amp; White"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/06.jpg" alt="Lotus" title="Fusce quam mi, sagittis nec, adipiscing at, sodales quis"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/07.jpg" alt="Mojave" title="Suspendisse volutpat posuere dui. Suspendisse sit amet lorem et risus faucibus pellentesque."></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/08.jpg" alt="081" title="08"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/09.jpg" alt="091" title="09"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/10.jpg" alt="091" title="09"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/11.jpg" alt="091" title="09"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/12.jpg" alt="091" title="09"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/13.jpg" alt="091" title="09"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/14.jpg" alt="091" title="09"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/15.jpg" alt="091" title="09"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/16.jpg" alt="091" title="09"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/17.jpg" alt="091" title="09"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/18.jpg" alt="091" title="09"></li>
	    <li onclick="liOnClick(this)"><img src="<%=request.getContextPath()%>/img/19.jpg" alt="091" title="09"></li> --%>
	</ul>
	<p class="nav">
		<!-- <a href="#" onclick="$.galleria.prev(); return false;">&laquo; previous</a> | 
		<a href="#" onclick="$.galleria.next(); return false;">next &raquo;</a> -->
	</p>
</div>
<div  id="uploadImage_ImageTableDiv">
	<form id="uploadImage_ImageForm" enctype="multipart/form-data" method="post" >
		<div class="top_table">
			<table class='input_table' width="100%" border="0" cellspacing="0" cellpadding="0" >
				<tr>
					<td class="table_text" align="right">请选择照片</td>
					<td >
						<span class="comboSpan"></span><!-- onchange="uploadAgentImage('select')" -->
						<input type="file" name="storeImage" id="uploadFile_storeImage"/>
						<span id="uploadFile_div">
							<a href="#" onclick="upoloadStoreImage()" class="easyui-linkbutton e-cis_button" iconCls="icon-tick">上传</a>
							<a href="#" onclick="deleteStoreImage()" class="easyui-linkbutton e-cis_button" iconCls="icon-cancel">删除</a>
						</span>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
