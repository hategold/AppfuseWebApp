<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="brandDetail.title"/></title>
    <meta name="menu" content="BrandMenu"/>
    <meta name="heading" content="<fmt:message key='brandDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="brandList.brand"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="brandDetail.heading"/></h2>
    <fmt:message key="brandDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="brand" method="post" action="brandform" cssClass="well"
           id="brandForm" onsubmit="return validateBrand(this)">
<form:hidden path="brandId"/>
    <spring:bind path="brand.brandName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="brand.brandName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="brandName" id="brandName"  maxlength="255"/>
        <form:errors path="brandName" cssClass="help-block"/>
    </div>
    <spring:bind path="brand.country">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="brand.country" styleClass="control-label"/>
        <form:input cssClass="form-control" path="country" id="country"  maxlength="255"/>
        <form:errors path="country" cssClass="help-block"/>
    </div>
    <spring:bind path="brand.website">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="brand.website" styleClass="control-label"/>
        <form:input cssClass="form-control" path="website" id="website"  maxlength="255"/>
        <form:errors path="website" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty brand.brandId}">
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

<v:javascript formName="brand" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['brandForm']).focus();
    });
</script>
