<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="shoesList.title"/></title>
    <meta name="menu" content="ShoesMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-danger alert-dismissable">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<h2><fmt:message key="shoesList.heading"/></h2>

<form method="get" action="${ctx}/shoess" id="searchForm" class="form-inline">
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

<p><fmt:message key="shoesList.message"/></p>

<div id="actions" class="btn-group">
    <a href='<c:url value="/shoesform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="shoesList" class="table table-condensed table-striped table-hover" requestURI="" id="shoesList" export="true" pagesize="25">
    <display:column property="shoesId" sortable="true" href="shoesform" media="html"
        paramId="shoesId" paramProperty="shoesId" titleKey="shoes.shoesId"/>
    <display:column property="shoesId" media="csv excel xml pdf" titleKey="shoes.shoesId"/>
    <display:column property="category" sortable="true" titleKey="shoes.category"/>
    <display:column property="price" sortable="true" titleKey="shoes.price"/>
    <display:column property="series" sortable="true" titleKey="shoes.series"/>
    <display:column property="shoesName" sortable="true" titleKey="shoes.shoesName"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="shoesList.shoes"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="shoesList.shoess"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="shoesList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="shoesList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="shoesList.title"/>.pdf</display:setProperty>
</display:table>
