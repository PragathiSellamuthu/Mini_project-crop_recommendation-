<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recommendation Result</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container result-container">
        <h1>Recommendation Result</h1>
        
        <% if(request.getAttribute("error") != null) { %>
            <p style="color: red;"><%= request.getAttribute("error") %></p>
        <% } else { %>
            <p>Based on the soil and weather conditions you provided, the most suitable crop is:</p>
            <div class="result-crop">
                <%= request.getAttribute("recommendedCrop") %>
            </div>
        <% } %>
        
        <a href="index.html" class="back-btn">Go Back</a>
    </div>
</body>
</html>
