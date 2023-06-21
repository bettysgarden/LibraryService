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
<div id="paginationButtons"></div>
<table id="booksTable">
    <tr>
        <th>Title</th>
        <th>Rating</th>
        <th>Year Published</th>
        <th>Description</th>
        <th>Cover</th>
    </tr>
</table>

<script>
    const booksPerPage = 20;
    let currentPage = 1;
    let totalBooks = 0;

    function getAllBooks() {
        fetch('/api/books')
            .then(response => response.json())
            .then(data => {
                totalBooks = data.length;
                displayBooks(data);
                renderPaginationButtons();
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    function displayBooks(data) {
        const booksTable = document.getElementById('booksTable');
        booksTable.innerHTML = '';
        const headerRow = document.createElement('tr');
        const titleHeader = document.createElement('th');
        titleHeader.textContent = 'Title';
        const ratingHeader = document.createElement('th');
        ratingHeader.textContent = 'Rating';
        const yearPublishedHeader = document.createElement('th');
        yearPublishedHeader.textContent = 'Year Published';
        const descriptionHeader = document.createElement('th');
        descriptionHeader.textContent = 'Description';
        const coverHeader = document.createElement('th');
        coverHeader.textContent = 'Cover';
        headerRow.appendChild(titleHeader);
        headerRow.appendChild(ratingHeader);
        headerRow.appendChild(yearPublishedHeader);
        headerRow.appendChild(descriptionHeader);
        headerRow.appendChild(coverHeader);
        booksTable.appendChild(headerRow);

        const startIndex = (currentPage - 1) * booksPerPage;
        const endIndex = startIndex + booksPerPage;
        const booksToDisplay = data.slice(startIndex, endIndex);

        booksToDisplay.forEach(book => {
            const row = document.createElement('tr');
            const titleCell = document.createElement('td');
            titleCell.textContent = book.title;
            const ratingCell = document.createElement('td');
            ratingCell.textContent = book.rating ? book.rating.toString() : '-';
            const yearPublishedCell = document.createElement('td');
            yearPublishedCell.textContent = book.yearPublished ? book.yearPublished.toString() : '-';
            const descriptionCell = document.createElement('td');
            descriptionCell.textContent = book.description ? book.description : '-';
            const coverCell = document.createElement('td');
            const coverImg = document.createElement('img');
            coverImg.src = book.coverUrl ? book.coverUrl : '';
            coverImg.alt = 'Cover Image';
            coverCell.appendChild(coverImg);
            row.appendChild(titleCell);
            row.appendChild(ratingCell);
            row.appendChild(yearPublishedCell);
            row.appendChild(descriptionCell);
            row.appendChild(coverCell);
            booksTable.appendChild(row);
        });
    }

    function renderPaginationButtons() {
        const paginationButtons = document.getElementById('paginationButtons');
        paginationButtons.innerHTML = '';
        const totalPages = Math.ceil(totalBooks / booksPerPage);

        if (totalPages > 1) {
            for (let i = 1; i <= totalPages; i++) {
                const button = document.createElement('button');
                button.textContent = i.toString();
                button.onclick = function () {
                    currentPage = i;
                    getAllBooks();
                };
                paginationButtons.appendChild(button);
            }
        }
    }

    // Initial load
    getAllBooks();
</script>
</body>
</html>
