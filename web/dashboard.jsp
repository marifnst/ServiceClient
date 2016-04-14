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

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="bs/assets/js/ie-emulation-modes-warning.js"></script>

        <script src="jquery/jquery-2.2.2.min.js" type="text/javascript"></script>
        <script src="datatables/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="datatables/js/dataTables.bootstrap.min.js" type="text/javascript"></script>

        <script type="text/javascript">
//            $(document).ready(function () {
//                $('#example').DataTable();
//            });

            function showData() {
                $('#main_admin_content').html('');
                //$("#example").dataTable().fnDestroy();

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
                    <h1 class="page-header">Buku</h1>

                    <div id="main_admin_content"></div>

                    <div class="table-responsive">
                        <table id="example" class="table table-striped table-bordered">
                            <!--                            <thead>
                                                            <tr>
                                                                <th>ID</th>
                                                                <th>NAMA BUKU</th>
                                                                <th>SHORT DESCRIPTION</th>
                                                                <th>DETAIL DESCRIPTION</th>
                                                                <th>IMAGE PATH</th>
                                                                <th>HARGA</th>
                                                                <th>PENULIS</th>
                                                                <th>WAKTU TERBIT</th>
                                                                <th>STOK</th>
                                                                <th>IS BEST SELLER</th>
                                                                <th>IS SPECIAL OFFER</th>
                                                                <th>JUMLAH HALAMAN</th>
                                                                <th>IS NEW BOOK</th>
                                                                <th>ID KATEGORI</th>
                                                                <th>CREATED DATE</th>
                                                                <th>ISBN 10</th>
                                                                <th>ISBN 13</th>
                                                            </tr>
                                                        </thead>-->
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <!--        <script src="jquery/jquery-2.2.2.min.js"></script>-->
        <!--<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>-->
        <!--        <script src="bs/dist/js/bootstrap.min.js"></script>
                 Just to make our placeholder images work. Don't actually copy the next line! 
                <script src="bs/assets/js/vendor/holder.min.js"></script>
                 IE10 viewport hack for Surface/desktop Windows 8 bug 
                <script src="bs/assets/js/ie10-viewport-bug-workaround.js"></script>-->
    </body>
</html>
