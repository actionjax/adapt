var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { adapt_csv: 'data/adapt_prediction10days.csv' });
});

module.exports = router;
