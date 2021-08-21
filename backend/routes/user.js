const express = require("express");

const { getUser } = require("../controllers/user");
const router = express.Router();

//router.router("/").get().post();

router.get("/:id", getUser);
module.exports = router;
