<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catalogo - Mondo Libri</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/app.js" defer></script>
</head>
<header class="header_cat">
    <div class="navbar">
        <h2> <a href="${pageContext.request.contextPath}/index">HOME</a></h2>
    </div>
</header>
<body>
    <main>
        <div class="container">

            <form action="catalogo" method="get">
                <label for="keyword">Parola chiave:</label>
                <input type="text" id="keyword" name="keyword">

                <label for="minPrice">Prezzo minimo:</label>
                <input type="text" id="minPrice" name="minPrice">

                <label for="maxPrice">Prezzo massimo:</label>
                <input type="text" id="maxPrice" name="maxPrice">

                <button type="submit">Cerca</button>
            </form>

            
            <div class="search-bar">
                <h2>Catalogo Completo</h2>
                <input type="text" id="searchTitle" placeholder="Cerca per titolo...">
                <input type="text" id="searchAuthor" placeholder="Cerca per autore...">
                <input type="text" id="searchGenre" placeholder="Cerca per genere...">
                <button onclick="searchBooks()">Cerca</button>
            </div>
            <div class="products" id="productList">
                <!-- Tutti i libri saranno inseriti qui dal database -->
                <div class="product">
                    <img src="${pageContext.request.contextPath}/img/harry_potter.jpg" alt="Harry Potter e la Pietra Filosofale">
                    <h3>Harry Potter e la Pietra Filosofale</h3>
                    <p>J.K. Rowling</p>
                    <p>Genere: Fantasy</p>
                </div>
                <div class="product">
                    <img src="${pageContext.request.contextPath}/img/il_nome_della_rosa.jpg" alt="Il Nome della Rosa">
                    <h3>Il Nome della Rosa</h3>
                    <p>Umberto Eco</p>
                    <p>Genere: Giallo Storico</p>
                </div>
                <div class="product">
                    <img src="${pageContext.request.contextPath}/img/la_solitudine_dei_numeri_primi.jpg" alt="La Solitudine dei Numeri Primi">
                    <h3>La Solitudine dei Numeri Primi</h3>
                    <p>Paolo Giordano</p>
                    <p>Genere: Drammatico</p>
                </div>
                <!-- Altri prodotti -->
            </div>
        </div>
    </main>
</body>
<footer>
    <div>
        <p>&copy; 2024 Mondo Libri. Tutti i diritti riservati.</p>
    </div>
</footer>
</html>
