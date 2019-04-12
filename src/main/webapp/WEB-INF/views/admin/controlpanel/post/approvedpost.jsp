<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<section class="content-header">
	<h1>
		Bài đã duyệt <small>Tuyển sinh HVN</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/home"><i class="fa fa-home"></i> Trang chủ</a></li>
		<li class="active">Bài viết</li>
		<li class="active">Bài đã duyệt</li>
	</ol>
</section>
<br>

<div style = "height: 100%">
	<div class="box">
		<div class="box-header"  style = "text-align: center;">
			<h5 class="box-title">Danh sách bài viết đã duyệt</h5>
		</div>
		<!-- /.box-header -->
		<div class="box-body">
			<div id="example2_wrapper"
				class="dataTables_wrapper form-inline dt-bootstrap">
				<div class="row">
					<div class="col-sm-6"></div>
					<div class="col-sm-6"></div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<table id="example2"
							class="table table-bordered table-hover dataTable" role="grid"
							aria-describedby="example2_info">
							<thead>
								<tr role="row">
									<th>Tiêu đề</th>
									<th>Chuyên mục</th>
									<th>Tác giả</th>
									<th>Thời gian sửa</th>
									<th>Thời gian xuất bản</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr role="row" class="odd">
									<td class="">Chất lượng đào tạo hàng đầu Việt Nam</td>
									<td class="sorting_1">Vì sao chọn HVN</td>
									<td>Hainx</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td><a href="newpost"> Duyệt bài </a></td>
								</tr>
								<tr role="row" class="odd">
									<td class="">Đội ngũ giảng viên trình độ cao</td>
									<td class="sorting_1">Vì sao chọn HVN</td>
									<td>HaiNX</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td><a href="newpost"> Duyệt bài </a></td>
								</tr>
								<tr role="row" class="odd">
									<td class="">Diều kiện cơ sở vật chất khang trang, chất
										lượng cao</td>
									<td class="sorting_1">Vì sao chọn HVN</td>
									<td>HaiNX</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td><a href="newpost"> Duyệt bài </a></td>
								</tr>
								<tr role="row" class="odd">
									<td class="">Điều kiện sống và sinh hoạt thuận tiện</td>
									<td class="sorting_1">Vì sao chọn HVN</td>
									<td>HaiNX</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td><a href="newpost"> Duyệt bài </a></td>
								</tr>
								<tr role="row" class="odd">
									<td class="">Cơ hội việc làm rộng mở</td>
									<td class="sorting_1">Vì sao chọn HVN</td>
									<td>HaiNX</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td><a href="newpost"> Duyệt bài </a></td>
								</tr>
								<tr role="row" class="odd">
									<td class="">Thông tin tuyển sinh 2019</td>
									<td class="sorting_1">Thông tin tuyển sinh</td>
									<td>HaiNX</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td><a href="newpost"> Duyệt bài </a></td>
								</tr>
								<tr role="row" class="odd">
									<td class="">Ngành công nghệ thông tin</td>
									<td class="sorting_1">Ngành đào tạo</td>
									<td>HaiNX</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td><a href="newpost"> Duyệt bài </a></td>
								</tr>
								<tr role="row" class="odd">
									<td class="">Ngành bảo vệ thực vật</td>
									<td class="sorting_1">Ngành đào tạo</td>
									<td>HaiNX</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td><a href="newpost"> Duyệt bài </a></td>
								</tr>
								<tr role="row" class="odd">
									<td class="">Cơ hội hoạt động ngoại khóa và hoạt động xã
										hội phong phú</td>
									<td class="sorting_1">Vì sao chọn HVN</td>
									<td>HaiNX</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td><a href="newpost"> Duyệt bài </a></td>
								</tr>
								<tr role="row" class="odd">
									<td class="">Chương trình đào tạo tiên tiến, chất lượng
										cao</td>
									<td class="sorting_1">Ngành đào tạo</td>
									<td>HaiNX</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td>05/04/2019 01:14:01 AM</td>
									<td><a href="newpost"> Duyệt bài </a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-5">
						<div class="dataTables_info" id="example2_info" role="status"
							aria-live="polite">Hiển thị 1 đến 10 trong số 57 mục</div>
					</div>
					<div class="col-sm-7">
						<div class="dataTables_paginate paging_simple_numbers"
							id="example2_paginate">
							<ul class="pagination">
								<li class="paginate_button previous disabled"
									id="example2_previous"><a href="#"
									aria-controls="example2" data-dt-idx="0" tabindex="0">Đầu</a></li>
								<li class="paginate_button active"><a href="#"
									aria-controls="example2" data-dt-idx="1" tabindex="0">1</a></li>
								<li class="paginate_button "><a href="#"
									aria-controls="example2" data-dt-idx="2" tabindex="0">2</a></li>
								<li class="paginate_button "><a href="#"
									aria-controls="example2" data-dt-idx="3" tabindex="0">3</a></li>
								<li class="paginate_button "><a href="#"
									aria-controls="example2" data-dt-idx="4" tabindex="0">4</a></li>
								<li class="paginate_button "><a href="#"
									aria-controls="example2" data-dt-idx="5" tabindex="0">5</a></li>
								<li class="paginate_button "><a href="#"
									aria-controls="example2" data-dt-idx="6" tabindex="0">6</a></li>
								<li class="paginate_button next" id="example2_next"><a
									href="#" aria-controls="example2" data-dt-idx="7" tabindex="0">Next</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.box-body -->
	</div>
</div>