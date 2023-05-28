<#macro formInputNumber id name label type="number" value="">
    <label for="${id}">${label}</label>
    <input type="${type}" id="${id}" name="${name}" value="${value}">
</#macro>
<#macro formInputString id name label type="text" value="">
    <label for="${id}">${label}</label>
    <input type="${type}" id="${id}" name="${name}" value="${value}">
</#macro>