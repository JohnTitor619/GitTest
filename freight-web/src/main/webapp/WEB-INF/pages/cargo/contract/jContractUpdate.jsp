<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="${ctx }/js/datepicker/WdatePicker.js"></script>
</head>

<body>
<form name="icform" method="post">
    <input type="hidden" name="contractId" value="${c.contractId}">
    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="save"><a href="#"
                                         onclick="formSubmit('contractAction_update','_self');this.blur();">保存</a></li>
                        <li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">
                    更新购销合同
                </div>
            </div>
        </div>


        <div>
            <table class="commonTable" cellspacing="1">
                <tr>
                    <td class="columnTitle">客户名称：</td>
                    <td class="tableContent"><input type="text" name="customName" value="${c.customName}"/></td>
                    <td class="columnTitle">打印版式：</td>
                    <td class="tableContentAuto">
                        <input type="radio" name="printStyle" value="2" ${c.printStyle=='2'?"checked":"" }
                               class="input">两款
                        <input type="radio" name="printStyle" value="1" ${c.printStyle=='1'?"checked":"" }
                               class="input">一款
                    </td>
                </tr>
                <tr>
                    <td class="columnTitle">合同号：</td>
                    <td class="tableContent"><input type="text" name="contractNo" value="${c.contractNo }"/></td>
                    <td class="columnTitle">收购方：</td>
                    <td class="tableContent"><input type="text" name="offeror" value="${c.offeror }"/></td>
                </tr>
                <tr>
                    <td class="columnTitle">制单人：</td>
                    <td class="tableContent"><input type="text" name="inputBy" value="${c.inputBy }"/></td>
                    <td class="columnTitle">审单人：</td>
                    <td class="tableContent"><input type="text" name="checkBy" value="${c.checkBy }"/></td>
                </tr>
                <tr>
                    <td class="columnTitle">验货员：</td>
                    <td class="tableContent"><input type="text" name="inspector" value="${c.inspector }"/></td>
                    <td class="columnTitle">签单日期：</td>
                    <td class="tableContent">
                        <input type="text" style="width:90px;" name="signingDate"
                               value="<fmt:formatDate value='${c.signingDate }' pattern='yyyy-MM-dd' />"
                               onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
                    </td>
                </tr>
                <tr>
                    <td class="columnTitle">重要程度：</td>
                    <td class="tableContentAuto">
                        <input type="radio" name="importNum" value="3" ${c.importNum==3?"checked":"" } class="input">★★★
                        <input type="radio" name="importNum" value="2" ${c.importNum==2?"checked":"" } class="input">★★
                        <input type="radio" name="importNum" value="1" ${c.importNum==1?"checked":"" } class="input">★
                    </td>
                    <td class="columnTitle">船期：</td>
                    <td class="tableContent">
                        <input type="text" style="width:90px;" name="shipTime"
                               value="<fmt:formatDate value='${c.shipTime }' pattern='yyyy-MM-dd' />"
                               onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
                    </td>
                </tr>
                <tr>
                    <td class="columnTitle">贸易条款：</td>
                    <td class="tableContent"><input type="text" name="tradeTerms" value="${c.tradeTerms }"/></td>
                    <td class="columnTitle">交货期限：</td>
                    <td class="tableContent">
                        <input type="text" style="width:90px;" name="deliveryPeriod"
                               value="<fmt:formatDate value='${c.deliveryPeriod }' pattern='yyyy-MM-dd' />"
                               onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"/>
                    </td>
                </tr>
                <tr>
                    <td class="columnTitle">要求：</td>
                    <td class="tableContent"><textarea name="crequest" style="height:150px;">${c.crequest }</textarea>
                    <td class="columnTitle">说明：</td>
                    <td class="tableContent"><textarea name="remark" style="height:150px;">${c.remark }</textarea>
                </tr>
            </table>
        </div>

    </div>
</form>
</body>
</html>

