<#--&lt;#&ndash;<#import "ui.ftlh" as ui/>&ndash;&gt;-->

<#--&lt;#&ndash;<div>&ndash;&gt;-->
<#--&lt;#&ndash;    <fieldset>&ndash;&gt;-->
<#--&lt;#&ndash;        <legend>Calculate the vacation pay</legend>&ndash;&gt;-->
<#--&lt;#&ndash;        <form name="book" action="add" method="POST">&ndash;&gt;-->
<#--&lt;#&ndash;            <br>&ndash;&gt;-->
<#--&lt;#&ndash;            <@ui.formInputString id="t1" name="title" label="Book Title"/> <br/>&ndash;&gt;-->
<#--&lt;#&ndash;            <br>&ndash;&gt;-->
<#--&lt;#&ndash;            <@ui.formInput id="t2" name="description" label="Description"/> <br/>&ndash;&gt;-->
<#--&lt;#&ndash;            <br>&ndash;&gt;-->
<#--&lt;#&ndash;            <input type="submit" value="submit"/>&ndash;&gt;-->
<#--&lt;#&ndash;        </form>&ndash;&gt;-->
<#--&lt;#&ndash;    </fieldset>&ndash;&gt;-->
<#--&lt;#&ndash;</div>&ndash;&gt;-->

<#--<!DOCTYPE html>-->
<#--<html lang="en">-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <title>Book List</title>-->
<#--    <style>-->
<#--        table {-->
<#--            width: 100%;-->
<#--            border-collapse: collapse;-->
<#--        }-->

<#--        th, td {-->
<#--            padding: 8px;-->
<#--            text-align: left;-->
<#--            border-bottom: 1px solid #ddd;-->
<#--        }-->

<#--        th {-->
<#--            background-color: #f2f2f2;-->
<#--        }-->
<#--    </style>-->
<#--</head>-->

<#--<body>-->
<#--<h1>Book List</h1>-->
<#--<#if booksList??>-->
<#--    <table>-->
<#--        <thead>-->
<#--        <tr>-->
<#--            <th>Title</th>-->
<#--            <th>Description</th>-->
<#--&lt;#&ndash;            <th>Genre</th>&ndash;&gt;-->
<#--            <th>Publication Year</th>-->
<#--        </tr>-->
<#--        </thead>-->
<#--        <tbody>-->
<#--        <#list booksList as book>-->
<#--            <tr>-->
<#--                <td>${book.title}</td>-->
<#--                <td>${book.description}</td>-->
<#--&lt;#&ndash;                <td>${book.genre}</td>&ndash;&gt;-->
<#--                <td>-->
<#--                    <#if book.datePublished?has_content>-->
<#--                        ${book.datePublished?datetime}-->
<#--                    </#if>-->
<#--                </td>-->
<#--            </tr>-->
<#--        </#list>-->
<#--        </tbody>-->
<#--    </table>-->
<#--<#else>-->
<#--    <p>No books available.</p>-->
<#--</#if>-->
<#--</body>-->
<#--</html>-->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Books</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h1>Books</h1>
<button onclick="getAllBooks()">Get All Books</button>
<table id="booksTable">
    <tr>
        <th>Title</th>
        <th>Author</th>
    </tr>
</table>

<script>
    function getAllBooks() {
        fetch('/books')
            .then(response => response.json())
            .then(data => {
                const booksTable = document.getElementById('booksTable');
                booksTable.innerHTML = '';
                const headerRow = document.createElement('tr');
                const titleHeader = document.createElement('th');
                titleHeader.textContent = 'Title';
                const authorHeader = document.createElement('th');
                authorHeader.textContent = 'Author';
                headerRow.appendChild(titleHeader);
                headerRow.appendChild(authorHeader);
                booksTable.appendChild(headerRow);

                data.forEach(book => {
                    const row = document.createElement('tr');
                    const titleCell = document.createElement('td');
                    titleCell.textContent = book.title;
                    const authorCell = document.createElement('td');
                    authorCell.textContent = book.author;
                    row.appendChild(titleCell);
                    row.appendChild(authorCell);
                    booksTable.appendChild(row);
                });
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
</script>
</body>
</html>
