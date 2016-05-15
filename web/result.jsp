<%-- 
    Document   : dashboard
    Created on : Apr 12, 2016, 1:29:18 AM
    Author     : Emerio
--%>

<%@page import="com.client.util.UtilResult"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
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
        <script src="chart/Chart.bundle.js"></script>

        <script type="text/javascript">
            var tes;

            $(document).ready(function () {
                loadDataAll();
            });

            function loadDataAll() {
                //$('#canvas').html('');
                //alert(threadid);
                $.ajax({
                    type: 'post',
                    url: '<%=request.getContextPath()%>/ServletResult',
                    data: {ACTION: 'SHOW_ALL'},
                    success: function (data) {
                        var dataJson = $.parseJSON(data);
                        $.each(dataJson, function (index, element) {
                            showChartAll(element);
                        });
                    }
                });
            }

            function showChartAll(element) {
                var datasetArray = [];
                var dataTemp = [];
                var threadIdArray = [];
                //alert(element.length);
                $.each(element, function (indexx, elementt) {
                    threadIdArray.push(elementt["ID_THREAD"]);
                    var temp = [];
                    temp.push(elementt["SIMULASI_1"] / Math.pow(10, 9));
                    temp.push(elementt["SIMULASI_2"] / Math.pow(10, 9));
                    temp.push(elementt["SIMULASI_3"] / Math.pow(10, 9));
                    temp.push(elementt["SIMULASI_4"] / Math.pow(10, 9));

//                    temp[0] = elementt["SIMULASI_1"];
//                    temp[1] = elementt["SIMULASI_2"];
//                    temp[2] = elementt["SIMULASI_3"];
//                    temp[3] = elementt["SIMULASI_4"];
                    //alert(temp[0]);
                    dataTemp.push(temp);
                });

                for (i = 0; i < element.length; i++) {
                    var datasetTemp = {};
                    datasetTemp["label"] = threadIdArray[i];
                    datasetTemp["data"] = dataTemp[i];
                    //alert(threadIdArray[i]);
                    datasetArray.push(datasetTemp);
                }

                var barChartData = {
                    labels: ["Simulasi 1", "Simulasi 2", "Simulasi 3", "Simulasi 4"],
                    datasets: datasetArray
                };
                generateChart(barChartData);
            }

            function loadDataGeneral(threadid) {
                //$('#canvas').html('');
                //alert(threadid);
                $.ajax({
                    type: 'post',
                    url: '<%=request.getContextPath()%>/ServletResult',
                    data: {ACTION: 'SHOW_BY_THREAD', ID: threadid},
                    success: function (data) {
                        var dataJson = $.parseJSON(data);
                        $.each(dataJson, function (index, element) {
                            $.each(element, function (indexx, elementt) {
                                showChartGeneral(threadid, elementt["SIMULASI_1"], elementt["SIMULASI_2"], elementt["SIMULASI_3"], elementt["SIMULASI_4"]);
                            });
                        });
                    }
                });
            }

            function showChartGeneral(threadid, hasil_1, hasil_2, hasil_3, hasil_4) {
                var barChartData = {
                    labels: ["Simulasi 1", "Simulasi 2", "Simulasi 3", "Simulasi 4"],
                    datasets: [{
                            label: 'General Chart for ID ' + threadid,
                            backgroundColor: "#FF0000",
                            data: [hasil_1 / Math.pow(10, 9), hasil_2 / Math.pow(10, 9), hasil_3 / Math.pow(10, 9), hasil_4 / Math.pow(10, 9)]
                        }]
                };
                generateChart(barChartData);
            }

            function generateChart(barChartData) {
                var pieChartContent = document.getElementById('main_admin_content');
                pieChartContent.innerHTML = '&nbsp;';
                $('#main_admin_content').append('<canvas id="canvas"><canvas>');
                
                var ctx = document.getElementById("canvas").getContext("2d");
//                window.myBar = new Chart(ctx, {
                tes = new Chart(ctx, {
                    type: 'bar',
                    data: barChartData,
                    options: {
                        // Elements options apply to all of the options unless overridden in a dataset
                        // In this case, we are setting the border of each bar to be 2px wide and green
                        scaleBeginAtZero: true,
                        elements: {
                        },
                        responsive: true,
                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: 'Throughput Analysis'
                        }
                    }
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
                        <li><a href='#' onClick='loadDataAll()'>All</a></li>
                            <%
                                List<Map<String, String>> result = UtilResult.getInstance().getAllThread();
                                for (Map<String, String> data : result) {
                                    out.println("<li><a href='#' onClick='loadDataGeneral(\"" + data.get("ID_THREAD") + "\")'>" + data.get("ID_THREAD") + "</a></li>");
                                };
                            %>
                    </ul>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header" id="main_page_header">Result</h1>

                    <div id="main_admin_content"><canvas id="canvas"></canvas></div>

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
