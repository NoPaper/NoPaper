const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const multer = require('multer');

app.use(bodyParser.urlencoded({extended: true}));

const storage = multer.diskStorage({
    destination: (req, file, callback) => {
        callback(null, '../uploads');
    },
    filename: (req, file, callback) => {
        callback(null, Date.now() + file.originalname)
    }
});
const upload = multer({storage: storage}); 

app.use('/', require('./restful/user.js'));
app.use('/upload', require('./restful/file.js'));

app.listen(3003, () => {
    console.log('Server is listening on 3003');
});

module.exports = upload;