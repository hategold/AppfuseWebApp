<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="shoesDetail.title"/></title>
    <meta name="menu" content="ShoesMenu"/>
    <meta name="heading" content="<fmt:message key='shoesDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="shoesList.shoes"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="shoesDetail.heading"/></h2>
    <fmt:message key="shoesDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="shoes" method="post" action="shoesform" cssClass="well"
           id="shoesForm" onsubmit="return validateShoes(this)">
<form:hidden path="shoesId"/>
    <!-- todo: change this to read the identifier field from the other pojo <spring:bind path="brand.brandId"></spring:bind>-->
    
    <!--<spring:bind path="shoes.brand.brandId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="brand.brandId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="brand.brandId" id="brand.brandId"  maxlength="255"/>
        <form:errors path="brand.brandId" cssClass="help-block"/>
    </div>-->
    
    <form:select cssClass="form-control" path="brand.brandId" id="brand">
    	<form:options items="${brandList}" itemValue="Id" itemLabel="brandName"/>
    </form:select>

    <spring:bind path="shoes.category">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="shoes.category" styleClass="control-label"/>
        <form:input cssClass="form-control" path="category" id="category"  maxlength="255"/>
        <form:errors path="category" cssClass="help-block"/>
    </div>
    <spring:bind path="shoes.price">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="shoes.price" styleClass="control-label"/>
        <form:input cssClass="form-control" path="price" id="price"  maxlength="255"/>
        <form:errors path="price" cssClass="help-block"/>
    </div>
    <spring:bind path="shoes.series">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="shoes.series" styleClass="control-label"/>
        <form:input cssClass="form-control" path="series" id="series"  maxlength="255"/>
        <form:errors path="series" cssClass="help-block"/>
    </div>
    <spring:bind path="shoes.shoesName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="shoes.shoesName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="shoesName" id="shoesName"  maxlength="255"/>
        <form:errors path="shoesName" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty shoes.shoesId}">
            <button type="submit" class="btn btn-danger" id="delete" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn btn-default" id="cancel" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>

<v:javascript formName="shoes" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['shoesForm']).focus();
    });
</script>
