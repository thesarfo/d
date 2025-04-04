<!DOCTYPE html>
<html>
<head>
    <title>Docker Manager</title>
    <script src="https://unpkg.com/htmx.org@1.9.2"></script>
    <style>
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 8px; border: 1px solid #ddd; }
        th { background-color: #f4f4f4; }
    </style>
</head>
<body>

<h1>Docker Containers</h1>

<div id="container-list" hx-get="/api/containers" hx-trigger="load, every 5s" hx-target="this" hx-swap="outerHTML"></div>

</body>
</html>
