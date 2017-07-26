const express = require('express');
const uuid = require('node-uuid');
const router = express.Router();
const upload = require('../app.js');

router.route('/uploadfile').post((req, res) => {
    req.file.uuid = uuid.v4();
});