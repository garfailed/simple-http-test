<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index page</title>
</head>
<body>
<h1>
    Test header
</h1>
<p>
    Test paragraph
</p>
<button id="test-button">Press this for a message</button>
</body>
<script>
    document.querySelector('#test-button').addEventListener('click', () => {
        alert('Hello from JavaScript!');
    });
</script>
</html>
