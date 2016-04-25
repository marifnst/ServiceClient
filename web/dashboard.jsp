<%-- 
    Document   : dashboard
    Created on : Apr 12, 2016, 1:29:18 AM
    Author     : Emerio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="bs/favicon.ico">

        <title>Dashboard Template for Bootstrap</title>

        <!-- Bootstrap core CSS -->
        <link href="bs/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="bs/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="bs/dashboard.css" rel="stylesheet">
        <link rel="stylesheet" href="jquery/jquery-ui.min.css">

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="bs/assets/js/ie-emulation-modes-warning.js"></script>

        <script src="jquery/jquery-2.2.2.min.js" type="text/javascript"></script>
        <script src="datatables/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="datatables/js/dataTables.bootstrap.min.js" type="text/javascript"></script>

        <script src="jquery/jquery-ui.min.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                //$("#dialog").dialog();
            });

            function closeDialog() {

                if ($('#dialog').is(':ui-dialog')) {
                    //alert('close');
                    $("#dialog").dialog('close');
                }
            }

            function showData() {
                $('#main_admin_content').html('');
                $('#main_page_header').html('Buku');
                //$("#example").dataTable().fnDestroy();
                closeDialog();

                if ($.fn.DataTable.isDataTable('#example')) {
                    $("#example").dataTable().fnDestroy();
                }
                showHeaderBuku();
                $('#example').DataTable({
                    "ajax": {url: '<%=request.getContextPath()%>/ServletAdminBuku', data: {"ACTION": "VIEW"}},
                    "bDestroy": true
                });
            }

            function showDataKategori() {
                $('#main_admin_content').html('');
                $('#main_page_header').html('Kategori');
                closeDialog();
                //$("#example").dataTable().fnDestroy();
                if ($.fn.DataTable.isDataTable('#example')) {
                    $("#example").dataTable().fnDestroy();
                }
                showHeaderKategori();
                $('#example').DataTable({
                    "ajax": {url: '<%=request.getContextPath()%>/ServletAdminKategori', data: {"ACTION": "VIEW"}},
                    "bDestroy": true
                });
            }

            function showDataNews() {
                $('#main_admin_content').html('');
                $('#main_page_header').html('News');
                closeDialog();

                //$("#example").dataTable().fnDestroy();
                if ($.fn.DataTable.isDataTable('#example')) {
                    $("#example").dataTable().fnDestroy();
                }
                showHeaderNews();
                $('#example').DataTable({
                    "ajax": {url: '<%=request.getContextPath()%>/ServletAdminNews', data: {"ACTION": "VIEW"}},
                    "bDestroy": true
                });
            }

            function showHeaderBuku(data) {
                //alert(data);
                var finalContent = '<thead>';
                finalContent += '<tr>';
                finalContent += ' <th></th>';
                finalContent += ' <th>ID</th>';
                finalContent += ' <th>NAMA BUKU</th>';
                finalContent += ' <th>SHORT DESCRIPTION</th>';
                finalContent += ' <th>DETAIL DESCRIPTION</th>';
                finalContent += ' <th>IMAGE PATH</th>';
                finalContent += ' <th>HARGA</th>';
                finalContent += ' <th>PENULIS</th>';
                finalContent += ' <th>WAKTU TERBIT</th>';
                finalContent += ' <th>STOK</th>';
                finalContent += ' <th>IS BEST SELLER</th>';
                finalContent += ' <th>IS SPECIAL OFFER</th>';
                finalContent += ' <th>JUMLAH HALAMAN</th>';
                finalContent += ' <th>IS NEW BOOK</th>';
                finalContent += ' <th>ID KATEGORI</th>';
                finalContent += ' <th>CREATED DATE</th>';
                finalContent += ' <th>ISBN 10</th>';
                finalContent += ' <th>ISBN 13</th>';
                finalContent += '</tr>';
                finalContent += '</thead>';
                $('#example').empty();
                $('#example').html(finalContent);
            }

            function showHeaderKategori(data) {
                //alert(data);
                var finalContent = '<thead>';
                finalContent += '<tr>';
                finalContent += ' <th></th>';
                finalContent += ' <th>ID</th>';
                finalContent += ' <th>DESKRIPSI</th>';
                finalContent += '</tr>';
                finalContent += '</thead>';
                $('#example').empty();
                $('#example').html(finalContent);
            }

            function showHeaderNews(data) {
                //alert(data);
                var finalContent = '<thead>';
                finalContent += '<tr>';
                finalContent += ' <th></th>';
                finalContent += ' <th>ID</th>';
                finalContent += ' <th>DESCRIPTION</th>';
                finalContent += ' <th>DETAIL_DESCRIPTION</th>';
                finalContent += ' <th>CREATED_DATE</th>';
                finalContent += ' <th>IS_VALID</th>';
                finalContent += '</tr>';
                finalContent += '</thead>';
                $('#example').empty();
                $('#example').html(finalContent);
            }

// ################################################################
// DELETE
            function deleteBuku(id) {
                $.ajax({
                    url: '<%=request.getContextPath()%>/ServletAdminBuku',
                    data: {"ACTION": "DELETE", "ID": id}
                }).done(function (data) {
                    alert(data);
                    showData();
                });
            }

            function deleteKategori(id) {
                $.ajax({
                    url: '<%=request.getContextPath()%>/ServletAdminKategori',
                    data: {"ACTION": "DELETE", "ID": id}
                }).done(function (data) {
                    alert(data);
                    showDataKategori();
                });
            }

            function deleteNews(id) {
                $.ajax({
                    url: '<%=request.getContextPath()%>/ServletAdminNews',
                    data: {"ACTION": "DELETE", "ID": id}
                }).done(function (data) {
                    alert(data);
                    showDataNews();
                });
            }

// ################################################################
// INSERT

            function showInsertDialog() {
                //alert('insert');
                //closeDialog();
                var finalContent = '<br><table>';
                //finalContent += '<tr><td>ID</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="ID" aria-describedby="basic-addon1" id="input_ID"></div></td>';
                finalContent += '<tr><td>NAMA_BUKU</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="NAMA_BUKU" aria-describedby="basic-addon1" id="input_NAMA_BUKU"></div></td>';
                finalContent += '<tr><td>SHORT_DESCRIPTION</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="SHORT_DESCRIPTION" aria-describedby="basic-addon1" id="input_SHORT_DESCRIPTION"></div></td>';
                finalContent += '<tr><td>DETAIL_DESCRIPTION</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="DETAIL_DESCRIPTION" aria-describedby="basic-addon1" id="input_DETAIL_DESCRIPTION"></div></td>';
                finalContent += '<tr><td>IMAGE_PATH</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="IMAGE_PATH" aria-describedby="basic-addon1" id="input_IMAGE_PATH"></div></td>';
                finalContent += '<tr><td>HARGA</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="HARGA" aria-describedby="basic-addon1" id="input_HARGA"></div></td>';
                finalContent += '<tr><td>PENULIS</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="PENULIS" aria-describedby="basic-addon1" id="input_PENULIS"></div></td>';
                finalContent += '<tr><td>WAKTU_TERBIT</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="WAKTU_TERBIT" aria-describedby="basic-addon1" id="input_WAKTU_TERBIT"></div></td>';
                finalContent += '<tr><td>STOK</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="STOK" aria-describedby="basic-addon1" id="input_STOK"></div></td>';
                finalContent += '<tr><td>IS_BEST_SELLER</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="IS_BEST_SELLER" aria-describedby="basic-addon1" id="input_IS_BEST_SELLER"></div></td>';
                finalContent += '<tr><td>IS_SPECIAL_OFFER</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="IS_SPECIAL_OFFER" aria-describedby="basic-addon1" id="input_IS_SPECIAL_OFFER"></div></td>';
                finalContent += '<tr><td>JUMLAH_HALAMAN</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="JUMLAH_HALAMAN" aria-describedby="basic-addon1" id="input_JUMLAH_HALAMAN"></div></td>';
                finalContent += '<tr><td>IS_NEW_BOOK</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="IS_NEW_BOOK" aria-describedby="basic-addon1" id="input_IS_NEW_BOOK"></div></td>';
                finalContent += '<tr><td>ID_KATEGORI</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="ID_KATEGORI" aria-describedby="basic-addon1" id="input_ID_KATEGORI"></div></td>';
                //finalContent += '<tr><td>CREATED_DATE</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="CREATED_DATE" aria-describedby="basic-addon1" id="input_CREATED_DATE"></div></td>';
                finalContent += '<tr><td>ISBN_10</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="ISBN_10" aria-describedby="basic-addon1" id="input_ISBN_10"></div></td>';
                finalContent += '<tr><td>ISBN_13</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="ISBN_13" aria-describedby="basic-addon1" id="input_ISBN_13"></div></td>';


                finalContent += '</table>';
                finalContent += '<div class="btn-group" role="group" aria-label="..."><button type="button" class="btn btn-default" onclick="insertBukuProcess()">Submit</button></div>';
                $("#dialog").html(finalContent).dialog({
                    width: 'auto'
                });
                $("#input_WAKTU_TERBIT").datepicker();
//                $("#dialog").dialog();
            }

            function insertBukuProcess() {
                alert('insert process');
            }

            function showKategoriDialog() {
                //alert('insert');
                closeDialog();
                var finalContent = '<br><table>';
                //finalContent += '<tr><td>ID</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="ID" aria-describedby="basic-addon1" id="input_ID"></div></td>';
                finalContent += '<tr><td>DESKRIPSI</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="DESKRIPSI" aria-describedby="basic-addon1" id="input_DESKRIPSI"></div></td>';
                finalContent += '</table>';
                finalContent += '<div class="btn-group" role="group" aria-label="..."><button type="button" class="btn btn-default" onclick="insertKategoriProcess()">Submit</button></div>';
                $("#dialog").html(finalContent).dialog({
                    width: 'auto'
                });

//                $("#dialog").dialog();
            }

            function insertKategoriProcess() {
                //alert('insert kat process');
                $.ajax({
                    url: '<%=request.getContextPath()%>/ServletAdminKategori',
                    data: {"ACTION": "INSERT", "DESKRIPSI": $('#input_DESKRIPSI').val()}
                }).done(function (data) {
                    alert(data);
                    closeDialog();
                    showDataKategori();
                });
            }

            function showNewsDialog() {
                //alert('insert');
                closeDialog();
                var finalContent = '<br><table>';
                //finalContent += '<tr><td>ID</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="ID" aria-describedby="basic-addon1" id="input_ID"></div></td>';
                finalContent += '<tr><td>DESCRIPTION</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="DESCRIPTION" aria-describedby="basic-addon1" id="input_DESCRIPTION"></div></td>';
                finalContent += '<tr><td>DETAIL_DESCRIPTION</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="DETAIL_DESCRIPTION" aria-describedby="basic-addon1" id="input_DETAIL_DESCRIPTION"></div></td>';

                finalContent += '</table>';
                finalContent += '<div class="btn-group" role="group" aria-label="..."><button type="button" class="btn btn-default" onclick="insertNewsProcess()">Submit</button></div>';
                $("#dialog").html(finalContent).dialog({
                    width: 'auto'
                });
            }

            function insertNewsProcess() {
                //alert('insert kat process');
                $.ajax({
                    url: '<%=request.getContextPath()%>/ServletAdminNews',
                    data: {"ACTION": "INSERT", "DESCRIPTION": $('#input_DESCRIPTION').val(), "DETAIL_DESCRIPTION": $('#input_DETAIL_DESCRIPTION').val()}
                }).done(function (data) {
                    alert(data);
                    closeDialog();
                    showDataNews();
                });
            }

// ################################################################
// UPDATE
            function showKategoriEditDialog(id) {
                $.ajax({
                    url: '<%=request.getContextPath()%>/ServletAdminKategori',
                    data: {"ACTION": "EDIT_SHOW", "ID": id},
                    dataType: "json"
                }).done(function (data) {
                    //alert(data);
                    var finalContent = '<br><table>';
                    //finalContent += '<tr><td>ID</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="ID" aria-describedby="basic-addon1" id="input_ID"></div></td>';
                    finalContent += '<tr><td>DESKRIPSI</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="DESKRIPSI" aria-describedby="basic-addon1" id="input_DESKRIPSI" value=' + JSON.stringify(data.DESKRIPSI) + '></div></td>';
                    finalContent += '</table>';
                    finalContent += '<div class="btn-group" role="group" aria-label="..."><button type="button" class="btn btn-default" onclick="editKategoriNews(' + data.ID + ')">Submit</button></div>';
                    $("#dialog").html(finalContent).dialog({
                        width: 'auto'
                    });
                });
            }

            function editKategoriNews(id) {
                //alert('SDSD');
                $.ajax({
                    url: '<%=request.getContextPath()%>/ServletAdminKategori',
                    data: {"ACTION": "EDIT", "ID": id, "DESKRIPSI": $('#input_DESKRIPSI').val()}
                }).done(function (data) {
                    alert(data);
                    closeDialog();
                    showDataKategori();
                });
            }

            function showNewsEditDialog(id) {
                $.ajax({
                    url: '<%=request.getContextPath()%>/ServletAdminNews',
                    data: {"ACTION": "EDIT_SHOW", "ID": id},
                    dataType: "json"
                }).done(function (data) {
                    alert(data);
                    var finalContent = '<br><table>';
                    //finalContent += '<tr><td>ID</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="ID" aria-describedby="basic-addon1" id="input_ID"></div></td>';
                    finalContent += '<tr><td>DESCRIPTION</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="DESKRIPSI" aria-describedby="basic-addon1" id="input_DESCRIPTION" value=' + JSON.stringify(data.DESCRIPTION) + '></div></td>';
                    finalContent += '<tr><td>DETAIL_DESCRIPTION</td><td><div class="input-group"><span class="input-group-addon" id="basic-addon1"></span><input type="text" class="form-control" placeholder="DESKRIPSI" aria-describedby="basic-addon1" id="input_DETAIL_DESCRIPTION" value=' + JSON.stringify(data.DETAIL_DESCRIPTION) + '></div></td>';
                    finalContent += '</table>';
                    finalContent += '<div class="btn-group" role="group" aria-label="..."><button type="button" class="btn btn-default" onclick="editNews(' + data.ID + ')">Submit</button></div>';
                    $("#dialog").html(finalContent).dialog({
                        width: 'auto'
                    });
                });
            }

            function editNews(id) {
                //alert('SDSD');
                $.ajax({
                    url: '<%=request.getContextPath()%>/ServletAdminNews',
                    data: {"ACTION": "EDIT", "ID": id, "DESCRIPTION": $('#input_DESCRIPTION').val(), "DETAIL_DESCRIPTION": $('#input_DETAIL_DESCRIPTION').val()}
                }).done(function (data) {
                    alert(data);
                    closeDialog();
                    showDataNews();
                });
            }
        </script>
    </head>

    <body>

        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Book Store Dashboard</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="<%=request.getContextPath()%>/admin.jsp">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar">
                        <li class="active"><a href="#">General Data</a></li>
                        <li><a href="#" onclick="showData()">Buku</a></li>
                        <li><a href="#" onclick="showDataKategori()">Kategori</a></li>
                        <li><a href="#" onclick="showDataNews()">News</a></li>
                    </ul>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header" id="main_page_header">Buku</h1>

                    <div id="main_admin_content"></div>

                    <div class="table-responsive">
                        <table id="example" class="table table-striped table-bordered">
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div id="dialog"></div>
    </body>
</html>
