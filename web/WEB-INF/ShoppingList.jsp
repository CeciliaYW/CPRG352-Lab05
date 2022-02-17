<%-- 
    Document   : ShoppingList
    Created on : 16-Feb-2022, 8:51:11 PM
    Author     : Cecilia Wang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List Page</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${userName}</p>
        <p><a href="ShoppingList?action=logout">Logout</a></p>
        
        <form method="POST" action="">
            <h2>Add Item</h2>
            <input type="text" name="cart_item">
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add Item">
        </form>
        
        <form method="POST" action="">
            <ul>
                <c:forEach var="item" items="${cartItems}">
                    <li><input type="radio" name="cart_item" value="${item}">${item}</li>
                </c:forEach>
            </ul>
            <input type="submit" value="Delete">  
            <input type="hidden" name="action" value="delete">
        </form>
    </body>
</html>
