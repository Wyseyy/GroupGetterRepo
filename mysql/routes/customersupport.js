//this is a backend class for linking the CServiceActivity page to the MySQL database
 express = require('express');
const nodemailer = require('nodemailer');
const router = express.Router();
require('dotenv').config();

const transporter = nodemailer.createTransport({
    service: 'gmail',
    auth: {
        user: process.env.GMAIL_USER,        
        pass: process.env.GMAIL_PASSWORD           
    }
});

router.post('/', async (req, res) => {
    const { name, email, message } = req.body;

    if (!name || !email || !message) {
        return res.status(400).json({ error: 'All fields required' });
    }

    const mailOptions = {
        from: email,                           
        to: 'groupgettersupp0rt@gmail.com',          
        subject: `Support Request from ${name}`,
        text: `Name: ${name}\nEmail: ${email}\n\nMessage:\n${message}`
    };

    try {
        await transporter.sendMail(mailOptions);
        res.json({ success: true, message: 'Message has been sent!' });
    } catch (error) {
        console.error('Error sending email:', error);
        res.status(500).json({ error: 'Failed to send request' });
    }
});

module.exports = router;
