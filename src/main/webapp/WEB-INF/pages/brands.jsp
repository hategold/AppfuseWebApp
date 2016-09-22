<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="brandList.title"/></title>
    <meta name="menu" content="BrandMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="brandList.heading"/></h2>

<form method="get" action="${ctx}/brands" id="searchForm" class="form-inline">
<div id="search" class="text-right">
    <span class="col-sm-9">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm"/>
    </span>
    <button id="button.search" class="btn btn-default btn-sm" type="submit">
        <i class="icon-search"></i> <fmt:message key="button.search"/>
    </button>
</div>
</form>

<p><fmt:message key="brandList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/brandform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="brandList" class="table table-condensed table-striped table-hover" requestURI="" id="brandList" export="true" pagesize="25">
    <display:column property="brandId" sortable="true" href="brandform" media="html"
        paramId="brandId" paramProperty="brandId" titleKey="brand.brandId"/>
    <display:column property="brandId" media="csv excel xml pdf" titleKey="brand.brandId"/>
    <display:column property="brandName" sortable="true" titleKey="brand.brandName"/>
    <display:column property="country" sortable="true" titleKey="brand.country"/>
    <display:column property="website" sortable="true" titleKey="brand.website"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="brandList.brand"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="brandList.brands"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="brandList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="brandList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="brandList.title"/>.pdf</display:setProperty>
</display:table>
