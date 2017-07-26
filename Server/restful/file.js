const express = require('express');
const uuid = require('node-uuid');
const router = express.Router();
const upload = require('../app.js');
const mysql = require('../support/mysql.js');

router.route('/uploadfile').post((req, res) => {
    const path = req.file.path;
    const uuid = uuid.v1();

    mysql.query(`INSERT INTO file_manager VALUES('${uuid}', '${path}')`, (err, rows) => {
        if(err) {
            console.log(err);
            res.sendStatus(204);
        } else {
            console.log('uuid와 파일 경로가 DB에 저장되었습니다');
            res.sendStatus(200);
        }
    })
    
    res.send(uuid);
});