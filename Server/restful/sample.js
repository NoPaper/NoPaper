const express = require('express');
const router = express.Router();
const mysql = require('./support/mysql.js');

router.route('').post((req, res) => {
    
});

router.route('login/teacher').post((req, res) => {
    // DB에 쿼리 날려서 로우 뜨면 성공(고유 키 포함)
    const id = req.body.id
    const password = req.body.password 
    const key = req.body.key

    mysql.query(`SELECT ${id}, ${password} FROM account_teacher`, (err, rows) => {
        if(rows.length != 0) {
            mysql.query(`SELECT ${key} from teacher_secret`, (err, rows) => {
                if(rows.length != 0) {
                    res.sendStatus(200)
                } else {
                    res.sendStatus(204)
                }
            });
        } else {
            res.sendStatus(204);
        }
    });
});
router.route('login/student').post((req, res) => {
    // DB에 쿼리 날려서 로우있으면 성공
    const id = req.body.id
    const password = req.body.password

    mysql.query(`SELECT ${id}, ${password} FROM account_stduent`, (err, rows) => {
        if(rows.length != 0) {
            console.log(rows);
            res.sendStatus(200)
        } else {
            res.sendStatus(204)
        }
    });
});

router.route('signup/teacher').post((req, res) => {
    // DB에 쿼리 날려서 로우가 없다면 성공
    const id = req.body.id
    const password = req.body.password

    mysql.query(`SELECT ${id} FROM account_teacher`, (err, rows) => {
        if(rows.lenth != 0) {
            res.sendStatus(204)
        } else {
            mysql.query(`INSERT INTO account_teacher VALUES(${id}, ${password})`, (err, rows) => {
               if(err) {
                   console.log(err);
                   res.sendStatus(204)
               } else {
                   console.log('DB에 정보 저장 성공');
                   res.sendStatus(200)
               }
            });
        }
    });
});
router.route('signup/student').post((req, res) => {
    // DB에 쿼리 날려서 로우가 없다면
    const id = req.body.id
    const password = req.body.password
    const grade = req.body.grade
    const classs = req.body.class

    mysql.query(`SELECT ${id} FROM account_student`, (err, rows) => {
        if(rows.lenth ! =0) {
            res.sendStatus(204)
        } else {
            mysql.query(`INSERT INTO account_teacher VALUES(${id}, ${password}, ${grade}, ${classs})`, (err, rows) => {
                if(err) {
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
