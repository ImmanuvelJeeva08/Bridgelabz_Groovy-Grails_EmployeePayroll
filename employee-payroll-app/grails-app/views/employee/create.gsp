<%@ page import="com.bridgelabz.epa.Employee" %>
<!-- Include Main Layout -->
<meta name="layout" content="main"/>

<div class="form-content">
    <!-- Form -->
%{--    <g:message code="employee" args="['Create']"/>--}%

    <g:form class="form" controller="employee" action="save">
        <g:render template="form" />
        <!-- Submit-Reset-Cancel Button -->
        <div class="buttonParent">
            <g:link controller="employee" action="index" class="resetButton button cancelButton" ><g:message code="cancel"/></g:link>
            <div class="submit-reset">
                <g:submitButton class="button submitButton" name="save" value="${g.message(code: "submit")}"/>
                <button type="reset" class="resetButton button" id="resetButton"><g:message code="reset"/></button>
            </div>
        </div>
    </g:form>
</div>