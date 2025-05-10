const express = require('express');
const router = express.Router();
const dbPromise = require('../db'); 

router.post('/messages', async (req, res) => {
    console.log('Received POST to /messages', req.body);
    const db = await dbPromise; // Await the connection
    const { username, message } = req.body;

    if (!username || !message) {
        return res.status(400).json({ error: 'Logged in user and a message are required.' });
    }

    try {
        await db.query(
            'INSERT INTO messages (username, message) VALUES (?, ?)',
            [username, message]
        );
        res.json({ success: true });
    } catch (err) {
        console.error(err);
        res.status(500).json({ error: 'Database error in saving.' });
    }
});

router.get('/messages', async (req, res) => {
    const db = await dbPromise; // Await the connection
    try {
        const [rows] = await db.query('SELECT * FROM messages ORDER BY id ASC');
        res.json(rows);
    } catch (err) {
        console.error(err);
        res.status(500).json({ error: 'Database error in retrieving.' });
    }
});

module.exports = router;
