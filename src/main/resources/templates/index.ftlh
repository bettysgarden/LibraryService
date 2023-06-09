<#--<#import "ui.ftlh" as ui/>-->

<#--<div>-->
<#--    <fieldset>-->
<#--        <legend>Calculate the vacation pay</legend>-->
<#--        <form name="book" action="add" method="POST">-->
<#--            <br>-->
<#--            <@ui.formInputString id="t1" name="title" label="Book Title"/> <br/>-->
<#--            <br>-->
<#--            <@ui.formInput id="t2" name="description" label="Description"/> <br/>-->
<#--            <br>-->
<#--            <input type="submit" value="submit"/>-->
<#--        </form>-->
<#--    </fieldset>-->
<#--</div>-->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>

<body>
<h1>Book List</h1>
<#if booksList??>
    <table>
        <thead>
        <tr>
            <th>Title</th>
            <th>Description</th>
<#--            <th>Genre</th>-->
            <th>Publication Year</th>
        </tr>
        </thead>
        <tbody>
        <#list booksList as book>
            <tr>
                <td>${book.title}</td>
                <td>${book.description}</td>
<#--                <td>${book.genre}</td>-->
                <td>
                    <#if book.datePublished?has_content>
                        ${book.datePublished?datetime}
                    </#if>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
<#else>
    <p>No books available.</p>
</#if>
</body>
</html>

