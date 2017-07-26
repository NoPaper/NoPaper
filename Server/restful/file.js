const express = require('express');
const uuid = require('node-uuid');
const router = express.Router();
const upload = require('../app.js');
const mysql = require('../support/mysql.js');
const fs = require('fs');

/**
 * 업도르 요청이 들어온 파일에 대해서 uuid부여 후 path를 DB에 저장
 */
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

/**
 * 클라이언트측에서 다운로드 요청이 있으면 uuid를 가지고 DB에서 path를 찾아내어 Send해줌
 */
router.route('/download').post((req, res) => {
    const id = req.body.id;
    mysql.query(`SELECT file_path FROM file_manager WHERE id='${id}'`, (err, rows) => {
        if(rows.length == 0) {
            res.sendStatus(204);
        } else {
            res.sendfile(rwos[0].file_path);
        }
    });
});

module.exports = router;
