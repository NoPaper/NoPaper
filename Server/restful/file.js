const express = require('express');
const uuid = require('node-uuid');
const router = express.Router();
const upload = require('../app.js');
const mysql = require('../support/mysql.js');

router.route('/uploadfile').post(upload.single('beacon_file'), (req, res) => {
    const filename = req.file.filename;
    const v1 = uuid.v1();

    mysql.query(`INSERT INTO file_manager VALUES('${v1}', '${filename}')`, (err, rows) => {
        if(err) {
            console.log(err);
            res.sendStatus(204);
        } else {
            console.log('uuid와 파일 경로가 DB에 저장되었습니다');
            res.status(200).send(v1);
        }
    });
});

module.exports = router;
