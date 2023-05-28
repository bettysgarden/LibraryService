<#import "ui.ftlh" as ui/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Calculator</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>

<body>
<h1>Calculator</h1>
<div>
    <fieldset>
        <legend>Calculate the vacation pay</legend>
        <form name="book" action="add" method="POST">
            <br>
            <@ui.formInputString id="t1" name="title" label="Book Title"/> <br/>
            <br>
            <@ui.formInput id="t2" name="description" label="Description"/> <br/>
            <br>
            <input type="submit" value="submit"/>
        </form>
    </fieldset>
</div>

<br>
<#if llistSize??>
    <h2>Book Vault</h2>

    <table>
        <thead>
        <tr>
            <td style="width: 200px; text-align: center">Title</td>
            <td style="width: 200px; text-align: center">Description</td>
            <td style="width: 200px; text-align: center">Ratings</td>
            <td style="width: 200px; text-align: center">Year of publication</td>
        </tr>
        </thead>
        <tbody>
        <#list booksList as book>
            <tr>
                <td style="text-align: center">${book.title}</td>
                <td style="text-align: center">${book.description}</td>
                <td style="text-align: center">${book.rating}</td>
                <td style="text-align: center">${book.yearPublished}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</#if>

</body>
</html>