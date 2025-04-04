<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    @for(container in containers) {
        <tr>
            <td>${container.id}</td>
            <td>${container.names}</td>
            <td>${container.status}</td>
            <td>
                <button hx-post="/api/containers/${container.id}/start" hx-target="closest tr">Start</button>
                <button hx-post="/api/containers/${container.id}/stop" hx-target="closest tr">Stop</button>
                <button hx-post="/api/containers/${container.id}/remove" hx-target="closest tr">Remove</button>
            </td>
        </tr>
    }
</table>
