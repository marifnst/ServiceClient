<%@page import="com.client.entities.TblNews"%>
<%@page import="com.client.entities.TblBuku"%>
<%@page import="java.util.List"%>
<%@page import="com.client.entities.TblKategori"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>E Commerce - LAW</title>
        <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
        <script src="<%=request.getContextPath()%>/jquery/jquery-2.2.2.min.js" type="text/javascript"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                $('.linkHome').click(function () {
                    loadHomeContent(1);
                });

                $('.linkSearch').click(function () {
                    loadSearchContent();
                });

                $('.linkContact').click(function () {
                    alert('contact');
                });

                $('.linkAbout').click(function () {
                    alert('about');
                });

                $('#linkShowSpecialOffer').click(function () {
                    alert('special offer');
                });

                loadHomeContent(1);
            });

            function loadHomeContent(page) {
                $('#main_content').html('');
                $.ajax({
                    type: 'post',
                    url: '<%=request.getContextPath()%>/ServletBuku',
                    data: {page: page},
                    success: function (data) {
                        generateContentHTML(page, data, 'home', 0);
                    }
                });
            }

            function loadHomeContentByCat(page, id) {
                $('#main_content').html('');
                $.ajax({
                    type: 'post',
                    url: '<%=request.getContextPath()%>/ServletBuku',
                    data: {page: page, view: 'VIEW_BY_CATEGORY', id: id},
                    success: function (data) {
                        generateContentHTML(page, data, 'category', id);
                    }
                });
            }

            function generateContentHTML(page, data, viewType, id) {
                var finalPaging = '';
                var count = 1;
                $.each(data, function (index, element) {
                    if (index == 'paging') {

                    } else {
                        var finalContent = '';
                        $.each(element, function (indexx, elementt) {
                            finalContent += '<div class="templatemo_product_box">';
                            finalContent += '<h1>' + elementt.nama + '  <span>(' + elementt.penulis + ')</span></h1>';
                            finalContent += '<img src="images/templatemo_image_01.jpg" alt="image" />';
                            finalContent += '<div class="product_info">';
                            finalContent += '<p>' + elementt.deskripsi + '</p>';
                            finalContent += '<h3>' + elementt.harga.toLocaleString('en-US', {style: 'currency', currency: 'IDR'}) + '</h3>';
                            finalContent += '<div class="buy_now_button"><a href="subpage.html">Buy Now</a></div>';
                            finalContent += '<div class="detail_button"><a href="#" onClick="loadDetailContent(' + elementt.id + ')">Detail</a></div>';
                            finalContent += '</div>';
                            finalContent += '<div class="cleaner">&nbsp;</div>';
                            finalContent += '</div>';

                            if (count % 2 == 0) {
                                finalContent += '<div class="cleaner_with_height">&nbsp;</div>';
                            } else {
                                finalContent += '<div class="cleaner_with_width">&nbsp;</div>';
                            }

                            count++;
                        });

                        if (count == 2) {
                            finalContent += '<div class="cleaner_with_height">&nbsp;</div>';
                            finalContent += '<div class="cleaner_with_width">&nbsp;</div>';
                            finalContent += '<div class="cleaner_with_height">&nbsp;</div>';
                        } else if (count == 3) {
                            finalContent += '<div class="cleaner_with_width">&nbsp;</div>';
                            finalContent += '<div class="cleaner_with_height">&nbsp;</div>';
                        } else if (count == 4) {
                            finalContent += '<div class="cleaner_with_height">&nbsp;</div>';
                        }

                        if (viewType == 'home') {
                            var pageBefore = page - 1;
                            var pageAfter = page + 1;
                            if (page != 1) {
                                finalPaging += '<center><a href="#" onClick="loadHomeContent(' + pageBefore + ')"><</a>';
                            } else {
                                finalPaging += '<center><a href="#"><</a>';
                            }
                            finalPaging += ' ' + page + ' ';
                            finalPaging += '<a href="#" onClick="loadHomeContent(' + pageAfter + ')">></a></center>';

                        } else if (viewType == 'category') {
                            var pageBefore = page - 1;
                            var pageAfter = page + 1;
                            if (page != 1) {
                                finalPaging += '<center><a href="#" onClick="loadHomeContentByCat(' + pageBefore + ',' + id + ')"><</a>';
                            } else {
                                finalPaging += '<center><a href="#"><</a>';
                            }
                            finalPaging += ' ' + page + ' ';
                            finalPaging += '<a href="#" onClick="loadHomeContentByCat(' + pageAfter + ',' + id + ')">></a></center>';
                        }

                        finalContent += finalPaging;
                        //alert(finalContent);
                        if (count != 1) {
                            // klo data tidak kosong
                            $('#main_content').html(finalContent);
                        } else {
                            if (page == 1 && count == 1) {
                                alert('Data not found, will be refresh to default setting');
                                loadHomeContent(1);
                            } else {
                                // klo data kosong
                                if (viewType == 'home') {
                                    loadHomeContent(1);
                                } else {
                                    loadHomeContentByCat(1, id)
                                }
                            }
                        }
                    }
                });
            }

            function loadSearchContent(page) {
                var finalContent = '<select id="searchKey">';
                finalContent += '<option>Nama</option>';
                finalContent += '<option>Deskripsi</option>';
                finalContent += '<option>Detail Deskripsi</option>';
                finalContent += '<option>Penulis</option>';
                finalContent += '</select> ';
                finalContent += ' <input type="text" id="keySearchInput" />';
                finalContent += ' <a href="#" id="linkSearchMenu">Search</a>';
//                $('#main_content').load('<%=request.getContextPath()%>/content_header_search.jsp');
                $('#main_content').html(finalContent);
                $('#linkSearchMenu').click(function () {
                    alert('Search Link ' + $('#searchKey').val());
                    $('#main_content').html('');
                    $.ajax({
                        type: 'post',
                        url: '<%=request.getContextPath()%>/ServletBuku',
                        data: {view: 'VIEW_BY_FILTER', page:page, filter:$('#searchKey').val(), key:$('#keySearchInput').val() },
                        success: function (data) {
                            
                        }
                    });
                });
            }

            function loadDetailContent(id) {
                $('#main_content').html('');
                $.ajax({
                    type: 'post',
                    url: '<%=request.getContextPath()%>/ServletBuku',
                    data: {view: 'DETAIL', id: id},
                    success: function (data) {
                        var finalContent = '';
//                        $.each(data, function(index, element){
                        finalContent += '<h1>' + data.nama + ' <span>(by ' + data.penulis + ')</span></h1>';
                        finalContent += '<div class="image_panel"><img src="images/templatemo_image_02.jpg" alt="CSS Template" width="100" height="150" /></div>';
                        finalContent += '<h2>' + data.deskripsi + '</h2>';
                        finalContent += '<ul>';
                        finalContent += '<li>By Deke <a href="#">McClelland</a></li>';
                        finalContent += '<li>' + data.waktuTerbit + '</li>';
                        finalContent += '<li>Pages: ' + data.jumlahHalaman + '</li>';
                        finalContent += '<li>ISBN 10: ' + data.isbn10 + ' | ISBN 13: ' + data.isbn13 + '</li>';
                        finalContent += '</ul>';
                        finalContent += data.detailDeskripsi;
                        finalContent += '<div class="cleaner_with_height">&nbsp;</div>';
//                        });
                        $('#main_content').html(finalContent);
                    }
                });
            }
        </script>
    </head>
    <body>
        <!--  Free CSS Templates from www.templatemo.com -->
        <div id="templatemo_container">
            <div id="templatemo_menu">
                <ul>
                    <li><a class='linkHome' href="#" class="current">Home</a></li>
                    <li><a class='linkAbout' href="#">About</a></li> 
                    <li><a class='linkSearch' href="#">Search</a></li>
                    <li><a class='linkContact' href="#">Contact</a></li>
                </ul>
            </div> <!-- end of menu -->

            <div id="templatemo_header">
                <div id="templatemo_special_offers">
                    <p>
                        <%
                            TblNews specialOffer = (TblNews) request.getAttribute("specialOffer");
                            out.println(specialOffer.getDescription());
                        %>
                        <!--                        <span>25%</span> discounts for
                                                purchase over $80-->
                    </p>
                    <a href="#" id='linkShowSpecialOffer' style="margin-left: 50px;">Read more...</a>
                </div>

                <div id="templatemo_new_books">
                    <ul>
                        <%
                            List<TblBuku> newBooks = (List<TblBuku>) request.getAttribute("newBooks");
                            for (TblBuku newBook : newBooks) {
                                out.println("<li>");
                                out.println("<a href='#' onClick='loadDetailContent(" + newBook.getId() + ")'>" + newBook.getNamaBuku() + "</a>");
                                out.println("</li>");
                            }
                        %>
                        <!--                        <li>Suspen disse</li>
                                                <li>Maece nas metus</li>
                                                <li>In sed risus ac feli</li>-->
                    </ul>
                    <!--                    <a href="subpage.html" style="margin-left: 50px;">Read more...</a>-->
                </div>
            </div> <!-- end of header -->

            <div id="templatemo_content">

                <div id="templatemo_content_left">
                    <div class="templatemo_content_left_section">
                        <h1>Categories</h1>
                        <ul>
                            <%
                                List<TblKategori> kategori = (List<TblKategori>) request.getAttribute("kategori");
                                for (TblKategori kat : kategori) {
                                    out.println("<li>");
                                    out.println("<a href='#' onClick='loadHomeContentByCat(1," + kat.getId() + ")'>" + kat.getDeskripsi() + "</a>");
                                    out.println("</li>");
                                }
                            %>
                        </ul>
                    </div>
                    <div class="templatemo_content_left_section">
                        <h1>Bestsellers</h1>
                        <ul>
                            <%
                                List<TblBuku> bestSellers = (List<TblBuku>) request.getAttribute("bestSeller");
                                for (TblBuku bs : bestSellers) {
                                    out.println("<li>");
                                    out.println("<a href='#' onClick='loadDetailContent(" + bs.getId() + ")'>" + bs.getNamaBuku() + "</a>");
                                    out.println("</li>");
                                }
                            %>
                        </ul>
                    </div>

                    <div class="templatemo_content_left_section">                
                        <a href="http://validator.w3.org/check?uri=referer"><img style="border:0;width:88px;height:31px" src="http://www.w3.org/Icons/valid-xhtml10" alt="Valid XHTML 1.0 Transitional" width="88" height="31" vspace="8" border="0" /></a>
                        <a href="http://jigsaw.w3.org/css-validator/check/referer"><img style="border:0;width:88px;height:31px"  src="http://jigsaw.w3.org/css-validator/images/vcss-blue" alt="Valid CSS!" vspace="8" border="0" /></a>
                    </div>
                </div> <!-- end of content left -->

                <div id="templatemo_content_right">
                    <div id="main_content" />
                    <center><div id="main_paging"></div></center>
                </div> <!-- end of content right -->

                <div class="cleaner_with_height">&nbsp;</div>
            </div> <!-- end of content -->

            <div id="templatemo_footer">
                <a href="#" class='linkHome'>Home</a> | 
                <a href="#" class='linkAbout'>About</a> | 
                <a href="#" class='linkSearch'>Search</a> | 
                <a href="#" class='linkContact'>Contact</a><br />
                Copyright © 2024 <a href="#"><strong>E Commerce - LAW</strong></a> 
            </div> 
        </div>
    </body>
</html>
