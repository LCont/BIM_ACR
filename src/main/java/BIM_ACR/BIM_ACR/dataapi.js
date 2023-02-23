




const express = require('express');
const mariadb = require('mariadb');

const app = express();

// Define a route for the API
app.get('/api/data', async (req, res) => {
    try {
        // Create a connection pool to the database
        const pool = mariadb.createPool({
            host: 'localhost',
            user: 'your_username',
            password: 'your_password',
            database: 'your_database'
        });

        // Get a connection from the pool
        const conn = await pool.getConnection();

        // Execute a query to retrieve all the data from the table
        const rows = await conn.query('SELECT * FROM your_table');

        // Release the connection back to the pool
        conn.release();

        // Send the data as a JSON response
        res.json(rows);
    } catch (err) {
        console.error(err);
        res.status(500).send('Error retrieving data');
    }
});

// Start the server
app.listen(3000, () => {
    console.log('Server started on port 3000');
});