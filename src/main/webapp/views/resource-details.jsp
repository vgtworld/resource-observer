<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vgt" uri="/WEB-INF/vgt.tld" %>
<%--@elvariable id="model" type="pl.vgtworld.resourceobserver.app.resources.models.details.DetailsModel"--%>

<t:basic title="Resource observer">

    <h1>${model.resource.name}</h1>

    <div class="container-fluid">
        <div class="col-lg-6 col-md-12">
            <h2>Versions monthly</h2>

            <div class="text-center calendar-navigation">
                <a class="btn btn-default btn-nav" href="${pageContext.request.contextPath}/app/resource-calendar/${model.resource.id}${model.calendarLinkSuffix}">Calendar view</a>
            </div>

            <t:versionsMonth versionsMonthly="${model.versionsMonthly}"/>
        </div>
        <div class="col-lg-4 col-md-8">
            <h2>Versions</h2>

            <div class="text-center versions-navigation">
                <a class="btn btn-default btn-nav" href="${pageContext.request.contextPath}/app/diff/${model.resource.id}">Compare</a>
            </div>

            <div class="container-scrollable">
                <table class="table table-striped">
                    <tr>
                        <th class="text-center">Version</th>
                        <th class="text-center">First occurrence</th>
                        <th class="text-center">Last occurrence</th>
                        <th class="text-center">Total</th>
                    </tr>
                    <c:forEach items="${model.versions}" var="version">
                        <tr>
                            <td>
                                <vgt:VersionLink version="${version}"/>
                            </td>
                            <td class="text-center"><vgt:SimpleDatetime date="${version.firstOccurrence}"/></td>
                            <td class="text-center"><vgt:SimpleDatetime date="${version.lastOccurrence}"/></td>
                            <td class="text-right">${version.count}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <c:if test="${empty model.versions}">
                <div class="alert alert-info">
                    No versions available.
                </div>
            </c:if>
        </div>
        <div class="col-lg-2 col-md-4">
            <h2>
                Latest scans
                <c:if test="${!empty model.newestScans}">
                    <a class="btn btn-default btn-nav" href="${pageContext.request.contextPath}/app/scans/resource-history/${model.resource.id}">
                        View all <span class="badge">${model.scanCount}</span>
                    </a>
                </c:if>
            </h2>
            <div class="container-scrollable">
                <t:scanHistory scans="${model.newestScans}"/>
            </div>
        </div>
    </div>

</t:basic>