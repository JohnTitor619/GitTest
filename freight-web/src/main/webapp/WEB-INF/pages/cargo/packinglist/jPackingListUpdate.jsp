<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<form name="icform" method="post">
    <input type="hidden" name="packingListId" value="${pack.packingListId}"/>

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="save"><a href="#" onclick="formSubmit('packingListAction_update','_self');this.blur();">保存</a>
                        </li>
                        <li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox-title">
        <img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
        修改装箱单
    </div>


    <div>
        <table class="commonTable" cellspacing="1">

            <tr>
                <td class="columnTitle">卖方：</td>
                <td class="tableContent"><input type="text" name="seller" value="${pack.seller}"/></td>

                <td class="columnTitle">买方：</td>
                <td class="tableContent"><input type="text" name="buyer" value="${pack.buyer}"/></td>
            </tr>
            <tr>
                <td class="columnTitle">发票号：</td>
                <td class="tableContent"><input type="text" name="invoiceNo" value="${pack.invoiceNo}"/></td>

                <td class="columnTitle">发票日期：</td>
                <td class="tableContent">
                    <input type="text" style="width:90px;" name="invoiceDate"
                           value="<fmt:formatDate value='${pack.invoiceDate}' pattern='yyyy-MM-dd'></fmt:formatDate>"
                           onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
                </td>
            </tr>
            <tr>
                <td class="columnTitle">唛头：</td>
                <td class="tableContent"><input type="text" name="marks" value="${pack.marks}"/></td>

                <td class="columnTitle">描述：</td>
                <td class="tableContent"><input type="text" name="descriptions" value="${pack.descriptions}"/></td>
            </tr>

        </table>
    </div>


</form>
</body>
</html>

