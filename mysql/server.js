//This is a backend class for allowing a user to register and login and have their credentials saved to the MySQL database.
const express = require('express');
const mysql = require('mysql2');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const app = express();
const cors = require('cors');
const port = 3000;
const supportRoute = require('./routes/customersupport');
const communityRoute = require('./routes/community');

require('dotenv').config();

app.use(cors());
app.use(express.json());
app.use('/support', supportRoute);
app.use('/community', communityRoute);

const db = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'WalterWhite3',
    database: 'groupgetter'
});

db.connect(err => {
    if (err) {
        console.error('Database connection error:', err);
        return;
    }
    console.log('Connected to database');
});

app.post('/register', (req, res) => {
    const {email, username, password } = req.body;

    if (!email || !username || !password) {
        return res.status(400).json({ error: 'All three fields required'});
    }

    db.query('SELECT * FROM users WHERE email = ? OR username =?', [email, username], (err, results) => {
        if (err) {
            return res.status(500).json({ error: 'Database error' });
        }

        if (results.length > 0) {
            return res.status(400).json({ error: 'User already exists with these credentials' });
        }

        bcrypt.hash(password, 10, (err, hashedPassword) => {
            if (err) {
                return res.status(500).json({ error: 'Error hashing password' });
            }

            const query = 'INSERT INTO users (email, username, password) VALUES (?, ?, ?)';
            db.query(query, [email, username, hashedPassword], (err, result) => {
                if (err) {
                    return res.status(500).json({ error: 'Failed to create a user' });
                }

                return res.status(201).json({ message: 'User has been registered' });
            });
        });
    });
});

app.post('/login', (req, res) => {
    const {emailOrUsername, password } = req.body;

    if (!emailOrUsername || !password) {
        return res.status(400).json({ error: 'Email/Username and password are required'});
    }
    
    const query = 'SELECT * FROM users WHERE email = ? OR username = ?';
    db.query(query, [emailOrUsername, emailOrUsername], (err, results) => {
    if (err){
        return res.status(500).json({ error: 'User not found' });
    }

    if(results.length == 0){
        return res.status(404).json({ error: 'User not found' }); 
    }
    const user = results[0];
    bcrypt.compare(password, user.password, (err, isMatch) => {
        if (err) {
            return res.status(500).json({ error: 'Verification error' });
        }

        if (!isMatch) {
            return res.status(401).json({ error: 'Password is incorrect' });
        }

            const token = jwt.sign( 
                { id: user.id, username: user.username },
                process.env.JWT_SECRET || 'insecurefallbackkey',
                {expiresIn: '1h' }
            );

            return res.json({ message: 'Logged in', token});
         });
    });
});

app.listen(port, () => {
    console.log(`Server is running at http://localhost:${port}`);
});