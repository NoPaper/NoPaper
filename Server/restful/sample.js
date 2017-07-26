const express = require('express');
const router = express.Router();
const mysql = require('./support/mysql.js');

router.route('').post((req, res) => {

});

router.route('login/teacher').post((req, res) => {
    // DB에 쿼리 날려서 로우 뜨면 성공(고유 키 포함)
    mysql.query('SELECT id FROM ')
});
router.route('login/student').post((req, res) => {
    // DB에 쿼리 날려서 로우있으면 성공
});

router.route('signup').post((req, res) => {
   // DB에 쿼리 날려서 로우가 없다면 성공

});

module.exports = router;
