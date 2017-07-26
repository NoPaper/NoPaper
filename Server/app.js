const express = require('express');
const app = express();
const bodyParser = require('body-parser');

app.use(bodyParser.urlencoded({extended: true}));

app.use('/', require('./restful/sample.js'));

app.listen(3003, () => {
    console.log('Server is listening on 3003');
});
