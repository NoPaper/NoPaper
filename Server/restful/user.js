const express = require('express');
const router = express.Router();
const mysql = require('../support/mysql.js');

router.route('').post((req, res) => {

});

router.route('/login/teacher').post((req, res) => {
    // DB에 쿼리 날려서 로우 뜨면 성공(고유 키 포함)
    const id = req.body.id;
    const password = req.body.password;

    mysql.query(`SELECT * FROM account_teacher WHERE id='${id}' AND password='${password}'`, (err, rows) => {
        if (rows.length != 0) {
            res.sendStatus(200);
        } else {
            res.sendStatus(204);
        }
    });
});
router.route('/login/student').post((req, res) => {
    // DB에 쿼리 날려서 로우있으면 성공
    const id = req.body.id;
    const password = req.body.password;

    mysql.query(`SELECT * FROM account_stduent WHERE id='${id}' AND password='${password}'`, (err, rows) => {
        if (rows.length != 0) {
            res.sendStatus(200);
        } else {
            res.sendStatus(204);
        }
    });
});

router.route('/signup/teacher').post((req, res) => {
    // DB에 쿼리 날려서 로우가 없다면 성공
    const id = req.body.id;
    const password = req.body.password;
    const key = req.body.key;

    mysql.query(`SELECT * FROM teacher_secret WHERE key='${key}'`, (err, rows) => {
        if (rows.length != 0) {
            res.sendStatus(203);
        } else {
            mysql.query(`SELECT * FROM account_teacher WHERE id='${id}'`, (err, rows) => {
                if (rows.length != 0) {
                    res.sendStatus(204);
                } else {
                    mysql.query(`INSERT INTO account_teacher VALUES('${id}', '${password}', '${key}')`, (err, rows) => {
                        if (err) {
                            res.sendStatus(204);
                        } else {
                            res.sendStatus(200);
                        }
                    });
                }
            });
        }
    });
});
router.route('/signup/student').post((req, res) => {
    // DB에 쿼리 날려서 로우가 없다면
    const id = req.body.id
    const password = req.body.password
    const grade = req.body.grade
    const classs = req.body.class

    mysql.query(`SELECT * FROM account_student WHERE id='${id}`, (err, rows) => {
        if (rows.length != 0) {
            res.sendStatus(204)
        } else {
            mysql.query(`INSERT INTO account_teacher VALUES('${id}', '${password}', '${grade}', '${classs}')`, (err, rows) => {
                if (err) {
                    console.log(err);
                    res.sendStatus(204)
                } else {
                    console.log('DB에 정보를 성공적으로 저장');
                    res.sendStatus(200)
                }
            });
        }
    });
});

module.exports = router;
