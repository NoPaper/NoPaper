const express = require('express');
const app = express();

const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({extended: true}));

const session = require('express-session');
app.use(session({
    secret: '2ka@>.!!ask',
    resave: false,
    saveUninitialized: true
}));

app.use('/', require('./restful/sample.js'));
require('./support/mysql');

app.listen(3003, () => {
    console.log('Server is listening on 3003');
});
