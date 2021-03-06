var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { adapt_csv: 'data/adapt_prediction10days.csv' });
});

router.get('/big', function(req, res, next) {
  res.render('index', { adapt_csv: 'data/adapt_prediction.csv' });
});

router.get('/olympics', function(req, res, next) {
  res.render('olympics', { });
});

module.exports = router;
