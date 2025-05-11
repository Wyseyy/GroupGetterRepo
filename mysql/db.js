//This class sets up the connection between GroupGetter and the MySQL database
require('dotenv').config();
const mysql = require('mysql2/promise');

async function connectDB() {
  const connection = await mysql.createConnection({
    host: 'localhost',
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME
  });
  return connection;
}

module.exports = connectDB();
